package com.cafe2team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe2team.domain.Contract;
import com.cafe2team.domain.Product;
import com.cafe2team.domain.Request;
import com.cafe2team.domain.WarehousingOrder;

@Mapper
public interface WarehousingMapper {
	
	//코드 가져오기
	public List<Product> getProductCode(String productCode);
	
	//입고요청
	public int addRequest(Request request);
	
	//상품코드 증가
	public String maxGoodsCode();
	
	//상품 QR등록
	public int addGoodsQR(Request request);
		
	public Request getRequestCode(String requestCode);
	
	public List<Contract> getContractList();
	
	//입고요청-> 입고지시서
	public int addRequestOrder(List<String> paramList);
	
	public Map<String, Object> wareHouseListDetail(Map<String,Object> paramMap);
	
	//입고요청 취소
	public int cancleReuqestWareHouse(List<String> paramList);
	
	public List<Request> receivingRequestWaiting(Map<String, Object> warehouseCodeParam);
	
}
