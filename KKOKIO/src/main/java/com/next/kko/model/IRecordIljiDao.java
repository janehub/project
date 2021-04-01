package com.next.kko.model;

import java.util.List;
import java.util.Map;

import com.next.kko.dtos.RecordIljiDto;

public interface IRecordIljiDao {
	
	
	/**
	 * <h1>폐사수 등록</h1>
	 * <p>설명 : 동별 폐사수를 입력한다.
	 * (일지입력 테이블에 들어가기 때문에 기본적으로 일지에 적는 내용도 함께 들어가야 한다.)
	 * ilji_Dto 객체를 입력받아서 입력에 성공하면 true를 반환하고
	 * 실패하면 false를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param RecordIljiDto
	 * @return true 성공
	 */
	public boolean dongDeathCntInput(RecordIljiDto idto);
	
	
	/**
	 * <h1>폐사수 수정</h1>
	 * <p>설명 : 일지_seq, 파스코드, 일령을 입력하여 동별 폐사수를 수정할수 있다.
	 * ilji_Dto 객체를 입력받아서 입력에 성공하면 true를 반환하고
	 * 실패하면 false를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String ilji_seq
	 * @return true 성공
	 */
	public boolean dongDeathCntModify(RecordIljiDto idto);
	
	
	/**
	 * <h1>폐사수 삭제</h1>
	 * <p>설명 : 일지_seq, 파스코드, 일령을 입력하여 동별 폐사수를 삭제할수 있다.
	 * (삭제: delflag 'Y')
	 * ilji_Dto 객체를 입력받아서 입력에 성공하면 true를 반환하고
	 * 실패하면 false를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param RecordIljiDto
	 * @return true 성공
	 */
	public boolean dongDeathCntDel(RecordIljiDto idto);
	
	
	/**
	 * <h1>폐사수 일령별 조회</h1>
	 * <p>설명 : 파스코드와 일령을 입력하면 일령별 폐사수를 조회할 수 있다.
	 * 입력한 파스와 일령에 해당하는 모든 폐사수 정보를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param RecordIljiDto
	 * @return List<Ilji_Dto>
	 */
	public List<RecordIljiDto> dongDeathCntSelectI(Map<String, String> ilmap);
	
	
	/**
	 * <h1>폐사수 동별 조회</h1>
	 * <p>설명 : 파스코드, 일령, 동을 입력하면 동별 폐사수를 조회할 수 있다.
	 * 입력한 파스, 일령, 동에 해당하는 폐사수 정보를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param RecordIljiDto
	 * @return Ilji_Dto
	 */
	public RecordIljiDto dongDeathCntSelectD(Map<String, String> ilmap);
	
	
	
	
	/**
	 * <h1>중량 등록</h1>
	 * <p>설명 : 동별 중량을 입력한다.(일지입력 테이블에 들어가기 때문에
	 * 기본적으로 일지에 적는 내용도 함께 들어가야 한다)
	 * 중량 입력 성공하면 true를 반환하고
	 * 실패하면 false를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param RecordIljiDto
	 * @return true 성공
	 */
	public boolean dongWeightInput(RecordIljiDto idto);
	
	
	/**
	 * <h1>중량 수정</h1>
	 * <p>설명 : 동별 중량을 수정한다.
	 * 수정 성공하면 true를 반환하고
	 * 실패하면 false를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String ilji_seq
	 * @return true 성공
	 */
	public boolean dongWeightModify(RecordIljiDto idto);
	
	
	/**
	 * <h1>중량 삭제</h1>
	 * <p>설명 : 동별 중량을 삭제한다.
	 * (삭제: delflag 'Y')
	 * 수정 성공하면 true를 반환하고
	 * 실패하면 false를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String ilji_seq
	 * @return true 성공
	 */
	public boolean dongWeightDel(RecordIljiDto idto);
	
	
	/**
	 * <h1>중량 일령별 조회</h1>
	 * <p>설명 : 파스코드와 일령을 입력하면 일령별 중량을 조회할 수 있다.
	 * 입력한 파스와 일령에 해당하는 모든 중량 정보를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param RecordIljiDto
	 * @return List<Ilji_Dto>
	 */
	public List<RecordIljiDto> dongWeightSelectI(Map<String, String> ilmap);
	
	
	/**
	 * <h1>중량 동별 조회</h1>
	 * <p>설명 : 파스코드, 일령, 동을 입력하면 동별 중량을 조회할 수 있다.
	 * 입력한 파스, 일령, 동에 해당하는 중량 정보를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param RecordIljiDto
	 * @return Ilji_Dto
	 */
	public RecordIljiDto dongWeightSelectD(Map<String, String> ilmap);
	
	
	
	public boolean dongWeatherComentInput(RecordIljiDto idto);
	public boolean dongWeatherComentModify(RecordIljiDto idto);
	public boolean dongWeatherComentDel(RecordIljiDto idto);
	
	
	public boolean dongEtcComentInput(RecordIljiDto idto);
	public boolean dongEtcComentModify(RecordIljiDto idto);
	public boolean dongEtcComentDel(RecordIljiDto idto);

	

	
	public List<RecordIljiDto> distinctTemp(RecordIljiDto dto);
	
	public boolean insertMedi(RecordIljiDto dto);
	
	public boolean modifyMedi(RecordIljiDto dto);
	
	public boolean deleteMedi(RecordIljiDto dto);
	
	public boolean insertTemp(RecordIljiDto dto);
	
	public boolean modifyTemp(RecordIljiDto dto);
	
	public boolean deleteTemp(RecordIljiDto dto);
	
	
	/**
	 * <h1>일령의 작성 항목 조회</h1>
	 * <p>설명 : 일지 작성시 작성된 내용 확인을 위한 조회
	 * 입력한 파스, 일령에 해당하는 폐사수, 중량, 사육장온도,약품사용정보,
	 * 비고, 날씨 상태에 대한 정보를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.28 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.28
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param Map<String, String> ilmap
	 * @return List<RecordIljiDto>
	 */
	public List<RecordIljiDto> illyungSelectAll(Map<String, String> ilmap);
}
