package com.next.kko.model;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressCodeServiceImple implements IAddressCodeService{
	
	@Autowired
	private IAddressCodeDao dao;
	
	@Override
	public boolean insertAddressCode(Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.insertAddressCode(map);
	}

	@Override
	public boolean updateAddressCode(Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.updateAddressCode(map);
	}

	@Override
	public String getAddressCode(String id) {
		// TODO Auto-generated method stub
		return dao.getAddressCode(id);
	}

}
