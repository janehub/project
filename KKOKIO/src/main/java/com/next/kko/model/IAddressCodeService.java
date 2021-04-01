package com.next.kko.model;

import java.util.Map;

public interface IAddressCodeService {
	
	/**
	 * <h1>주소코드 등록</h1>
	 * <p>설명 : 회원가입시 id와 addresscode를 입력 받아서 데이터베이스에 저장한다.</p>
	 * @author 박지연
	 * @since 2018.11.29
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param map
	 * @return true 성공
	 */
	public boolean insertAddressCode(Map<String, String> map);
	
	/**
	 * <h1>주소코드 수정</h1>
	 * <p>설명 : id와 addresscode를 입력 받아서 계정에 따른 주소코드를 수정한다.</p>
	 * @author 박지연
	 * @since 2018.11.29
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param map
	 * @return  true 성공
	 */
	public boolean updateAddressCode(Map<String, String> map);
	
	/**
	 * <h1>주소코드 조회</h1>
	 * <p>설명 : 로그인시 id를 입력 받아서 계정에 따른 주소코드를 조회한다.</p>
	 * @author 박지연
	 * @since 2018.11.29
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param id
	 * @return String
	 */
	public String getAddressCode(String id);
}
