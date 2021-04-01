package com.next.kko.model;

import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.kko.dtos.AccountDto;
import com.next.kko.dtos.RecordIljiDto;

@Service
public class AccountServiceImple implements IAccountService{
	
	@Autowired
	private IAccountDao dao;

	private final Logger logger = LoggerFactory.getLogger(AccountServiceImple.class);

	public boolean idDuplicateCheck(String id) {
		logger.info("Account_Service: idDuplicate");
		return dao.idDuplicateCheck(id);
	}

	@Override
	public boolean registUser(AccountDto dto) {
		logger.info("Account_service : registUser");
		
		return dao.registUser(dto);
	}

	@Override
	public AccountDto loginUser(Map<String, String> map) {
		logger.info("Account_Service: loginUser");
		
		return dao.loginUser(map);
	}

	@Override
	public AccountDto searchUser(String id) {
		logger.info("Account_Service: searchUser");
		
		return dao.searchUser(id);
	}

	@Override
	public boolean modifyUser(AccountDto dto) {
		logger.info("Account_service: modifyUser");
			
		return dao.modifyUser(dto);
	}

	@Override
	public String findUserId(Map<String, String> map) {
		logger.info("Account_service: findUserId");
		
		return dao.findUserId(map);
	}

	@Override
	public boolean dropUser(String id) {
		logger.info("Account_service: dropUser");
		
		return dao.dropUser(id);
	}

	@Override
	public List<RecordIljiDto> passSearch(String passcode) {
		logger.info("RecordIlji_service: passSearch");
		
		
		return (List<RecordIljiDto>) dao.passSearch(passcode);
	}

	@Override
	public List<RecordIljiDto> yearSearch(String year) {
		logger.info("RecordIlji_service: yearSearch");
		
		return (List<RecordIljiDto>) dao.yearSearch(year);
	}

	@Override
	public List<RecordIljiDto> monthSearch(String month) {
		logger.info("RecordIlji_service: monthSearch");
		
		return (List<RecordIljiDto>) dao.monthSearch(month);
	}

	@Override
	public List<RecordIljiDto> deathcountSearch(Map<String, String> map) {
		logger.info("RecordIlji_service: deathcountSearch");
		
		return (List<RecordIljiDto>) dao.deathcountSearch(map);
	}

	@Override
	public List<RecordIljiDto> weightSearch(String passcode) {
		logger.info("RecordIlji_service: weightSearch");
		
		return (List<RecordIljiDto>) dao.weightSearch(passcode);
	}

	@Override
	public List<RecordIljiDto> keywordSearch(Map<String, String> map) {
		logger.info("RecordIlji_service: wordSearch");
		
		return (List<RecordIljiDto>)dao.keywordSearch(map);
	}

	@Override
	public List<RecordIljiDto> dongDeathCount(Map<String, String> map) {
		logger.info("RecordIlji_service: dongDeathCount");
		return (List<RecordIljiDto>) dao.dongDeathCount(map);
	}

	@Override
	public List<RecordIljiDto> dongMedicine(Map<String, String> map) {
		logger.info("RecordIlji_service: dongMedicine");
		return (List<RecordIljiDto>) dao.dongMedicine(map);
	}

	@Override
	public List<RecordIljiDto> dongEtc(Map<String, String> map) {
		logger.info("RecordIlji_service: dongEtc");
		return (List<RecordIljiDto>) dao.dongEtc(map);
	}

	@Override
	public List<RecordIljiDto> modifyIlji(Map<String, String> map) {
		logger.info("RecordIlji_service: modifyIlji");
		
		return (List<RecordIljiDto>)dao.modifyIlji(map);
	}



}
