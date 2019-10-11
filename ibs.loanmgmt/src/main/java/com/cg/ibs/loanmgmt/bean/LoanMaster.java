package com.cg.ibs.loanmgmt.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class LoanMaster implements Serializable {

	private static long incLoanNumber = 1000;
	private String loanNumber;
	private double loanAmount;
	private int loanTenure;
	private LoanType loanType;
	private float interestRate;

	private double emiAmount;
	private CustomerBean customerBean;
	private int numberOfEmis;
	private int totalNumberOfEmis;
	private LocalDate appliedDate;
	private LocalDate nextEmiDate;

	public LoanMaster(String loanNumber,LoanType loanType, double loanAmount, int loanTenure, double emiAmount, CustomerBean customerBean,
			int numberOfEmis, int totalNumberOfEmis, LocalDate appliedDate, LocalDate nextEmiDate) {
		super();
		this.loanNumber = loanNumber;
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.loanTenure = loanTenure;
		this.emiAmount = emiAmount;
		this.customerBean = customerBean;
		this.numberOfEmis = numberOfEmis;
		this.totalNumberOfEmis = totalNumberOfEmis;
		this.appliedDate = appliedDate;
		this.nextEmiDate = nextEmiDate;
	}

	public static long generateLoanNumber() {
		return ++incLoanNumber;
	}

	public LoanMaster() {
		super();
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(int loanTenure) {
		this.loanTenure = loanTenure;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public String setLoanNumber(long loanNum) {
		this.loanNumber = Long.toString(loanNum);
		return loanNumber;
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	public int getNumberOfEmis() {
		return numberOfEmis;
	}

	public void setNumberOfEmis(int numberOfEmis) {
		this.numberOfEmis = numberOfEmis;
	}

	public int getTotalNumberOfEmis() {
		return totalNumberOfEmis;
	}

	public void setTotalNumberOfEmis(int totalNumberOfEmis) {
		this.totalNumberOfEmis = totalNumberOfEmis;
	}

	public double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(double emiAmount) {
		this.emiAmount = emiAmount;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public LocalDate getNextEmiDate() {
		return nextEmiDate;
	}

	public void setNextEmiDate(LocalDate nextEmiDate) {
		this.nextEmiDate = nextEmiDate;
	}

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String toString() {
		return "\nCustomer Details :\n" + customerBean.toString() + "\nLoan Number :\t" + loanNumber
				+ "\nLoan Amount :\t" + loanAmount + "\nLoan Tenure :\t" + loanTenure + " Months"
				+ "\nMonthly EMI Amount :\t" + emiAmount + "\nNumber Of EMI's paid :\t" + numberOfEmis
				+ "\nTotal Number Of EMI's to be paid :\t" + totalNumberOfEmis + "\nApplied Date :\t" + appliedDate;
	}

}
