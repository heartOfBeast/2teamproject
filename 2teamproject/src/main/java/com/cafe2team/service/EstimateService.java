package com.cafe2team.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe2team.dao.EstimateMapper;
import com.cafe2team.domain.Estimate;

@Service
public class EstimateService {

@Autowired
private EstimateMapper estimateMapper;


	//견적신청 검토 - 견적신청 등록된 리스트 조회
	public List<Estimate> getEstimatePermit(){
		List<Estimate> estimatePermit = estimateMapper.getEstimatePermit();
		return estimatePermit;
	}
	
	public List<Estimate> getEstimateAnotherPermit(){
		List<Estimate> estimateAnotherPermit = estimateMapper.getEstimateAnotherPermit();
		return estimateAnotherPermit;
	}

	//비회원 견적신청
	public int addEstimateAnother(Estimate estimate) {
		int result = estimateMapper.addEstimateAnother(estimate);
		
		return result;
	}
	//비회원 정보조회
	public List<Estimate> getEstimateAnotherInfoCheck(String companyPhone, String companyEmail) {
		return estimateMapper.getEstimateAnotherInfoCheck(companyPhone, companyEmail);
	}
	//기존회원 정보조회
	public List<Estimate> getEstimateInfoCheck(String companyPhone, String companyEmail) {
		return estimateMapper.getEstimateInfoCheck(companyPhone, companyEmail);
	}
	//기존회원 정보 조회 후 리스트
	public List<Estimate> getEstimateLookList(){
		List<Estimate> getEstimateLookList = estimateMapper.getEstimateLookList();
		return getEstimateLookList;
	}
	//비회원 정보 조회 후 리스트
	public List<Estimate> getEstimateAnotherLookList(){
		List<Estimate> getEstimateAnotherLookList = estimateMapper.getEstimateAnotherLookList();
		return getEstimateAnotherLookList;
	}
	//기존 쇼핑몰회원 견적신청
	public int addEstimate(Estimate estimate) {
		int result = estimateMapper.addEstimate(estimate);
		
		return result;
	}
}
