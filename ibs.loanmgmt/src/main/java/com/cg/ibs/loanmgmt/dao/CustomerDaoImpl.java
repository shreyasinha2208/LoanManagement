package com.cg.ibs.loanmgmt.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cg.ibs.loanmgmt.bean.CustomerBean;
import com.cg.ibs.loanmgmt.bean.LoanMaster;
import com.cg.ibs.loanmgmt.exception.ExceptionMessages;
import com.cg.ibs.loanmgmt.exception.IBSException;

public class CustomerDaoImpl implements CustomerDao {
	public static DataBase base = new DataBase();
	public static Map<String, LoanMaster> loanData = base.getLoanMasterData();
	public static Map<String, CustomerBean> customerData = base.getCustomerBeanData();
	private static LoanMaster loanMaster = new LoanMaster();
	private static CustomerBean customer = new CustomerBean();

	public LoanMaster updateEMI(LoanMaster loanMaster) {
		loanMaster.setNumberOfEmis(loanMaster.getNumberOfEmis() + 1);
		loanMaster.setNextEmiDate(loanMaster.getNextEmiDate().plusMonths(1));
		loanData.replace(loanMaster.getLoanNumber(), loanMaster);
		return loanMaster;
	}

	public LoanMaster getEMIDetails(String loanNumber) {
		loanMaster = null;
		if (loanData.containsKey(loanNumber)) {
			loanMaster = loanData.get(loanNumber);
		}
		return loanMaster;
	}

	@Override
	public CustomerBean getCustomerDetails(String userId) {
		customer = null;
		if (customerData.containsKey(userId)) {
			customer = customerData.get(userId);
		}
		return customer;
	}

	// LoanDetails
	public List<LoanMaster> getHistory(
			String userId) { /* getting list of loans */

		List<LoanMaster> loanMasters = new ArrayList<>();

		for (Entry<String, LoanMaster> entry : loanData.entrySet()) {
			if (entry.getValue().getCustomerBean().getUserId().equals(userId)) {
				loanMasters.add(entry.getValue());
			}
		}
		return loanMasters;
	}

	// PreClosure
	public LoanMaster getPreClosureLoanDetails(String loanNumber) {
		/* Fetch loan Details against the loan number */
		loanMaster = null;
		if (loanData.containsKey(loanNumber)) {
			loanMaster = loanData.get(loanNumber); // LoanData HashMap
		}

		return loanMaster;
	}

	@Override
	public boolean verifyLoanNumber(
			String loanNumber) { /* Verification of loan number (Pre Closure) */
		boolean check = false;
		if (loanData.containsKey(loanNumber)) {
			check = true;
		}
		return check;
	}

	public boolean sendPreClosureForVerification(LoanMaster loanMaster) throws FileNotFoundException,
			IOException { /* Send Loan for Pre Closure */
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./PreClosureDetails.dat"));
		out.writeObject(loanMaster);
		out.close();
		return true;

	}

	public boolean copyDocument(String srcPath, String destPath) throws IBSException {
		boolean isDone = false;
		File srcFile = new File(srcPath);
		File destFile = new File(destPath);

		if (srcFile.exists()) {
			try (FileInputStream fin = new FileInputStream(srcFile);
					FileOutputStream fout = new FileOutputStream(destFile)) {

				byte[] data = new byte[1024];

				while (fin.read(data) > -1) {
					fout.write(data);
				}
				isDone = true;
			} catch (IOException exp) {
				throw new IBSException(ExceptionMessages.messageForFileNotFound);
			}
		}
		return isDone;
	}

	@Override
	public boolean verifyCustomer(String userId) {
		boolean check = false;
		if (customerData.containsKey(userId)) {
			check = true;
		}
		return check;
	}
}
