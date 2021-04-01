package com.next.kko.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.next.kko.dtos.AccountDto;
import com.next.kko.dtos.IssueDto;
import com.next.kko.dtos.NoticeBoardDto;
import com.next.kko.model.INoticeBoardService;

import oracle.sql.DATE;

@Controller
public class NoticeBoardController {
	
	private final Logger logger = LoggerFactory.getLogger(NoticeBoardController.class);
	
	@Autowired
	INoticeBoardService iboardService;

	/**
	 * <h1>공지사항 글 등록 Controller</h1>
	 * <p>설명 : NoticeBoardDto 객체를 입력받아서 insertBoard 서비스를 실행한다.</p>
	 * @author 박지연
	 * @since 2018.11.07
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param NoticeBoardDto
	 * @return String
	 */
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
	public String insertBoard(NoticeBoardDto ndto) {
		
		System.out.println(ndto);
		boolean isc=iboardService.insertBoard(ndto);
		System.out.println(isc);
		return "redirect:/getAllboard.do";
	}
	
	/**
	 * <h1>공지사항 글 전체조회 Controller</h1>
	 * <p>설명 : getAllboard 서비스를 실행한다.
	 * </p>
	 * @author 박지연
	 * @since 2018.11.07
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/getAllboard.do", method=RequestMethod.GET)
	public String getAllboard(HttpSession session, Model model) {
		logger.info("getAllboard:{}",new DATE());
		Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
		List<NoticeBoardDto> lists = iboardService.getAdminboard();
		model.addAttribute("lists", lists);
		System.out.println("공지사항에 넘어가는 값들 : "+lists);
		return "NoticeBoard";
	}
	
	/**
	 * <h1>공지사항 글 상세조회 Controller</h1>
	 * <p>설명 : getOneBoard 서비스를 실행한다.
	 * </p>
	 * @author 박지연
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String seq
	 * @return String
	 */
	@RequestMapping(value="/getOneBoard.do", method=RequestMethod.GET)
	public String getOneBoard(String seq) {
		logger.info("getOneBoard:{}",new DATE());
		
		NoticeBoardDto ndto=iboardService.getOneBoard(seq);
		System.out.println(ndto);
		
		return null;
	}
	
	/**
	 * <h1>공지사항 글 삭제 Controller</h1>
	 * <p>설명 : 공지사항 글의 sequence를 입력받아 deleteOneBoard 서비스를 실행한다.
	 * </p>
	 * @author 박지연
	 * @since 2018.11.07
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String
	 * @return String
	 */
	@RequestMapping(value="/deleteOneBoard.do", method=RequestMethod.GET)
	public String deleteOneBoard(String seq) {
		logger.info("deleteOneBoard:{}",new DATE());
		System.out.println("seq:  "+seq);
		boolean isc=iboardService.deleteOneBoard(seq);
		System.out.println(isc);
		return "redirect:/getAllboard.do";
	}
	
	/**
	 * <h1>공지사항 글 수정 Controller</h1>
	 * <p>설명 : 공지사항 글의 sequence를 입력받아 modifyBoard 서비스를 실행한다.
	 * </p>
	 * @author 박지연
	 * @since 2018.11.07
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param NoticeBoardDto
	 * @return String
	 */
	@RequestMapping(value="/modifyBoard.do", method= RequestMethod.POST)
	public String modifyBoard(NoticeBoardDto ndto) {
		logger.info("modifyBoard:{}",new DATE());
		System.out.println(ndto);
		boolean isc=iboardService.modifyBoard(ndto);
		System.out.println(isc);
		return "redirect:/getAllboard.do";
	}
	/**
	 * <h1>공지사항 jqGrid로 출력 Controller</h1>
	 * <p>설명 : 세션의 권한을 받아서 유저와 관리자의 차이를 두고 공지사항 게시판을 출력
	 * </p>
	 * @author 홍도현
	 * @since 2018.11.27
	 * @version 1.1
	 * @package com.next.kko.model
	 * @param HttpSession
	 * @return jsonFromList.toString()
	 */
	@RequestMapping(value="/NoticeBoardresult.do", method= RequestMethod.GET)
	@ResponseBody
	public String NoticeBoardresult(HttpSession session) {
		Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
		String AUTH =smap.get("AUTH");
		System.out.println(smap);
		List<NoticeBoardDto> lists = null;
		if(AUTH.trim().equalsIgnoreCase("U")) {
			lists = iboardService.getAllboard();
		}else {
			lists = iboardService.getAdminboard();
		}
		Gson gsonBuilder = new GsonBuilder().create();
		String jsonFromList = gsonBuilder.toJson(lists);
		System.out.println("jsonFromList :" + jsonFromList);
		System.out.println("toString:" +jsonFromList.toString());
		return jsonFromList.toString();
	}
	// location 객체로 추가폼으로 이동
	@RequestMapping(value="/makeboard.do", method=RequestMethod.GET)
	public String makeboard() {
		return "makeBoard";
	}
	@RequestMapping(value="/noticemodify.do", method=RequestMethod.POST, produces="application/text; charset=UTF-8")
	@ResponseBody
	public String noticemodify(String noticecode_seq) {
		NoticeBoardDto dto = iboardService.getOneBoard(noticecode_seq);
		JSONObject json = new JSONObject();
		json.put("noticecode_seq", dto.getNoticecode_seq());
		json.put("writer", dto.getWriter());
		json.put("title", dto.getTitle());
		json.put("content", dto.getContent());
		json.put("regdate", dto.getRegdate());
		json.put("viewcount", dto.getViewcount());
		System.out.println(json.toString());
		return json.toString();
	}
}
