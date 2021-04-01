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
import org.springframework.web.bind.annotation.RequestParam;
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
public class PurchaseController {
	
	private final Logger logger = LoggerFactory.getLogger(PurchaseController.class);
	
	@Autowired
	private IPurchaseService iPurchaseService;

	
	
	/**
	 * 구매내역을 jqGrid edit의 add로 사용하지 않고 입력하는 기능
	 * @param PurchaseDto HttpSession
	 * @return
	 */
	@RequestMapping(value="/purchanseInfInput.do", method=RequestMethod.POST)
	public String purchanseInfInput(PurchaseDto purdto, HttpSession session) {
		System.out.println(purdto);
		
		PurchaseDto pdto = new PurchaseDto();
		
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		logger.info("session에 있는 값: {}", smap);
		String passcode = smap.get("passcode");
		// illyung passcode productcode productin productnum etc
		
		pdto.setPasscode(passcode);
		pdto.setIllyung(purdto.getIllyung());
		pdto.setProductcode(purdto.getProductcode());
		pdto.setProductin(purdto.getProductin());
		pdto.setProductnum(purdto.getProductnum());
		pdto.setEtc(purdto.getEtc());
		
		boolean isc = iPurchaseService.purchanseInfInput(pdto);
		System.out.println(isc);
		
		return "redirect:./purchaseList.do";
	}
	
	
	
	@RequestMapping(value="/purchanseInfModify.do", method=RequestMethod.POST)
	public String purchanseInfModify(PurchaseDto purdto) {
		System.out.println(purdto);
		
		PurchaseDto pdto = new PurchaseDto();
		
		//productcode productin illyung productnum etc purchasecode
		pdto.setProductcode(purdto.getProductcode());
		pdto.setProductin(purdto.getProductin());
		pdto.setIllyung(purdto.getIllyung());
		pdto.setProductnum(purdto.getProductnum());
		pdto.setEtc(purdto.getEtc());
		pdto.setPurchasecode(purdto.getPurchasecode());
		
		boolean isc = iPurchaseService.purchanseInfModify(pdto);
		System.out.println(isc);
		
		return null;
	}
	
	@RequestMapping(value="/purchanseInfDel.do", method=RequestMethod.GET)
	public String purchanseInfDel(String purchasecode) {
		
		System.out.println(purchasecode);
		
		boolean isc = iPurchaseService.purchanseInfDel(purchasecode);
		System.out.println(isc);
		
		return null;
	}
	
	
	
	/**
	 * 메뉴에서 구매내역 선택시 구매내역 화면으로 이동하는 기능
	 * @return	purchaseList.jsp
	 */
	@RequestMapping(value="/purchaseList.do", method=RequestMethod.GET)
	public String purchaseList() {
		logger.info("구매내역 조회 페이지 이동");
		
		return "purchaseList";
	}
	
	
	/**
	 * 구매내역 목록 전체를 전달하는 기능
	 * @param model json 타입의 List<ProductDto>를 담을 객체
	 * @return json type의 data
	 */
	@RequestMapping(value="/purchaseAll.do", method=RequestMethod.GET)
	@ResponseBody
	public String purchaseAll(HttpSession session ,Model model) {
		// session에서 passcode 받아야 함
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		logger.info("session에 있는 값: {}", smap);
		String passcode = smap.get("passcode");
		
		List<ProductDto> purlists =  iPurchaseService.purchanseInfSelectAll(passcode);
		//System.out.println("List: "+purlists+"-------------------");
		// 날짜 format 변경
		
//		 purlists.get(0).getPurchase_Dto().getProductin();
		
		Gson gsonBuilder = new GsonBuilder().create();
		String jsonPurchaseList = gsonBuilder.toJson(purlists);
		//System.out.println("jsonProductList: "+jsonPurchaseList);
		//System.out.println("toString: "+jsonPurchaseList.toString()+"-----------------------");
		
		//model.addAttribute("purlists", jsonPurchaseList.toString());
		
		return jsonPurchaseList.toString();
	}
	
	
	/**
	 * 편집할 작업에 따라 추가, 수정, 삭제를 해주는 기능
	 * @param oper 편집할 작업이 추가, 수정, 삭제 중 어느 작업인지 구분
	 * @param dto 추가, 수정 시 구매내역의 정보가 담기는 ProductDto(join사용)
	 * @return jaGrid가 있는 화면
	 */
	@RequestMapping(value="/purchaseedit.do", method=RequestMethod.POST)
	public String purchaseedit(String oper, @RequestParam Map<String,Object> map, String id, HttpSession session ) {
		
		System.out.println("입력/수정/삭제 구분: "+oper);
		System.out.println("ID: "+ id);
		System.out.println("받은 값 형태는?"+ map);
		
		if(oper.equalsIgnoreCase("del")) {
			
			boolean isc = iPurchaseService.purchanseInfDel(id);
			logger.info("Purchase 삭제: {}", isc);
			
		}else {
		
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
				
        // purchasecode, illyung, productin, passcode, productcode, pname, productnum, etc
		PurchaseDto purdto = new PurchaseDto();
		
		String purchasecode = map.get("PURCHASECODE").toString();
		int illyung = Integer.parseInt(map.get("ILLYUNG").toString());
		String productin = map.get("PRODUCTIN").toString();
		// passcode는 session에서 받아옴
		/*String passcode = map.get("PASSCODE").toString();*/
		String passcode = smap.get("passcode");

		String productcode = map.get("PRODUCTCODE").toString();
		//String pname = map.get("PNAME").toString();
		int productnum = Integer.parseInt(map.get("PRODUCTNUM").toString());
		String etc = map.get("ETC").toString();
	

		purdto.setPurchasecode(purchasecode);
		purdto.setIllyung(illyung);
		purdto.setProductin(productin);
		purdto.setPasscode(passcode);
		purdto.setProductcode(productcode);
		purdto.setProductnum(productnum);
		purdto.setEtc(etc);
		
		System.out.println("들오는 값들"+purdto);
		
		if(oper.equalsIgnoreCase("edit")) {
			//productcode productin illyung productnum etc purchasecode
			
			boolean isc = iPurchaseService.purchanseInfModify(purdto);
			logger.info("Purchase 수정: {}", isc);
			
		}else if(oper.equalsIgnoreCase("add")) {
			// illyung passcode productcode productin productnum etc
			
			boolean isc = iPurchaseService.purchanseInfInput(purdto);
			logger.info("Purchase 입력: {}", isc);
		}
		
		}
		
		return "purchaseList";
	}
	
	
	@RequestMapping(value="/purchanseInfSelectAll.do", method=RequestMethod.GET)
	public String purchanseInfSelectAll(String passcode) {
		System.out.println(passcode);
		
		List<ProductDto> lists = iPurchaseService.purchanseInfSelectAll(passcode);
		System.out.println(lists);
		
		return null;
	}
	
	
	@RequestMapping(value="/purchanseInfSelectOne.do", method=RequestMethod.POST)
	public String purchanseInfSelectOne(String passcode, String purchasecode) {
		System.out.println(passcode);
		System.out.println(purchasecode);
		
		Map<String, String> pmap = new HashMap<String, String>();
		
		//passcode purchasecode
		pmap.put("passcode", passcode);
		pmap.put("purchasecode", purchasecode);
		
		System.out.println(pmap);
		
		ProductDto result = iPurchaseService.purchanseInfSelectOne(pmap);
		System.out.println(result);
		
		return null;
	}
	
	

	
	

}
