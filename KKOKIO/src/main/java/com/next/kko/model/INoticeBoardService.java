package com.next.kko.model;

import java.util.List;

import com.next.kko.dtos.NoticeBoardDto;

public interface INoticeBoardService {

	/**
	 * <h1>공지사항 글 등록</h1>
	 * <p>설명 : NoticeBoardDto 객체를 입력받아서 성공하면 true를 반환하고
	 * 실패하면 false를 반환한다.</p>
	 * @author 박지연
	 * @since 2018.11.07
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param ndto
	 * @return true 성공
	 */
	public boolean insertBoard(NoticeBoardDto ndto);
	
	/**
	 * <h1>공지사항 글 조회</h1>
	 * <p>설명 : 공지사항의 모든 글을 조회한다.
	 * </p>
	 * @author 홍도현
	 * @since 2018.11.27
	 * @version 1.0
	 * @package com.next.kko.model
	 * @return List<NoticeBoardDto>
	 */
	public List<NoticeBoardDto> getAdminboard();
	
	/**
	 * <h1>공지사항 글 전체조회</h1>
	 * <p>설명 : 공지사항의 모든 글을 조회한다.
	 * </p>
	 * @author 박지연
	 * @since 2018.11.07
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param 
	 * @return List<NoticeBoardDto>
	 */
	public List<NoticeBoardDto> getAllboard();
	
	/**
	 * <h1>공지사항 글 상세조회</h1>
	 * <p>설명 : 입력받은 sequence를 가진 공지사항 글을 조회한다.
	 * </p>
	 * @author 박지연
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String
	 * @return NoticeBoardDto
	 */
	public NoticeBoardDto getOneBoard(String noticecode_seq);

	/**
	 * <h1>공지사항 글 삭제</h1>
	 * <p>설명 : 입력받은 sequence를 가진 공지사항 글의 delflag를 'Y'로 설정한다.
	 * </p>
	 * @author 박지연
	 * @since 2018.11.07
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String
	 * @return true 성공
	 */
	public boolean deleteOneBoard(String seq);
	
	/**
	 * <h1>공지사항 글 수정</h1>
	 * <p>설명 : NoticeBoardDto 객체를 입력받아서 성공하면 true를 반환하고
	 * 실패하면 false를 반환한다.</p>
	 * @author 박지연
	 * @since 2018.11.07
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param NoticeBoardDto
	 * @return true 성공
	 */
	public boolean modifyBoard(NoticeBoardDto ndto);

}
