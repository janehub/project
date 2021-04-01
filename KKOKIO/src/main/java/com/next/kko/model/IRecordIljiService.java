package com.next.kko.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.next.kko.dtos.RecordIljiDto;

public interface IRecordIljiService {

	public List<RecordIljiDto> distinctTemp(RecordIljiDto dto);
	
	public  boolean insertMedi(RecordIljiDto dto);
	
	public boolean modifyMedi(RecordIljiDto dto);
	
	public boolean deleteMedi(RecordIljiDto dto);
	
	public boolean insertTemp(RecordIljiDto dto);
	
	public boolean modifyTemp(RecordIljiDto dto);
	
	public boolean deleteTemp(RecordIljiDto dto);

	public boolean dongDeathCntInput(RecordIljiDto idto);
	public boolean dongDeathCntModify(RecordIljiDto idto);
	public boolean dongDeathCntDel(RecordIljiDto idto);
	public List<RecordIljiDto> dongDeathCntSelectI(Map<String, String> ilmap);
	public RecordIljiDto dongDeathCntSelectD(Map<String, String> ilmap);
	
	public boolean dongWeightInput(RecordIljiDto idto);
	public boolean dongWeightModify(RecordIljiDto idto);
	public boolean dongWeightDel(RecordIljiDto idto);
	public List<RecordIljiDto> dongWeightSelectI(Map<String, String> ilmap);
	public RecordIljiDto dongWeightSelectD(Map<String, String> ilmap);	

	public boolean dongWeatherComentInput(RecordIljiDto idto);
	public boolean dongWeatherComentModify(RecordIljiDto idto);
	public boolean dongWeatherComentDel(RecordIljiDto idto);
	
	
	public boolean dongEtcComentInput(RecordIljiDto idto);
	public boolean dongEtcComentModify(RecordIljiDto idto);
	public boolean dongEtcComentDel(RecordIljiDto idto);
	
	
	public List<RecordIljiDto> illyungSelectAll(Map<String, String> ilmap);
	
	
	public boolean modifyRecordIlji(List<RecordIljiDto> deathList,
									List<RecordIljiDto> weightList,
									List<RecordIljiDto> tempList,
									List<RecordIljiDto> etcList,
									List<RecordIljiDto> medicineList,
									List<RecordIljiDto> i_deathList,
									List<RecordIljiDto> i_weightList);
}
