package org.hojin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "APP_BOARD_ATTACH")
public class BoardAttach {
	@Id
	private String fullName;
	
	@NotEmpty
	@Column(name = "bno", nullable = false)
	private Integer bno;
	
	@Column(name = "REGDATE", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date regdate;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "[BNO : " + bno + ", FileName: " + fullName + "]";
	}
	
	
	
}
