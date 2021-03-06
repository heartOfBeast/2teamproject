package com.cafe2team.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe2team.domain.Receiving;
import com.cafe2team.domain.WareSector;
import com.cafe2team.service.WarehousingOrderService;

@Controller
@RequestMapping("/warehousing")
public class WarehousingOrderController {
	private final WarehousingOrderService warehousingOrderService;
	@Autowired
	public WarehousingOrderController(WarehousingOrderService warehousingOrderService) {
		this.warehousingOrderService = warehousingOrderService;
	}
	
	private static final Logger log = LoggerFactory.getLogger(WarehousingOrderController.class);


	
	
	//수정시 보관할 창고 구역 가져오기
	@PostMapping("getWarehouseSector")
	@ResponseBody
	public List<WareSector> getWarehouseSector(@RequestParam Map<String,Object> paramMap){
		String warehouseCode = (String)paramMap.get("warehouseCode");
		List<WareSector> getWarehouseSectorByCode = warehousingOrderService.getWarehouseSectorByCode(warehouseCode);
		return getWarehouseSectorByCode;
	}
	
	//수정 후 입고(단품)
	@PostMapping("/receivingFromModal")
	public String receivingFromModal(Receiving receiving,String warehousingOrderCode,String sectorColumnFinalCode,
									HttpSession session,
									RedirectAttributes reAttr
									) {
		String sessionId = (String) session.getAttribute("SID");
		//String wareAdminId = (String) session.getAttribute("SID");
		if(sessionId !=null/*||wareAdminId !=null*/) {
			receiving.setWareAdminId(sessionId);
			warehousingOrderService.receivingFromModal(receiving);
			warehousingOrderService.changeWarehousingStatusFromModal(warehousingOrderCode, sectorColumnFinalCode);
			warehousingOrderService.insertOrUpdateStock(receiving);
			reAttr.addAttribute("wareAdminId", sessionId);
			//reAttr.addAttribute("wareAdminId", wareAdminId);
		}
		return "redirect:/receivingOrder";
		
	}
	
	//입고확정
	@PostMapping("/confirmWarehousing")
	@ResponseBody
	public Map<String,Object> entering(@RequestParam(value = "confirmWarehousingDataArr[]")List<String> confirmWarehousingDataArr
										,HttpSession session
										,RedirectAttributes reAttr
										,HttpServletResponse response
										,Receiving receiving) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		String sessionId = (String) session.getAttribute("SID");
		//String wareAdminId = (String)session.getAttribute("SID");
		String changeWarehousingOrderStatus = warehousingOrderService.changeWarehousingStatus(confirmWarehousingDataArr)+"";
		if(sessionId != null) {
			int addReceiving = warehousingOrderService.addReceiving(confirmWarehousingDataArr);
			
			log.info(" 회원 아이디 sessionId : {}",  sessionId);
			paramMap.put("changeWarehousingOrderStatus", changeWarehousingOrderStatus);
			paramMap.put("addReceiving", addReceiving);
			receiving.setWareAdminId(sessionId);
			reAttr.addAttribute("wareAdminId", sessionId);
			//reAttr.addAttribute("wareAdminId", wareAdminId);
		}
		
		return paramMap;
	}
	
	
	//입고 취소
	@PostMapping("/cancleWarehousing")
	@ResponseBody
	public int cancleEntering(@RequestParam(value = "cancleDataArr[]")String[] warehousingOrderCode) {
		int result = 1;
		int size = warehousingOrderCode.length;
		for(int i = 0; i<size;i++) {
			warehousingOrderService.cancleEntering(warehousingOrderCode[i]);
		}
		return result;
		
	}
	
	//입고현황리스트
	
}
