package com.feb.test.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.feb.test.service.MemberService;

@Controller
public class LoginController {
	@Autowired
	private MemberService memberService;

	public LoginController() {
		System.out.println("~~~~~~~~~~~~ First ~~~~~~~~~~~~");
	}

	@GetMapping("/login.do")
	public String login() {
		System.out.println("~~~~~~~~~~~~ Second ~~~~~~~~~~~~");
		return "login";
	}

	@PostMapping("/join.do")
	public ModelAndView join(@RequestParam HashMap<String, String> params) {
		
		ModelAndView mv = new ModelAndView();
		
		System.out.println("~~~~~~~~~~~~ Join ~~~~~~~~~~~~");
		System.out.println("params.memberId		" + params.get("passwd"));
		System.out.println("params: 			 " + params);

		int result = memberService.join(params);
//		DB에 정보가 저장되면 1이 반환된다.
		if (result == 1) {
			mv.addObject("resultMsg", "회원가입 성공 ");
		} else {
			mv.addObject("resultMsg", "회원가입 실패 ");
		}
		mv.setViewName("login");
		return mv;
	}

}
