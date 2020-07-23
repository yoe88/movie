package com.spring.baseSetting.dto;
import javax.servlet.http.HttpSession;

public class SearchCriteria extends Criteria {

	private String searchType = "";
	private String keyword = "";
  private String mem_id = "";
	 
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
	public String getMem_id() {
    return mem_id;
  }
  public void setMem_id(String mem_id) {
    this.mem_id = mem_id;
    System.out.println("s_id : " + this.mem_id);
  }
  @Override
  public String toString() {
    return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + ", mem_id=" + mem_id + "]";
  }
  
	
}
