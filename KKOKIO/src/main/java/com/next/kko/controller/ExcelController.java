package com.next.kko.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.RecordIljiDto;
import com.next.kko.model.IExcelService;
import com.next.kko.util.KkokioUtil;

import oracle.sql.DATE;

@Controller
public class ExcelController {
	
private final Logger logger = LoggerFactory.getLogger(ExcelController.class);
	
	@Autowired
	IExcelService iexcelService;
	
	@RequestMapping(value="/excelPage.do", method=RequestMethod.GET)
	public String excelPage() {
		return "excelPage";
	}
	
	@RequestMapping(value="/downloadExcelTemplate.do", method=RequestMethod.GET)
	public void downloadExcelTemplate(HttpServletRequest request,HttpServletResponse response) throws IOException {
		logger.info("downloadExcelTemplate");
		
	    // 저장된파일의 절대경로
//	    String filePath = "C:\\Users\\silbestar\\Documents\\프로젝트\\육계일지서식.xlsx";
	    
		// 저장된파일의 상대경로
		String filePath = request.getSession().getServletContext().getRealPath("\\download\\육계일지서식.xlsx");
	    System.out.println(request.getSession().getServletContext().getRealPath("\\download\\육계일지서식.xlsx"));
	    
	    byte fileByte[] = FileUtils.readFileToByteArray(new File(filePath));
	     
	    response.setContentType("application/octet-stream");
	    response.setContentLength(fileByte.length);												// 다운로드시 변경할 파일명
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("육계일지서식.xlsx","UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    
	    response.getOutputStream().write(fileByte);
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	    
	}

	@RequestMapping(value="/fileUpload.do", method=RequestMethod.POST)
	@ResponseBody
	public String fileUpload(MultipartHttpServletRequest multipartRequest) throws IOException{
		logger.info("fileUpload");
		
		//절대 경로
//		String filepath="C:\\Users\\silbestar\\Documents\\workspace_jsp\\KKOKIO\\src\\main\\webapp\\upload";
		//상대 경로 : 서버에 애플리케이션 배포해서 실행할 때 상대경로로 바꿈
		String filepath = multipartRequest.getSession().getServletContext().getRealPath("\\upload");
		logger.info("filepath: {}",filepath);
		
		MultipartFile mpf =multipartRequest.getFile("filename");
		
		String originalFilename = mpf.getOriginalFilename(); //파일명
	    logger.info("originalFilename: "+originalFilename);
//유효성 검사-파일을 올렸는지 확인	    
	    if(originalFilename==null||originalFilename==""){
	    	return "file_not_selected";
	    }
	    
//유효성 검사-확장자가 xlsx인지 확인		
		logger.info("확장자: {}",originalFilename.substring(originalFilename.lastIndexOf(".")+1));
		if(!originalFilename.substring(originalFilename.lastIndexOf(".")+1).equalsIgnoreCase("xlsx")) {
			return "not_xlsx_file";
		}
			
	    String UUIDFilename = KkokioUtil.createUUID()	//UUID로 변환한 파일명
	    				+ originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileFullPath = filepath+"\\"+UUIDFilename; //파일 전체 경로
        logger.info("fileFullPath: "+fileFullPath);
		
		try {
			mpf.transferTo(new File(fileFullPath));
			logger.info("파일 저장 SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("파일 저장 ERROR");
			e.printStackTrace();
		}

//성공시 파일경로 리턴
		return ".\\upload"+"\\"+UUIDFilename;
	}	
	
	
	@RequestMapping(value="/insertExcel.do", method=RequestMethod.POST)
	@ResponseBody
	public String insertExcel(@RequestBody String json,HttpSession session) throws ParseException {
		PassDto pdto;
		RecordIljiDto[] rarray;
//세션에서 id가져오기		
		Map<String, String> smap = (Map<String, String>) session.getAttribute("memid");
		logger.info("세션에있는값: {}",smap);
		String id=smap.get("ID");
		
//json 겉 []제거		
		logger.info("s: "+json);
		json=json.substring(1, json.length()-1);
		logger.info("s: "+json);
		
//JSON Parsing		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
//--파스정보		
		Object excelpass=element.getAsJsonObject().get("excelpass");
		System.out.println("excelpass: "+excelpass.toString());
		
		String excelpass_s=excelpass.toString();
		excelpass_s=excelpass_s.substring(1, excelpass_s.length()-1);
		JsonElement element_pass = parser.parse(excelpass_s);

//		String startpass=element_pass.getAsJsonObject().get("startpass").getAsString();
//		System.out.println("startpass: "+startpass);

//gson으로 json VO 매핑	 - 파스정보	
		Gson gson = new Gson();
		pdto = gson.fromJson(element_pass, PassDto.class);
		
		pdto.setId(id);
		
		logger.info("PassDto :{}",pdto);

//--육계일지 정보
		Object excelilji=element.getAsJsonObject().get("excelilji");
		System.out.println("excelilji: "+excelilji.toString());
		String excelilji_s=excelilji.toString();
		JsonElement element_excelilji = parser.parse(excelilji_s);
		System.out.println("element_excelilji: "+element_excelilji);

		
//gson으로 json VO 매핑	 - 육계일지정보
		rarray = gson.fromJson(element_excelilji, RecordIljiDto[].class);
		logger.info("RecordIljiDto_length :{}",rarray.length);
		for(int i=0;i<rarray.length;i++) {
			logger.info("RecordIljiDto :{}",rarray[i]);
		}

		
//널 처리-파스
		if(pdto.getEndpass()==null) {
			//디폴트값: startpass+45-1 처리
			pdto.setEndpass(KkokioUtil.addDates(pdto.getStartpass(),45-1));
		}
		if(pdto.getInactivity()==null) {
			pdto.setInactivity("");
		}
		if(pdto.getInbuhwa()==null) {
			pdto.setInbuhwa("");
		}
		if(pdto.getIntype()==null) {
			pdto.setIntype("");
		}
	
		logger.info("널 처리 PassDto :{}",pdto);
		
//널 처리-육계일지		
		for (int i = 0; i < rarray.length; i++) {
			//예전 날짜의 기상청 온도 어떻게 할건지?
			rarray[i].setHightemp(30);
			rarray[i].setLowtemp(20);
			
			rarray[i].setPasscode(pdto.getId()+pdto.getStartpass());
			rarray[i].setRecordtime(rarray[i].getRecorddate());
			
			if(rarray[i].getMedicine()==null) {
				rarray[i].setMedicine("");
			}
			if(rarray[i].getWeathercon()==null) {
				rarray[i].setWeathercon("");
			}
			if(rarray[i].getEtc()==null) {
				rarray[i].setEtc("");
			}		
		}
		
		logger.info("PassDto :{}",pdto);		
		for(int i=0;i<rarray.length;i++) {
			logger.info("널 처리 RecordIljiDto :{}",rarray[i]);
		}
		
		
//서비스 실행		
		boolean isc = iexcelService.insertExcel(pdto,rarray);
		System.out.println(isc);
		
		return null;
	}	
		

	@RequestMapping(value="/getExcel.do", method=RequestMethod.GET)
	@ResponseBody
	public String getExcel(String passcode){
		logger.info("getExcel:{}",new DATE());
		logger.info("passcode:{}",passcode);
		
		//파스 정보 불러오기
		PassDto pdto= iexcelService.getExcelPass(passcode);
		logger.info("pdto:{}",pdto);
		
		Gson gsonBuilder = new GsonBuilder().create();
		
		String jsonFromPassDto = gsonBuilder.toJson(pdto);
		logger.info("jsonFromPassDto:{}",jsonFromPassDto);
		logger.info("toString:{}",jsonFromPassDto.toString());
		
		//일지 정보 불러오기
		List<RecordIljiDto> list= iexcelService.getExcelIlji(passcode);
		logger.info("list: {}",list);
		
		String jsonFromList = gsonBuilder.toJson(list);
		logger.info("jsonFromList: {}",jsonFromList);
		
		logger.info("toString: {}",jsonFromList.toString());
		
		String json="[{\"excelpass\":"+jsonFromPassDto.toString()+", \"excelilji\":"+jsonFromList.toString()+"}]";
		logger.info("json: {}",json);
		
		return json;
	}
	
	
	@RequestMapping(value="/getExcelPass.do", method=RequestMethod.GET)
	public String getExcelPass(String passcode){
		logger.info("getExcelPass:{}",new DATE());
		
		
		System.out.println("passcode: "+passcode);
		//널 처리
		
		PassDto pdto= iexcelService.getExcelPass(passcode);
		System.out.println("pdto: "+pdto);
		
		Gson gsonBuilder = new GsonBuilder().create();
		
		String jsonFromPassDto = gsonBuilder.toJson(pdto);
		System.out.println("jsonFromPassDto: "+jsonFromPassDto);
		
		System.out.println("toString: "+jsonFromPassDto.toString());
		
		return null;
	}
	
	
	@RequestMapping(value="/getExcelIlji.do", method=RequestMethod.GET)
	public String getExcelIlji(String passcode){
		logger.info(" getExcelIlji:{}",new DATE());
		
		
		System.out.println("passcode: "+passcode);
		//널 처리
		
		List<RecordIljiDto> list= iexcelService.getExcelIlji(passcode);
		System.out.println("list: "+list);
		
		Gson gsonBuilder = new GsonBuilder().create();
		
		String jsonFromList = gsonBuilder.toJson(list);
		System.out.println("jsonFromList: "+jsonFromList);
		
		System.out.println("toString: "+jsonFromList.toString());
		
		return null;
	}
	
}
