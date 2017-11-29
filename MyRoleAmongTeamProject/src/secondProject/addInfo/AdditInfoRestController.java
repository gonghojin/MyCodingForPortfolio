package com.aloa.additinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdditInfoRestController {
	@Autowired
	AccountAdditInfoRepository accountAdditInfoRepository;

	@RequestMapping(value="/accounts/additinfo/{id}", method=RequestMethod.PUT)
	public ResponseEntity additInfo(@PathVariable Long id,
									@RequestBody AccountAdditInfo accountAddit){
		accountAddit.setId(id);
		
		accountAdditInfoRepository.save(accountAddit);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/accounts/additinfo/{id}", method=RequestMethod.GET)
	public ResponseEntity getAdditInfo(@PathVariable Long id){
		//TODO service 레이어 서버사이드 유효성 검사.
		//TODO accountAddit == null -> throw exception
		AccountAdditInfo accountAddit = accountAdditInfoRepository.findOne(id);
		
		return new ResponseEntity<>(accountAddit, HttpStatus.OK);
	}
}
