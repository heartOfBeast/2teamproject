package com.cafe2team.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe2team.domain.Estimate;
import com.cafe2team.domain.Shoppingmall;
import com.cafe2team.service.EstimateService;
import com.cafe2team.service.ShoppingmallService;

@Controller
public class EstimateController {
	
	private final ShoppingmallService shoppingmallService;
	private final EstimateService estimateservice;
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	public EstimateController(EstimateService estimateservice, ShoppingmallService shoppingmallService) {
		this.estimateservice = estimateservice;
		this.shoppingmallService = shoppingmallService;
	}
	
		/************************************************************
		 * 견적신청 검토 - 견적신청 한 리스트조회 - 쇼핑몰회원, 비회원(관리자용)
		 ************************************************************/	
		@GetMapping("/estimatePermit")
		public String getEstimatePermit(Model model) {
			
			List<Estimate> estimatePermit = estimateservice.getEstimatePermit();
			List<Estimate> estimateAnotherPermit = estimateservice.getEstimateAnotherPermit();
			model.addAttribute("title", "견적신청 검토");
			model.addAttribute("estimatePermit", estimatePermit);
			model.addAttribute("estimateAnotherPermit", estimateAnotherPermit);
			
			return "estimate/estimatePermit";
		}
		/************************************************************/
		
		
		/************************************************************
		 * 비회원 견적신청
		 ************************************************************/			
		@PostMapping("/addEstimateAnother")
		public String addEstimateAnother(Estimate estimate) {
			
			estimateservice.addEstimateAnother(estimate);
			log.info("estimate", estimate);
			
			return "redirect:/estimateLook";
			
		}
		@GetMapping("/estimateAnother")
		public String estimateAnother(Model model) {
			model.addAttribute("title", "비회원 견적신청");
			return "estimate/estimateAnother";
		}
		/************************************************************/
		
		
		/************************************************************
		 * 견적신청 조회
		 ************************************************************/		
		@GetMapping("/estimateLook")
		public String estimateLook(Model model) {
			
			model.addAttribute("title", "견적신청 조회하기");

			return "estimate/estimateLook";
		}
		/************************************************************/
	
		
		/************************************************************
		 * 견적신청 조회 데이터검색
		 ************************************************************/	

		@PostMapping("/estimateCheck")
		public String estimateLook(Model model
									,String companyPhone
									,String companyEmail
									,HttpServletResponse response
									,HttpSession session) throws IOException {
			List<Estimate> estimate = null;
			boolean estimateCheck = true;
			String SID = (String)session.getAttribute("SID");
			String SLEVEL = (String)session.getAttribute("SLEVEL");
			
			if(SLEVEL.equals("게스트")) {
				estimate = estimateservice.getEstimateAnotherInfoCheck(companyPhone, companyEmail); 
				System.out.println("게스트 estimateCheck ->>>" + estimateCheck);
				
			}else if(SID != null || SLEVEL.equals("사업자")) {
				estimate = estimateservice.getEstimateInfoCheck(companyPhone, companyEmail); 
				System.out.println("사업자 estimateCheck ->>>" + estimateCheck);
			}
			
			
			System.out.println("etstestsetestestsets"+ estimate.size());
			if(estimate.size() == 0) estimateCheck = false;
			System.out.println("estimate >>>>>>" + estimate);
			System.out.println(SLEVEL);
			System.out.println(companyPhone);
			System.out.println(companyEmail);
			System.out.println("estimateCheck >>>>" + estimateCheck);
			
			if(estimateCheck == false) {
				System.out.println("response estimateCheck >>>>" + estimateCheck);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('없는 데이터입니다. 다시 확인하여 입력해주세요.');</script>");
				out.flush();
				return "estimate/estimateLook";
				
			}else {
				model.addAttribute("estimate", estimate);
				log.info("estimate", estimate);
				System.out.println(estimate);
				if(SLEVEL.equals("게스트")) return "estimate/estimateAnotherLookList";
				return "estimate/estimateLookList";	
			}
					
					
		}
		/************************************************************/
		
		
		/************************************************************
		 * 비회원 견적신청 조회 리스트
		 ************************************************************/
		@GetMapping("estimateAnotherLookList")
		public String getEstimateAnotherLookList(Model model) {
			
			List<Estimate> estimateAnotherLookList = estimateservice.getEstimateAnotherLookList();
			
			
			model.addAttribute("title", "견적신청 조회");
			model.addAttribute("estimateAnotherLookList");
			log.info("estimateAnotherLookList", estimateAnotherLookList);
			
			return "estimate/getEstimateAnotherLookList";
		}
		/************************************************************/
		
		
		/************************************************************
		 * 기존회원 견적신청 조회 리스트
		 ************************************************************/
		@GetMapping("estimateLookList")
		public String getEistmateLookList(Model model) {
			
			List<Estimate> estimateLookList = estimateservice.getEstimateLookList();
			
			
			model.addAttribute("title", "견적신청 조회");
			model.addAttribute("estimateLookList", estimateLookList);
			
			return "estimate/estimateLookList";
		}
		/************************************************************/

		
		
		/************************************************************
		 * 기존 쇼핑몰회원 새 견적신청
		 * @throws IOException 
		 ************************************************************/	
		@GetMapping("/estimate")
		public String estimate(Model model
								,HttpSession session
								,HttpServletResponse response
								,@RequestParam(name = "shoppingmallId", required = false) String shoppingmallId) throws IOException {
			
			Shoppingmall shoppingmall = shoppingmallService.shoppingmallInfo(shoppingmallId);	
			String SID = (String)session.getAttribute("SID");
			String SLEVEL = (String)session.getAttribute("SLEVEL");
			model.addAttribute("shoppingmallId", shoppingmall);
			
			if(SID == null || !SLEVEL.equals("사업자")){
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				model.addAttribute("title", "견적신청");
				out.println("<script>alert('기존 쇼핑몰 사업자 회원만 등록이 가능합니다. \\n  \"비회원 견적신청\" 페이지로 전환됩니다.');</script>");
				out.flush();
				return "main/main";
				//redirect가 왜 안되는지 생각해보자
				
			}else {
				System.out.println(shoppingmall);
				model.addAttribute("title", "견적신청");
				
				return "estimate/estimate";
			}
			
		}
		
		@PostMapping("/addEstimate")
		public String addEstimate(Estimate estimate) {
			
			estimateservice.addEstimate(estimate);
			log.info("estimate", estimate);
			
			return "redirect:/estimateLook";
		}
		/************************************************************/	
	}



