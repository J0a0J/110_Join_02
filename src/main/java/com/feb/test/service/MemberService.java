package com.feb.test.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feb.test.dao.MemberDao;
import com.feb.test.util.Sha512Encoder;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	public int join(HashMap<String, String> params) {
		
		Sha512Encoder encoder = Sha512Encoder.getInstance();
		String passwd = params.get("passwd");
		String encodeTxt = encoder.getSecurePassword(passwd);
		params.put("passwd", encodeTxt);
		
		String memberName = params.get("firstName") + " " + params.get("lastName");
		params.put("memberName", memberName);
		System.out.println("memberName ~~~~~~~" + memberName);
		
		return memberDao.join(params);
	}
}
