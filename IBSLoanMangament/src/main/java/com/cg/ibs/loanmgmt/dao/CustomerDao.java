package com.cg.ibs.loanmgmt.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.cg.ibs.loanmgmt.bean.CustomerBean;
import com.cg.ibs.loanmgmt.bean.LoanMaster;
import com.cg.ibs.loanmgmt.exception.IBSException;

public interface CustomerDao {

	public CustomerBean getCustomerDetails(String userId);

	public boolean verifyCustomer(String userId);

	public LoanMaster updateEMI(LoanMaster loanMaster);

	public LoanMaster getEMIDetails(String loanNumber);

	public List<LoanMaster> getHistory(String userId);

	public LoanMaster getPreClosureLoanDetails(String loanNumber);

	public boolean verifyLoanNumber(String loanNumber);

	public boolean sendPreClosureForVerification(LoanMaster loanMaster) throws FileNotFoundException, IOException;

	public boolean copyDocument(String srcPath, String destPath) throws IBSException;
}