package com.ebm.billpayment.pojos;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name="transactions")
public class Transactions {
	@Id
	@Column(name="transaction_no")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "transaction_generator")
	@SequenceGenerator(name="transaction_generator", sequenceName = "transaction_sequence", allocationSize = 1)
	private int transaction_no;
	
	@Column(name="bill_no")
	private int billNo;
	
	@Column(name="consumer_no")
	private int consumerNo;
	
	@Column(name="meter_reading")
	private int meterReading;
	
	@Column(name="amount_paid")
	private double amountPaid;
	
	@Column(name="transaction_date")
	private Date transactionDate;
	
	public Transactions() {}

	public Transactions(int billNo, int consumerNo,int meterReading, double amountPaid) {
		super();
		this.billNo = billNo;
		this.meterReading = meterReading;
		this.consumerNo = consumerNo;
		this.amountPaid = amountPaid;
	}

	public int getMeterReading() {
		return meterReading;
	}

	public void setMeterReading(int meterReading) {
		this.meterReading = meterReading;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public int getConsumerNo() {
		return consumerNo;
	}

	public void setConsumerNo(int consumerNo) {
		this.consumerNo = consumerNo;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
