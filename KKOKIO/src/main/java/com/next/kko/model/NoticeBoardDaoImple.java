package com.next.kko.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.next.kko.dtos.NoticeBoardDto;

@Repository
public class NoticeBoardDaoImple implements INoticeBoardDao{
	
	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS="com.next.kko.board.";

	@Override
	public boolean insertBoard(NoticeBoardDto ndto) {
		// TODO Auto-generated method stub
		int n=session.insert(NS+"insertBoard", ndto);
		return n>0?true:false;
	}

	@Override
	public List<NoticeBoardDto> getAllboard() {
		// TODO Auto-generated method stub
		
		return session.selectList(NS+"getAllboard");
	}
	
	@Override
	public NoticeBoardDto getOneBoard(String noticecode_seq) {
		// TODO Auto-generated method stub
		return (NoticeBoardDto)session.selectOne(NS+"getOneBoard",noticecode_seq);
	}

	@Override
	public boolean deleteOneBoard(String seq) {
		// TODO Auto-generated method stub
		
		int n=session.update(NS+"deleteOneBoard", seq);
		return n>0?true:false;
	}

	@Override
	public boolean modifyBoard(NoticeBoardDto ndto) {
		// TODO Auto-generated method stub
		int n=session.update(NS+"modifyBoard", ndto);
		return n>0?true:false;
	}

	@Override
	public List<NoticeBoardDto> getAdminboard() {
		
		return session.selectList(NS+"getAdminboard");
	}

}
