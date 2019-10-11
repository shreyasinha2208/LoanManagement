package com.cg.ibs.loanmgmt.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.ibs.loanmgmt.bean.LoanType;

class CustomerServiceImplTest {
	private static CustomerServiceImpl customerService = new CustomerServiceImpl();

	//HOME_LOAN
	@Test
	void calculateHLoanEmiPositiveTest() {
		Loan loanTemp = new HomeLoan();

		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		double actual = customerService.calculateEmi(loanTemp);
		Assertions.assertEquals(1103.6846021816305, actual);

	}

	@Test
	void calculateHLoanEmiNegativeTest() {
		Loan loanTemp = new HomeLoan();

		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		double actual = customerService.calculateEmi(loanTemp);
		Assertions.assertNotEquals(11058, actual);
	}
	
	@Test
	void loanCustomerHLoanVerificationPositiveTest() {
		Loan loanTemp = new EducationLoan();
		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		boolean actual= customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertEquals(true, actual);	
	}
	
	@Test
	void loanCustomerHLoanVerificationNegativeTest() {
		Loan loanTemp = new EducationLoan();
		loanTemp.setLoanAmount(1561899);
		loanTemp.setLoanTenure(13);
		boolean actual= customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertNotEquals(true, actual);	
	}

	//VEHICLE_LOAN
	@Test
	void calculateVLoanEmiPositiveTest() {
		Loan loanTemp = new VehicleLoan();

		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		double actual = customerService.calculateEmi(loanTemp);

		Assertions.assertEquals(1108.0815900950197, actual);

	}

	@Test
	void calculateVLoanEmiNegativeTest() {
		Loan loanTemp = new VehicleLoan();

		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		double actual = customerService.calculateEmi(loanTemp);
		Assertions.assertNotEquals(11058, actual);
	}
	
	@Test
	void loanCustomerVLoanVerificationPositiveTest() {
		Loan loanTemp = new EducationLoan();
		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		boolean actual= customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertEquals(true, actual);	
	}
	
	@Test
	void loanCustomerVLoanVerificationNegativeTest() {
		Loan loanTemp = new EducationLoan();
		loanTemp.setLoanAmount(12654896);
		loanTemp.setLoanTenure(128);
		boolean actual= customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertNotEquals(true, actual);	
	}

	//PERSONAL_LOAN
	@Test
	void calculatePLoanEmiPositiveTest() {
		Loan loanTemp = new PersonalLoan();

		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		double actual = customerService.calculateEmi(loanTemp);

		Assertions.assertEquals(1116.9056586284007, actual);

	}

	@Test
	void calculatePLoanEmiNegativeTest() {
		Loan loanTemp = new PersonalLoan();

		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		double actual = customerService.calculateEmi(loanTemp);

		Assertions.assertNotEquals(11058, actual);
	}
	
	@Test
	void loanCustomerPLoanVerificationPositiveTest() {
		Loan loanTemp = new EducationLoan();
		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		boolean actual= customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertEquals(true, actual);	
	}
	
	@Test
	void loanCustomerPLoanVerificationNegativeTest() {
		Loan loanTemp = new EducationLoan();
		loanTemp.setLoanAmount(1265489654);
		loanTemp.setLoanTenure(13);
		boolean actual= customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertNotEquals(true, actual);	
	}

	//EDUCATION_LOAN
	@Test
	void calculateELoanEmiPositiveTest() {
		Loan loanTemp = new EducationLoan();

		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		double actual = customerService.calculateEmi(loanTemp);

		Assertions.assertEquals(1120.4517435964037, actual);

	}

	@Test
	void calculateELoanEmiNegativeTest() {
		Loan loanTemp = new EducationLoan();

		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		double actual = customerService.calculateEmi(loanTemp);
		Assertions.assertNotEquals("garbage", actual);
	}


	@Test
	void loanCustomerELoanVerificationPositiveTest() {
		Loan loanTemp = new EducationLoan();
		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		boolean actual= customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertEquals(true, actual);	
	}
	

	@Test
	void loanCustomerELoanVerificationNegativeTest() {
		Loan loanTemp = new EducationLoan();
		loanTemp.setLoanAmount(99999999);
		loanTemp.setLoanTenure(33);
		boolean actual= customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertNotEquals(true, actual);	
	}
	

	@Test
	void verifyCustomerPositiveTest() {
		boolean actual= customerService.verifyCustomer("ckohli");
		Assertions.assertEquals(true, actual);	
	}
	
	@Test
	void verifyCustomerNegativeTest() {
		boolean actual= customerService.verifyCustomer("ssinha");
		Assertions.assertNotEquals(true, actual);	
	}
	
	@Test
	void verifyEmiApplicablePositiveTest() {
		
	}
	
	
}
