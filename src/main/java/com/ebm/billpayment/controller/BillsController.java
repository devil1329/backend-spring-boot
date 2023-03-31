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

import com.ebm.billpayment.pojos.Bills;
import com.ebm.billpayment.repository.BillsRepository;

@RestController
@RequestMapping(value="/api/ebm/bills")
public class BillsController {
	@Autowired
	private BillsRepository billsRepo;
		
	@GetMapping("/get-bills/{consumer_no}")
	public List<Bills> getAllBills(@PathVariable int consumer_no) {
		List<Bills> list = billsRepo.getAllBills(consumer_no);
		return list;
	}
	
	@PostMapping("/generate-bills")
	public String generateBills( @RequestBody Bills obj) 
	{
		Bills billsObj = new Bills(obj.getConsumer_no(), obj.getPresentReading(), returnPreviousReading(obj.getConsumer_no()), returnPreviousBillDate(obj.getConsumer_no()));
		obj = billsRepo.save(billsObj);
		return "Record inserted successfully in the database...";
	}
	
	public float returnPreviousReading(int consumer_no) {
		if(billsRepo.checkEmpty(consumer_no) != 0) {
			return billsRepo.getPreviousReading(consumer_no);
		}
		return 0;
	}
	
	public Date returnPreviousBillDate(int consumer_no) {
		if(billsRepo.checkEmpty(consumer_no) != 0) {
			return billsRepo.getPreviousBillDate(consumer_no);
		}
		else {
			LocalDate dt = LocalDate.now().minusMonths(2);
			return Date.valueOf(dt);
		}
	}
}
