package com.next.kko.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.next.kko.dtos.IssueDto;

@Repository
public class IssueDaoImple implements IIssueDao{

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS="com.next.kko.issue.";
	
	@Override
	public boolean insertIssue(IssueDto idto) {
		// TODO Auto-generated method stub
		
		int n=session.insert(NS+"insertIssue", idto);
		return n>0?true:false;
	}

	@Override
	public boolean modifyIssue(IssueDto idto) {
		// TODO Auto-generated method stub
		int n=session.update(NS+"modifyIssue", idto);
		return n>0?true:false;
	}

	@Override
	public boolean InsertActionU(String contentcode) {
		// TODO Auto-generated method stub
		int n=session.update(NS+"InsertActionU", contentcode);
		return n>0?true:false;
	}

	@Override
	public boolean InsertActionI(IssueDto idto) {
		// TODO Auto-generated method stub
		int n=session.insert(NS+"InsertActionI", idto);
		return n>0?true:false;
	}

	@Override
	public boolean modifyAction(IssueDto idto) {
		// TODO Auto-generated method stub
		int n=session.update(NS+"modifyAction", idto);
		return n>0?true:false;
	}

	@Override
	public boolean deleteAction(String issuecode) {
		// TODO Auto-generated method stub
		int n=session.delete(NS+"deleteAction", issuecode);
		return n>0?true:false;
	}

	@Override
	public boolean modifyActionResult(String contentcode) {
		// TODO Auto-generated method stub
		int n=session.update(NS+"modifyActionResult", contentcode);
		return n>0?true:false;
	}

	@Override
	public boolean deleteIssue(String contentcode) {
		// TODO Auto-generated method stub
		int n=session.delete(NS+"deleteIssue", contentcode);
		return n>0?true:false;
	}

	@Override
	public List<IssueDto> getAllissue(String passcode) {
		// TODO Auto-generated method stub
		return session.selectList(NS+"getAllissue",passcode);
	}

	@Override
	public IssueDto getOneIssue(String issuecode) {
		// TODO Auto-generated method stub
		return session.selectOne(NS+"getOneIssue", issuecode);
	}

}
