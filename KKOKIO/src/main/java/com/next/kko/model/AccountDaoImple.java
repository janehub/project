package com.next.kko.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.next.kko.dtos.AccountDto;
import com.next.kko.dtos.RecordIljiDto;

@Repository
public class AccountDaoImple implements IAccountDao {

	private final String NS = "com.next.kko.account.";
	private final Logger logger = LoggerFactory.getLogger(AccountDaoImple.class);
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public boolean idDuplicateCheck(String id) {
		logger.info("Account: idDuplicateCheck");
		int n = session.selectOne(NS+"idduplicate", id);
		return n==0?true:false;
	}

	@Override
	public boolean registUser(AccountDto dto) {
		logger.info("Account_Service: registerUser");
		
		int n = session.insert(NS+"userregister", dto);
		
		return n>0?true:false;
	}

	@Override
	public AccountDto loginUser(Map<String, String> map) {
		logger.info("Account: loginUser");
		
		return session.selectOne(NS+"loginuser", map);
	}

	@Override
	public AccountDto searchUser(String id) {
		logger.info("Account: searchUser");
		
		return session.selectOne(NS+"usersearch", id);
	}

	@Override
	public boolean modifyUser(AccountDto dto) {
		int n = session.update(NS+"userupdate", dto);
		
		return n > 0 ? true : false;
	}

	@Override
	public String findUserId(Map<String, String> map) {
		logger.info("Account: findUserId");
		
		return session.selectOne(NS+"userfindid", map);
	}

	@Override
	public boolean dropUser(String id) {
		logger.info("Account: dropUser");
		
		int n = session.update(NS+"userdrop", id);
		
		return n > 0 ? true : false;
	}

	@Override
	public List<RecordIljiDto> passSearch(String passcode) {
		logger.info("Account: passSearch");
		
		return session.selectList(NS+"searchbypass", passcode);
	}

	@Override
	public List<RecordIljiDto> yearSearch(String year) {
		logger.info("RecordIlji: yearSearch");
		
		return session.selectList(NS+"searchbyyear", year);
	}

	@Override
	public List<RecordIljiDto> monthSearch(String month) {
		logger.info("RecordIlji: monthSearch");
		
		return session.selectList(NS+"searchbymonth", month);
	}

	@Override
	public List<RecordIljiDto> deathcountSearch(Map<String, String> map) {
		logger.info("RecordIlji: deathcountSearch");
		
		return session.selectList(NS+"searchdeathcount", map);
	}

	@Override
	public List<RecordIljiDto> weightSearch(String passcode) {
		logger.info("RecordIlji: weightSearch");
		
		return session.selectList(NS+"searchweight", passcode);
	}

	@Override
	public List<RecordIljiDto> keywordSearch(Map<String, String> map) {
		logger.info("RecordIlji: wordSearch");
		
		return session.selectList(NS+"keywordsearch", map);
	}

	@Override
	public List<RecordIljiDto> dongDeathCount(Map<String, String> map) {
		logger.info("RecordIlji: dongDeathCount");
		return session.selectList(NS+"dongdeathcount", map);
	}

	@Override
	public List<RecordIljiDto> dongMedicine(Map<String, String> map) {
		logger.info("RecordIlji: dongMedicine");
		return session.selectList(NS+"dongmedicine", map);
	}

	@Override
	public List<RecordIljiDto> dongEtc(Map<String, String> map) {
		logger.info("RecordIlji: dongEtc");
		return session.selectList(NS+"dongetc", map);
	}

	public List<RecordIljiDto> modifyIlji(Map<String, String> map) {
		logger.info("RecordIlji: modifyIlji");
		
		return session.selectList(NS+"modifyilji", map);
	}

	
}