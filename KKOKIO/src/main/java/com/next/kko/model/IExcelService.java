package com.next.kko.model;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.RecordIljiDto;


@Service
public interface IExcelService {

	/**
	 * <h1>엑셀 파일 업로드</h1>
	 * <p>설명 : 엑셀 파일의 첫번째 페이지(파스 정보)를 PassDto,
	 *  두번째 페이지(육계일지정보)를 RecordIljiDto 배열로 만든 객체를
	 *  입력받아서 데이터베이스에 저장한다.</p>
	 * @author 박지연
	 * @since 2018.11.23
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param pdto
	 * @param rdtos
	 * @return
	 */
	public boolean insertExcel(PassDto pdto,RecordIljiDto[] rdtos) throws ParseException;
	

	
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
