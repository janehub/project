package com.next.kko.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.RecordIljiDto;

@Repository
public class StaticDaoImple implements IStaticDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.next.kko.static.";

	@Override
	public PassDto dongChickNumAll(String passcode) {
		
		return session.selectOne(NS+"dongChickNumAll", passcode);
	}

	@Override
	public List<RecordIljiDto> dongDeathCntAll(Map<String, String> map) {
		
		return session.selectList(NS+"dongDeathCntAll", map);
	}

	@Override
	public List<RecordIljiDto> getDeathCountStatic(String passcode) {
		// TODO Auto-generated method stub
		return session.selectList(NS+"getDeathCountStatic", passcode);
	}

}
