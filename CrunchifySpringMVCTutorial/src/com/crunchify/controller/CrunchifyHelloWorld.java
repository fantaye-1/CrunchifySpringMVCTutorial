package com.crunchify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Hello World controller from the Crunchify Spring MVC tutorial.
 *
 * <p>The package used to be {@code Com.crunchify.controller} (capital C), which did
 * not match the {@code com.crunchify.controller} component scan in
 * {@code crunchify-servlet.xml}, so the controller was never registered.
 */
@Controller
public class CrunchifyHelloWorld {

	static final String MESSAGE = "Hello World, Spring MVC Tutorial. This message is coming from CrunchifyHelloWorld.java";

	/** {@code /welcome.html} is kept so the original tutorial link still works. */
	@GetMapping({ "/welcome", "/welcome.html" })
	public ModelAndView helloWorld() {
		return new ModelAndView("welcome", "message", MESSAGE);
	}
}
