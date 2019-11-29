package com.arvoia.sampletask.controller;

import com.arvoia.sampletask.model.AvailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Configuration
@RestController
public class AvailRestController {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${vendor.url}")
  private String vendorUrl;

  @GetMapping(value = "/avail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody AvailResponse getAvailabilities() {
    AvailResponse ret = new AvailResponse();
    String searchResult = getSearchResults();
    // TODO implement
    ret.setResult(searchResult);
    return ret;
  }

  private String getSearchResults() {
    return restTemplate.getForEntity(vendorUrl, String.class).getBody();
  }
}
