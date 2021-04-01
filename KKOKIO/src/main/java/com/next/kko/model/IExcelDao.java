package com.next.kko.model;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.RecordIljiDto;

@Repository
public interface IExcelDao {

	/**
	 * <h1>엑셀 첫 페이지 파스 등록</h1>
	 * <p>설명 : PassDto 객체를 입력받아서 파스 정보를 입력한다.</p>
	 * @author 박지연
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param PassDto
	 * @return true 성공
	 */	
	public boolean insertExcelPass(PassDto pdto);

	/**
	 * <h1>코드셋 생성</h1>
	 * <p>설명 : 파스가 입력되면 자동으로 codeset 입력되야한다.
	 * 파스코드 - 파스가 생성되면 파스 시작일부터 파스 종료일까지의 일령을 코드화한다.</p>
	 * @author 박지연
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model	
	 * @param pdto
	 * @return true 성공
	 * @throws ParseException 
	 */
	public boolean codeset(PassDto pdto) throws ParseException;

	
	/**
	 * <h1>엑셀 두번째 페이지 일지 등록</h1>
	 * <p>설명 : RecordIljiDto 객체를 입력받아서 육계일지를 입력한다.</p>
	 * @author 박지연
	 * @since 2018.11.10
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param RecordIljiDto[]
	 * @return true 성공
	 */
	public boolean insertExcelIlji(RecordIljiDto[] rdtos);
	
	
	/**
	 * <h1>엑셀 첫 페이지 파스 조회</h1>
	 * <p>설명 : 입력받은 passcode값과 일치하는 파스정보를 조회한다.</p>
	 * @author 박지연
	 * @since 2018.11.12
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String
	 * @return PassDto
	 */
	public PassDto getExcelPass(String passcode);
	
	/**
	 * <h1>엑셀 두번째 페이지 일지 검색</h1>
	 * <p>설명 : 입력받은 passcode값과 일치하는 육계일지 정보를 조회한다.</p>
	 * @author 박지연
	 * @since 2018.11.12
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String
	 * @return RecordIljiDto[]
	 */
	public List<RecordIljiDto> getExcelIlji(String passcode);
}
