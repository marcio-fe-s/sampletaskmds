/** This class was generated by GenTest@Mobacar */
package com.arvoia.sampletask;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = SampletaskApplication.class)
@AutoConfigureMockMvc
public class SampletaskApplicationTest {

	@Autowired
	private SampletaskApplication underTest;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testContextLoads() throws Exception {
		// given
		// check if context was loaded without exception
		MvcResult result = this.mockMvc.perform(get("/avail")).andExpect(status().isOk()).andReturn();
		String contentAsString = result.getResponse().getContentAsString();
	    assertNotNull(contentAsString);
	    
	}

	@Test
	public void testGetRestTemplate() throws Exception {
		// given
		// when
		RestTemplate actual = underTest.getRestTemplate();
		// then
		assertNotNull(actual);
	}

}
