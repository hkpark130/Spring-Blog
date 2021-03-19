package kr.pe.hkpark130.springblog.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String home() {
		return "Hello, Spring boot!";
	}
	
}