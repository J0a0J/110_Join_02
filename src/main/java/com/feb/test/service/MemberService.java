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
		
//	 	성과 이름을 각각 정보를 받기 때문에 이를 합쳐줘야 한다. 
		String memberName = params.get("firstName") + " " + params.get("lastName");
//		합친 이름을 params에 memberName 으로 넣어줘야 sql 문에서 사용 가능하다. 
		params.put("memberName", memberName);
		System.out.println("memberName ~~~~~~~" + memberName);
		
		return memberDao.join(params);
	}
}
