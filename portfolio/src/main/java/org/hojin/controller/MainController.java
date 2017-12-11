package org.hojin.controller;

import org.hojin.model.PageMaker;
import org.hojin.model.RequestPage;
import org.hojin.model.SearchPage;
import org.hojin.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = {"/main","/"}, method = RequestMethod.GET)
	public String main(Model model, @ModelAttribute("reqPage") SearchPage searchPage){
		logger.info("show all list..............");
		logger.info(searchPage.toString());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setReqPage(searchPage);
		pageMaker.setTotalCount(service.countListSearch(searchPage));
		
		model.addAttribute("list", service.listSearch(searchPage));
		model.addAttribute("pageMaker", pageMaker
				);
		return "/main/main";
	}
}
