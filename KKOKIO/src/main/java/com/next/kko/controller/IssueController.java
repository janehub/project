package com.next.kko.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.support.SessionStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.next.kko.dtos.IssueDto;
import com.next.kko.dtos.RecordIljiDto;
import com.next.kko.model.IIssueService;
import com.next.kko.model.IRecordIljiService;
import com.next.kko.model.IssueServiceImple;
import com.next.kko.model.RecordIljiServiceImple;

import oracle.sql.DATE;

@Controller
public class IssueController {

	private final Logger logger = LoggerFactory.getLogger(IssueController.class);

	@Autowired
	IIssueService service = new IssueServiceImple();

	@Autowired
	IRecordIljiService recordservice = new RecordIljiServiceImple();
	
	@RequestMapping(value="/iljiInfo.do", method=RequestMethod.GET)
	@ResponseBody
	public String IljiInfo(HttpSession session) {
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		String passcode = smap.get("passcode");
		RecordIljiDto dto = new RecordIljiDto();
		String recordilji_seq = dto.getRecordilji_seq();
		Map<String, String> ilmap = new HashMap<String, String>();
		ilmap.put("passcode", passcode);
		ilmap.put("recordilji_seq", recordilji_seq);
		System.out.println(ilmap);
		return null;
	}
	// 로케이션 객체를 이용하여 이슈추가 페이지로 이동
	@RequestMapping(value = "/makeIssue.do", method = RequestMethod.GET)
	public String makeIssue(HttpSession session, Model model) {
		Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
		String passcode = smap.get("passcode");
		System.out.println("passcode 값 넘어가나"+passcode);
		model.addAttribute("passcode",passcode);
		return "makeIssue";
	}
	@RequestMapping(value = "/insertIssue.do", method = RequestMethod.POST)
	public String insertIssue(Model model, IssueDto idto) {
		System.out.println("idto:  " + idto);
		logger.info("Welcome insertIssue: {}", new DATE());
		boolean isc = service.insertIssue(idto);
		model.addAttribute(idto);
		System.out.println(isc);
		return "redirect:/IssueList.do";
	}
//	@RequestMapping(value="/insertForm.do", method=RequestMethod.POST)
//	public String insertForm(String recordilji_seq,IssueDto dto) {
//		JSONObject json = new JSONObject();
//		json.put("issuecode", dto.getIssuecode());
//		json.put("illyung", dto.getIllyung());
//		json.put("passcode", dto.getPasscode());
//		json.put("contentcode", dto.getContentcode());
//		json.put("actioncode", dto.getActioncode());
//		json.put("actiondetail", dto.getActiondetail());
//		json.put("actionresult", dto.getActionresult());
//		json.put("issuedetail", dto.getIssuedetail());
//		json.put("issueetc", dto.getIssueetc());
//		json.put("recordilji_seq", dto.getRecordilji_seq());
//		return json.toString();
//	}
	 	
	@RequestMapping(value = "/deleteIssue.do", method = RequestMethod.POST)
	public String deleteIssue(String contentcode) {
		logger.info("Welcome deleteIssue: {}", new DATE());
		IssueDto dto = new IssueDto();
		dto.setContentcode(contentcode);
		boolean isc = service.deleteIssue(contentcode);
		System.out.println(isc);
		return "redirect:/IssueList.do";
	}

	@RequestMapping(value = "/InsertAction.do", method = RequestMethod.POST)
	public String InsertAction(IssueDto idto) {
		System.out.println("idto:  " + idto);

		boolean isc = service.InsertAction(idto);
		System.out.println(isc);

		return null;
	}

	@RequestMapping(value = "/modifyAction.do", method = RequestMethod.POST)
	public String modifyAction(IssueDto idto) {
		System.out.println("idto:  " + idto);

		boolean isc = service.modifyAction(idto);
		System.out.println(isc);

		return null;
	}

	@RequestMapping(value = "/deleteAction.do", method = RequestMethod.GET)
	public String deleteAction(String issuecode) {

		boolean isc = service.deleteAction(issuecode);
		System.out.println(isc);

		return null;
	}
	//
	// @RequestMapping(value="/getAllissue.do", method=RequestMethod.GET)
	// public String getAllissue() {
	//
	// List<IssueDto> lists=service.getAllissue();
	// System.out.println(lists);
	//
	// return null;
	// }

	@RequestMapping(value = "/getOneIssue.do", method = RequestMethod.GET)
	public String getOneIssue(String issuecode) {
		IssueDto idto = service.getOneIssue(issuecode);
		System.out.println(idto);
		return null;
	}

	@RequestMapping(value = "/modifyActionResult.do", method = RequestMethod.GET)
	public String modifyActionResult(String contentcode) {
		boolean isc = service.modifyActionResult(contentcode);
		System.out.println(isc);
		return null;
	}

	@RequestMapping(value = "/IssueList.do", method = RequestMethod.GET)
	public String IssueList() {
		logger.info("이슈페이지로 이동: {}", new DATE());
		return "IssueList";
	}
	@RequestMapping(value = "/Issueresult.do", method = RequestMethod.GET)
	@ResponseBody
	public String resultIssue(HttpSession session,String passcode) {
		logger.info("jqGrid 세션에 담긴 이슈사항 :{}", new DATE());
//		session에서 issuecode 받아야함
		Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
		passcode = smap.get("passcode");
		List<IssueDto> lists = service.getAllissue(passcode);
		logger.info("이슈사항 모든 값들:{}",lists);
		System.out.println("리스트에 있는 값 :"+lists);
		Gson gsonBuilder = new GsonBuilder().create();
		String jsonFromList = gsonBuilder.toJson(lists);
		System.out.println("jsonFromList :" + jsonFromList);
		System.out.println("toString:" + jsonFromList.toString());
		return jsonFromList.toString();
	}

	@RequestMapping(value = "/IssueDetail.do", method = RequestMethod.GET)
	public String IssueDetail(Model model, String issuecode) {
		logger.info("Welcome IssueDetail: {}", new DATE());
		IssueDto dto = service.getOneIssue(issuecode);
		model.addAttribute("dto", dto);
		return "IssueDetail";
	}

	@RequestMapping(value = "/modifyForm.do", method = RequestMethod.POST)
	@ResponseBody
	public String modifyForm(String issuecode) {
		System.out.println(issuecode);
		return null;
	}

	@RequestMapping(value = "/modify.do", method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	public String modifyIssue(IssueDto idto) {
		logger.info("Welcome IssueModify: {}", new DATE());
		boolean isc = service.modifyIssue(idto);
		return "redirect:/IssueList.do";
	}

	//jqGrid에 있는 edit, del 기능 컨트롤러
	@RequestMapping(value = "/issueedit.do", method = RequestMethod.POST)
	public String issueedit(HttpSession session, String oper, IssueDto idto, String id) {
		System.out.println(oper);
		System.out.println(idto);
		
		String contentcode = id;
		//세션에서 가져오기
		Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
		String user_code=smap.get("contentcode");
		
		if (oper.equalsIgnoreCase("edit")) {
			idto.setContentcode(user_code);
			boolean isc = service.modifyIssue(idto);
			logger.info("Issue 수정: {}", isc);
		} /*else if (oper.equalsIgnoreCase("add")) {
			boolean isc = service.insertIssue(idto);
			logger.info("Issue 입력: {}", isc);
		}*/ else if (oper.equalsIgnoreCase("del")) {
			System.out.println(contentcode);
			boolean isc = service.deleteIssue(contentcode);
			logger.info("Issue 삭제:{}", isc);
		}
		return "redirect:/IssueList.do";
	}
}
