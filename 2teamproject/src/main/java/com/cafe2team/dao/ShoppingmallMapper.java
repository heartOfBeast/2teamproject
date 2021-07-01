package com.cafe2team.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe2team.domain.Shoppingmall;


@Mapper
public interface ShoppingmallMapper {
	
	
	// 쇼핑몰 회원조회
	public List<Shoppingmall> ShoppingmallList();
	
	
	// 쇼핑몰 사업자 등록
	public int shoppingmallAdd(Shoppingmall shoppingmall);
	
}
