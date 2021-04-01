package com.next.kko.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.kko.dtos.RecordIljiDto;
@Service
public class RecordIljiServiceImple implements IRecordIljiService{

	@Autowired
	private IRecordIljiDao dao;

	
	@Override
	public List<RecordIljiDto> distinctTemp(RecordIljiDto dto){
		return dao.distinctTemp(dto);
	}

	@Override
	public boolean insertMedi(RecordIljiDto dto) {
		return dao.insertMedi(dto);
	}

	@Override
	public boolean modifyMedi(RecordIljiDto dto) {
		return dao.modifyMedi(dto);
	}

	@Override
	public boolean deleteMedi(RecordIljiDto dto) {
		return dao.deleteMedi(dto);
	}

	@Override
	public boolean insertTemp(RecordIljiDto dto) {
		return dao.insertTemp(dto);
	}

	@Override
	public boolean modifyTemp(RecordIljiDto dto) {
		return dao.modifyTemp(dto);
	}

	@Override
	public boolean deleteTemp(RecordIljiDto dto) {
		return dao.deleteTemp(dto);
	}
	

	@Override
	public boolean dongDeathCntInput(RecordIljiDto idto) {
		// TODO Auto-generated method stub
		return dao.dongDeathCntInput(idto);
	}

	@Override
	public boolean dongDeathCntModify(RecordIljiDto idto) {
		// TODO Auto-generated method stub
		return dao.dongDeathCntModify(idto);
	}

	@Override
	public boolean dongDeathCntDel(RecordIljiDto idto) {
		// TODO Auto-generated method stub
		return dao.dongDeathCntDel(idto);
	}

	@Override
	public List<RecordIljiDto> dongDeathCntSelectI(Map<String, String> ilmap){
		
		return dao.dongDeathCntSelectI(ilmap);
	}
	
	@Override
	public RecordIljiDto dongDeathCntSelectD(Map<String, String> ilmap) {
		return dao.dongDeathCntSelectD(ilmap);
		
	}
	
	
	
	@Override
	public boolean dongWeightInput(RecordIljiDto idto) {
		// TODO Auto-generated method stub
		return dao.dongWeightInput(idto);
	}

	@Override
	public boolean dongWeightModify(RecordIljiDto idto) {
		// TODO Auto-generated method stub
		return dao.dongWeightModify(idto);
	}

	@Override
	public boolean dongWeightDel(RecordIljiDto idto) {
		// TODO Auto-generated method stub
		return dao.dongWeightDel(idto);
	}
	
	@Override
	public List<RecordIljiDto> dongWeightSelectI(Map<String, String> ilmap){
		
		return dao.dongWeightSelectI(ilmap);
	}
	
	@Override
	public RecordIljiDto dongWeightSelectD(Map<String, String> ilmap) {
		
		return dao.dongWeightSelectD(ilmap);
	}
	
	
	
	@Override
	public boolean dongWeatherComentInput(RecordIljiDto idto) {
		return dao.dongWeatherComentInput(idto);
	}
	
	@Override
	public boolean dongWeatherComentModify(RecordIljiDto idto) {
		return dao.dongWeatherComentModify(idto);
	}
	
	@Override
	public boolean dongWeatherComentDel(RecordIljiDto idto) {
		return dao.dongWeatherComentDel(idto);
	}
	
	
	@Override
	public boolean dongEtcComentInput(RecordIljiDto idto) {
		return dao.dongEtcComentInput(idto);
	}
	
	@Override
	public boolean dongEtcComentModify(RecordIljiDto idto) {
		return dao.dongEtcComentModify(idto);
	}
	
	@Override
	public boolean dongEtcComentDel(RecordIljiDto idto) {
		return dao.dongEtcComentDel(idto);
	}
	
	@Override
	public List<RecordIljiDto> illyungSelectAll(Map<String, String> ilmap){
		return dao.illyungSelectAll(ilmap);
	}

	@Override
	public boolean modifyRecordIlji(List<RecordIljiDto> deathList, List<RecordIljiDto> weightList,
			List<RecordIljiDto> tempList, List<RecordIljiDto> etcList, List<RecordIljiDto> medicineList,
			List<RecordIljiDto> i_deathList,List<RecordIljiDto> i_weightList) {
		//update
		System.out.println("modifyRecordIlji");
		System.out.println(deathList);
		System.out.println(weightList);
		System.out.println(tempList);
		System.out.println(etcList);
		System.out.println(medicineList);
		//insert
		System.out.println(i_deathList);
		System.out.println(i_weightList);
		
		boolean isc=true;
		
		//폐사수 수정
		for(int i=0;i<deathList.size();i++) {
			System.out.println(deathList.get(i));
			isc=dao.dongDeathCntModify(deathList.get(i));
		}
		//중량 수정
		for(int i=0;i<weightList.size();i++) {
			System.out.println(weightList.get(i));
			isc=dao.dongWeightModify(weightList.get(i));
		}
		
		//사육장 온도 수정
		for(int i=0;i<tempList.size();i++) {
			System.out.println(tempList.get(i));
			isc=dao.modifyTemp(tempList.get(i));
		}	
		
		//비고 수정
		for(int i=0;i<etcList.size();i++) {
			System.out.println(etcList.get(i));
			isc=dao.dongEtcComentModify(etcList.get(i));
		}		
		
		//약품 수정
		for(int i=0;i<medicineList.size();i++) {
			System.out.println(medicineList.get(i));
			isc=dao.modifyMedi(medicineList.get(i));
		}
		
		//폐사수 입력
		for(int i=0;i<i_deathList.size();i++) {
			System.out.println(i_deathList.get(i));
			isc=dao.dongDeathCntInput(i_deathList.get(i));
		}
		//중량 입력
		for(int i=0;i<i_weightList.size();i++) {
			System.out.println(i_weightList.get(i));
			isc=dao.dongWeightInput(i_weightList.get(i));
		}
		
		return isc;
	}
}
