package com.aloa.manage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aloa.account.Account;
import com.aloa.restaurant.Restaurant;

@Component("ManagerDAO")
@Transactional
public class ManagerDAOMybatis implements ManagerDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<MemberDTO> memberList() {
		List<MemberDTO> list = sqlSession.selectList("managerMapper.memberList");
		
		return list;
	}

	@Override
	public List<Restaurant> restaurantList() {
		List<Restaurant> list = sqlSession.selectList("managerMapper.restaurantList");
		
		return list;
	}
	
	public Account checkId(String id) {
		Account account = sqlSession.selectOne("managerMapper.checkId", id);
		return account;
	}

	@Override
	public void insertRestaurant(Map<String, String> map) {
		sqlSession.insert("managerMapper.insertRestaurant", map);
		
	}

	@Override
	public List<MemberDTO> memberBlackList() {
		List<MemberDTO> list = sqlSession.selectList("managerMapper.memberBlackList");
		
		return list;
	}

	@Override
	public void blacklistAdd(String[] checkEmail) {
		for(int i = 0; i < checkEmail.length; i++){
			
			sqlSession.update("managerMapper.blacklistAdd",checkEmail[i]);
		}
	}
	
	@Override
	public MemberDTO sepcificMember(String findId) {
		
		MemberDTO memberDTO = sqlSession.selectOne("managerMapper.sepcificMember", Integer.parseInt(findId));
		return memberDTO;
	}
	
	@Override
	public void memberUpdate(MemberDTO memberDTO) {
		sqlSession.update("managerMapper.memberUpdate", memberDTO);
		
	}
	
	@Override
	public void restaurantDelete(int[] checkResno) {
		for(int i = 0; i < checkResno.length; i++){
			
			sqlSession.delete("managerMapper.restaurantDelete",checkResno[i]);
		}
		
	}

	@Override
	public Restaurant restaurantInfo(String resno) {
		Restaurant restaurant = sqlSession.selectOne("managerMapper.restaurantInfo", Integer.parseInt(resno));
		return restaurant;
	}

	@Override
	public void restaurantUpdate(Restaurant restaurant) {
		sqlSession.update("managerMapper.restaurantUpdate", restaurant);
	}
	
	public List<ReportRevDTO> revClaimList() {
		List<ReportRevDTO> list = sqlSession.selectList("managerMapper.revClaimList");
		return list;
	}
	
	@Override
	public void reviewRemove(String[] checkNo) {
		for(int i = 0 ; i < checkNo.length; i++ ){
			sqlSession.delete("managerMapper.reviewRemove", Integer.parseInt(checkNo[i]));
		}
	}

	@Override
	public List<ReportResDTO> resClaimList() {
		List<ReportResDTO> list = sqlSession.selectList("managerMapper.resClaimList");
	
		return list;
	}

	@Override
	public ReportResDTO restaurantClaimDetail(String resNo) {
		ReportResDTO reportResDTO = sqlSession.selectOne("managerMapper.restaurantClaimDetail",Integer.parseInt(resNo));
		System.out.println(reportResDTO.getResNo());
		return reportResDTO;
	}

	@Override
	public void restaurantClaimRefuse(String resNo) {
		sqlSession.update("managerMapper.restaurantClaimRefuse", Integer.parseInt(resNo));
		
	}

	@Override
	public void restaurantClaimUnused(String resNo) {
		sqlSession.delete("managerMapper.restaurantClaimUnused", Integer.parseInt(resNo));
		
	}

	@Override
	public ReportRevDTO reviewClaimDetail(String reviewNo) {
		ReportRevDTO reportRevDTO = sqlSession.selectOne("managerMapper.reviewClaimDetail", Integer.parseInt(reviewNo));
		
		return reportRevDTO;
	}

	@Override
	public void reviewClaimRefuse(String reviewNo) {
		System.out.println(reviewNo);
		sqlSession.update("managerMapper.reviewClaimRefuse", Integer.parseInt(reviewNo));
		
	}

	@Override
	public void reviewClaimUnused(String reviewNo) {
		sqlSession.delete("managerMapper.reviewClaimUnused", Integer.parseInt(reviewNo));
		
	}


	
	

	

}
