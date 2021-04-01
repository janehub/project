package com.next.kko.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.next.kko.model.IAddressCodeService;

@Controller
public class tempController_jy {

	@Autowired
	private IAddressCodeService AddressCodeService;
	
	
	@RequestMapping(value="/insertAddressCode.do",method=RequestMethod.GET)
	public String insertAddressCode(String addresscode) {
		//id: 세션에서 얻어오기
		String id="ONG001";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("addresscode", addresscode);
		
		boolean isc = AddressCodeService.insertAddressCode(map);
		System.out.println(isc);
		
		return null;
	}
	
//	@RequestMapping(value="/updateAddressCode.do",method=RequestMethod.GET)
//	public String updateAddressCode(String addresscode) {
//		//id: 세션에서 얻어오기
//		String id="ONG001";
//		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("id", id);
//		map.put("addresscode", addresscode);
//		
//		boolean isc = AddressCodeService.updateAddressCode(map);
//		System.out.println(isc);
//		
//		return null;
//	}

	@RequestMapping(value="/getAddressCode.do",method=RequestMethod.GET)
	public String getAddressCode() {
		//id: 세션에서 얻어오기
		String id="ONG001";
		
		String addresscode = AddressCodeService.getAddressCode(id);
		System.out.println(addresscode);
		
		return null;
	}
	
	
	
	
	
	
	/////
	@RequestMapping(value="/indexToMain.do", method=RequestMethod.GET)
	public String indexToMain() {
		
		return "main";
	}
	
	@RequestMapping(value="/ProductList.do", method=RequestMethod.GET)
	public String productList() {
		
		return "ProductList";
	}
	
	
	

}
