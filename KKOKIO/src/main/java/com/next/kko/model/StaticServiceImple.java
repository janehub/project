package com.next.kko.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.RecordIljiDto;


@Service
public class StaticServiceImple implements IStaticService {
	
	@Autowired
	private IStaticDao dao;

	@Override
	public PassDto dongChickNumAll(String passcode) {
		// TODO Auto-generated method stub
		return dao.dongChickNumAll(passcode);
	}

	@Override
	public List<RecordIljiDto> dongDeathCntAll(Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.dongDeathCntAll(map);
	}

	@Override
	public List<RecordIljiDto> getDeathCountStatic(String passcode) {
		// TODO Auto-generated method stub
		return dao.getDeathCountStatic(passcode);
	}

}
