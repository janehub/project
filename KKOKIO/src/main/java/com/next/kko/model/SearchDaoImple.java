package com.next.kko.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.next.kko.dtos.RecordIljiDto;

@Repository
public class SearchDaoImple implements ISearchDao{
	
	private final String NS = "com.next.kko.search.";
	private final Logger logger = LoggerFactory.getLogger(SearchDaoImple.class);
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<RecordIljiDto> searchIlji(Map<String, Object> map) {
		// TODO Auto-generated method stub
		logger.info("searchIlji");
		logger.info("map: {}",map);
		
		return session.selectList(NS+"searchIlji", map);
	}
	
	@Override
	public List<RecordIljiDto> getAllIlji(String idpassym) {
		// TODO Auto-generated method stub
		return session.selectList(NS+"getAllIlji",idpassym);
	}

}
