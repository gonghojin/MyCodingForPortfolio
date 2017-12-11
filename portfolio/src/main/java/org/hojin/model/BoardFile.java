package org.hojin.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass//persistent class가 아니지만 공통으로 mapping되는 걸 허락
public class BoardFile {
	private String[] files;

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}
	
	
}
