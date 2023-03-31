package com.ebm.billpayment.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebm.billpayment.pojos.Transactions;
import com.ebm.billpayment.repository.TransactionsRepository;

@RestController
@RequestMapping(value="/api/ebm/transactions")
public class TransactionsController {

	@Autowired
	private TransactionsRepository transactionsRepository;
	
	@GetMapping("/get-transactions/{consumer_no}")
	public List<Transactions> getTransactions(@PathVariable int consumer_no) {
		List<Transactions> list = transactionsRepository.getTransactions(consumer_no);
		return list;
	}
	
	@PostMapping("/create-transaction")
	public String createTransaction(@RequestBody Transactions transactions) {
		transactions.setTransactionDate(Date.valueOf(LocalDate.now()));
		transactionsRepository.save(transactions);
		return "Payment Successfull!!!";
	}
	
	@GetMapping("/get-paid-bills/{consumer_no}")
	public List<Integer> getPaidBills(@PathVariable int consumer_no){
		return transactionsRepository.getPaidBills(consumer_no);
	}
	
}
