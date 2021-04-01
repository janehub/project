package com.next.kko.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.RecordIljiDto;
import com.next.kko.model.IRecordIljiService;
import com.next.kko.model.IStaticService;

@Controller
public class StaticController {
	
	private final Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	@Autowired
	private IRecordIljiService iRecordIljiService;
	
	@Autowired
	private IStaticService iStaticService;
	
	@RequestMapping(value="/staticChart.do", method=RequestMethod.GET)
	public String goChart(HttpSession session, Model model) {
		
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		logger.info("세션에서 받은 값: {}", smap);
		
		String passcode = smap.get("passcode");
		int dongNumber = Integer.parseInt(smap.get("DONGNUMBER"));
		
		PassDto pdto = iStaticService.dongChickNumAll(passcode);
		
//		
//		/*폐사수[] 저장*/
//		Map<String, String> callMap = new HashMap<>();
//		callMap.put("passcode", passcode);
//		callMap.put("distinctdong", "1");
//		List<RecordIljiDto> list1 = iStaticService.dongDeathCntAll(callMap);
//		
//		callMap.put("distinctdong", "2");
//		List<RecordIljiDto> list2 = iStaticService.dongDeathCntAll(callMap);
//
//	
//		
//		System.out.println("------------사이즈!!");
//		int illyung_1 = list1.size();
//		System.out.println("일령"+illyung_1);
//		// int[] 배열자체는 동 개수만큼, 배열의 크기는 일령수만큼
//		
//		
//		int[] dCnt_1 = new int[illyung_1];
//		int[] dCnt_2 = new int[illyung_1];
//		                      
//		int[] rCnt_1 = new int[illyung_1+1];
//		int[] rCnt_2 = new int[illyung_1+1];
//
//		System.out.println("/------------------------------------폐사수/");
//		
//		for (int i = 0; i < illyung_1; i++) {
//			dCnt_1[i] = list1.get(i).getDeathcount();
//			dCnt_2[i] = list2.get(i).getDeathcount();
//		}
//
//		System.out.println("/------------------------------------입추수수/");
//		String dongchickNum = pdto.getDongchicknum();
//		int[] dChickNum = new int[dongNumber]; 
//		String[] temp = new String[dongNumber];
//		
//		// 동별 입추수수 1동 
//		for (int i = 0; i < dongNumber ; i++) {
//			temp = dongchickNum.split("/");
//			dChickNum[i] = Integer.parseInt(temp[i]);
//			System.out.println(dChickNum[i]);
//		}
//		
//		System.out.println("/------------------------------------남은 마리수/");
//		
//		rCnt_1[0] = dChickNum[0]-dCnt_1[0];
//		rCnt_2[0] = dChickNum[1]-dCnt_2[0];
//		System.out.println("첫날 남은 마리수:"+rCnt_1[0]);
//		System.out.println("첫날 남은 마리수:"+rCnt_2[0]);
//		for (int i = 1; i < illyung_1; i++) {
//			rCnt_1[i] = rCnt_1[i-1]-dCnt_1[i];
//			rCnt_2[i] = rCnt_2[i-1]-dCnt_2[i];
//
//			System.out.println(i+"남은 마리수:"+rCnt_1[i]);
//			System.out.println(i+"남은 마리수:"+rCnt_2[i]);
//		}
//		
//		System.out.println("/------------------------------------폐사율/");
//		
//		double[] dRatio_1 = new double[illyung_1+1];
//		double[] dRatio_2 = new double[illyung_1+1];
//		
//		for (int i = 0; i < illyung_1; i++) {
//			dRatio_1[i] = ((double)dCnt_1[i]/(double)rCnt_1[i])*100;
//			dRatio_2[i] = ((double)dCnt_2[i]/(double)rCnt_2[i])*100;
//			
//			System.out.println(i+"폐사율:"+ dRatio_1[i]);
//			System.out.println(i+"폐사율:"+ dRatio_2[i]);
//			//System.out.println(i+"폐사율:"+ Math.round(dRatio_1[i]));
//			//System.out.println(i+"폐사율:"+ Math.round(dRatio_1[i]*100)/100.0);
//			//System.out.println(i+"폐사율:"+dRatio_2[i]);
//		}
//		
//		System.out.println("/------------------------------------Model 담기/");
//		// 입추수수 dChickNum
//		Map<String, int[]> iMap = new HashMap<>();
//		iMap.put("dChickNum", dChickNum);
//		
//		// 폐사수
//		Map<String, int[]> dMap = new HashMap<>();
//		dMap.put("dCnt_1", dCnt_1);
//		dMap.put("dCnt_2", dCnt_2);
//		
//		// 폐사율
//		Map<String, double[]> rMap = new HashMap<>();
//		rMap.put("dRatio_1", dRatio_1);
//		rMap.put("dRatio_2", dRatio_2);
//		
//		model.addAttribute("iMap", iMap);
//		model.addAllAttributes(dMap);
//		model.addAllAttributes(rMap);
//		
//		int[]a = iMap.get("dChickNum");
//		
//		System.out.println(a.length);
		
		
		return "staticChart";
	}
	
	@RequestMapping(value="/chartData.do", method=RequestMethod.GET)
	@ResponseBody
	public String dongDeathCntChart() {
		
		JSONObject data = new JSONObject();
		JSONObject ajaxObjCols1 = new JSONObject();    //cols의 1번째 object를 담을 JSONObject
		JSONObject ajaxObjCols2 = new JSONObject();    //cols의 2번째 object를 담을 JSONObject
		JSONArray ajaxArryCols = new JSONArray();        //위의 두개의 JSONObject를 담을 JSONArray
		
		ajaxObjCols1.put("type","number"); // 일령 int
		ajaxObjCols2.put("type", "number");// 1동 폐사수 int

		ajaxArryCols.add(ajaxObjCols1);
		ajaxArryCols.add(ajaxObjCols2);
		
		System.out.println(ajaxArryCols);
		
		return null;
	}

	
	////
	
	@RequestMapping(value="/getDeathCountStatic.do", method=RequestMethod.GET)
	@ResponseBody
	public String getDeathCountStatic(HttpSession session,Model model) {
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		logger.info("세션에서 받은 값: {}", smap);
		
		String passcode = smap.get("passcode");
		
		List<RecordIljiDto> list=iStaticService.getDeathCountStatic(passcode);
		System.out.println(list);
		
		model.addAttribute("dongnumber", smap.get("DONGNUMBER"));
		
		Gson gsonBuilder = new GsonBuilder().create();
		String jsonFromList = gsonBuilder.toJson(list);
		System.out.println("jsonFromList: "+jsonFromList);
		System.out.println("toString: "+jsonFromList.toString());
		
		return jsonFromList.toString();
	}
	
}
