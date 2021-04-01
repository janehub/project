package com.next.kko.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.next.kko.dtos.AccountDto;
import com.next.kko.dtos.RecordIljiDto;

public interface IAccountDao {

	public boolean idDuplicateCheck(String id);
	public boolean registUser(AccountDto dto);
	public AccountDto loginUser(Map<String, String> map);
	public AccountDto searchUser(String id);
	public boolean modifyUser(AccountDto dto);
	public String findUserId(Map<String, String> map);
	public boolean dropUser(String id);
	public List<RecordIljiDto> passSearch(String passcode);
	public List<RecordIljiDto> yearSearch(String year);
	public List<RecordIljiDto> monthSearch(String month);
	public List<RecordIljiDto> deathcountSearch(Map<String, String> map);
	public List<RecordIljiDto> weightSearch(String passcode);
	public List<RecordIljiDto> keywordSearch(Map<String, String> map);
	public List<RecordIljiDto> dongDeathCount(Map<String, String> map);
	public List<RecordIljiDto> dongMedicine(Map<String, String> map);
	public List<RecordIljiDto> dongEtc(Map<String, String> map);
	public List<RecordIljiDto> modifyIlji(Map<String, String> map);
	
	
	
	
}
