package com.next.kko.model;

import java.util.List;
import java.util.Map;

import com.next.kko.dtos.ProductDto;
import com.next.kko.dtos.PurchaseDto;

public interface IPurchaseService {
	
	
	/**
	 * <h1>구매내역정보 입력</h1>
	 * <p>설명 : 구매내역에 대한 내용을 입력하여 성공하면 true를 반환하고
	 * 실패하면 false를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param PurchaseDto
	 * @return true 성공
	 */
	public boolean purchanseInfInput(PurchaseDto pdto);
	
	/**
	 * <h1>구매내역정보 수정</h1>
	 * <p>설명 : 구매에 대한 내용을 수정하여 성공하면 true를 반환하고
	 * 실패하면 false를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param PurchaseDto
	 * @return true 성공
	 */
	public boolean purchanseInfModify(PurchaseDto pdto);
	
	/**
	 * <h1>구매내역정보 삭제</h1>
	 * <p>설명 : 구매내역에 대한 내용을 삭제하여 성공하면 true를 반환하고
	 * 실패하면 false를 반환한다.</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String
	 * @return true 성공
	 */
	public boolean purchanseInfDel(String pcode);
	
	/**
	 * <h1>구매내역정보 조회(전체)</h1>
	 * <p>설명 : 구매내역에 대한 전체 정보를 확인한다.
	 * 제품 정보는 List로 받아 온다</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param String
	 * @return true 성공
	 */
	public List<ProductDto> purchanseInfSelectAll(String passcode);
	
	
	/**
	 * <h1>구매내역정보 조회(하나)</h1>
	 * <p>설명 : 하나의 구매내역에 대한 내용을 조회한다.
	 * 제품 정보는 Dto로 받아온다</p>
	 * <pre>
	 * <b>History</b>
	 * 		허지영, 버전 1.0, 2018.11.08 최초작성
	 * </pre>
	 * @author 허지영
	 * @since 2018.11.08
	 * @version 1.0
	 * @package com.next.kko.model
	 * @param PurchaseDto
	 * @return true 성공
	 */
	public ProductDto purchanseInfSelectOne(Map<String, String> pmap);


}
