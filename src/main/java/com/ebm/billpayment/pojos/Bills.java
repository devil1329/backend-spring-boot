package com.ebm.billpayment.pojos;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="bills")
public class Bills {

	@Id
	@Column(name="bill_no")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "bill_no_generator")
	@SequenceGenerator(name="bill_no_generator", sequenceName = "bill_no_sequence", allocationSize = 1)
	private int bill_no;
	
	@Column(name="consumer_no")
	private int consumer_no;
	
	@Column(name="present_reading")
	private float presentReading;
	
	@Column(name="previous_reading")
	private float previousReading;
	
	@Column(name="current_bill_date")
	private Date currrentBillDate;
	
	@Column(name="previous_bill_date")
	private Date previousBillDate;
	
	@Column(name="due_date")
	private Date dueDate;
	
	@Column(name="amount_pay")
	private double amountToPay;
	
	// function to calculate bill
	private double calculateAmount() {
		float units = this.presentReading - this.previousReading;
		if (units <= 100) {
            return units * 2.9;
        }
        else if (units <= 200) {
            return (100 * 2.9)
                + (units - 100)
                      * 4.2;
        }
        else if (units <= 400) {
            return (100 * 2.9)
                + (100 * 4.2)
                + (units - 200)
                      * 5.8;
        }
        else if (units > 400) {
            return (100 * 2.9)
                + (100 * 4.2)
                + (100 * 5.8)
                + (units - 300)
                      * 6.55;
        }
        return 0;
	}
	
	//constructors
	protected Bills() {}

	public Bills(int consumer_no, float presentReading, float previousReading, Date previousBillDate) {
		super();
		this.consumer_no = consumer_no;
		this.presentReading = presentReading;
		this.currrentBillDate = Date.valueOf(LocalDate.now());
		this.setPreviousReading(previousReading);
		this.setPreviousBillDate(previousBillDate);
		this.setDueDate();
		this.setAmountToPay();
		this.toString();
	}

	// getters
	public long getBill_no() {
		return bill_no;
	}
	
	public int getConsumer_no() {
		return consumer_no;
	}

	public float getPresentReading() {
		return presentReading;
	}

	public float getPreviousReading() {
		return previousReading;
	}

	public Date getCurrrentBillDate() {
		return currrentBillDate;
	}
	
	public Date getPreviousBillDate() {
		return previousBillDate;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public double getAmountToPay() {
		return amountToPay;
	}
	
	//setters
	public void setConsumer_no(int consumer_no) {
		this.consumer_no = consumer_no;
	}

	public void setPresentReading(float presentReading) {
		this.presentReading = presentReading;
	}
	
	public void setPreviousReading(float previousReading) {
		this.previousReading = previousReading;
	}

	public void setPreviousBillDate(Date previousBillDate) {
		this.previousBillDate = previousBillDate;
	}

	public void setDueDate() {
		LocalDate ld = this.currrentBillDate.toLocalDate().plusMonths(1);
		this.dueDate = Date.valueOf(ld);
	}

	public void setAmountToPay() {
		this.amountToPay = calculateAmount();
	}

	@Override
	public String toString() {
		return "Bills [bill_no=" + bill_no + ", consumer_no=" + consumer_no + ", presentReading=" + presentReading
				+ ", previousReading=" + previousReading + ", currrentBillDate=" + currrentBillDate
				+ ", previousBillDate=" + previousBillDate + ", dueDate=" + dueDate + ", amountToPay=" + amountToPay
				+ "]";
	}
	
}
