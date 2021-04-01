package com.next.kko.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddressCodeController {

	private final Logger logger = LoggerFactory.getLogger(AddressCodeController.class);
	
	//코드 정보를 가지고와서 weatherInfo 페이지로 넘겨줌
	@RequestMapping(value="/weatherOpen.do", method=RequestMethod.GET)
	public String weatherOpen(String code,Model model) {
		logger.info("weatherOpen {}",new Date());
		logger.info("code {}",code);
		
		model.addAttribute("code", code);
		
		return "weatherInfo";
	}
}
