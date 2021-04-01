package com.next.kko.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.next.kko.dtos.RecordIljiDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.next.kko.dtos.ProductDto;
import com.next.kko.dtos.PurchaseDto;
import com.next.kko.model.IRecordIljiService;
import com.next.kko.model.IProductService;
import com.next.kko.model.IPurchaseService;

@Controller
public class ProductController {
	
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private IProductService iProductService;
	
	
	
	/**
	 * 제품등록을 jqGrid add를 사용하지 않고 입력하는 기능
	 * @param gdto
	 * @return
	 */
	@RequestMapping(value="/goodsInfInput.do", method=RequestMethod.POST)
	public String goodsInfInput(ProductDto gdto) {
		System.out.println(gdto);
		
		ProductDto pdto = new ProductDto();
		
		// productcode, pname, pprice, pdetail

		
		boolean isc = iProductService.goodsInfInput(gdto);
		System.out.println(isc);

		
		return "redirect:./productList.do";
	}
	
	@RequestMapping(value="/goodsInfModify.do", method=RequestMethod.POST)
	public String goodsInfModify(ProductDto gdto) {
		System.out.println(gdto);
		
		ProductDto pdto = new ProductDto();
		
		// productcode, pname, pprice, pdetail

		
		boolean isc = iProductService.goodsInfModify(gdto);
		System.out.println(isc);
		
		return null;
	}
	
	@RequestMapping(value="/goodsInfDel.do", method=RequestMethod.GET)
	public String goodsInfDel(String productcode) {
		System.out.println(productcode);
		
		boolean isc = iProductService.goodsInfDel(productcode);
		System.out.println(isc);
		
		return null;
	}
	
	
	/**
	 * 메뉴에서 비품 선택시 비품내용 화면으로 이동하는 기능
	 * @return productList.jsp
	 */
	@RequestMapping(value="/productList.do", method=RequestMethod.GET)
	public String productList() {
		logger.info("비품 리스트 조회 페이지 이동");
		
		return "ProductList";
	}
	
	/**
	 * 비품 목록 전체를 전달하는 기능
	 * @param model json타입의 List<ProductDto>를 담을 객체
	 * @return json type의 data
	 */
	@RequestMapping(value="/goodsInfSelectAll.do", method=RequestMethod.GET)
	@ResponseBody
	public String goodsInfSelectAll(Model model) {
		
		List<ProductDto> lists = iProductService.goodsInfSelectAll();
		//System.out.println(lists);
		
		Gson gsonBuilder = new GsonBuilder().create();
		String jsonProductList = gsonBuilder.toJson(lists);
      //  System.out.println("jsonProductList: "+jsonProductList);
      //  System.out.println("toString: "+jsonProductList.toString());
		
		model.addAttribute("lists", jsonProductList.toString());
		
		return jsonProductList.toString();
	}
	
	
	/**
	 * 편집할 작업에 따라 추가, 수정, 삭제를 해주는 기능
	 * @param oper 편집할 작업이 추가, 수정, 삭제 중 어느 작업인지 구분
	 * @param dto 추가, 수정시 비품정보가 담기는 ProductDto
	 * @return jqGrid가 있는 화면
	 */
	@RequestMapping(value="/productedit.do", method=RequestMethod.POST)
	public String productedit(String oper, ProductDto dto, String id) {

		System.out.println("입력/수정/삭제 구분: "+oper);
		System.out.println("Dto 값: "+dto);
		System.out.println("id값(productcode): "+id);
		
		if(oper.equalsIgnoreCase("edit")) {
			
			// productcode, pname, pprice, pdetail
			
			boolean isc = iProductService.goodsInfModify(dto);
			logger.info("Product 수정: {}", isc);
			
		}else if(oper.equalsIgnoreCase("add")) {
			
			// productcode, pname, pprice, pdetail
			//System.out.println(dto.getProductcode());
			boolean isc = iProductService.goodsInfInput(dto);
			logger.info("Product 입력: {}", isc);
			
		}else if(oper.equalsIgnoreCase("del")) {
			
			System.out.println(id);
			boolean isc = iProductService.goodsInfDel(id);
			logger.info("Product 삭제: {}", isc);
		}
		
		return "ProductList";
	}
	
	
	
	@RequestMapping(value="/goodsInfSelectOne.do", method=RequestMethod.GET)
	public String goodsInfSelectOne(String productcode) {
		System.out.println(productcode);
		
		ProductDto dto = iProductService.goodsInfSelectOne(productcode);
		System.out.println(dto);
		
		return null;
	}
	
	

}
