package com.next.kko.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.kko.dtos.ProductDto;


@Service
public class ProductServiceImple implements IProductService {
	
	@Autowired
	private IProductDao dao;

	@Override
	public boolean goodsInfInput(ProductDto gdto) {
		// TODO Auto-generated method stub
		return dao.goodsInfInput(gdto);
	}

	@Override
	public boolean goodsInfModify(ProductDto gdto) {
		// TODO Auto-generated method stub
		return dao.goodsInfModify(gdto);
	}

	@Override
	public boolean goodsInfDel(String pcode) {
		// TODO Auto-generated method stub
		return dao.goodsInfDel(pcode);
	}

	@Override
	public List<ProductDto> goodsInfSelectAll() {
		// TODO Auto-generated method stub
		return dao.goodsInfSelectAll();
	}

	@Override
	public ProductDto goodsInfSelectOne(String pcode) {
		// TODO Auto-generated method stub
		return dao.goodsInfSelectOne(pcode);
	}

}
