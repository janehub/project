package com.next.kko.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.next.kko.dtos.RecordIljiDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.next.kko.dtos.IljiModifyDto;
import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.ProductDto;
import com.next.kko.dtos.PurchaseDto;
import com.next.kko.model.IRecordIljiService;
import com.next.kko.util.KkokioUtil;
import com.next.kko.model.IAccountService;
import com.next.kko.model.IPassService;
import com.next.kko.model.IProductService;
import com.next.kko.model.IPurchaseService;

@Controller
public class RecordIljiController {
	
	private final Logger logger = LoggerFactory.getLogger(RecordIljiController.class);
	
	
	@Autowired
	private IRecordIljiService iIljiService;
	
	@Autowired
	private IAccountService iAccountService;
	
	@Autowired
	private IPassService iPassService;
	

	
	/**
	 * 일지작성 페이지로 이동 
	 * @param session
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/iljiWrite.do", method=RequestMethod.GET)
	public String iljiWrite (HttpSession session, Model model) throws ParseException {
		logger.info("일지작성 페이지 이동");
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		logger.info("session에 있는 값: {}", smap);

		
		// passcode: session에 있는 값
		String passcode = smap.get("passcode");
		
		// passstart: passcode에서 id를 제외한 값을 int로 변경
		int idlength = smap.get("ID").length();
		String sdate = passcode.substring(idlength);//20181110
		Date startdate = KkokioUtil.stringToDate(sdate);
		System.out.println("파스시작날짜: "+startdate);
				
		// 현재 날짜
		Date currentdate = new Date();
		System.out.println("현재날짜: "+currentdate);
			
		// illyung 구하기 
		long calDate = startdate.getTime() - currentdate.getTime();
		long calDateDays = calDate / (24*60*60*1000);
		calDateDays = Math.abs(calDateDays);
		System.out.println("일령: "+calDateDays);
				
		int illyung = (int)calDateDays+1;
		System.out.println(illyung);
		
		String id = smap.get("ID");
		System.out.println("id: "+id);
		System.out.println("passcode: "+passcode);
		PassDto pdto = iPassService.infoDetailPass(passcode, id);
		System.out.println(pdto);
		
		String edate = pdto.getEndpass();
		edate = KkokioUtil.removeTime(edate);
		edate = KkokioUtil.removeHyphen(edate);
		System.out.println(edate);
		Date endDate = KkokioUtil.stringToDate(edate);
		
		System.out.println(endDate);
		System.out.println(currentdate);
		
		int compare = currentdate.compareTo(endDate);
		// 
		
		if(compare<0 || compare==0 ) {
			System.out.println("작성가능------------------------------");
	
		
		// passcode illyung
		Map<String, String> ilmap = new HashMap<String, String>();
		ilmap.put("passcode", passcode);
		ilmap.put("illyung",Integer.toString(illyung));
		
		//List<RecordIljiDto> illyungList = new ArrayList<RecordIljiDto>();
		
		// 폐사수(lists) 중량(wlists)
		List<RecordIljiDto> lists = iIljiService.dongDeathCntSelectI(ilmap);
		List<RecordIljiDto> wlists = iIljiService.dongWeightSelectI(ilmap);
		
		// 일지작성 확인용 
		List<RecordIljiDto> chklists = iIljiService.illyungSelectAll(ilmap);
		
		model.addAttribute("lists", lists);
		model.addAttribute("wlists", wlists);
		model.addAttribute("chklists", chklists);
		

		return "iljiWrite";
		
		}else {
			System.out.println("작성불가능------------------------------");
			
			return "main";
		}
		
		
		
		
	}
	
	
	/**
	 * 새로고침 버튼을 눌렀을때 현재까지 입력된 내용을 보여주는 기능 
	 * @param session
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/illyungNewData.do", method=RequestMethod.GET)
	@ResponseBody
	public String illyungNewData (HttpSession session) throws ParseException {
		logger.info("일지작성 페이지 이동");
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		logger.info("session에 있는 값: {}", smap);

		
		// passcode: session에 있는 값
		String passcode = smap.get("passcode");
		
		// passstart: passcode에서 id를 제외한 값을 int로 변경
		int idlength = smap.get("ID").length();
		String sdate = passcode.substring(idlength);//20181110
		Date startdate = KkokioUtil.stringToDate(sdate);
		System.out.println("파스시작날짜: "+startdate);
		
		// 현재 날짜
		Date currentdate = new Date();
		System.out.println("현재날짜: "+currentdate);
		
		// illyung 구하기 
		long calDate = startdate.getTime() - currentdate.getTime();
		long calDateDays = calDate / (24*60*60*1000);
		calDateDays = Math.abs(calDateDays);
		System.out.println("일령: "+calDateDays);
		
		int illyung = (int)calDateDays+1;
		System.out.println(illyung);
		
		
		// passcode illyung
		Map<String, String> ilmap = new HashMap<String, String>();
		ilmap.put("passcode", passcode);
		ilmap.put("illyung",Integer.toString(illyung));

		// 일지작성 확인용 
		List<RecordIljiDto> chklists = iIljiService.illyungSelectAll(ilmap);

		Gson gsonBuilder = new GsonBuilder().create();
		String returnData = gsonBuilder.toJson(chklists);
		System.out.println("jsonFromLists: "+ returnData);
		System.out.println("toString: "+returnData.toString());
		
		Map<String, String> returnmap = new HashMap<String, String>();
		returnmap.put("newdata", returnData);

		
		System.out.println("-------------------"+returnmap);

		return new JSONObject(returnmap).toString();
	}
	
	
	/**
	 * 일지작성에서 당일 일령에 대한 동별 폐사수를 입력하는 기능
	 * @param map
	 * @param session
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/dongDeathCntInsert.do", method=RequestMethod.GET)
	@ResponseBody
	public boolean dongDeathCntEdit(@RequestParam Map<String,Object> map, HttpSession session) throws ParseException {
		 
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		int dongNum =Integer.parseInt(smap.get("DONGNUMBER"));
		logger.info("동수: {}", dongNum);
		
		String[] deathTemp = new String[dongNum];
		
		for (int i = 0; i < deathTemp.length; i++) {
			System.out.println("객체 만들어진거 확인"+deathTemp[i]);
		}
		
		// map으로 넘어오는건 String
		//System.out.println("map으로 넘어오는건 String?"+map.get("dCount") instanceof String);
		// ex) 2,10
		System.out.println(map.get("dCount"));
		// String으로 변환
		String deathCnts = map.get("dCount").toString();
		//System.out.println(deathCnts);
		
		// 동별 폐사수 저장
		String[] dtemp =  deathCnts.split(",");
		for (int i = 0; i < dtemp.length; i++) {
			System.out.println("값 저장 확인"+dtemp[i]);
		}
		
		// 넘어온 배열 복사      원본배열,(), 복사배열,(),원본길이
		System.arraycopy(dtemp, 0, deathTemp, 0, dtemp.length);

		
		// 입력 _ passcode illyung hightemp lowtemp deathcount distinctdong
		
		// passcode: session에 있는 값
		String passcode = smap.get("passcode");
		
		// passstart: passcode에서 id를 제외한 값을 int로 변경
		int idlength = smap.get("ID").length();
		String sdate = passcode.substring(idlength);//20181110
		Date startdate = KkokioUtil.stringToDate(sdate);
		System.out.println("파스시작날짜: "+startdate);
				
		// 현재 날짜
		Date currentdate = new Date();
		System.out.println("현재날짜: "+currentdate);
			
		// illyung 구하기 
		long calDate = startdate.getTime() - currentdate.getTime();
		long calDateDays = calDate / (24*60*60*1000);
		calDateDays = Math.abs(calDateDays);
		System.out.println("일령: "+calDateDays);
		
		int illyung = (int)calDateDays+1;
		System.out.println(illyung);

		// 입력 _ passcode illyung hightemp lowtemp deathcount distinctdong
		RecordIljiDto idto = new RecordIljiDto();
		idto.setPasscode(passcode);
		idto.setIllyung(illyung);
		idto.setHightemp(20.0);
		idto.setLowtemp(15.0);
		
		int chk = 0;
		for (int j = 0; j < dongNum; j++) {

			if(deathTemp[j]==null || deathTemp[j].equals("")) {
					System.out.println(deathTemp[j]+"-> 0: 입력");
					idto.setDeathcount(0);
					idto.setDistinctdong(Integer.toString(j+1));
					boolean isc = iIljiService.dongDeathCntInput(idto);
					logger.info("폐사수 입력 성공여부 {}", isc);
					if(isc == true) {
						chk++;
					}
				}else {
					System.out.println(deathTemp[j]+"그대로 입력");
					idto.setDeathcount(Integer.parseInt(deathTemp[j]));
					idto.setDistinctdong(Integer.toString(j+1));
					boolean isc = iIljiService.dongDeathCntInput(idto);
					logger.info("폐사수 입력 성공여부 {}", isc);
					if(isc == true) {
						chk++;
					}
				}
		}//for문
		
/*		// passcode illyung
		Map<String,String> callmap = new HashMap<String,String>();
		callmap.put("passcode", passcode);
		callmap.put("illyung", Integer.toString(illyung));
		
		List<RecordIljiDto> returnLists = iIljiService.dongDeathCntSelectI(callmap);
		System.out.println(returnLists);
		
		Gson gsonBuilder = new GsonBuilder().create();
		String new_deathCnt = gsonBuilder.toJson(returnLists);
		System.out.println("jsonFromLists: "+ new_deathCnt);
		System.out.println("toString: "+new_deathCnt.toString());
		
		boolean result_chk = false;
		if(chk==dongNum) {
			result_chk = true;
		}else {
			result_chk = false;
		}
		
		String send_result = gsonBuilder.toJson(result_chk);
		System.out.println("chk_result: "+send_result);
		System.out.println("toString: "+send_result.toString());
		
		Map<String, String> returnmap = new HashMap<String, String>();
		returnmap.put("newdata", new_deathCnt);
		returnmap.put("result", send_result);
		
		System.out.println("-------------------"+returnmap);*/
		
		return (chk==dongNum)?true:false;
		//return new JSONObject(returnmap).toString();
	}
	
	@RequestMapping(value="/dongDeathCntInput.do", method=RequestMethod.POST)
	public String dongDeathCntInput(HttpSession session , Model model) {
		// 최고/최저 온도가 없으므로 IljiDto 사용 안함
		System.out.println(model);
		
		// session에서 passcode 받아야 함
/*		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		logger.info("session에 있는 값: {}", smap);
		String passcode = smap.get("passcode");
		
		Double hightemp = 30.2;
		Double lowtemp = 20.2;
		
		
		RecordIljiDto idto = new RecordIljiDto();
		idto.setPasscode(passcode);
		idto.setIllyung(illyung);
		idto.setHightemp(hightemp);
		idto.setLowtemp(lowtemp);
		
		int temp = 0;*/
		
		
/*		for (int i = 0; i < deathcount.length; i++) {
			idto.setDeathcount(deathcount[i]);
			temp = i+1;
			idto.setDistinctdong(Integer.toString(temp));
			//System.out.println((i+1)+"처리"+idto);
			
			Map<String, String> ilmap = new HashMap<String, String>();
			ilmap.put("passcode", passcode);
			ilmap.put("illyung", Integer.toString(illyung));
			ilmap.put("distinctdong", Integer.toString(temp));
			RecordIljiDto chkDto = iIljiService.dongDeathCntSelectD(ilmap);
			System.out.println(chkDto);
			
			if(chkDto == null) {
				System.out.println("입력가능");
			}else {
				System.out.println("입력불가");
				
			}
			
			
		}
*/
		// passcode illyung hightemp lowtemp deathcount distinctdong

		
		return "iljiWrite";
	}

	
	@RequestMapping(value="/dongDeathCntModify.do", method=RequestMethod.POST)
	public String dongDeathCntModify(RecordIljiDto idto) {
		System.out.println(idto);
		
		//deathcount recordilji_seq passcode illyung distinctdong

		
		boolean isc = iIljiService.dongDeathCntModify(idto);
		System.out.println(isc);
		
		return null;
	}
	
		
	@RequestMapping(value="/dongDeathCntDel.do", method=RequestMethod.POST)
	public String dongDeathCntDel(RecordIljiDto idto) {
		System.out.println(idto);
		
		//recordilji_seq passcode illyung distinctdong

	

		
		boolean isc = iIljiService.dongDeathCntDel(idto);
		System.out.println(isc);
		
		return null;
	}
	

	@RequestMapping(value="/dongDeathCntSelectI.do", method=RequestMethod.POST)
	public String dongDeathCntSelectI(String passcode, String illyung) {
	
		System.out.println(passcode);
		System.out.println(illyung);
		
		//passcode illyung
		Map<String, String> ilmap = new HashMap<String, String>();
		
		
		ilmap.put("passcode", passcode);
		ilmap.put("illyung", illyung);
		
		List<RecordIljiDto> lists = iIljiService.dongDeathCntSelectI(ilmap);
		System.out.println(lists);
		
		return null;
	}
	
	
	@RequestMapping(value="/dongDeathCntSelectD.do", method=RequestMethod.POST)
	public String dongDeathCntSelectD(String passcode, String illyung, String distinctdong) {
		System.out.println(passcode);
		System.out.println(illyung);
		System.out.println(distinctdong);
		
		
		//passcode illyung distinctdong
		Map<String, String> ilmap = new HashMap<String, String>();
		
		
		ilmap.put("passcode", passcode);
		ilmap.put("illyung", illyung);
		ilmap.put("distinctdong", distinctdong);

		RecordIljiDto iljidto= iIljiService.dongDeathCntSelectD(ilmap);
		System.out.println(iljidto);
		
		return null;
	}
	
	
	/**
	 * 일지작성에서 당일 일령에 대한 동별 중량을 입력하는 기능
	 * @param map
	 * @param session
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/dongWeightInsert.do", method=RequestMethod.GET)
	@ResponseBody
	public boolean dongWeightInsert(@RequestParam Map<String,Object> map, HttpSession session) throws ParseException {
		 
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		int dongNum =Integer.parseInt(smap.get("DONGNUMBER"));
		logger.info("동수: {}", dongNum);
		
		String[] weightTemp = new String[dongNum];
		
		for (int i = 0; i < weightTemp.length; i++) {
			System.out.println("객체 만들어진거 확인"+weightTemp[i]);
		}
		
		// map으로 넘어오는건 String
		System.out.println("map으로 넘어오는건 String?"+map.get("dWeight") instanceof String);
		// ex) 110, 110
		System.out.println(map.get("dWeight"));
		// String으로 변환
		String dongWeight = map.get("dWeight").toString();
		System.out.println(dongWeight);
		
		// 동별 중량 저장
		String[] wtemp =  dongWeight.split(",");
		for (int i = 0; i < wtemp.length; i++) {
		System.out.println("값 저장 확인"+wtemp[i]);
		}
		
		// 넘어온 배열 복사      원본배열,(), 복사배열,(),원본길이
		System.arraycopy(wtemp, 0, weightTemp, 0, wtemp.length);
		for (int i = 0; i < wtemp.length; i++) {
			System.out.println("복사 확인"+weightTemp[i]);
		}

		
		// 입력 _ passcode illyung hightemp lowtemp weight distinctdong
		
		// passcode: session에 있는 값
		String passcode = smap.get("passcode");
		
		// passstart: passcode에서 id를 제외한 값을 int로 변경
		int idlength = smap.get("ID").length();
		String sdate = passcode.substring(idlength);//20181110
		Date startdate = KkokioUtil.stringToDate(sdate);
		System.out.println("파스시작날짜: "+startdate);
				
		// 현재 날짜
		Date currentdate = new Date();
		System.out.println("현재날짜: "+currentdate);
			
		// illyung 구하기 
		long calDate = startdate.getTime() - currentdate.getTime();
		long calDateDays = calDate / (24*60*60*1000);
		calDateDays = Math.abs(calDateDays);
		System.out.println("일령: "+calDateDays);
				
		int illyung = (int)calDateDays+1;
		System.out.println(illyung);

		// 입력 _ passcode illyung hightemp lowtemp weight distinctdong
		RecordIljiDto idto = new RecordIljiDto();
		idto.setPasscode(passcode);
		idto.setIllyung(illyung);
		idto.setHightemp(20.0);
		idto.setLowtemp(15.0);
		
		int chk = 0;
		for (int j = 0; j < dongNum; j++) {

			if(weightTemp[j]==null || weightTemp[j].equals("")) {
					System.out.println(weightTemp[j]+"-> 0.0: 입력");
					idto.setWeight(0.0);
					idto.setDistinctdong(Integer.toString(j+1));
					boolean isc = iIljiService.dongWeightInput(idto);
					logger.info("중량 입력 성공여부 {}", isc);
					if(isc == true) {
						chk++;
					}
				}else {
					System.out.println(weightTemp[j]+"그대로 입력");
					idto.setWeight(Double.parseDouble(weightTemp[j]));
					idto.setDistinctdong(Integer.toString(j+1));
					boolean isc = iIljiService.dongWeightInput(idto);
					logger.info("중량 입력 성공여부 {}", isc);
					if(isc == true) {
						chk++;
					}
				}
		}//for문
		
		
		return (chk==dongNum)?true:false;
	}
	
	
	@RequestMapping(value="/dongWeightInput.do", method=RequestMethod.POST)
	public String dongWeightInput(RecordIljiDto idto) {
		System.out.println(idto);
		
		// passcode illyung hightemp lowtemp weight distinctdong

	


		boolean isc = iIljiService.dongWeightInput(idto);
		System.out.println(isc);
		
		return null;
	}
	
	
	@RequestMapping(value="/dongWeightModify.do", method=RequestMethod.POST)
	public String dongWeightModify(RecordIljiDto idto) {
		System.out.println(idto);
		
		//weight recordilji_seq passcode illyung distinctdong


		
		boolean isc = iIljiService.dongWeightModify(idto);
		System.out.println(isc);
		
		
		return null;
	}
	
	
	@RequestMapping(value="/dongWeightDel.do", method=RequestMethod.POST)
	public String dongWeightDel(RecordIljiDto idto) {
		System.out.println(idto);
		
		//recordilji_seq passcode illyung distinctdong

		
		boolean isc = iIljiService.dongWeightDel(idto);
		System.out.println(isc);
				
		
		return null;
	}
	
		
	@RequestMapping(value="/dongWeightSelectI.do", method=RequestMethod.POST)
	public String dongWeightSelectI(String passcode, String illyung) {
		System.out.println(passcode);
		System.out.println(illyung);
		
		//passcode illyung
		Map<String, String> ilmap = new HashMap<String, String>();
		
		ilmap.put("passcode", passcode);
		ilmap.put("illyung", illyung);
		
		List<RecordIljiDto> lists = iIljiService.dongWeightSelectI(ilmap);
		System.out.println(lists);
		
		
		return null;
	}
	
	
	@RequestMapping(value="/dongWeightSelectD.do", method=RequestMethod.POST)
	public String dongWeightSelectD(String passcode, String illyung, String distinctdong) {
		System.out.println(passcode);
		System.out.println(illyung);
		System.out.println(distinctdong);
		
		//passcode illyung distinctdong
		Map<String, String> ilmap = new HashMap<String, String>();
		
		ilmap.put("passcode", passcode);
		ilmap.put("illyung", illyung);
		ilmap.put("distinctdong", distinctdong);
		
		RecordIljiDto iljidto = iIljiService.dongWeightSelectD(ilmap);
		System.out.println(iljidto);
		
		
		return null;
	}
	
	
	/**
	 * 일지작성에서 당일 일령에 대한 동별 사육장 온도를 입력하는 기능
	 * @param map
	 * @param session
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/dongBuildTempInsert.do", method=RequestMethod.GET)
	@ResponseBody
	public boolean dongBuildTempInsert(@RequestParam Map<String,Object> map, HttpSession session) throws ParseException {
		 
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		int dongNum =Integer.parseInt(smap.get("DONGNUMBER"));
		logger.info("동수: {}", dongNum);
		
		String[] buildTemp = new String[dongNum];
		
		for (int i = 0; i < buildTemp.length; i++) {
			System.out.println("객체 만들어진거 확인"+buildTemp[i]);
		}
		
		// map으로 넘어오는건 String
		System.out.println("map으로 넘어오는건 String?"+map.get("dBuildTemp") instanceof String);
		// ex) 25.5, 30
		System.out.println(map.get("dBuildTemp"));
		// String으로 변환
		String dongBuildTemp = map.get("dBuildTemp").toString();
		System.out.println(dongBuildTemp);
		
		// 동별 사육장 온도 저장
		String[] btemp =  dongBuildTemp.split(",");
		for (int i = 0; i < btemp.length; i++) {
		System.out.println("값 저장 확인"+btemp[i]);
		}
		
		// 넘어온 배열 복사      원본배열,(), 복사배열,(),원본길이
		System.arraycopy(btemp, 0, buildTemp, 0, btemp.length);
		for (int i = 0; i < btemp.length; i++) {
			System.out.println("복사 확인"+buildTemp[i]);
		}

		
		// 입력 _ passcode illyung hightemp lowtemp weight distinctdong
		
		// passcode: session에 있는 값
		String passcode = smap.get("passcode");
		
		// passstart: passcode에서 id를 제외한 값을 int로 변경
		int idlength = smap.get("ID").length();
		String sdate = passcode.substring(idlength);//20181110
		Date startdate = KkokioUtil.stringToDate(sdate);
		System.out.println("파스시작날짜: "+startdate);
				
		// 현재 날짜
		Date currentdate = new Date();
		System.out.println("현재날짜: "+currentdate);
				
		// illyung 구하기 
		long calDate = startdate.getTime() - currentdate.getTime();
		long calDateDays = calDate / (24*60*60*1000);
		calDateDays = Math.abs(calDateDays);
		System.out.println("일령: "+calDateDays);
				
		int illyung = (int)calDateDays+1;
		System.out.println(illyung);

		// 입력 _ passcode illyung hightemp lowtemp buildtemp distinctdong
		RecordIljiDto idto = new RecordIljiDto();
		idto.setPasscode(passcode);
		idto.setIllyung(illyung);
		idto.setHightemp(20.0);
		idto.setLowtemp(15.0);
		
		int chk = 0;
		for (int j = 0; j < dongNum; j++) {

			if(buildTemp[j]==null || buildTemp[j].equals("")) {
					System.out.println(buildTemp[j]+"-> 0.0: 입력");
					idto.setBuildtemp(0.0);
					idto.setDistinctdong(Integer.toString(j+1));
					boolean isc = iIljiService.insertTemp(idto);
					logger.info("사육장 온도 입력 성공여부 {}", isc);
					if(isc == true) {
						chk++;
					}
				}else {
					System.out.println(buildTemp[j]+"그대로 입력");
					idto.setBuildtemp(Double.parseDouble(buildTemp[j]));
					idto.setDistinctdong(Integer.toString(j+1));
					boolean isc = iIljiService.insertTemp(idto);
					logger.info("중량 입력 성공여부 {}", isc);
					if(isc == true) {
						chk++;
					}
				}
		}//for문
	
		
		return (chk==dongNum)?true:false;
	}
	
	
	
	/**
	 * 일지작성에서 당일 일령에 대한 약품 사용 정보를 입력하는 기능
	 * @param dong
	 * @param medi
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/dongMediInput.do", method=RequestMethod.POST)
	public String dongMediInput (String[] dong, String medi, HttpSession session) throws ParseException {
		System.out.println(medi);
		
		// distinctdong 만들기
		String dongs = "";
			for(int i=0; i<dong.length;i++) {
				System.out.println(dong[i]);
				dongs += "/" + dong[i];
			}
			
		String distinctdong = dongs.substring(1);
		System.out.println(distinctdong);
		
		// 입력 _ passcode illyung hightemp lowtemp medicine distinctdong
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		
		// passcode: session에 있는 값
		String passcode = smap.get("passcode");
				
		// passstart: passcode에서 id를 제외한 값을 int로 변경
		int idlength = smap.get("ID").length();
		String sdate = passcode.substring(idlength);//20181110
		Date startdate = KkokioUtil.stringToDate(sdate);
		System.out.println("파스시작날짜: "+startdate);
				
		// 현재 날짜
		Date currentdate = new Date();
		System.out.println("현재날짜: "+currentdate);
				
		// illyung 구하기 
		long calDate = startdate.getTime() - currentdate.getTime();
		long calDateDays = calDate / (24*60*60*1000);
		calDateDays = Math.abs(calDateDays);
		System.out.println("일령: "+calDateDays);
				
		int illyung = (int)calDateDays+1;
		System.out.println(illyung);

		// 입력 _ passcode illyung hightemp lowtemp medicine distinctdong
		RecordIljiDto idto = new RecordIljiDto();
			idto.setPasscode(passcode);
			idto.setIllyung(illyung);
			idto.setHightemp(20.0);
			idto.setLowtemp(15.0);
			idto.setMedicine(medi);
			idto.setDistinctdong(distinctdong);
			
		//System.out.println(idto);
		boolean isc = iIljiService.insertMedi(idto);
		logger.info("약품 사용정보 입력 성공여부: {}", isc);
		
		return "redirect:/iljiWrite.do";
	}
	
	
	
	@RequestMapping(value="/dongWeatherComentInput.do", method=RequestMethod.POST)
	public String dongWeatherComentInput (String[] dong, RecordIljiDto dto) {
		//System.out.println(dto);
		
		// distinctdong 만들기
		String dongs = "";
		for(int i=0; i<dong.length;i++) {
			System.out.println(dong[i]);
			dongs += "/" + dong[i];
		}
		
	
		String result = dongs.substring(1);
		

		
		System.out.println(result);
		
		dto.setDistinctdong(result);
		
		// passcode illyung hightemp lowtemp weathercon distinctdong
		System.out.println(dto);
		
		boolean isc = iIljiService.dongWeatherComentInput(dto);
		System.out.println(isc);
		
		return null;
	}
	
	
	@RequestMapping(value="/dongWeatherComentModify.do", method=RequestMethod.POST)
	public String dongWeatherComentModify (RecordIljiDto dto) {
		System.out.println(dto);
		
		// weathercon recordilji_seq passcode illyung distinctdong
		boolean isc = iIljiService.dongWeatherComentModify(dto);
		System.out.println(isc);
		
		return null;
	}
	
	
	@RequestMapping(value="/dongWeatherComentDel.do", method=RequestMethod.POST)
	public String dongWeatherComentDel(RecordIljiDto dto) {
		System.out.println(dto);
		
		// recordilji_seq passcode illyung
		boolean isc = iIljiService.dongWeatherComentDel(dto);
		System.out.println(isc);
		
		return null;
	}
	
	
	
	/**
	 * 일지작성에서 비고를 입력하는 기능
	 * @param dong
	 * @param medi
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/dongEtcInput.do", method=RequestMethod.POST)
	public String dongEtcInput (String[] dong, String etc, HttpSession session) throws ParseException {
		System.out.println(etc);
		
		// distinctdong 만들기
		String dongs = "";
			for(int i=0; i<dong.length;i++) {
				System.out.println(dong[i]);
				dongs += "/" + dong[i];
			}
			
		String distinctdong = dongs.substring(1);
		System.out.println(distinctdong);
		
		// 입력 _ passcode illyung hightemp lowtemp medicine distinctdong
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		
		// passcode: session에 있는 값
		String passcode = smap.get("passcode");
				
		// passstart: passcode에서 id를 제외한 값을 int로 변경
		int idlength = smap.get("ID").length();
		String sdate = passcode.substring(idlength);//20181110
		Date startdate = KkokioUtil.stringToDate(sdate);
		System.out.println("파스시작날짜: "+startdate);
				
		// 현재 날짜
		Date currentdate = new Date();
		System.out.println("현재날짜: "+currentdate);
				
		// illyung 구하기 
		long calDate = startdate.getTime() - currentdate.getTime();
		long calDateDays = calDate / (24*60*60*1000);
		calDateDays = Math.abs(calDateDays);
		System.out.println("일령: "+calDateDays);
				
		int illyung = (int)calDateDays+1;
		System.out.println(illyung);
				
		// 입력 _ passcode illyung hightemp lowtemp medicine distinctdong
		RecordIljiDto idto = new RecordIljiDto();
			idto.setPasscode(passcode);
			idto.setIllyung(illyung);
			idto.setHightemp(20.0);
			idto.setLowtemp(15.0);
			idto.setEtc(etc);
			idto.setDistinctdong(distinctdong);
			
		//System.out.println(idto);
		boolean isc = iIljiService.dongEtcComentInput(idto);
		logger.info("비고 입력 성공여부: {}", isc);
		
		return "redirect:/iljiWrite.do";
	}
	
	
	
	
	@RequestMapping(value="/dongEtcComentInput.do", method=RequestMethod.POST)
	public String dongEtcComentInput (String[] dong, RecordIljiDto dto) {
		
		
		// distinctdong 만들기
		String dongs = "";
		for(int i=0; i<dong.length;i++) {
			System.out.println(dong[i]);
			dongs += "/" + dong[i];
		}
		
		String result = dongs.substring(1);
		

		System.out.println(result);
		
		dto.setDistinctdong(result);
		
		// passcode illyung hightemp lowtemp etc distinctdong
		System.out.println(dto);
		
		boolean isc = iIljiService.dongEtcComentInput(dto);
		System.out.println(isc);
		
		return null;
	}
	
	
	@RequestMapping(value="/dongEtcComentModify.do", method=RequestMethod.POST)
	public String dongEtcComentModify (String[] dong, RecordIljiDto dto) {
		
		
		// distinctdong 만들기
		String dongs = "";
		for(int i=0; i<dong.length;i++) {
			System.out.println(dong[i]);
			dongs += "/" + dong[i];
		}
		
		String result = dongs.substring(1);
		

		System.out.println(result);
		
		dto.setDistinctdong(result);
		
		// passcode illyung hightemp lowtemp etc distinctdong
		System.out.println(dto);
		
		boolean isc = iIljiService.dongEtcComentModify(dto);
		System.out.println(isc);

		
		return null;
	}
	
	
	@RequestMapping(value="/dongEtcComentDel.do", method=RequestMethod.POST)
	public String dongEtcComentDel(RecordIljiDto dto) {
		System.out.println(dto);
		
		// recordilji_seq passcode illyung
		
		boolean isc = iIljiService.dongEtcComentDel(dto);
		System.out.println(isc);
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/passSearch.do", method= {RequestMethod.GET})
	public String passSearch(String passcode) {
		List<RecordIljiDto> rDto = iAccountService.passSearch(passcode);
		
		System.out.println(rDto);
		
		return null;
	}
	
	@RequestMapping(value="/yearSearch.do", method= {RequestMethod.GET})
	public String yearSearch(String year) {
		List<RecordIljiDto> yDto = iAccountService.yearSearch(year);
		
		System.out.println(yDto);
		
		return null;
	}
	
	@RequestMapping(value="/monthSearch.do", method= {RequestMethod.GET})
	public String monthSearch(String month) {
		List<RecordIljiDto> mDto = iAccountService.monthSearch(month);
		
		System.out.println(mDto);
		
		return null;
	}
	

	@RequestMapping(value="/iljiDetail.do", method=RequestMethod.GET)
	public String toiljiDetail(HttpSession session) {
		logger.info("일지디테일로 이동");
		Map<String, String> smap =  (Map<String, String>)session.getAttribute("memid");
		
		logger.info("일지디테일 이동할때 세션에 담긴 값 {}", smap);
		
		return "iljiDetail";
	}
	
	@RequestMapping(value="/modifyIlji.do", method=RequestMethod.POST)
	public String modifyIlji(String illyung, String deathdong1, String deathdong2, String deathdong3, String weightdong1, String weightdong2, String weightdong3, HttpSession session, Model model) {
		logger.info("deathcount 정보 {} ", deathdong1);
		logger.info("deathcount 정보 {} ", deathdong2);
		logger.info("deathcount 정보 {} ", deathdong3);
		logger.info("deathcount 정보 {} ", weightdong1);
		logger.info("deathcount 정보 {} ", weightdong2);
		logger.info("deathcount 정보 {} ", weightdong3);
		
		Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
		smap.put("passcode", smap.get("passcode"));

		Map<String, String> map = new HashMap<String, String>();
		map.put("illyung", illyung);
		map.put("passcode", smap.get("passcode"));
		map.put("deathcount", deathdong1);
		map.put("weight", weightdong1);
		
		
		
//		Map<String, String> dmap = new HashMap<String, String>();
//		dmap.put("deathdong1", deathdong1);
//		dmap.put("deathdong2", deathdong2);
//		dmap.put("deathdong3", deathdong3);
//
//		Map<String, String> wmap = new HashMap<String, String>();
//		wmap.put("weightdong1", weightdong1);
//		wmap.put("weightdong2", weightdong2);
//		wmap.put("weightdong3", weightdong3);
		
		
		logger.info("illyung의 값은 ? {} ", illyung);
		logger.info("smap의 값은? {} ", smap.get("passcode"));
		logger.info("map의 값은? {} ", map);
//		logger.info("dmap의 값은? {} ", dmap);
//		logger.info("wmap의 값은? {} ", wmap);
		
		List<RecordIljiDto> ldto = (List<RecordIljiDto>) iAccountService.modifyIlji(map);
		
		
		logger.info("rdto의 정보는? {} ", ldto);
		
		logger.info("성공하셨습니다");
		
		return "iljiDetail";
	}
	
	
	@RequestMapping(value="/deathcountSearch.do", method=RequestMethod.GET)
	@ResponseBody
	   public String searchDeathcount(String passcode, String illyung, HttpSession session, Model model) {
	      
	      Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
	      model.addAttribute("passcode", smap.get("passcode"));
	      
	      
	      
	      logger.info("세션에서 담기는 파스정보는? {} ", smap.get("passcode"));
	      logger.info("일령에 대한 값은? {} ", illyung);
	      
	      //===================================================================
	      
	      Map<String, String> map = new HashMap<String, String>();
	      map.put("passcode", smap.get("passcode"));
	      map.put("illyung", illyung);
	      
	      logger.info("map의 값은? {}", map);
	      
	      List<RecordIljiDto> lists = iAccountService.deathcountSearch(map);
	      model.addAttribute("lists", lists);
	      logger.info("lists {} ", lists);
	      
	      Gson gsonBuilder = new GsonBuilder().create();
	      
	      String jsonFromList = gsonBuilder.toJson(lists);
	      System.out.println("jsonFromList" + jsonFromList);
	      System.out.println("toString 형태: " + jsonFromList.toString());	//여기까지 값 가져옴
	      
	      Map<String, String> jmap = new HashMap<String, String>();
	      jmap.put("jlist", jsonFromList);
	      
	      logger.info("jmap의 값은? {} " , jmap);
	      	
//	      return jsonFromList.toString();
	      return new JSONObject(jmap).toString();
//	      return null;
	}
	
	@RequestMapping(value="/weightSearch.do", method= {RequestMethod.POST})
	public String weightSearch(String passcode) {
		System.out.println(passcode);
		
		List<RecordIljiDto> wDto = iAccountService.weightSearch(passcode);
		System.out.println(wDto);
		
		return null;
	}
	
	@RequestMapping(value="/keywordSearch.do", method= {RequestMethod.POST})
	public String wordSearch(String keyword, String etc) {
		System.out.println(keyword);
		System.out.println(etc);
		Map<String, String> map = new HashMap<String,String>();
		map.put("keyword", keyword);
		map.put("etc", etc);
		List<RecordIljiDto> kDto = iAccountService.keywordSearch(map);

		System.out.println(kDto);
		
		return null;
	}
	
	@RequestMapping(value="/deathperdong.do", method= {RequestMethod.POST})
	public String perDong(String distinctdong) {
		
//		String[] splitdong = distinctdong.split("/");//{1,2,3}
		String splitdong = distinctdong.replaceAll("/", "|");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("distinctdong_", splitdong);
		
		List<RecordIljiDto> rDto = iAccountService.dongDeathCount(map);
		
		System.out.println(rDto);
		
		return null;
	}
	
	@RequestMapping(value="/medicineperdong.do", method= {RequestMethod.POST})
	public String medperDong(String donggubun) {
		String splitdong = donggubun.replace("/", "|");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("distinctdong_", splitdong);
		
		List<RecordIljiDto> rDto = iAccountService.dongMedicine(map);
		System.out.println(rDto);
				
		return null;
	}

	@RequestMapping(value="/etcperdong.do", method= {RequestMethod.POST})
	public String etcperDong(String donggubun) {
		String splitdong = donggubun.replace("/", "|");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("distinctdong", splitdong);
		
		List<RecordIljiDto> rDto = iAccountService.dongEtc(map);
		System.out.println(rDto);
		
		return null;
	}
	

	//일지 조회에서 수정하는 기능
		@RequestMapping(value="/iljiModify.do", method=RequestMethod.POST)
		@ResponseBody
		public String iljiModify(HttpSession session,int illyung,IljiModifyDto m_dto) {
			logger.info("iljiModify");
			logger.info("illyung {}",illyung);
			logger.info("IljiModifyDto {}", m_dto);
			
			//세션 값 불러오기
			Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
			logger.info("session에 있는 값: {}", smap);
			// passcode: session에 있는 값
			String passcode = smap.get("passcode");
			logger.info("passcode: {}", passcode);
			
			//update
			List<RecordIljiDto> deathList= new ArrayList<RecordIljiDto>();
			List<RecordIljiDto> weightList= new ArrayList<RecordIljiDto>();
			List<RecordIljiDto> tempList= new ArrayList<RecordIljiDto>();
			List<RecordIljiDto> etcList= new ArrayList<RecordIljiDto>();
			List<RecordIljiDto> medicineList= new ArrayList<RecordIljiDto>();
			
			//insert
			List<RecordIljiDto> i_deathList= new ArrayList<RecordIljiDto>();
			List<RecordIljiDto> i_weightList= new ArrayList<RecordIljiDto>();
			
			//정규표현식 - 숫자만 허용
			Pattern p = Pattern.compile("(^[0-9]*$)");
			Matcher m;
			
			//폐사수
			if(m_dto.getDeathcount_seq()!=null) {
				for(int i=0; i<m_dto.getDeathcount_seq().length;i++) {
					System.out.println(i+" : "+m_dto.getDeathcount_seq()[i]+" : "+m_dto.getDeathcount_value()[i]);
					
					//폐사수 값
					m = p.matcher(m_dto.getDeathcount_value()[i]);
					if(m.find()) {	//숫자라면 insert /update
						System.out.println("폐사수 값이 숫자임");
						
						//폐사수 seq
						if(!m_dto.getDeathcount_seq()[i].equals("")) {	//seq값이 있다면 update할 list에 추가
							System.out.println("폐사수 seq가 존재");
							RecordIljiDto edto= new RecordIljiDto();
							edto.setPasscode(passcode);
							edto.setIllyung(illyung);
							edto.setDistinctdong(String.valueOf(i+1));
							edto.setRecordilji_seq(m_dto.getDeathcount_seq()[i]);
							edto.setDeathcount(Integer.parseInt(m_dto.getDeathcount_value()[i]));
							
							deathList.add(edto);
							
							
						}else {			 	//seq값이 없다면 insert할 list에 추가
							System.out.println("폐사수 seq가 존재하지 않음");
							RecordIljiDto edto= new RecordIljiDto();
							edto.setPasscode(passcode);
							edto.setIllyung(illyung);
							edto.setDistinctdong(String.valueOf(i+1));
							edto.setDeathcount(Integer.parseInt(m_dto.getDeathcount_value()[i]));
							
							i_deathList.add(edto);
						}
						
					}else {			// '-' 아무것도 하지 않음
						System.out.println("폐사수 값이 숫자아님-아무것도 하지 않음");
						continue;
					}
					
				
				
				}
			}
			
			System.out.println(deathList);
			System.out.println(i_deathList);
			
			
			//중량 수정
			if(m_dto.getWeight_seq()!=null) {
				for(int i=0; i<m_dto.getWeight_seq().length;i++) {
					System.out.println(i+" : "+m_dto.getWeight_seq()[i]+" : "+m_dto.getWeight_value()[i]);
					
					//중량 값
					m = p.matcher(m_dto.getWeight_value()[i]);
					if(m.find()) {	//숫자라면 insert /update
						System.out.println("중량 값이 숫자임");
						
						//중량 seq
						if(!m_dto.getWeight_seq()[i].equals("")) {	//seq값이 있다면 update할 list에 추가
							System.out.println("중량 seq가 존재");
							RecordIljiDto edto= new RecordIljiDto();
							edto.setPasscode(passcode);
							edto.setIllyung(illyung);
							edto.setDistinctdong(String.valueOf(i+1));
							edto.setRecordilji_seq(m_dto.getWeight_seq()[i]);
							edto.setWeight(Double.parseDouble(m_dto.getWeight_value()[i]));
							
							weightList.add(edto);
							
							
						}else {			 	//seq값이 없다면 insert할 list에 추가
							System.out.println("중량 seq가 존재하지 않음");
							RecordIljiDto edto= new RecordIljiDto();
							edto.setPasscode(passcode);
							edto.setIllyung(illyung);
							edto.setDistinctdong(String.valueOf(i+1));
							edto.setWeight(Double.parseDouble(m_dto.getWeight_value()[i]));
							
							i_weightList.add(edto);
						}
						
					}else {			// '-' 아무것도 하지 않음
						System.out.println("중량 값이 숫자아님-아무것도 하지 않음");
						continue;
					}
					
				
				
				}
			}
			
			System.out.println(weightList);
			System.out.println(i_weightList);
			
			//사육장 온도 수정
			if(m_dto.getBuildtemp_seq()!=null) {
				for(int i=0; i<m_dto.getBuildtemp_seq().length;i++) {
					System.out.println(i+": "+m_dto.getBuildtemp_seq()[i]+" : "+m_dto.getBuildtemp_value()[i]);
					RecordIljiDto edto= new RecordIljiDto();
					edto.setPasscode(passcode);
					edto.setRecordilji_seq(m_dto.getBuildtemp_seq()[i]);
					edto.setBuildtemp(Double.parseDouble(m_dto.getBuildtemp_value()[i]));
					
					tempList.add(edto);
				}
			}
			//비고 수정
			if(m_dto.getEtc_seq()!=null) {
				for(int i=0; i<m_dto.getEtc_seq().length;i++) {
					System.out.println("i: "+m_dto.getEtc_seq()[i]+" : "+m_dto.getEtc_value()[i]);
					RecordIljiDto edto= new RecordIljiDto();
					edto.setPasscode(passcode);
					edto.setRecordilji_seq(m_dto.getEtc_seq()[i]);
					edto.setEtc(m_dto.getEtc_value()[i]);
					
					etcList.add(edto);
				}
			}
			//약품 수정
			if(m_dto.getMedicine_seq()!=null) {
				for(int i=0; i<m_dto.getMedicine_seq().length;i++) {
					System.out.println("i: "+m_dto.getMedicine_seq()[i]+" : "+m_dto.getMedicine_value()[i]);
					RecordIljiDto edto= new RecordIljiDto();
					edto.setPasscode(passcode);
					edto.setRecordilji_seq(m_dto.getMedicine_seq()[i]);
					edto.setMedicine(m_dto.getMedicine_value()[i]);
					
					medicineList.add(edto);
				}
			}
			
			
			// 서비스 호출
			boolean isc = iIljiService.modifyRecordIlji(deathList, weightList, tempList, etcList, medicineList,i_deathList, i_weightList);
			System.out.println(isc);
			if(isc) {
				return "success";
			}else {
				return "failed";
			}
		}
	
	

}
