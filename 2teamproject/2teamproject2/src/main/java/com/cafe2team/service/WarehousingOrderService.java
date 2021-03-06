package com.cafe2team.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe2team.dao.WarehousingOrderMapper;
import com.cafe2team.domain.Receiving;
import com.cafe2team.domain.WarehousingOrder;



@Service
public class WarehousingOrderService {

	@Autowired
	private WarehousingOrderMapper warehousingOrderMapper;

	
	//입고지시서 목록
	public List<WarehousingOrder> getWarehousingOrderList(){
		List<WarehousingOrder> getWarehousingOrderList = warehousingOrderMapper.getWarehousingOrderList();
		return getWarehousingOrderList;
	}
	
	//입고취소
	public int cancleEntering(String warehousingOrderCode) {
		return warehousingOrderMapper.cancleEntering(warehousingOrderCode);
	}
	
	//입고 현황 리스트
	public List<Receiving> getReceivingListForWarehouse(){
		return warehousingOrderMapper.getReceivingList();
	}
	
	//입고 확정후 입고지시서 상태 변환
	public int changeWarehousingStatus(List<String> confirmWarehousingDataArr) {
		return warehousingOrderMapper.changeWarehousingStatus(confirmWarehousingDataArr);
	}
	//입고 확정 후 입고 테이블 데이터 저장
	public int addReceiving(List<String> confirmWarehousingDataArr) {
		return warehousingOrderMapper.addReceiving(confirmWarehousingDataArr);
	}
}
