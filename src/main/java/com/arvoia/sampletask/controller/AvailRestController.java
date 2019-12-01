package com.arvoia.sampletask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.arvoia.sampletask.helper.AvailResponseHelper;
import com.arvoia.sampletask.model.AvailResponse;

@Configuration
@RestController
public class AvailRestController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${vendor.url}")
	private String vendorUrl;

	@GetMapping(value = "/avail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody AvailResponse getAvailabilities() {
		AvailResponse ret=null;
		try {
			String searchResult = getSearchResults();
			ret = AvailResponseHelper.parse(searchResult);
			AvailResponseHelper.orderVehiclesByPrice(ret);
		}catch(NullPointerException | ResourceAccessException t) {
			ret = AvailResponseHelper.getVendorServiceError();
		}
		return ret;
	}
	
	@GetMapping(value = "/availRaw", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String getSearchResults() {
		return restTemplate.getForEntity(vendorUrl, String.class).getBody();
	}
}
