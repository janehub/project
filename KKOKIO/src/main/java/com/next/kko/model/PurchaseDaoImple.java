package com.next.kko.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.next.kko.dtos.ProductDto;
import com.next.kko.dtos.PurchaseDto;


@Repository
public class PurchaseDaoImple implements IPurchaseDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.next.kko.purchase.";

	@Override
	public boolean purchanseInfInput(PurchaseDto pdto) {
		int n = session.insert(NS+"purchanseInfInput", pdto);
		return (n>0)?true:false;
	}

	@Override
	public boolean purchanseInfModify(PurchaseDto pdto) {
		int n = session.update(NS+"purchanseInfModify", pdto);
		return (n>0)?true:false;
	}

	@Override
	public boolean purchanseInfDel(String pcode) {
		int n = session.update(NS+"purchanseInfDel", pcode);
		return (n>0)?true:false;
	}

	@Override
	public List<ProductDto> purchanseInfSelectAll(String passcode) {
		
		return session.selectList(NS+"purchanseInfSelectAll", passcode);
	}

	@Override
	public ProductDto purchanseInfSelectOne(Map<String, String> pmap) {
		
		return session.selectOne(NS+"purchanseInfSelectOne", pmap);
	}

}
