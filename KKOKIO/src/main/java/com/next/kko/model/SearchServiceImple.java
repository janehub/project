package com.next.kko.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.kko.dtos.RecordIljiDto;

@Service
public class SearchServiceImple implements ISearchService{

	@Autowired
	ISearchDao dao;
	
	@Override
	public List<RecordIljiDto> searchIlji(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.searchIlji(map);
	}
	
	@Override
	public List<RecordIljiDto> getAllIlji(String idpassym) {
		// TODO Auto-generated method stub
		return dao.getAllIlji(idpassym);
	}


	
}
