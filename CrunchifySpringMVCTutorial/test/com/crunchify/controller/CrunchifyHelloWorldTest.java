package com.crunchify.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Loads the real {@code crunchify-servlet.xml}, so these tests also check that the
 * component scan finds the controller and that the JSP view resolver is wired up.
 */
@SpringJUnitWebConfig(locations = "file:WebContent/WEB-INF/crunchify-servlet.xml")
class CrunchifyHelloWorldTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@ParameterizedTest
	@ValueSource(strings = { "/welcome", "/welcome.html" })
	void welcomeRendersWelcomeJspWithMessage(String path) throws Exception {
		mockMvc.perform(get(path))
				.andExpect(status().isOk())
				.andExpect(view().name("welcome"))
				.andExpect(model().attribute("message", CrunchifyHelloWorld.MESSAGE))
				.andExpect(forwardedUrl("/WEB-INF/jsp/welcome.jsp"));
	}

	@Test
	void welcomeOnlyAcceptsGet() throws Exception {
		mockMvc.perform(post("/welcome")).andExpect(status().isMethodNotAllowed());
	}
}
