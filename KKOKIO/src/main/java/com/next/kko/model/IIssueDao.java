package com.next.kko.model;

import java.util.List;

import com.next.kko.dtos.IssueDto;

public interface IIssueDao {

	/**
	 * <h1>이슈사항 등록</h1>
	 * <p>설명 : IssueDto 객체를 입력받아서 성공하면 이슈사항 글을 등록한다.</p>
	 * @author 박지연
	 * @since 2018.11.15
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param IssueDto
	 * @return true 성공
	 */
	public boolean insertIssue(IssueDto idto);
	
	/**
	 * <h1>이슈사항 수정</h1>
	 * <p>설명 : IssueDto 객체를 입력받아 이슈사항 글을 수정한다.</p>
	 * @author 박지연
	 * @since 2018.11.15
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param IssueDto
	 * @return true 성공
	 */
	public boolean modifyIssue(IssueDto idto);
	/**
	 * <h1>이슈사항 삭제</h1>
	 * <p>설명 : contentcode을 입력받아 이슈사항 글을 삭제한다.</p>
	 * @author 박지연
	 * @since 2018.11.15
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String
	 * @return true 성공
	 */
	public boolean deleteIssue(String contentcode);	
	/**
	 * <h1>조치사항 등록 - 이전 글 update</h1>
	 * <p>설명 :CONTENTCODE를 입력받아 같은 이슈사항을 상위로 하는 조치사항들의 
	 * ACTIONCODE를 하나씩 증가시킨다.</p>
	 * @author 박지연
	 * @since 2018.11.15
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String
	 * @return true 성공
	 */
	public boolean InsertActionU(String contentcode);
	
	/**
	 * <h1>조치사항 등록 - 실제 글 등록 insert</h1>
	 * <p>설명 : IssueDto 객체를 입력받아 조치사항을 등록한다.</p>
	 * @author 박지연
	 * @since 2018.11.15
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param IssueDto
	 * @return true 성공
	 */
	public boolean InsertActionI(IssueDto idto);
	/**
	 * <h1>조치사항 수정</h1>
	 * <p>설명 : IssueDto 객체를 입력받아서 조치사항을 수정한다.</p>
	 * @author 박지연
	 * @since 2018.11.15
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param IssueDto
	 * @return true 성공
	 */
	public boolean modifyAction(IssueDto idto);
	/**
	 * <h1>조치사항 삭제</h1>
	 * <p>설명 : IssueDto 객체를 입력받아서 조치사항을 수정한다.</p>
	 * @author 박지연
	 * @since 2018.11.15
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String
	 * @return true 성공
	 */
	public boolean deleteAction(String issuecode);

	/**
	 * <h1>이슈사항 / 조치사항 전체조회</h1>
	 * <p>설명 : 이슈사항 / 조치사항을 전체조회한다.</p>
	 * @author 박지연
	 * @since 2018.11.15
	 * @version 1.0
	 * @package com.next.kko.model
	 * @return true 성공
	 */
	public List<IssueDto> getAllissue(String passcode);
	/**
	 * <h1>이이슈사항 /조치사항 상세조회</h1>
	 * <p>설명 : 이슈사항 / 조치사항을 상세 조회한다.</p>
	 * @author 박지연
	 * @since 2018.11.15
	 * @version 1.0
	 * @package com.next.kko.model
	 * @return true 성공
	 */
	public IssueDto getOneIssue(String issuecode);

	/**
	 * <h1>이슈사항/조치사항 결과 수정</h1>
	 * <p>설명 : 입력받은 contentcode와 같은 contentcode를 가지고 있는 모돈 글들의 ACTIONRESULT를 'Y'로 수정한다.</p>
	 * @author 박지연
	 * @since 2018.11.15
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String
	 * @return true 성공
	 */
	public boolean modifyActionResult(String contentcode);
}
