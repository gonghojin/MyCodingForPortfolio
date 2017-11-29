package com.aloa.manage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aloa.account.Account;
import com.aloa.restaurant.Restaurant;

@Controller
public class ManagerController {
	@Autowired
	ManagerDAO managerDAO;

	//관리자 메인
	@RequestMapping(value = "manager/index", method = RequestMethod.GET)
	public String test() {
		return "manager/index";
	}

	// 회원관리
	@RequestMapping(value = "/manager/memberManage")
	public ModelAndView memberManage() {
		ModelAndView mav = new ModelAndView();

		List<MemberDTO> list = null;
		if (managerDAO.memberList() != null) {
			list = managerDAO.memberList();
		}
		
		mav.addObject("list", list);
		mav.setViewName("manager/memberManage");

		return mav;
	}
	//회원관리-삭제
	@RequestMapping(value = "/manager/blacklistAdd", method = RequestMethod.POST)
	public ModelAndView listRemove(@RequestParam String[] checkEmail){
		
		for(int i=0; i<checkEmail.length; i++){
			System.out.println(checkEmail[i]);
		}
		
		managerDAO.blacklistAdd(checkEmail);
		
		return new ModelAndView("redirect:/manager/memberManage");
	}
	
	//회원관리- 수정 뷰
	@RequestMapping(value = "/manager/memberUpdateView", method = RequestMethod.POST)
	public ModelAndView memberUpdateView(@RequestParam String findId ){
		MemberDTO memberDTO = managerDAO.sepcificMember(findId);
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDTO", memberDTO);
		mav.setViewName("manager/memberUpdate");
		
		return mav;
	}
	
	//회원관리 - 수정
	@RequestMapping(value = "/manager/memberUpdate", method = RequestMethod.POST)
	public ModelAndView memberUpdate(@ModelAttribute MemberDTO memberDTO){		
		managerDAO.memberUpdate(memberDTO);
		
		return new ModelAndView("redirect:/manager/memberManage");
	}
	
	@RequestMapping(value = "/manager/blacklistManage")
	public ModelAndView blacklistManage() {
		ModelAndView mav = new ModelAndView();

		List<MemberDTO> list = null;

		if (managerDAO.memberList() != null) {
			list = managerDAO.memberBlackList();

		}

		mav.addObject("list", list);
		mav.setViewName("manager/blacklistManage");

		return mav;
	}

	@RequestMapping(value = "manager/usingRejectedlistManage", method = RequestMethod.GET)
	public String usingRejectedlistManage() {
		return "manager/usingRejectedlistManage";
	}

	//맛집관리
	@RequestMapping(value = "manager/restaurantManage", method = RequestMethod.GET)
	public ModelAndView restaurantManage(){
		
		List<Restaurant> list =null;
		if(managerDAO.memberList() != null){
			list = managerDAO.restaurantList();
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list", list);
		mav.setViewName("manager/restaurantManage");

		return mav;
	}
	
	//맛집관리삭제
	@RequestMapping(value = "/manager/restaurantRemove", method = RequestMethod.POST)
	public ModelAndView restaurantRemove(@RequestParam int[] checkResno) {
		
		managerDAO.restaurantDelete(checkResno);
	
		return (ModelAndView)new ModelAndView("redirect:/manager/restaurantManage");

	}
	
	//맛집관리수정뷰
	@RequestMapping(value = "/manager/restaurantUpdateView", method = RequestMethod.POST)
	public ModelAndView restaurantUpdateView(@RequestParam String findresno) {
		Restaurant restaurant = managerDAO.restaurantInfo(findresno);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("restaurant", restaurant);
		mav.setViewName("manager/restaurantUpdate");
		
		return mav;
	}
	
	//맛집관리수정
		@RequestMapping(value = "/manager/restaurantUpdate", method = RequestMethod.POST)
		public ModelAndView restaurantUpdate(@ModelAttribute Restaurant restaurant) {
			managerDAO.restaurantUpdate(restaurant);
			
			return (ModelAndView)new ModelAndView("redirect:/manager/restaurantManage");
		}

	
	@RequestMapping(value = "/manager/restaurantAgree", method = RequestMethod.GET)
	public String restaurantAgree() {
		return "manager/restaurantAgree";
	}
	
	//맛집신청승인페이지
	@RequestMapping(value = "manager/restaurantAgreeDetail", method = RequestMethod.GET)
	public String restaurantAgreeDetail(){
		return "manager/restaurantAgreeDetail";
	}
	
	//맛집신청페이지 id체크
	@RequestMapping(value = "manager/restaurantAgreeDetailCheckId", method = RequestMethod.GET)
	public ModelAndView restaurantAgreeDetailCheckId(@RequestParam String id){
		
		ModelAndView mav = new ModelAndView();
		
		Account check = managerDAO.checkId(id);
		//System.out.println("check="+check);
		
		if(check.getEmail()!=null){
			if(check.getServer()==1){//이미 가맹점이있는지 여부
				mav.addObject("checkid", 3);
			}
			else{
				mav.addObject("checkid", 1);
				mav.addObject("checkokId", id);
			}
		}
		else {
			mav.addObject("checkid", 2);
		}
		
		mav.setViewName("manager/restaurantAgreeDetail");
		return mav;
	}
	
	//맛집신청승인완료
	@RequestMapping(value = "manager/restaurantAgreeOk", method = RequestMethod.POST)
	public ModelAndView restaurantAgreeOk(@RequestParam Map<String, String> map){
			
		//맛집등록(이미지도)
		managerDAO.insertRestaurant(map);

		return (ModelAndView)new ModelAndView("redirect:/manager/restaurantManage");
	}
	
	//리뷰신고
	@RequestMapping(value = "/manager/reviewClaim", method = RequestMethod.GET)
	public ModelAndView reviewClaim() {
		
		List<ReportRevDTO> list = null;
		if(managerDAO.revClaimList() != null){
			list = managerDAO.revClaimList();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("manager/reviewClaim");
		return mav;
	}
	
	@RequestMapping(value = "/manager/reviewClaimDetail", method = RequestMethod.GET)
	public ModelAndView reviewClaimDetail(@RequestParam String reviewNo){
		ReportRevDTO reportRevDTO = managerDAO.reviewClaimDetail(reviewNo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("reportRevDTO", reportRevDTO);
		mav.setViewName("manager/reviewClaimDetail");
		return mav;
	}
	
	//리뷰 신고글 삭제
		@RequestMapping(value = "/manager/reviewRemove", method = RequestMethod.POST)
		public ModelAndView reviewRemove(@RequestParam String[] checkNo){
			
			managerDAO.reviewRemove(checkNo);
			return new ModelAndView("redirect:/manager/reviewClaim");
		}
	@RequestMapping(value = "/manager/reviewClaimRefuse")
	public ModelAndView reviewClaimRefuse(@RequestParam String reviewNo){
		managerDAO.reviewClaimRefuse(reviewNo);
		System.out.println("컨트롤러 " + reviewNo);
		return new ModelAndView("redirect:/manager/reviewClaim");
	}	

	//맛집신고 완료( 확인부탁요)
	@RequestMapping(value = "/manager/restaurantClaim", method = RequestMethod.GET)
	public ModelAndView restaurantClaim() {
		List<ReportResDTO> list = null;
		
		if(managerDAO.resClaimList()!= null){
			list = managerDAO.resClaimList();
		}
		
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("manager/restaurantClaim");
		return mav;
	}
	
	@RequestMapping(value = "/manager/restaurantClaimDetail", method = RequestMethod.GET)
	public ModelAndView restaurantClaimDetail(@RequestParam String resNo){
		
		//<form name = "restaurantClaim" method = "post" action = "/manager/restaurantClaimDetail">

		ReportResDTO reportResDTO = managerDAO.restaurantClaimDetail(resNo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("reportResDTO", reportResDTO);
		mav.setViewName("manager/restaurantClaimDetail");
		return mav;
	}
	
	@RequestMapping(value = "/manager/restaurantClaimRefuse")
	public ModelAndView restaurantClaimRefuse(@RequestParam String resNo){
		managerDAO.restaurantClaimRefuse(resNo);
		return new ModelAndView("redirect:/manager/restaurantClaim");
	}
	
	@RequestMapping(value = "/manager/restaurantClaimUnused")
	public ModelAndView restaurantClaimUnused(@RequestParam String resNo){
		managerDAO.restaurantClaimUnused(resNo);
		
		return new ModelAndView("redirect:/manager/restaurantClaim");
	} 
	
	@RequestMapping(value = "/manager/reviewClaimUnused")
	public ModelAndView reviewClaimUnused(@RequestParam String reviewNo){
		managerDAO.reviewClaimUnused(reviewNo);
		
		return new ModelAndView("redirect:/manager/restaurantClaim");
	} 
}