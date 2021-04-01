package com.next.kko.model;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressCodeDaoImple implements IAddressCodeDao{

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS="com.next.kko.addresscode.";
	
	@Override
	public boolean insertAddressCode(Map<String, String> map) {
		// TODO Auto-generated method stub
		System.out.println("insertAddressCode");
		int n=session.insert(NS+"insertAddressCode",map);
		
		return n>0?true:false;
	}

	@Override
	public boolean updateAddressCode(Map<String, String> map) {
		// TODO Auto-generated method stub
		System.out.println("updateAddressCode");
		int n=session.update(NS+"updateAddressCode", map);
		
		return n>0?true:false;
	}

	@Override
	public String getAddressCode(String id) {
		// TODO Auto-generated method stub
		System.out.println("getAddressCode");
		String result=session.selectOne(NS+"getAddressCode", id);
		
		System.out.println("result: "+result);
		
		return result;
	}

}
