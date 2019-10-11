package com.cg.ibs.loanmgmt.exception;

public interface ExceptionMessages {

	public static String messageForWrongInput = "Sorry! Invalid Input.";
	public static String messageForInputMismatch = "Sorry! You are allowed to enter a single-digit numeric.";
	public static String messageForWrongFileNameInput = "File name should only include alphabets, begin with an upper case and contain a maximum of  8 characters.";
	public static String messageForFileNotFound = "Sorry!  File with the specified pathname does not exist or is inaccessible";
	public static String messageForIOException = "Sorry! Failed or interrupted input/output operation.";
	public static String messageForException = "Sorry! An error occured.";
	public static String messageForInvalidLoanNumber = "Loan number can't begin with 0 and must contain 3 or 4 numerics.";
}
