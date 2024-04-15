package com.ict.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.transaction.dao.TxVO;
import com.ict.transaction.service.TxService;

@Controller
public class TransactionController {
	@Autowired
	private TxService txService;
	
	@GetMapping("transaction_go.do")
	public ModelAndView getTransaction() {
		return new ModelAndView("tx/tx_form");
	}
	
	@PostMapping("transaction_ok.do")
	public ModelAndView getTransactionOK(@ModelAttribute("txvo")TxVO txvo) {
		try {
			ModelAndView mv = new ModelAndView("tx/tx_result");
			System.out.println(txvo.getAmount());
			System.out.println(txvo.getCustomerId());
			int res = txService.getTxInsert(txvo);
			mv.addObject("res", res);
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common/error");
	}
}
