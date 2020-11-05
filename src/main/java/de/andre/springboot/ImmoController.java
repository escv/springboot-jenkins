package de.andre.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImmoController {

	@GetMapping("/")
	String hello() {
		return "hello";
	}
}
