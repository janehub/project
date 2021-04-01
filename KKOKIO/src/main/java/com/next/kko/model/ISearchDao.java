package com.next.kko.model;

import java.util.List;
import java.util.Map;

import com.next.kko.dtos.RecordIljiDto;

public interface ISearchDao {
	
	/**
	 * <h1>일지 키워드  검색</h1>
	 * <p>설명 : 입력받은 날짜(년월)-파스,전체/일령, 동(배열)과 일치하는 해당 일지 정보 리스트를 반환한다.</p>
	 * @author 박지연
	 * @since 2018.12.01
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param idpassym
	 * @return List<RecordIljiDto>
	 */
	public List<RecordIljiDto> searchIlji(Map<String,Object> map);	
	
	
	
	/**
	 * <h1>일지 전체 조회</h1>
	 * <p>설명 : 입력받은 id,파스시작일(startpass)과 일치하는 파스의 해당 일지 정보 리스트를 반환한다.</p>
	 * @author 박지연
	 * @since 2018.11.30
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param idpassym
	 * @return List<RecordIljiDto>
	 */
	public List<RecordIljiDto> getAllIlji(String idpassym);
}
