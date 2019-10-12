package com.cg.ibs.loanmgmt.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.ibs.loanmgmt.bean.CustomerBean;
import com.cg.ibs.loanmgmt.bean.LoanMaster;
import com.cg.ibs.loanmgmt.bean.LoanType;

class CustomerServiceImplTest {
	private static CustomerServiceImpl customerService = new CustomerServiceImpl();
	Loan loanTemp1 = new HomeLoan();
	LoanMaster loanMasterTemp = new LoanMaster();

	// HOME_LOAN
	@Test
	void calculateHLoanEmiTest() {

		loanTemp1.setLoanAmount(12654);
		loanTemp1.setLoanTenure(12);
		double actual = customerService.calculateEmi(loanTemp1);
		Assertions.assertEquals(1103.6846021816305, actual);
		Assertions.assertNotEquals(11058, actual);

	}

	@Test
	void calculateHLoanEmiNegativeTest() {
		Loan loanTemp = new HomeLoan();

		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		double actual = customerService.calculateEmi(loanTemp);

	}

	@Test
	void loanCustomerHLoanVerificationPositiveTest() {
		Loan loanTemp = new EducationLoan();
		loanTemp.setLoanAmount(12654);
		loanTemp.setLoanTenure(12);
		boolean actual = customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertEquals(true, actual);
	}

	@Test
	void loanCustomerHLoanVerificationNegativeTest() {
		Loan loanTemp = new EducationLoan();
		loanTemp.setLoanAmount(1561899);
		loanTemp.setLoanTenure(13);
		boolean actual = customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertNotEquals(true, actual);
	}

	// VEHICLE_LOAN
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
		boolean actual = customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertEquals(true, actual);
	}

	@Test
	void loanCustomerVLoanVerificationNegativeTest() {
		Loan loanTemp = new EducationLoan();
		loanTemp.setLoanAmount(12654896);
		loanTemp.setLoanTenure(128);
		boolean actual = customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertNotEquals(true, actual);
	}

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
		boolean actual = customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertEquals(true, actual);
	}

	@Test
	void loanCustomerPLoanVerificationNegativeTest() {
		Loan loanTemp = new EducationLoan();
		loanTemp.setLoanAmount(1265489654);
		loanTemp.setLoanTenure(13);
		boolean actual = customerService.loanCustomerInputVerificationService(loanTemp);
		Assertions.assertNotEquals(true, actual);
	}

	// EDUCATION_LOAN
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
		assertTrue(customerService.loanCustomerInputVerificationService(loanTemp));

	}

	@Test
	void loanCustomerELoanVerificationNegativeTest() {
		Loan loanTemp = new EducationLoan();
		loanTemp.setLoanAmount(99999999);
		loanTemp.setLoanTenure(33);
		assertFalse(customerService.loanCustomerInputVerificationService(loanTemp));

	}

	@Test
	void verifyCustomerPositiveTest() {
		boolean actual = customerService.verifyCustomer("ckohli");
		Assertions.assertEquals(true, actual);
	}

	@Test
	void verifyCustomerNegativeTest() {
		boolean actual = customerService.verifyCustomer("ssinha");
		Assertions.assertNotEquals(true, actual);
	}

//	@Test
//	void verifyEmiApplicablePositiveTest() {
//		
//		loanMasterTemp.setLoanNumber(998);
//		//loanMasterTemp.set
//		LoanMaster actual=customerService.verifyEmiApplicable(loanMasterTemp.getLoanNumber());
//		System.out.println(actual);
//		LoanMaster loanMaster1 = new LoanMaster("998", LoanType.VEHICLE_LOAN, 1000000.00, 24, 45800.0, new CustomerBean("Chetan", "Kohli", "12346", "ckohli"),
//				16, 24, LocalDate.of(2018, 6, 15), LocalDate.now());
//		System.out.println(loanMaster1);
//		Assertions.assertEquals(loanMaster1, actual);
//	}

//	@Test
//	void updateEMIPositiveTest() {
//		
//		LoanMaster actual=customerService.updateEMI(19635, loanMasterTemp);
//		Assertions.assertEquals(expected, actual);
//		
//
//	}
	@Test
	void verifyLoanNumberPositiveTest() {
		boolean actual = customerService.verifyLoanNumber(loanMasterTemp.setLoanNumber(1000));

	}

	@Test
	void verifyLoanNumberNegativeTest() {
		boolean actual = customerService.verifyLoanNumber(loanMasterTemp.setLoanNumber(1000));
		Assertions.assertNotEquals(false, actual);
	}

	@Test
	void checkPreClosure() {
		loanMasterTemp.setAppliedDate(LocalDate.of(2018, Month.SEPTEMBER, 8));
		loanMasterTemp.setLoanType(LoanType.HOME_LOAN);
		loanMasterTemp.setEmiAmount(78954.00);
		loanMasterTemp.setInterestRate(8.5f);
		loanMasterTemp.setLoanAmount(2000000);
		loanMasterTemp.setLoanTenure(24);
		loanMasterTemp.setLoanNumber(1234);
		CustomerBean customerBean = new CustomerBean("Dev", "Goyal", "1234", "dgoyal");
		loanMasterTemp.setCustomerBean(customerBean);
		loanMasterTemp.setNumberOfEmis(13);
		loanMasterTemp.setTotalNumberOfEmis(24);
		loanMasterTemp.setNextEmiDate(LocalDate.of(2019, 10, 14));
		double actual = customerService.calculatePreClosure(loanMasterTemp);
		Assertions.assertEquals(1158400.640604138, actual);
	}

	@Test
	void checkPreClosureNeg() {
		loanMasterTemp.setAppliedDate(LocalDate.of(2018, Month.SEPTEMBER, 8));
		loanMasterTemp.setLoanType(LoanType.HOME_LOAN);
		loanMasterTemp.setEmiAmount(78954.00);
		loanMasterTemp.setInterestRate(8.5f);
		loanMasterTemp.setLoanAmount(2000000);
		loanMasterTemp.setLoanTenure(24);
		loanMasterTemp.setLoanNumber(1234);
		CustomerBean customerBean = new CustomerBean("Dev", "Goyal", "1234", "dgoyal");
		loanMasterTemp.setCustomerBean(customerBean);
		loanMasterTemp.setNumberOfEmis(13);
		loanMasterTemp.setTotalNumberOfEmis(24);
		loanMasterTemp.setNextEmiDate(LocalDate.of(2019, 10, 14));
		double actual = customerService.calculatePreClosure(loanMasterTemp);
		Assertions.assertNotEquals(110.60, actual);
	}

	@Test
	void sendPreClosureForVerificationPos() throws FileNotFoundException, IOException {
		loanMasterTemp.setAppliedDate(LocalDate.of(2018, Month.SEPTEMBER, 8));
		loanMasterTemp.setLoanType(LoanType.HOME_LOAN);
		loanMasterTemp.setEmiAmount(78954.00);
		loanMasterTemp.setInterestRate(8.5f);
		loanMasterTemp.setLoanAmount(2000000);
		loanMasterTemp.setLoanTenure(24);
		loanMasterTemp.setLoanNumber(1234);
		CustomerBean customerBean = new CustomerBean("Dev", "Goyal", "1234", "dgoyal");
		loanMasterTemp.setCustomerBean(customerBean);
		loanMasterTemp.setNumberOfEmis(13);
		loanMasterTemp.setTotalNumberOfEmis(24);
		loanMasterTemp.setNextEmiDate(LocalDate.of(2019, 10, 14));
		boolean actual = customerService.sendPreClosureForVerification(loanMasterTemp);
		Assertions.assertEquals(true, actual);

	}

	@Test
	void sendPreClosureForVerificationNeg() throws FileNotFoundException, IOException {
		loanMasterTemp.setAppliedDate(LocalDate.of(2018, Month.SEPTEMBER, 8));
		loanMasterTemp.setLoanType(LoanType.HOME_LOAN);
		loanMasterTemp.setEmiAmount(78954.00);
		loanMasterTemp.setInterestRate(8.5f);
		loanMasterTemp.setLoanAmount(2000000);
		loanMasterTemp.setLoanTenure(24);
		loanMasterTemp.setLoanNumber(1234);
		CustomerBean customerBean = new CustomerBean("Dev", "Goyal", "1234", "dgoyal");
		loanMasterTemp.setCustomerBean(customerBean);
		loanMasterTemp.setNumberOfEmis(13);
		loanMasterTemp.setTotalNumberOfEmis(24);
		loanMasterTemp.setNextEmiDate(LocalDate.of(2019, 10, 14));
		boolean actual = customerService.sendPreClosureForVerification(loanMasterTemp);
		Assertions.assertNotEquals(false, actual);

	}
}
