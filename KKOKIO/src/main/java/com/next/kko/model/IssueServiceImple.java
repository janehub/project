package com.next.kko.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.next.kko.dtos.IssueDto;

@Service
public class IssueServiceImple implements IIssueService{

	@Autowired
	IIssueDao dao = new IssueDaoImple();
	
	@Override
	public boolean insertIssue(IssueDto idto) {
		// TODO Auto-generated method stub
		return dao.insertIssue(idto);
	}

	@Override
	public boolean modifyIssue(IssueDto idto) {
		// TODO Auto-generated method stub
		return dao.modifyIssue(idto);
	}

	@Override
	@Transactional
	public boolean InsertAction(IssueDto idto) {
		// TODO Auto-generated method stub
		boolean isc = false;
		isc = dao.InsertActionU(idto.getContentcode());
		isc = dao.InsertActionI(idto);
		
		return isc;
	}

	@Override
	public boolean modifyAction(IssueDto idto) {
		// TODO Auto-generated method stub
		return dao.modifyAction(idto);
	}

	@Override
	public boolean deleteAction(String issuecode) {
		// TODO Auto-generated method stub
		return dao.deleteAction(issuecode);
	}

	@Override
	public boolean modifyActionResult(String contentcode) {
		// TODO Auto-generated method stub
		return dao.modifyActionResult(contentcode);
	}

	@Override
	public boolean deleteIssue(String contentcode) {
		// TODO Auto-generated method stub
		return dao.deleteIssue(contentcode);
	}

	@Override
	public List<IssueDto> getAllissue(String passcode) {
		// TODO Auto-generated method stub
		return dao.getAllissue(passcode);
	}

	@Override
	public IssueDto getOneIssue(String issuecode) {
		// TODO Auto-generated method stub
		return dao.getOneIssue(issuecode);
	}

}
