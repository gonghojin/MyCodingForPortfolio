package org.hojin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "APP_BOARD")
public class Board {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bno;
	
	@NotEmpty
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@NotEmpty
	@Column(name = "CONTENT", nullable = false)
	private String content;
	
	@NotEmpty
	@Column(name = "WRITER", nullable = false)
	private String writer;
	
	@Column(name = "REGDATE", insertable =false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date regdate;
	
	@Column(name = "VIEWCNT", insertable =false, updatable = false)
	private Integer viewcnt;
	

	public Integer getBno() {
		return bno;
	}
	
	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Integer getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(Integer viewcnt) {
		this.viewcnt = viewcnt;
	}

	@Override
	public String toString() {
		return "[BNO : " + bno + ", TITLE: " + title + "]";
	}
	
	
	
	
}
