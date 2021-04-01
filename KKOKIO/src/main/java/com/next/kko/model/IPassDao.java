package com.next.kko.model;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.next.kko.dtos.RecordIljiDto;
import com.next.kko.dtos.PassDto;

public interface IPassDao {

	/**
	 * <h1>파스 생성</h1>
	 * <p>설명 : PassDto 객체를 입력받아서 파스 정보를 입력한다.</p>
	 * @author 박지연
	 * @since 2018.11.20
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param PassDto
	 * @return true 성공
	 */	
	public boolean makePass(PassDto dto);
	
	/**
	 * <h1>코드셋 생성 -파스 입력</h1>
	 * <p>설명  : 파스 입력 시 자동으로 codeset 입력되야한다.
	 * 파스가 생성되면 파스 시작일부터 파스 종료일까지의 일령을 코드화한다.</p>
	 * @author 박지연
	 * @since 2018.11.20
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param pdto
	 * @return true 성공
	 * @throws ParseException 
	 */
	public boolean codeset(PassDto pdto) throws ParseException;
	
	/**
	 * <h1>코드셋 생성 -파스 수정</h1>
	 * <p>설명  : 수정할 파스 종료일이 원래 파스 종료일보다 클 경우 그 차이만큼 코드셋을 생성한다. </p>
	 * @author 박지연
	 * @since 2018.11.21
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param passcode
	 * @param term
	 * @param maxCodset
	 * @return true 성공
	 */
	public boolean codesetForModify(String passcode,int term,int maxCodset);
	
	/**
	 * <h1>파스 정보 수정</h1>
	 * <p>설명 : PassDto 객체를 입력받아서 파스 정보를 수정한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		홍도현, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 박지연
	 * @since 2018.11.20
	 * @version 1.1
	 * @package com.next.kko.model
	 * @param dto
	 * @return true 성공
	 */
	public boolean modifyPass(PassDto dto);
	
	/**
	 * <h1>파스 상세 조회</h1>
	 * <p>설명 : 입력받은 passcode, id와 일치하는 파스 정보를 반환한다.</p>
	 * @author 박지연
	 * @since 2018.11.20
	 * @version 1.1
	 * @package com.next.kko.model
	 * @param passcode
	 * @param id
	 * @return PassDto
	 */
	public PassDto infoDetailPass(String passcode,String id);

	/**
	 * <h1>메인페이지 조회</h1>
	 * <p>설명 : 입력받은 passcode, dongnumber와 일치하는 일지 정보(동별 폐사수,중량)를 반환한다.</p>
	 * @author 박지연
	 * @since 2018.11.26
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param passcode
	 * @param dongnumber
	 * @return RecordIljiDto
	 */
	public List<RecordIljiDto> infoMainIlji(String passcode,String dongnumber);	
	
	/**
	 * <h1>파스 전체 조회</h1>
	 * <p>설명 : 입력받은 id와 일치하는 파스 정보 리스트를 반환한다.</p>
	 * @author 박지연
	 * @since 2018.11.21
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param id
	 * @return List<PassDto>
	 */
	public List<PassDto> infoPassList(String id);	

	/**
	 * <h1>파스 삭제</h1>
	 * <p>설명 : 입력받은 passcode, id와 일치하는 파스 정보를 삭제한다.</p>
	 * <b>History</b>
	 * 		홍도현, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 박지연
	 * @since 2018.11.21
	 * @version 1.1
	 * @package com.next.kko.model
	 * @param map
	 * @return true 성공
	 */
	public boolean deletePass(Map<String,String> map);
	
	/**
	 * <h1>코드셋 삭제</h1>
	 * <p>설명 : 입력받은 passcode과 일치하는 코드셋 정보를 삭제한다.</p>
	 * @author 박지연
	 * @since 2018.11.24
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param passcode
	 * @return true 성공
	 */
	public boolean deleteCodeset(String passcode);
	
	/**
	 * <h1>일지 삭제</h1>
	 * <p>설명 : 입력받은 passcode과 일치하는 일지 정보를 삭제한다.</p>
	 * @author 박지연
	 * @since 2018.11.24
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param passcode
	 * @return true 성공
	 */
	public boolean deleteIlji(String passcode);
	
	/**
	 * <h1>파스 중복확인</h1>
	 * <p>설명 : 입력받은 passcode와 일치하는 파스 정보가 존재하는지 확인한다.</p>
	 * @author 박지연
	 * @since 2018.12.03
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param passcode
	 * @return true 중복
	 */
	public boolean checkPassDuplicate(String passcode);
	
	/**
	 * 파스 기본 정보 조회
	 * @return true 성공
	 */
	public List<PassDto> baseInfoPass();
	
	
}
