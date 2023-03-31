package com.ebm.billpayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ebm.billpayment.pojos.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

	@Query(value="select * from transactions t where t.consumer_no = ?1 order by t.transaction_no desc", nativeQuery = true)
	public List<Transactions> getTransactions(int consumer_no);
	
	@Query(value="select b.bill_no from bills b where b.bill_no in (select t.bill_no from transactions t) and b.consumer_no = ?1", nativeQuery = true)
	public List<Integer> getPaidBills(int consumer_no);
	
}
