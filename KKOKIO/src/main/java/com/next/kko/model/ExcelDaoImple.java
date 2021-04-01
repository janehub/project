package com.next.kko.model;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.RecordIljiDto;
import com.next.kko.util.KkokioUtil;

@Repository
public class ExcelDaoImple implements IExcelDao{
	
	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS="com.next.kko.excel.";
	
	@Override
	public boolean insertExcelPass(PassDto pdto) {
		// TODO Auto-generated method stub
		System.out.println("insertExcelPass dao");
		int n=session.insert(NS+"insertExcelPass",pdto);
		
		System.out.println("insertExcelPass실행 후 pdto :"+pdto);
		
		return n>0?true:false;
	}

	
	
	//passcode, illyung
	@Override
	public boolean codeset(PassDto pdto) throws ParseException {
		// TODO Auto-generated method stub
		String startpass = pdto.getStartpass();
		String endpass = pdto.getEndpass();
		int diffdays=KkokioUtil.diffDates(startpass, endpass)+1;
		
		
		System.out.println("codeset dao");
		
		System.out.println("Passcode	:"+pdto.getPasscode());
		System.out.println("Startpass	:"+KkokioUtil.stringToDate(startpass));
		System.out.println("Endpass		:"+KkokioUtil.stringToDate(endpass));
		System.out.println("Endpass-Startpass		:"+diffdays);
		
		
		int n=0;
		//일령 수 만큼
		for (int i = 1; i <= diffdays; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("passcode", pdto.getPasscode());
			map.put("illyung", String.valueOf(i));
			n+=session.insert(NS+"codeset",map);
		}
//		int n=session.insert(NS+"codeset",pdto);
		
		return n>0?true:false;
	}



	@Override
	public boolean insertExcelIlji(RecordIljiDto[] rdtos) {
		// TODO Auto-generated method stub
		System.out.println("insertExcelIlji dao");
		
		int n=0;
		
		for (int i = 0; i < rdtos.length; i++) {
			n+=session.insert(NS+"insertExcelIlji",rdtos[i]);
		}
		
		return n>0?true:false;
//		return false;
	}



	@Override
	public PassDto getExcelPass(String passcode) {
		// TODO Auto-generated method stub
		System.out.println("getExcelPass dao");
		
		return session.selectOne(NS+"getExcelPass", passcode);
	}



	@Override
	public List<RecordIljiDto> getExcelIlji(String passcode) {
		// TODO Auto-generated method stub
		System.out.println("getExcelIlji dao");
		
		return session.selectList(NS+"getExcelIlji", passcode);
	}


}
