package com.next.kko.controller;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.next.kko.dtos.AccountDto;
import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.RecordIljiDto;
import com.next.kko.model.IAccountDao;
import com.next.kko.model.IAccountService;
import com.next.kko.model.IAddressCodeService;

@Controller
public class AccountController {

	private final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private IAccountService iAccountService;
	
	@Autowired
	private IAddressCodeService AddressCodeService;
	
	//==============================================================
	//이동해주는 controller   
	   @RequestMapping(value="/goPass.do", method= {RequestMethod.GET})
	   public String movetoMakePass() {
	      return "makePass";
	   }

	   @RequestMapping(value="/movetotest.do", method=RequestMethod.GET)
	   public String movetoTest() {
		   
		   return "iljidetailtest";
	   }
	   
//	   @RequestMapping(value="/gopassInfo.do", method= {RequestMethod.GET})
//	   public String movetoPassInfo() {
//	      return "passInfo";
//	   }
	   
	   @RequestMapping(value="/gopassList.do", method= {RequestMethod.GET})
	   public String movetoPassList() {
	      return "passList";
	   }
	   
	   @RequestMapping(value="/goproductList.do", method= {RequestMethod.GET})
	   public String movetoProductList() {
	      return "ProductList";
	   }

	   @RequestMapping(value="/gotemplate.do", method= {RequestMethod.GET})
	   public String movetoTemplate() {
	      return "template";
	   }
	//==============================================================   
	
	@RequestMapping(value="/signUpForm.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String signForm() {
		logger.info("Welcome registForm:{}", new Date());
		return "registForm";
	}
	
	@RequestMapping(value="/searchId.do", method= RequestMethod.GET)
	public String searchId() {
		logger.info("Welcome searchId: {}", new Date());
		return "findId";
	}
	
	@RequestMapping(value="/searchPw.do", method= RequestMethod.POST)
	public String searchPw() {
		logger.info("Welcome searchPw: {}", new Date());
		return "findPw";
	}
	
	@RequestMapping(value="/loginForm.do", method=RequestMethod.GET)
	public String loginForm() {
		logger.info("Welcome loginForm{} ", new Date());
		
		return "loginForm";
	}
	
	@RequestMapping(value="/mainForm.do", method=RequestMethod.GET)
	public String mainForm() {
		logger.info("Welcome mainForm{}", new Date());
		return "main";
	}
	
	@RequestMapping(value="/logoutForm.do", method=RequestMethod.GET)
	public String logoutForm() {
		logger.info("Welcome logoutForm{}", new Date());
		return "logoutForm";
	}
	
	@RequestMapping(value="/gonewsPage.do", method=RequestMethod.GET)
	public String newsPage() {
		logger.info("Welcome newspage : {}", new Date());
		return "newsPage";
	}
	
	@RequestMapping(value="/idDuplicateCheck.do", method=RequestMethod.POST,
			produces="application/text; charset=UTF-8")
	@ResponseBody
	public String idCheck(String id) {
		logger.info("Welcome idCheck:{}", new Date());
		char[] temp =  id.toCharArray();
		for (int i = 0; i < temp.length; i++) {
			if(!((temp[i]>='a' && temp[i]<='z')
				||(temp[i]>='A' && temp[i]<='Z')
				||(temp[i]>='0' && temp[i]<='9'))) {
				return "특수문자는 사용할 수 없습니다.";
			}
		}
		boolean isc = iAccountService.idDuplicateCheck(id);
		System.out.println(isc);
		return isc==true?"사용가능한 아이디":"중복된 아이디";
	}
	
	/**
	 * <h1>registUser</h1>
	 * <p>설명: 유저가 정보를 입력하면은 Map에 값을 담는다.
	 * <pre>
	 * <b>History</b>
	 * 		작성자 이름:이정은, 버전 1.0, 2018.11.07 최초작성
	 * 		작성자 이름:이정은, 버전 1.1, 2018.11.13 수정
	 * 		작성자 이름:박지연, 버전 1.2, 2018.11.29 주소코드 입력 추가
	 * 
	 * @author 이정은
	 * @since 2018.11.07
	 * @version 1.1
	 */
	@RequestMapping(value="/registUser.do", method=RequestMethod.POST)
	public String registerUser(AccountDto dto, String sample6_address, String sample6_address2) {
		System.out.println(dto.getPhone());
		System.out.println(sample6_address);
		System.out.println(sample6_address2);
		
		String addedAddress = sample6_address+","+sample6_address2;
		System.out.println(addedAddress);
		dto.setAddress(addedAddress);
		System.out.println(addedAddress);
		
		boolean isc = iAccountService.registUser(dto);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", dto.getId());
		map.put("addresscode", "");
		
		boolean isc2 = AddressCodeService.insertAddressCode(map);
		System.out.println(isc);
		
		
		logger.info("가입 성공!");
		
		return isc&&isc2 ? "loginForm":"signUpForm";
	}
	
	/**
	 * <h1>loginUser</h1>
	 * <p>설명 : 유저가 정확한 정보를 입력하면 세션에 dto 값을 담는다.
	 * <pre>
	 * <b>History</b>
	 *       이전작성자 이름, 버전 1.0, 2018.11.07 최초작성
	 * </pre>
	 * 
	 * @author 이정은
	 * @since 2018.11.07
	 * @version 1.0
	 */
	@RequestMapping(value="/loginUser.do", method={RequestMethod.POST, RequestMethod.GET})
	public String loginUser(Map<String, String> map,  HttpSession session) {
//		System.out.println(dto.getId());
//		System.out.println(dto.getPw());
//		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("id", dto.getId());
//		map.put("pw", dto.getPw());
//		
//		Map<String, String> mapp =  iAccountService.loginUser(map);
//		System.out.println(mapp);
		Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
		
		System.out.println("세션에 보내진값?"+smap);
		
		return "main";
	}
	
	
	@RequestMapping(value="/loginCheck.do", method=RequestMethod.POST,
			produces="application/text; charset=UTF-8")
	@ResponseBody
	public String loginCheck(AccountDto dto, HttpSession session) {
		System.out.println(dto);
		
		Map<String, String> mDto = new HashMap<String, String>();
		mDto.put("id", dto.getId());
		mDto.put("pw", dto.getPw());
//		mDto.put("auth", dto.getAuth());
//		mDto.put("faddress", dto.getFarmaddress());
//		mDto.put("dongnumber", String.valueOf(dto.getdongnumber()));
//		ID, AUTH, FARMADDRESS, DONGNUMBER
		
//		Map<String, String> lmap = iAccountService.loginUser(mDto);
		AccountDto rdto = iAccountService.loginUser(mDto);
		logger.info("rdto  {}",rdto);
		Map<String, String> lmap = new HashMap<String, String>();
		lmap.put("AUTH", rdto.getAuth());
		lmap.put("ID", rdto.getId());
		lmap.put("FARMADDRESS", rdto.getFarmaddress());
		lmap.put("DONGNUMBER", String.valueOf(rdto.getdongnumber()));
		lmap.put("FARMNAME", rdto.getFarmname());
		
		//주소코드 가져오기
		String addresscode = AddressCodeService.getAddressCode(rdto.getId());
		System.out.println(addresscode);
		lmap.put("ADDRESSCODE", addresscode);
		
		
		session.setAttribute("memid", lmap);
		logger.info("Return loginCheck 반환된 값: {}\n", lmap);
		
		System.out.println(Integer.parseInt(lmap.get("DONGNUMBER")));
		
		return (lmap==null)?"실패":"성공";
	}
	
	/**
	 * 회원이 로그아웃을 할 수 있도록 도와주는 controller.
	 * 회원이 로그아웃을 할 시 session을 지운다.
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logOut.do", method=RequestMethod.GET)
	public String logOut(HttpSession session) {
		logger.info("Welcome logOut: {}", new Date());
		Map<String, String> ldto = (Map<String, String>) session.getAttribute("memid");
		
		logger.info("세션에 담긴 값 {}", ldto);
		
		if(ldto != null) {
			session.removeAttribute("memid");
			session.invalidate();
			return "loginForm";
		} else {
			return "loginForm";
		}
	}
	
	
	/**
	 * 회원정보 보여주는 Controller
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/userSearch.do", method= RequestMethod.GET)
	public String searchUser(HttpSession session, Model model) {
		
		Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
		model.addAttribute("id", smap.get("ID"));
		model.addAttribute("email",smap.get("EMAIL"));
		
		AccountDto dto = iAccountService.searchUser(smap.get("ID"));
		model.addAttribute("dto",dto);
		
		logger.info("값은? {} ", smap.get("ID"));
		
		logger.info("dto 안에 있는 값은? {} ", dto);

		return "accinfoForm";
	}
	
	@RequestMapping(value="/userModify.do", method= {RequestMethod.POST})
	public String modifyUser(AccountDto dto, HttpSession session, Model model) {

		Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
		model.addAttribute("ID", smap.get("ID"));
		logger.info(smap.get("ID"));
		
		//=====================================================================================
		//email, phone, address, name, farmname, farmaddress, farmtel, dongnumber
		String email = dto.getEmail();
		String phone = dto.getPhone();
		String address = dto.getAddress();
		String name = dto.getName();
		String farmname = dto.getFarmname();
		String farmaddress = dto.getFarmaddress();
		String farmtel = dto.getFarmtel();
		int dongnumber = dto.getdongnumber();
		
//		logger.info("1차 DTO 내의 값은 ? :::: {} ", dto);
		
		dto.setId(smap.get("ID"));
		dto.setEmail(email);
		dto.setPhone(phone);
		dto.setAddress(address);
		dto.setName(name);
		dto.setFarmname(farmname);
		dto.setFarmaddress(farmaddress);
		dto.setFarmtel(farmtel);
		dto.setdongnumber(dongnumber);
		
		logger.info("DTO 내의 값은 ? :::: {} ", dto);

		boolean isc = iAccountService.modifyUser(dto);
		logger.info("성공했으면 true{}", isc);
		//=====================================================================================
		
//		return "main";
		return isc ? "redirect:/userSearch.do" : null;
	}
	
	@RequestMapping(value="/findUserId.do", method= {RequestMethod.POST})
	public String findUserId(String name, String phone, String email, Model model) {
		System.out.println(name);
		System.out.println(phone);
		System.out.println(email);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("phone", phone);
		map.put("email", email);
		
		String temp = iAccountService.findUserId(map);
		model.addAttribute("id",temp);
		System.out.println("찾은아이디는?" + temp);
		
		if(temp != null) {
			logger.info("아이디를 찾았습니다.");
			return "findIdResult";
		} else {
			logger.info("아이디를 찾지 못하였습니다");
			return "findIdFail";
		}
	}
	
	@RequestMapping(value="/dropUser.do", method= {RequestMethod.GET})
	public String dropUser(String id, HttpSession session, Model model) {
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		model.addAttribute("ID", smap.get("ID"));
		
		boolean isc = iAccountService.dropUser(smap.get("ID"));
		
//		logger.info("{}",smap.get("ID"));
		
		
		return "dropUser";
	}
	

	/**
	 * <h1>주소코드 변경</h1>
	 * <p>설명 : addresscode를 입력받아서 id에 따른 주소코드 정보를 세션에 저장하고 데이터베이스에 입력한다.</p>
	 * @author 박지연
	 * @since 2018.11.29
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param addresscode
	 * @return
	 */
	@RequestMapping(value="/updateAddressCode.do",method=RequestMethod.GET)
	@ResponseBody
	public String updateAddressCode(String addresscode,HttpSession session) {
		Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
		logger.info("Return loginCheck 세션에 있는 값: {}\n", smap);
		
		//세션에 담기
		smap.put("ADDRESSCODE", addresscode);
		session.setAttribute("memid", smap);
		logger.info("Return loginCheck 반환된 값: {}\n", smap);
		
		//DB에 저장
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", smap.get("ID"));
		map.put("addresscode", addresscode);
		
		boolean isc = AddressCodeService.updateAddressCode(map);
		System.out.println(isc);
		
		return isc?"성공":"실패";
	}
	
}