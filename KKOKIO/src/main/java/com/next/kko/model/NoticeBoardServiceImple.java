package com.next.kko.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.kko.dtos.NoticeBoardDto;

@Service
public class NoticeBoardServiceImple implements INoticeBoardService{

	@Autowired
	private INoticeBoardDao dao;
	
	private final Logger logger = LoggerFactory.getLogger(NoticeBoardServiceImple.class);
	
	@Override
	public boolean insertBoard(NoticeBoardDto ndto) {
		// TODO Auto-generated method stub
		return dao.insertBoard(ndto);
	}

	@Override
	public List<NoticeBoardDto> getAllboard() {
		// TODO Auto-generated method stub
		return dao.getAllboard();
	}
	
	@Override
	public NoticeBoardDto getOneBoard(String noticecode_seq) {
		// TODO Auto-generated method stub
		return dao.getOneBoard(noticecode_seq);
	}

	@Override
	public boolean deleteOneBoard(String seq) {
		// TODO Auto-generated method stub
		return dao.deleteOneBoard(seq);
	}

	@Override
	public boolean modifyBoard(NoticeBoardDto ndto) {
		// TODO Auto-generated method stub
		return dao.modifyBoard(ndto);
	}

	@Override
	public List<NoticeBoardDto> getAdminboard() {
		return dao.getAdminboard();
	}


}
