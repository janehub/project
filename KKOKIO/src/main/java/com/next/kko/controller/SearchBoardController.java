package com.next.kko.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.next.kko.dtos.RecordIljiDto;
import com.next.kko.model.ISearchService;

@Controller
public class SearchBoardController {

	private final Logger logger = LoggerFactory.getLogger(ExcelController.class);
	
	@Autowired
	ISearchService iSearchService;
	
	@RequestMapping(value="/searchBoard.do",method=RequestMethod.GET)
	public String searchBoard(){
		return "searchBoard";
	}
	
	//illyung 타입이 String인 이유 : disabled 되면 null이라서 int에 바인딩되지 못함
	@RequestMapping(value="/searchIlji.do",method=RequestMethod.POST)
	public String searchIlji(HttpSession session,Model model,String passDate,String isAll,String illyung,String[] dong){
		logger.info("passDate {}",passDate);	//2018-12
		logger.info("isAll {}",isAll);			//Y/N
		logger.info("illyung {}",illyung);		//숫자/null
		logger.info("dong {}",dong);			//[1,2,...]
		
//		//세션에서 가져오기
		Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
		logger.info("세션에있는값: {}",smap);
		String id=smap.get("ID");
		logger.info("id: {}",id);	//ONG001
		
		String idpassYM=id+passDate.replace("-", "");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("idpassYM", idpassYM);	//ONG001201812
		map.put("isAll", isAll);
		map.put("illyung", illyung);
		map.put("dong", dong);
		logger.info("map {}",map);
		
		List<RecordIljiDto> list = iSearchService.searchIlji(map);
		logger.info("list: {}",list);
		
		Gson gsonBuilder = new GsonBuilder().create();
		String jsonFromList = gsonBuilder.toJson(list);
		System.out.println("jsonFromList: "+jsonFromList);
		System.out.println("toString: "+jsonFromList.toString());
		
		model.addAttribute("searchResult", jsonFromList);
		
		return "searchBoard";
	}
	
	
	
	
	@RequestMapping(value="/getAllIljiList.do",method=RequestMethod.POST)
	@ResponseBody
	public String getAllIljiList(HttpSession session,String passYM,int illyung) {
		logger.info("getAllIljiList");
		logger.info("passYM {}",passYM);
		logger.info("illyung {}",illyung);
		
		
//		//세션에서 가져오기
//		Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
//		logger.info("세션에있는값: {}",smap);
//		String id=smap.get("ID");
//		logger.info("id: {}",id);
//		
//		String idpassym=id+passYM;
//		
//		List<RecordIljiDto> list = iSearchService.getAllIlji(idpassym);
//		logger.info("list: {}",list);
//		
//		Gson gsonBuilder = new GsonBuilder().create();
//		
//		String jsonFromList = gsonBuilder.toJson(list);
//		System.out.println("jsonFromList: "+jsonFromList);
//		
//		System.out.println("toString: "+jsonFromList.toString());
//
//		return jsonFromList.toString();
		
		return null;
	}
}
