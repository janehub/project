package com.next.kko.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.next.kko.dtos.ProductDto;


@Repository
public class ProductDaoImple implements IProductDao{
	
	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.next.kko.product.";
	

	@Override
	public boolean goodsInfInput(ProductDto gdto) {
		int n = session.insert(NS+"goodsInfInput", gdto);
		return (n>0)?true:false;
	}

	@Override
	public boolean goodsInfModify(ProductDto gdto) {
		int n = session.update(NS+"goodsInfModify", gdto);
		return (n>0)?true:false;
	}

	@Override
	public boolean goodsInfDel(String pcode) {
		int n = session.update(NS+"goodsInfDel", pcode);
		return (n>0)?true:false;
	}

	@Override
	public List<ProductDto> goodsInfSelectAll() {
		
		return session.selectList(NS+"goodsInfSelectAll");
	}

	@Override
	public ProductDto goodsInfSelectOne(String pcode) {
		
		return session.selectOne(NS+"goodsInfSelectOne", pcode);
	}

}
