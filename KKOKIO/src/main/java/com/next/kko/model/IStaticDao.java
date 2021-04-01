package com.next.kko.model;

import java.util.List;
import java.util.Map;

import com.next.kko.dtos.PassDto;
import com.next.kko.dtos.RecordIljiDto;

public interface IStaticDao {
	
	/**
	 * <h1>한 파스의 입추수수 값 조회</h1>
	 * <p>설명 : 파스코드를 입력받으면 해당 파스의 입추수수값을
	 * 반환한다</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.12.01 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.12.01
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String
	 * @return String
	 */
	public PassDto dongChickNumAll(String passcode);
	
	
	/**
	 * <h1>한 파스의 일령별 폐사수 조회</h1>
	 * <p>설명 : 파스코드와 동을 입력하면 해당하는 파스와 동의
	 * 일령과 폐사수를 반환하다</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.12.01 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.12.01
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param Map<String, String>String
	 * @return List<RecordIljiDto>
	 */
	public List<RecordIljiDto> dongDeathCntAll(Map<String, String> map);

	/**
	 * <h1>한 파스의 폐사수 통계 조회</h1>
	 * <p>설명 : 파스코드를 입력하면 해당하는 파스의 일령별 동별 폐사수 합계를 반환한다</p>
	 * @author 박지연
	 * @since 2018.12.04
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param passcode
	 * @return
	 */
	public List<RecordIljiDto> getDeathCountStatic(String passcode);
}
