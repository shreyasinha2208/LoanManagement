package com.cg.ibs.loanmgmt.bean;

public class Document {

	private String pathOfDocument=null;
	private String nameOfDocument=null;

	public Document() {
		super();
	}

	public Document(String nameOfDocument, String pathOfDocument) {
		super();
		this.nameOfDocument = nameOfDocument;
		this.pathOfDocument = pathOfDocument;
	}

	public String getNameOfDocument() {
		return nameOfDocument;
	}

	public void setNameOfDocument(String nameOfDocument) {
		this.nameOfDocument = nameOfDocument;
	}

	public String getPathOfDocument() {
		return pathOfDocument;
	}

	public void setPathOfDocument(String pathOfDocument) {
		this.pathOfDocument = pathOfDocument;
	}


}
