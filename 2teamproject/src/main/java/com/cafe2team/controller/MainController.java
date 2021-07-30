package com.cafe2team.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe2team.domain.Receiving;
import com.cafe2team.domain.ReleaseInfo;
import com.cafe2team.domain.Stock;
import com.cafe2team.service.ReleaseService;
import com.cafe2team.service.StockService;
import com.cafe2team.service.WarehousingOrderService;

@Controller
public class MainController {
	
	private final WarehousingOrderService warehousingOrderService;
	private final ReleaseService releaseService;
	private final StockService stockService;


	
		@Autowired
		public MainController(WarehousingOrderService warehousingOrderService,
				ReleaseService releaseService, StockService stockService) {
		
			this.stockService = stockService;
			this.warehousingOrderService = warehousingOrderService;
			this.releaseService=releaseService;
	
		}
	
	@GetMapping("/")
	public String login(Model model) {
		
		return "logindefault";
	}
	
	@GetMapping("/main")
	public String main(Model model) {
		
		List<Receiving> receivingStatusForWarehouse = warehousingOrderService.getReceivingListForWarehouse();
		List<ReleaseInfo> releaseInfo = releaseService.getReleaseList();
		List<Stock> stockList = stockService.getStockList();

		model.addAttribute("stockList", stockList);
		model.addAttribute("releaseInfo", releaseInfo);
		model.addAttribute("receivingStatusListForWarehouse", receivingStatusForWarehouse);
		
		return "main/main";
	}
}
