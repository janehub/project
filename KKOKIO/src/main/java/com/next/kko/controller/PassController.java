package com.next.kko.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.RecordIljiDto;
import com.next.kko.model.IPassService;
import com.next.kko.util.KkokioUtil;

@Controller
public class PassController {

		private final Logger logger = LoggerFactory.getLogger(PassController.class);
		
		@Autowired
		private IPassService iPassService;
		
		@RequestMapping(value="/homePage.do", method=RequestMethod.GET)
		public String homePage() {
			return "main";
		}
		
		@RequestMapping(value="/makePassPage.do", method=RequestMethod.GET)
		public String makePassPage() {
			logger.info("makePassPage");
			return "makePass";
		}
		
		@RequestMapping(value="/infoPassList.do", method=RequestMethod.GET)
		public String infoPassList() {
			return "passList";
		}
		
		//파스코드를 세션에 설정하고 infoDetailPass 서비스를 실행시킴
		@RequestMapping(value="/setPasscode.do", method = RequestMethod.GET)
		@ResponseBody
		public String setPasscode(HttpSession session,String passcode) {
			logger.info("setPasscode");

			logger.info("파스코드 {}",passcode);
			//세션에서 가져오기
			Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
			logger.info("세션에있는값: {}",smap);
			
//			Set<String> keys = smap.keySet();
//			Iterator<String> iter = keys.iterator();
//			while (iter.hasNext()) {
//				String string = (String) iter.next();
//				System.out.println(string+":"+smap.get(string)+":"+string.length());
//				
//			}
			
			smap.put("passcode",passcode);
			//세션 값 설정
			session.setAttribute("memid", smap);
			logger.info("세션에있는값: {}",smap);
			
			String id=smap.get("ID");
			String dongnumber=smap.get("DONGNUMBER");
			
			logger.info("dongnumber: {}",dongnumber);
			
			PassDto pdto = iPassService.infoDetailPass(passcode, id);
			logger.info("pdto: {}",pdto);
			
			Gson gsonBuilder = new GsonBuilder().create();
			String json = gsonBuilder.toJson(pdto);
			System.out.println("jsonFromDto: "+json);
			System.out.println("toString: "+json.toString());

			List<RecordIljiDto> list=iPassService.infoMainIlji(passcode,dongnumber);
			logger.info("list: {}",list);
			
			String jsonFromList = new Gson().toJson(list);
			System.out.println("jsonFromList: "+jsonFromList);
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("pass", json);
			map.put("ilji",jsonFromList);
			
			System.out.println(map);
			
			return new JSONObject(map).toString();
		}
		
		
		@RequestMapping(value="/makePass.do", method = RequestMethod.POST)
		@ResponseBody
		public String makePass(HttpSession session,PassDto dto) throws ParseException {
			logger.info("PassDto {}",dto);
			logger.info("dongchicknum {}",dto.getDongchicknum());
			dto.setDongchicknum(dto.getDongchicknum().replace(",",""));
			//데이터 형식 맞추기
			String s=KkokioUtil.removeHyphen(dto.getStartpass());
			dto.setStartpass(s);
			if(dto.getEndpass()==null ||dto.getEndpass().equals("")) {
				//파스 종료일이 입력되지 않았을 경우 default 값으로
				//파스 종료일 = 파스 시작일 +44 로 설정(45가 아닌 이유 : 첫쨋날 포함)
				dto.setEndpass(KkokioUtil.addDates(dto.getStartpass(), 44));
			}else {
				s=KkokioUtil.removeHyphen(dto.getEndpass());
				dto.setEndpass(s);
			}
			
			//세션에서 가져오기
			Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
			logger.info("세션에있는값: {}",smap);
			dto.setId(smap.get("ID"));
			smap.put("passcode",dto.getId()+dto.getStartpass());
			logger.info("2_PassDto {}",dto);
			
			//이미 있는 파스인지 확인
			String passcode=smap.get("ID")+dto.getStartpass();
			if(iPassService.checkPassDuplicate(passcode)) {
				logger.info("pass_duplicated");
				return "pass_duplicated";
			}
			
			
			//세션 값 설정
			session.setAttribute("memid", smap);
			logger.info("세션에있는값: {}",smap);
			//파스 생성
			boolean isc = iPassService.makePass(dto);
			System.out.println(isc);
			
			return "pass_not_duplicated";
		}
		
		//jqGrid 수정,삭제시 호출됨
		@RequestMapping(value="/editPass.do", method=RequestMethod.POST)
		public String editPass(HttpSession session,String oper,PassDto dto,String id) throws ParseException {
			logger.info("editPass");
			
			String passcode=id;
			
			//세션에서 가져오기
			Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
			logger.info("세션에있는값: {}",smap);
			String user_id=smap.get("ID");
			logger.info("user_id: {}",user_id);
			
			//분기
			if(oper.equalsIgnoreCase("edit")) {
				logger.info("edit");
				dto.setId(user_id);
				dto.setStartpass(KkokioUtil.removeTime(dto.getStartpass()));
				dto.setEndpass(KkokioUtil.removeTime(dto.getEndpass()));
				System.out.println(dto);
				
				boolean isc = iPassService.modifyPass(dto);
				System.out.println(isc);
			}else if(oper.equalsIgnoreCase("del")) {
				logger.info("del");
				
				Map<String,String> map = new HashMap<String, String>();
				map.put("passcode", passcode);
				map.put("id", user_id);
				
				boolean isc = iPassService.deletePass(map);
				
			}
			return "passList";
			
		}
		
//		@RequestMapping(value="/infoDetailPass.do", method=RequestMethod.GET)
//		public String infoDetailPass(HttpSession session,Model model) {
//			//세션에서 가져오기
////			String id="ONG001";
//			Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
//			logger.info("세션에있는값: {}",smap);
//			String id=smap.get("ID");
//			String passcode=smap.get("passcode");
//			
//			System.out.println(passcode+" : "+id);
//			PassDto pdto = iPassService.infoDetailPass(passcode, id);
//			System.out.println(pdto);
//			model.addAttribute("pdto", pdto);
//			
//			return "passInfo";
//		}
		
		@RequestMapping(value="/passList.do", method=RequestMethod.GET)
		@ResponseBody
		public String passList(HttpSession session) {
			//세션에서 가져오기
//			String id="ONG001";
			Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
			logger.info("세션에있는값: {}",smap);
			
			String id=smap.get("ID");
			logger.info("id: {}",id);
			
			List<PassDto> list = iPassService.infoPassList(id);
			logger.info("list: {}",list);
			
			Gson gsonBuilder = new GsonBuilder().create();
			
			String jsonFromList = gsonBuilder.toJson(list);
			System.out.println("jsonFromList: "+jsonFromList);
			
			System.out.println("toString: "+jsonFromList.toString());

			return jsonFromList.toString();
		}
		
		
		//세션에서 파스코드 정보 지우고 메인페이지로 이동
		@RequestMapping(value="/changePass.do", method=RequestMethod.GET)
		public String changePass(HttpSession session) {
			//세션에서 가져오기
			Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
			logger.info("세션에있는값: {}",smap);
			smap.remove("passcode");
			
			//세션 값 설정
			session.setAttribute("memid", smap);
			logger.info("세션에있는값: {}",smap);
			
			return "main";
		}
		
		
//		@RequestMapping(value="/baseInfoPass", method=RequestMethod.POST)
//		public String baseInfoPass() {
//			List<PassDto> lists = iPassService.baseInfoPass();
//			System.out.println(lists);
//			return null;
//		}
}
