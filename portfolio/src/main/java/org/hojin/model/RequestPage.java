package org.hojin.model;

public class RequestPage {
	private int page;

	public RequestPage() {
		this.page = 1;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page < 0){
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
	public int getPageStart(){
		return (page - 1) * 6;
	}
	
	@Override
	public String toString() {
		return "RequestPage [page = " + page + "]";
	}
	
}
