package com.next.kko.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.next.kko.util.KkokioUtil;
import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.RecordIljiDto;

@Repository
public class PassDaoImple implements IPassDao{

	private final String NS = "com.next.kko.ilji.";
	
	private final Logger logger = LoggerFactory.getLogger(PassDaoImple.class);
	
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public boolean makePass(PassDto dto) {
		int n = session.insert(NS+"makePass",dto);
		return (n>0)? true : false;
	}
	
	//passcode, illyung
	@Override
	public boolean codeset(PassDto pdto) throws ParseException {
		// TODO Auto-generated method stub
		String startpass = pdto.getStartpass();
		String endpass = pdto.getEndpass();
		// 첫째날 포함하므로 +1
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
//			int n=session.insert(NS+"codeset",pdto);
		
		return n>0?true:false;
	}
	
	@Override
	public boolean codesetForModify(String passcode, int term, int maxCodset) {
		// TODO Auto-generated method stub
		int n=0;
		//일령 수 만큼
		for (int i = maxCodset+1; i <= term; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("passcode", passcode);
			map.put("illyung", String.valueOf(i));
			n+=session.insert(NS+"codeset",map);
		}
		
		return n>0?true:false;
	}

	@Override
	public boolean modifyPass(PassDto dto) {
		int n = session.update(NS+"modifyPass",dto);
		return (n>0)? true : false;
	}

	@Override
	public PassDto infoDetailPass(String passcode,String id) {
		System.out.println("infoDetailPass");
		Map<String, String> map = new HashMap<String, String>();
		map.put("passcode", passcode);
		map.put("id", id);
		PassDto dto = session.selectOne(NS+"infoDetailPass",map);
		return dto;
	}
	
	@Override
	public List<RecordIljiDto> infoMainIlji(String passcode, String dongnumber) {
		// TODO Auto-generated method stub
		logger.info("passcode , dongnumber {} : {}",passcode,dongnumber);
		List<RecordIljiDto> list= new ArrayList<RecordIljiDto>();
		
		for(int i=1;i<=Integer.parseInt(dongnumber);i++) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("passcode", passcode);
		map.put("dongnumber", String.valueOf(i));
		RecordIljiDto rdto = session.selectOne(NS+"infoMainIlji",map);
		logger.info("rdto {} : {}",i,rdto);
		list.add(rdto);
		}
		
		return list;
	}
	
	@Override
	public List<PassDto> infoPassList(String id) {
		// TODO Auto-generated method stub
		return session.selectList(NS+"infoPassList",id);
	}

	@Override
	public boolean deletePass(Map<String,String> map) {
		int n = session.update(NS+"deletePass",map);
		return (n>0)? true : false;
	}
	
	@Override
	public boolean deleteCodeset(String passcode) {
		// TODO Auto-generated method stub
		int n = session.update(NS+"deleteCodeset",passcode);
		return (n>0)? true : false;
	}

	@Override
	public boolean deleteIlji(String passcode) {
		// TODO Auto-generated method stub
		int n = session.update(NS+"deleteIlji",passcode);
		return (n>0)? true : false;
	}
	
	@Override
	public boolean checkPassDuplicate(String passcode) {
		// TODO Auto-generated method stub
		logger.info("checkPassDuplicate");
		logger.info("passcode {}", passcode);
		int isDup=session.selectOne(NS+"checkPassDuplicate",passcode);
		logger.info("isDup {}",isDup);
		return (isDup>0)? true : false;
	}
	
	@Override
	public List<PassDto> baseInfoPass() {
		List<PassDto> lists = session.selectList(NS+"baseInfoPass");
		return lists;
	}
	
}
