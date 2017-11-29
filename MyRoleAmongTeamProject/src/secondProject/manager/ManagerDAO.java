package com.aloa.manage;

import java.util.List;
import java.util.Map;

import com.aloa.account.Account;
import com.aloa.restaurant.Restaurant;

public interface ManagerDAO {
	public List<MemberDTO> memberList();

	public List<Restaurant> restaurantList();

	public List<MemberDTO> memberBlackList();

	public void blacklistAdd(String[] checkEmail);
	
	public MemberDTO sepcificMember(String findId);
	
	public void memberUpdate(MemberDTO memberDTO);
	
	public Account checkId(String id);

	public void insertRestaurant(Map<String, String> map);

	public void restaurantDelete(int[] checkResno);

	public Restaurant restaurantInfo(String resno);

	public void restaurantUpdate(Restaurant restaurant);

	public List<ReportRevDTO> revClaimList();
	
	public void reviewRemove(String[] checkNo);

	public List<ReportResDTO> resClaimList();

	public ReportResDTO restaurantClaimDetail(String resNo);

	public void restaurantClaimRefuse(String resNo);

	public void restaurantClaimUnused(String resNo);

	public ReportRevDTO reviewClaimDetail(String reviewNo);

	public void reviewClaimRefuse(String reviewNo);

	public void reviewClaimUnused(String reviewNo);


	

	


}
