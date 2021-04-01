package com.next.kko.model;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.RecordIljiDto;

@Service
public class ExcelServiceImple implements IExcelService{

	@Autowired
	private IExcelDao dao;

	
	@Override
	@Transactional
	public boolean insertExcel(PassDto pdto, RecordIljiDto[] rdtos) throws ParseException {
		//파스코드 설정
		String passcode=pdto.getId()+pdto.getStartpass();
		pdto.setPasscode(passcode);
		
		//파스 정보 입력
		boolean isc =dao.insertExcelPass(pdto);
		System.out.println(isc);
		
		//코드화 입력
		isc =dao.codeset(pdto);
		System.out.println(isc);

		//육계일지 입력
		isc = dao.insertExcelIlji(rdtos);
		System.out.println(isc);
		
		return isc;
	}
	

	@Override
	public PassDto getExcelPass(String passcode) {
		// TODO Auto-generated method stub
		return dao.getExcelPass(passcode);
	}

	@Override
	public List<RecordIljiDto> getExcelIlji(String passcode) {
		// TODO Auto-generated method stub
		return dao.getExcelIlji(passcode);
	}

}
