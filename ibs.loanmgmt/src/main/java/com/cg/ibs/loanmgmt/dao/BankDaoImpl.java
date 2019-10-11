package com.cg.ibs.loanmgmt.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

import com.cg.ibs.loanmgmt.bean.CustomerBean;
import com.cg.ibs.loanmgmt.bean.LoanMaster;

public class BankDaoImpl implements BankDao {
	public static DataBase base = new DataBase();
	public static Map<String, LoanMaster> loanData = base.getLoanMasterData();
	public static Map<String, CustomerBean> customerData = base.getCustomerBeanData();
	private LoanMaster loanMaster = new LoanMaster();

	@Override
	public boolean saveLoan(LoanMaster loanMaster) {
		loanData.put(loanMaster.getLoanNumber(), loanMaster);
		return true;
	}

	@Override
	public StringBuilder getDocumentsForVerification() throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("./Documents.dat"));
		StringBuilder sb = new StringBuilder();
		sb = (StringBuilder) in.readObject();
		in.close();
		return sb;
	}

	@Override
	public LoanMaster getLoanDetailsForVerification() throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("./LoanDetails.dat"));
		loanMaster = (LoanMaster) in.readObject();
		in.close();
		return loanMaster;
	}

	@Override
	public boolean copyDocument(String srcPath, String destPath) {

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
				// srcFile.delete();
			} catch (IOException e) {
				// raise a user defined exception
			}
		} else {
			// throw your exception
		}
		return isDone;
	}

	@Override
	public LoanMaster updatePreClosure(
			LoanMaster loanMaster) { /*
										 * Updating EMI after approval of
										 * PreClosure
										 */
		loanMaster.setNumberOfEmis(loanMaster.getTotalNumberOfEmis());
		loanMaster.setNextEmiDate(null);
		loanData.replace(loanMaster.getLoanNumber(), loanMaster);
		return loanMaster;
	}

	@Override
	public LoanMaster getPreClosureDetailsForVerification() throws IOException,
			ClassNotFoundException { /* Fetches Details for verification */
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("./PreClosureDetails.dat"));
		loanMaster = (LoanMaster) in.readObject();
		in.close();
		return loanMaster;
	}

}
