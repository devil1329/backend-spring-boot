package com.ebm.billpayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ebm.billpayment.pojos.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

	@Query(value="select * from transactions t where t.consumer_no = ?1 order by t.transaction_no desc", nativeQuery = true)
	public List<Transactions> getTransactions(int consumer_no);
}
