package org.hojin.model;

public class SearchPage extends RequestPage {
	private String searchType;
	private String keyword;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return super.toString() + "SearchPage [searchType = " + searchType +
				", keyWord = " + keyword + "]";
	}
	
	
}
