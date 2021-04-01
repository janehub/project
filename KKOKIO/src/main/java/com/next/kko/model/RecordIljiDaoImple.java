package com.next.kko.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.next.kko.dtos.RecordIljiDto;


@Repository
public class RecordIljiDaoImple implements IRecordIljiDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	
	private final String NS = "com.next.kko.ilji.";
	

	@Override
	public boolean dongDeathCntInput(RecordIljiDto idto) {
		int n = session.insert(NS+"dongDeathCntInput", idto);
		return (n>0)?true:false;
	}

	@Override
	public boolean dongDeathCntModify(RecordIljiDto idto) {
		int n = session.update(NS+"dongDeathCntModify", idto);
		return (n>0)?true:false;
	}

	@Override
	public boolean dongDeathCntDel(RecordIljiDto idto) {
		int n = session.update(NS+"dongDeathCntDel", idto);
		return (n>0)?true:false;
	}
	
	
	@Override
	public List<RecordIljiDto> dongDeathCntSelectI(Map<String, String> ilmap){
		return session.selectList(NS+"dongDeathCntSelectI", ilmap);
	}
	
	@Override
	public RecordIljiDto dongDeathCntSelectD(Map<String, String> ilmap) {
		return session.selectOne(NS+"dongDeathCntSelectD", ilmap);
	}

	
	
	@Override
	public boolean dongWeightInput(RecordIljiDto idto) {
		int n = session.insert(NS+"dongWeightInput", idto);
		return (n>0)?true:false;
	}

	@Override
	public boolean dongWeightModify(RecordIljiDto idto) {
		int n = session.update(NS+"dongWeightModify", idto);
		return (n>0)?true:false;
	}

	@Override
	public boolean dongWeightDel(RecordIljiDto idto) {
		int n = session.update(NS+"dongWeightDel", idto);
		return (n>0)?true:false;
	}
	
	@Override
	public List<RecordIljiDto> dongWeightSelectI(Map<String, String> ilmap){
		return session.selectList(NS+"dongWeightSelectI", ilmap);
	}
	
	@Override
	public RecordIljiDto dongWeightSelectD(Map<String, String> ilmap) {
		return session.selectOne(NS+"dongWeightSelectD", ilmap);
	}
	
	
	
	
	@Override
	public boolean dongWeatherComentInput(RecordIljiDto idto) {
		int n = session.insert(NS+"dongWeatherComentInput", idto);
		return (n>0)?true:false;
	}
	
	@Override
	public boolean dongWeatherComentModify(RecordIljiDto idto) {
		int n = session.update(NS+"dongWeatherComentModify", idto);
		return (n>0)?true:false;
	}
	
	@Override
	public boolean dongWeatherComentDel(RecordIljiDto idto) {
		int n = session.update(NS+"dongWeatherComentDel", idto);
		return (n>0)?true:false;
	}
	
	@Override
	public boolean dongEtcComentInput(RecordIljiDto idto) {
		int n = session.insert(NS+"dongEtcComentInput", idto);
		return (n>0)?true:false;
	}
	@Override
	public boolean dongEtcComentModify(RecordIljiDto idto) {
		int n = session.update(NS+"dongEtcComentModify", idto);
		return (n>0)?true:false;
	}
	@Override
	public boolean dongEtcComentDel(RecordIljiDto idto) {
		int n = session.update(NS+"dongEtcComentDel", idto);
		return (n>0)?true:false;
	}
	

	@Override
	public List<RecordIljiDto> distinctTemp(RecordIljiDto dto) {
		List<RecordIljiDto> lists = session.selectList(NS+"distinctTemp",dto);
		return lists;
	}

	@Override
	public boolean insertMedi(RecordIljiDto dto) {
		int n = session.insert(NS+"insertMedi",dto);
		return (n>0)?true:false;
	}

	@Override
	public boolean modifyMedi(RecordIljiDto dto) {
		int n = session.update(NS+"modifyMedi",dto);
		return (n>0)?true:false;
	}

	@Override
	public boolean deleteMedi(RecordIljiDto dto) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("recordilji_seq", dto.getRecordilji_seq());
		map.put("passcode", dto.getPasscode());
		int n = session.update(NS+"deleteMedi",dto);
		return (n>0)?true:false;
	}

	@Override
	public boolean insertTemp(RecordIljiDto dto) {
		int n = session.insert(NS+"insertTemp",dto);
		return (n>0)?true:false;
	}

	@Override
	public boolean modifyTemp(RecordIljiDto dto) {
		int n = session.update(NS+"modifyTemp",dto);
		return (n>0)?true:false;
	}

	@Override
	public boolean deleteTemp(RecordIljiDto dto) {
		Map<String, String>map = new HashMap<String, String>();
		map.put("recordilji_seq", dto.getRecordilji_seq());
		map.put("passcode", dto.getPasscode());
		int n = session.update(NS+"deleteTemp",dto);
		return (n>0)?true:false;
	}
	
	@Override
	public List<RecordIljiDto> illyungSelectAll(Map<String, String> ilmap){
		return session.selectList(NS+"illyungSelectAll", ilmap);
	}
	
}
