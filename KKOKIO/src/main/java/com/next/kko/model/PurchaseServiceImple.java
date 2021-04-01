package com.next.kko.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.kko.dtos.ProductDto;
import com.next.kko.dtos.PurchaseDto;


@Service
public class PurchaseServiceImple implements IPurchaseService {

	@Autowired
	private IPurchaseDao dao;
	
	@Override
	public boolean purchanseInfInput(PurchaseDto pdto) {
		// TODO Auto-generated method stub
		return dao.purchanseInfInput(pdto);
	}

	@Override
	public boolean purchanseInfModify(PurchaseDto pdto) {
		// TODO Auto-generated method stub
		return dao.purchanseInfModify(pdto);
	}

	@Override
	public boolean purchanseInfDel(String pcode) {
		// TODO Auto-generated method stub
		return dao.purchanseInfDel(pcode);
	}

	@Override
	public List<ProductDto> purchanseInfSelectAll(String passcode) {
		// TODO Auto-generated method stub
		return dao.purchanseInfSelectAll(passcode);
	}

	@Override
	public ProductDto purchanseInfSelectOne(Map<String, String> pmap) {
		// TODO Auto-generated method stub
		return dao.purchanseInfSelectOne(pmap);
	}

}
