package com.next.kko.model;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.next.kko.util.KkokioUtil;
import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.RecordIljiDto;

@Service
public class PassServiceImple implements IPassService{
	
	private final Logger logger = LoggerFactory.getLogger(PassServiceImple.class);
	
	@Autowired
	private IPassDao dao;

	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	@Transactional
	public boolean makePass(PassDto dto) throws ParseException {
		boolean isc =dao.makePass(dto);
		System.out.println(isc);
		
		dto.setPasscode(dto.getId()+dto.getStartpass());
		
		isc =dao.codeset(dto);
		System.out.println(isc);

		return isc;
		
	}

	@Override
	@Transactional
	public boolean modifyPass(PassDto dto) throws ParseException {
		boolean isc = false;
		
		//만약 수정할 파스 종료일이 원래의 파스 종료일보다 크다면 코드화를 늘려줘야한다. 첫째날 포함하므로 +1
		int term = KkokioUtil.getPeriod(dto.getStartpass(),dto.getEndpass())+1;
		System.out.println("term: "+term);
			
		int maxCodset = session.selectOne("com.next.kko.ilji.getMaxCodset",dto.getPasscode());
		logger.info("maxCodset {}",maxCodset);
		
		//만약 수정할 파스 종료일이 원래의 파스 종료일보다 크다면 코드화를 늘려줘야한다. 
		if(term>maxCodset) {
			isc = dao.codesetForModify(dto.getPasscode(),term,maxCodset);
		}
		
		isc=dao.modifyPass(dto);
		return isc;
	}

	@Override
	public PassDto infoDetailPass(String passcode,String id){
		return dao.infoDetailPass(passcode,id);
	}
	
	@Override
	public List<RecordIljiDto> infoMainIlji(String passcode, String dongnumber) {
		// TODO Auto-generated method stub
		return dao.infoMainIlji(passcode,dongnumber);
	}
	
	@Override
	public List<PassDto> infoPassList(String id) {
		// TODO Auto-generated method stub
		return dao.infoPassList(id);
	}

	@Override
	@Transactional
	public boolean deletePass(Map<String, String> map) {
		boolean isc = false;
		
		isc=dao.deletePass(map);
		isc=dao.deleteCodeset(map.get("passcode"));
		isc=dao.deleteIlji(map.get("passcode"));
		
		return isc;
	}

	@Override
	public boolean checkPassDuplicate(String passcode) {
		// TODO Auto-generated method stub
		return dao.checkPassDuplicate(passcode);
	}
	
	@Override
	public List<PassDto> baseInfoPass() {
		return dao.baseInfoPass();
	}

}
