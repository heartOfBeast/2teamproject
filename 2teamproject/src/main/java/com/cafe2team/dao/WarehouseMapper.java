package com.cafe2team.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe2team.domain.Warehouse;
import com.cafe2team.domain.WarehouseSector;

@Mapper
public interface WarehouseMapper {
	
	//창고정보수정
	public int warehouseUpdateModal(Warehouse warehouse);
	//창고정보조회
	public Warehouse getWarehouseInfoByCode(String warehouseCode);
	//
	public List<Warehouse> getWarehouseListDetailAddr(String warehouse_addr);
	
	public List<Warehouse> getWarehouseListDetailName(String warehouse_addr, String warehouse_name);
	
	public List<Warehouse> getWarehouseListDetailCate(String warehouse_name, String warehouse_addr, String warehouse_cate);
	//창고조회
	public List<Warehouse> getWarehouseList();
	//창고등록
	public int addWarehouse(Warehouse warehouse);
	//창고구역등록
	public int addWarehouseSector(Warehouse warehouse);
	
	//창고최종위치등록
	public int addWarehouseFinalSector(Warehouse warehouse);
	
	public List<Warehouse> getWarehouseLocation();
	
	//재고실사 창고별 조회를 위해 창고명 가져오기
	public List<Warehouse> getWarehouseName();
	
	//재고실사 구역별 조회를 위해 구역명 가져오기
	public List<WarehouseSector> getWarehouseSector(String wareName	);
}
