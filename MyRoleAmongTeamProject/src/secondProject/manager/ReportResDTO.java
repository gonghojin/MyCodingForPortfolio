package com.aloa.manage;

public class ReportResDTO {
	private String reEmail;
	private String reportName;
	private String reportContent;
	private int resNo;
	private String resname;
	private String deEmail;
	private String reStatus;
	//private String image;

	public String getReEmail() {
		return reEmail;
	}

	public void setReEmail(String reEmail) {
		this.reEmail = reEmail;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public int getResNo() {
		return resNo;
	}

	public void setResNo(int resNo) {
		this.resNo = resNo;

	}

	public String getDeEmail() {
		return deEmail;
	}

	public String getResname() {
		return resname;
	}

	public void setResname(String resname) {
		this.resname = resname;
	}

	public void setDeEmail(String deEmail) {
		this.deEmail = deEmail;
	}

	public String getReStatus() {
		return reStatus;
	}

	public void setReStatus(String reStatus) {
		this.reStatus = reStatus;
	}
/*	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}*/
}
