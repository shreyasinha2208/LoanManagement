package com.cg.ibs.loanmgmt.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.ibs.loanmgmt.bean.Document;
import com.cg.ibs.loanmgmt.bean.LoanBean;
import com.cg.ibs.loanmgmt.bean.LoanMaster;
import com.cg.ibs.loanmgmt.bean.LoanType;
import com.cg.ibs.loanmgmt.exception.ExceptionMessages;
import com.cg.ibs.loanmgmt.exception.IBSException;
import com.cg.ibs.loanmgmt.service.BankService;
import com.cg.ibs.loanmgmt.service.BankServiceImpl;
import com.cg.ibs.loanmgmt.service.CustomerService;
import com.cg.ibs.loanmgmt.service.CustomerServiceImpl;
import com.cg.ibs.loanmgmt.service.LoanService;
import com.cg.ibs.loanmgmt.service.LoanServiceImpl;

public class User implements ExceptionMessages {
	static Scanner read = new Scanner(System.in);

	public void userLogin() throws IBSException {
		UserOptions choice = null;
		while (choice != UserOptions.EXIT) {
			System.out.println("Login as an existing customer or admin? ");
			System.out.println("------------------------");
			for (UserOptions menu : UserOptions.values()) {
				System.out.println((menu.ordinal() + 1) + "\t" + menu);
			}
			String userLoginInput = read.next();
			Pattern pattern = Pattern.compile("[0-9]{1}");
			Matcher matcher = pattern.matcher(userLoginInput);
			if (matcher.matches()) {
				int ordinal = Integer.parseInt(userLoginInput);
				if (ordinal >= 1 && ordinal < (UserOptions.values().length) + 1) {
					choice = UserOptions.values()[ordinal - 1];
					switch (choice) {
					case EXISTING_CUSTOMER:
						init();
						break;
					case ADMIN:
						adminInit();
						break;
					case EXIT:
						System.out.println("Have a nice day!");
						break;
					}
				} else {
					choice = null;
					try {
						if (choice == null) {
							throw new IBSException(ExceptionMessages.MESSAGEFORWRONGINPUT);
						}
					} catch (IBSException exp) {
						System.out.println(exp.getMessage());

					}

				}
			} else {
				try {
					throw new IBSException(ExceptionMessages.MESSAGEFORINPUTMISMATCH);
				} catch (IBSException exp) {
					System.out.println(exp.getMessage());
				}
			}
		}
	}

	/* CUSTOMER_LOGIN */

	static CustomerService customerService = new CustomerServiceImpl();
	Document document = new Document();
	LoanBean loan = new LoanBean();
	LoanMaster loanMaster = new LoanMaster();

	public void init() throws IBSException {
		CustomerOptions customerChoice = null;
		while (customerChoice != CustomerOptions.EXIT) {
			System.out.println("You've entered as an Existing customer :");
			System.out.println("--------------------");
			System.out.println("Please select one of the following to proceed further : ");
			System.out.println("--------------------");
			for (CustomerOptions menu : CustomerOptions.values()) {
				System.out.println((menu.ordinal() + 1) + ".\t" + menu);
			}
			System.out.println("Choice");
			String customerLoginInput = read.next();
			Pattern pattern = Pattern.compile("[0-9]{1}");
			Matcher matcher = pattern.matcher(customerLoginInput);
			if (matcher.matches()) {
				int ordinal = Integer.parseInt(customerLoginInput);
				if (ordinal >= 1 && ordinal < (CustomerOptions.values().length) + 1) {
					customerChoice = CustomerOptions.values()[ordinal - 1];
					switch (customerChoice) {
					case APPLY_LOAN:
						selectLoanType();
						break;
					case PAY_EMI:
						System.out.println("Enter the loan Number: ");
						payEMI(read.next());
						break;
					case APPLY_PRECLOSURE:
						applyPreClosure();
						break;
					case VIEW_HISTORY:
						System.out.println(" Enter your customer ID: ");
						getLoanDetails(read.next());
						break;
					case EXIT:
						System.out.println("Thank You! Come Again.");
						userLogin();
					}

				} else {
					customerChoice = null;
					try {
						if (customerChoice == null)

							throw new IBSException(ExceptionMessages.MESSAGEFORWRONGINPUT);
					} catch (IBSException exp) {
						System.out.println(exp.getMessage());
					}
				}
			} else {
				try {
					throw new IBSException(ExceptionMessages.MESSAGEFORINPUTMISMATCH);
				} catch (IBSException exp) {
					System.out.println(exp.getMessage());
				}
			}
		}
	}

	private void selectLoanType() throws IBSException {
		LoanTypes choice = null;
		while (choice != LoanTypes.GO_BACK) {
			System.out.println("Menu");
			System.out.println("--------------------");
			System.out.println("Choice");
			System.out.println("--------------------");
			for (LoanTypes menu : LoanTypes.values()) {
				System.out.println((menu.ordinal() + 1) + "\t" + menu);
			}
			System.out.println("Choice");
			String customerLoginInput = read.next();
			Pattern pattern = Pattern.compile("[0-9]{1}");
			Matcher matcher = pattern.matcher(customerLoginInput);
			if (matcher.matches()) {
				int ordinal = Integer.parseInt(customerLoginInput);
				if (ordinal >= 1 && ordinal < (LoanTypes.values().length) + 1) {
					choice = LoanTypes.values()[ordinal - 1];

					switch (choice) {
					case HOME_LOAN:
						createHomeLoan();
						break;
					case EDUCATION_LOAN:
						createEducationLoan();
						break;
					case PERSONAL_LOAN:
						createPersonalLoan();
						break;
					case VEHICLE_LOAN:
						createVehicleLoan();
						break;
					case GO_BACK:
						System.out.println("Thank You!");
						break;
					}
				} else {
					choice = null;
					try {
						if (choice == null) {
							throw new IBSException(ExceptionMessages.MESSAGEFORWRONGINPUT);
						}
					} catch (IBSException exp) {
						System.out.println(exp.getMessage());

					}
				}

			} else {
				try {
					throw new IBSException(ExceptionMessages.MESSAGEFORINPUTMISMATCH);
				} catch (IBSException exp) {
					System.out.println(exp.getMessage());
				}
			}

		}
	}

	// Apply Loan
	private void createHomeLoan() throws IBSException {
		LoanService loanService = new LoanServiceImpl();
		System.out.print("Interest Rate for Home Loan is : ");
		System.out.println("8.5 %\n");
		boolean check = false;
		while (!check) {
			System.out.println("Enter the Loan Amount required  : ");
			System.out.println("\n\t***Minimum Loan Limit = 10 Thousand***");
			System.out.println("\t***Maximum Loan Limit = 2 Crores***");
			loan.setLoanAmount(read.nextDouble());
			System.out.println("Enter the Loan Tenure : ");
			System.out.println("\n\t***Tenure should be in months and multiple of 6***");
			loan.setLoanTenure(read.nextInt());
			loan.setLoanType(LoanType.HOME_LOAN);
			loan.setInterestRate(8.5f);
			check = customerService.loanCustomerInputVerificationService(loanService.setLoanDetails(loan));
			if (!check) {
				System.out.println("INVALID INPUT! Adhere to the Loan Limits Specified \n ");
			}
		}
		loan.setEmiAmount(customerService.calculateEmi(loanService.setLoanDetails(loan)));
		System.out.println("---------*********---------\n\n" + loan);
		addLoan(loan);
	}

	private void createPersonalLoan() throws IBSException {
		LoanService loanService = new LoanServiceImpl();
		System.out.print("Interest Rate for Home Loan is : ");
		System.out.println("10.75 %");
		boolean check = false;
		while (!check) {
			System.out.println("Enter the Loan Amount required  : ");
			System.out.println("***Minimum Loan Limit = 10 Thousand***");
			System.out.println("***Maximum Loan Limit = 20 Lakhs***");
			loan.setLoanAmount(read.nextDouble());
			System.out.println("Enter the Loan Tenure : ");
			System.out.println("***Tenure should be in months and multiple of 6***");
			loan.setLoanTenure(read.nextInt());
			loan.setLoanType(LoanType.PERSONAL_LOAN);
			loan.setInterestRate(10.75f);
			check = customerService.loanCustomerInputVerificationService(loanService.setLoanDetails(loan));
			if (!check) {
				System.out.println("INVALID INPUT! Adhere to the Loan Limits Specified \n ");
			}
		}
		loan.setEmiAmount(customerService.calculateEmi(loanService.setLoanDetails(loan)));
		System.out.println("---------*********---------\n\n" + loan);
		addLoan(loan);
	}

	private void createVehicleLoan() throws IBSException {
		LoanService loanService = new LoanServiceImpl();
		System.out.print("Interest Rate for Vehicle Loan is : ");
		System.out.println("9.25 %");
		boolean check = false;
		while (!check) {
			System.out.println("Enter the Loan Amount required  : ");
			System.out.println("***Minimum Loan Limit = 10 Thousand***");
			System.out.println("***Maximum Loan Limit = 30 Lakhs***");
			loan.setLoanAmount(read.nextDouble());
			System.out.println("Enter the Loan Tenure : ");
			System.out.println("***Tenure should be in months and multiple of 6***");
			loan.setLoanTenure(read.nextInt());
			loan.setLoanType(LoanType.VEHICLE_LOAN);
			loan.setInterestRate(9.25f);
			check = customerService.loanCustomerInputVerificationService(loanService.setLoanDetails(loan));
			if (!check) {
				System.out.println("INVALID INPUT! Adhere to the Loan Limits Specified \n ");
			}
		}
		loan.setEmiAmount(customerService.calculateEmi(loanService.setLoanDetails(loan)));
		System.out.println("---------*********---------\n\n" + loan);
		addLoan(loan);
	}

	private void createEducationLoan() throws IBSException {
		LoanService loanService = new LoanServiceImpl();
		System.out.print("Interest Rate for Vehicle Loan is : ");
		System.out.println("11.35 %");
		boolean check = false;
		while (!check) {
			System.out.println("Enter the Loan Amount required  : ");
			System.out.println("***Minimum Loan Limit = 10 Thousand***");
			System.out.println("***Maximum Loan Limit = 50 Lakhs***");
			loan.setLoanAmount(read.nextDouble());
			System.out.println("Enter the Loan Tenure : ");
			System.out.println("***Tenure should be in months and multiple of 6***");
			loan.setLoanTenure(read.nextInt());
			loan.setLoanType(LoanType.EDUCATION_LOAN);
			loan.setInterestRate(11.35f);
			check = customerService.loanCustomerInputVerificationService(loanService.setLoanDetails(loan));
			if (!check) {
				throw new IBSException(ExceptionMessages.MESSAGEFORWRONGINPUT);
			}
		}
		loan.setEmiAmount(customerService.calculateEmi(loanService.setLoanDetails(loan)));
		System.out.println("---------*********---------\n\n" + loan);
		addLoan(loan);
	}

	private void addLoan(LoanBean loanBean) throws IBSException {
		LoanService loanService = new LoanServiceImpl();
		System.out.println("\nWould you like to apply for the Loan ?");
		System.out.println("1. Yes \n2. No");
		String applyLoanChoice = read.next();
		Pattern pattern = Pattern.compile("[0-9]{1}");
		Matcher matcher = pattern.matcher(applyLoanChoice);
		boolean matches = matcher.matches();
		if (matches) {
			int selection = Integer.parseInt(applyLoanChoice);
			switch (selection) {
			case 1: {
				System.out.println("\nNew Loan Application\n");
				String custId = "";
				do {
					System.out.println("Enter your customer id :- ");
					custId = read.next();
				} while (customerService.verifyCustomer(custId) != true);
				try {
					customerService.sendLoanForVerification(
							customerService.getLoanValues(loanService.setLoanDetails(loanBean), custId));
					uploadDocument();
				} catch (FileNotFoundException exp) {
					throw new IBSException(ExceptionMessages.MESSAGEFORFILENOTFOUND);
				} catch (IOException exp) {
					throw new IBSException(ExceptionMessages.MESSAGEFORIOEXCEPTION);
				}
			}

			case 2: {
				System.out.println("Thank You!");
			}
			}
		} else {
			try {
				throw new IBSException(ExceptionMessages.MESSAGEFORINPUTMISMATCH);
			} catch (IBSException exp) {
				System.out.println(exp.getMessage());
			}
		}
	}

	private void uploadDocument() throws IBSException {
		boolean uploadCheck = false;
		System.out.println("Enter the name of the document to be uploaded");
		String nameOfDocument = read.next();
		Pattern pattern = Pattern.compile("[A-Z]{1}[a-z]{0,7}");
		Matcher matcher = pattern.matcher(nameOfDocument);
		boolean matches = matcher.matches();
		if (matches) {
			document.setNameOfDocument(nameOfDocument);
			System.out.println("Enter the path of the document to be uploaded.(Including File Name)");
			document.setPathOfDocument(read.next());
			uploadCheck = customerService.getDocument(document);
			if (uploadCheck) {
				System.out.println("Document successfully uploaded.");
			} else {
				try {
					throw new IBSException(ExceptionMessages.MESSAGEFORFILENOTFOUND);
				} catch (IBSException exp) {
					System.out.println(exp.getMessage());
				}
			}
		} else {
			try {
				throw new IBSException(ExceptionMessages.MESSAGEFORWRONGFILENAMEINPUT);
			} catch (IBSException exp) {
				System.out.println(exp.getMessage());
				exp.getMessage();
			}
		}
	}

	// Pay EMI
	private void payEMI(String loanNumber) throws IBSException {

		loanMaster = customerService.verifyEmiApplicable(loanNumber);
		if (loanMaster == null) {
			System.out.println("Loan is either closed or does not exist");
		} else {
			System.out.println(loanMaster);
			System.out.println("\nEnter the amount to pay EMI:");
			String emiPaymentAmount = read.next();
			Pattern pattern = Pattern.compile("([0-9]{4,7})\\.([0-9]{1,2})");
			Matcher matcher = pattern.matcher(emiPaymentAmount);
			if (matcher.matches()) {
				loanMaster = customerService.updateEMI(Double.valueOf(emiPaymentAmount), loanMaster);
				if (loanMaster == null) {
					System.out.println("Transaction Unsuccessful! Try Again");
				} else {
					System.out.println("Transaction successful! \nNumber Of EMI's left : "
							+ (loanMaster.getTotalNumberOfEmis() - loanMaster.getNumberOfEmis())
							+ "\nNext date for EMI payment is : " + loanMaster.getNextEmiDate());
				}
			} else {
				try {
					throw new IBSException(ExceptionMessages.MESSAGEFORINPUTMISMATCHATEMI);
				} catch (IBSException exp) {
					System.out.println(exp.getMessage());
				}
			}
		}
	}

	// APPLY PRECLOSURE
	private void applyPreClosure() throws IBSException {
		System.out.println("Enter the Loan Number :");
		String loanNumber = read.next();
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{2,3}");
		Matcher matcher = pattern.matcher(loanNumber);
		boolean matches = matcher.matches();
		if (matches) {
			boolean check = customerService.verifyLoanNumber(loanNumber);
			if (check) {
				System.out.println("Your Loan against Loan Number : " + loanNumber
						+ " is applicable for PreClosure.\nFollowing are the details of the loan :");
				loanMaster = customerService.getPreClosureLoanDetails(loanNumber);
				System.out.println(loanMaster);
				System.out.println(
						"The amount for PreClosure is: INR:\t " + customerService.calculatePreClosure(loanMaster));
				System.out.println("\nDo you want to pay the amount? \n1. Yes\n2. No");
				switch (read.nextInt()) {
				case 1:
					try {
						if (customerService.sendPreClosureForVerification(loanMaster)) {
							System.out.println("Thank You!\n Your loan has been sent for verification.");
						}
					} catch (IOException exp) {
						throw new IBSException(ExceptionMessages.MESSAGEFORIOEXCEPTION);
					}
				case 2:
					init();
				}

			} else {
				System.out.println("Loan with Loan Number : " + loanNumber + " does not exist");
			}
		} else {
			try {
				throw new IBSException(ExceptionMessages.MESSAGEFORINVALIDLOANNUMBER);
			} catch (IBSException exp) {
				System.out.println(exp.getMessage());
			}
		}
	}

	// VIEW HISTORY
	private void getLoanDetails(String userId) {
		List<LoanMaster> loanMasters = new ArrayList<>();
		loanMasters = customerService.getHistory(userId);
		/* getting collection of all the loans related to given userID */
		try {
			if (loanMasters.isEmpty()) {
				throw new IBSException(ExceptionMessages.MESSAGEFORWRONGINPUT);
			} else {
				System.out.println(loanMasters);
			}
		} catch (IBSException exp) {
			System.out.println(exp.getMessage());
		}
	}

	// ADMIN_LOGIN
	BankService bankService = new BankServiceImpl();

	public void adminInit() throws IBSException {
		AdminOptions adminChoice = null;
		while (adminChoice != AdminOptions.GO_BACK) {
			System.out.println("Menu");
			System.out.println("--------------------");
			System.out.println("Choice");
			System.out.println("--------------------");
			for (AdminOptions menu : AdminOptions.values()) {
				System.out.println((menu.ordinal() + 1) + "\t" + menu);
			}
			System.out.println("Choice");
			int ordinal = read.nextInt();
			if (ordinal >= 1 && ordinal < (AdminOptions.values().length) + 1) {
				adminChoice = AdminOptions.values()[ordinal - 1];
				switch (adminChoice) {
				case VERIFY_LOAN:
					verifyLoan();
					break;
				case VERIFY_PRECLOSURE:
					verifyPreClosure();
					break;
				case GO_BACK:
					userLogin();
				}

			} else {
				adminChoice = null;
				try {
					if (adminChoice == null) {
						throw new IBSException(ExceptionMessages.MESSAGEFORWRONGINPUT);
					}
				} catch (IBSException exp) {
					System.out.println(exp.getMessage());

				}

			}
		}
	}

	// GET DOCUMENTS
	public void selectDocument() throws IBSException {
		List<String> listOfFilesAvailable = bankService.getFilesAvailable();
		for (int i = 0; i < listOfFilesAvailable.size(); i++) {
			System.out.println(i + "\t" + listOfFilesAvailable.get(i));
		}
		System.out.println("Enter file index to download: ");
		int index = read.nextInt();
		System.out.println("Enter a download folder loc: ");
		String dwnLoc = read.next();
		String selectedFileName = listOfFilesAvailable.get(index);
		if (bankService.downloadDocument(dwnLoc, selectedFileName)) {
			System.out.println("Documents downloaded.");
		} else {
			throw new IBSException(ExceptionMessages.MESSAGEFORFILENOTFOUND);
		}
	}

	// VERIFY LOAN
	public void verifyLoan() throws IBSException {
		{
			try {
				loanMaster = bankService.getLoanDetailsForVerification();
			} catch (ClassNotFoundException | IOException exp) {
				throw new IBSException(ExceptionMessages.MESSAGEFORIOEXCEPTION);
			}
			System.out.println("Downloading Document");
			try {
				selectDocument();
			} catch (Exception exp) {
				throw new IBSException(ExceptionMessages.MESSAGEFORFILENOTFOUND);
			}
			System.out.println("Application For Loan : " + loanMaster);
			System.out.println("Document for the above loan has been downloaded in the downloads folder ");
			System.out.println("Verification Response : ");
			System.out.println("1. Approve Loan \n 2. Decline Loan");
			switch (read.nextInt()) {
			case 1:
				try {
					bankService.verifyLoan(loanMaster);
				} catch (Exception exp) {
					throw new IBSException(ExceptionMessages.MESSAGEFORFILENOTFOUND);
				}
				break;
			case 2:
				adminInit();
				break;
			default:
				throw new IBSException(ExceptionMessages.MESSAGEFORWRONGINPUT);
			}
		}

	}

	// VERIFY PRECLOSURE
	public void verifyPreClosure() throws IBSException {
		try {
			loanMaster = bankService.getPreClosureDetailsForVerification();
		} catch (ClassNotFoundException | IOException exp) {
			throw new IBSException(ExceptionMessages.MESSAGEFORFILENOTFOUND);
		} /* fetch data for verification */
		System.out.println("Pre-Closure application For Loan : " + loanMaster);
		System.out.println("\n1. Approve Loan \n2. Decline Loan");
		switch (read.nextInt()) {
		case 1:
			bankService.updatePreClosure(loanMaster);
			System.out.println(
					"Preclosure for Loan Number : " + loanMaster.getLoanNumber() + " has been approved and updated.");
			break;
		case 2:
			System.out.println("PreClosure for Loan Number : " + loanMaster.getLoanNumber() + " has been declined.");
			adminInit();
			break;
		default:
			throw new IBSException(ExceptionMessages.MESSAGEFORWRONGINPUT);
		}
	}

	public static void main(String[] args) throws IBSException {
		User user = new User();
		user.userLogin();
	}

}
