package com.ebm.billpayment.repository;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ebm.billpayment.pojos.Bills;

public interface BillsRepository extends JpaRepository<Bills, Serializable>{
	
	@Query(value="select * from bills b where b.conusmer_no=?1 order by b.bill_no desc", nativeQuery = true)
	public List<Bills> getAllBills(int consumer_no);
	
	@Query(value="select max(b.current_bill_date) from bills b where consumer_no=?1",  nativeQuery = true)
	public Date getPreviousBillDate(int consumer_no);

	@Query(value="select max(b.present_reading) from bills b where consumer_no=?1",  nativeQuery = true)
	public float getPreviousReading(int consumer_no);
	
	@Query(value="select count(b.bill_no) from bills b where consumer_no=?1", nativeQuery = true)
	public int checkEmpty(int consumer_no);
}
