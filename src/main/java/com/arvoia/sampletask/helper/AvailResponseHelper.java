package com.arvoia.sampletask.helper;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.arvoia.sampletask.model.AvailResponse;
import com.arvoia.sampletask.model.Error;
import com.arvoia.sampletask.model.Offer;
import com.arvoia.sampletask.model.Vehicle;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AvailResponseHelper {
	
	private static final Logger LOGGER = Logger.getLogger(AvailResponseHelper.class.getName());
	private static final String ERROR = "error";
	private static final String SUCCESS = "success";
	private static final String ERROR_FIELD = "error";
	private static final String VEHICLE_FIELD = "vehicles";

	private AvailResponseHelper() {
	}
	
	public static AvailResponse parse(String searchResult) {
		AvailResponse availResponse = new AvailResponse();
		try {
	    	JsonNode productNode = new ObjectMapper().readTree(searchResult);
	    	JsonNode errorField = productNode.get(ERROR_FIELD);
	    	if(errorField!=null) {
	    		parseError(availResponse,errorField.toString());
	    	}
	    	JsonNode vehicleField = productNode.get(VEHICLE_FIELD);
	    	if(vehicleField!=null) {
	    		parseVehicles(availResponse,vehicleField.toString());
	    	}
	    } catch (IOException e) {
	    	LOGGER.severe(e.getMessage());
	    }
		return availResponse;
	}

	static void parseVehicles(AvailResponse availResponse, String asText) {
		if(!StringUtils.isBlank(asText)&&!asText.equals("null")) {
			try {
				Offer offer=new Offer();
				ObjectMapper mapper = new ObjectMapper();
		    	List<Vehicle> vehicles = mapper.readValue(asText,new TypeReference<List<Vehicle>>(){}); 
		    	offer.setAvailable(vehicles.size());
		    	offer.setVehicles(vehicles);
		    	availResponse.setOffer(offer);
		    	availResponse.setTimestamp(new Timestamp(System.currentTimeMillis()));
		    	availResponse.setResult(SUCCESS);
		    } catch (IOException e) {
		    	LOGGER.severe(e.getMessage());
		    }
		}
	}

	static void parseError(AvailResponse availResponse, String asText) {
		if(!StringUtils.isBlank(asText)&&!asText.equals("null")) {
			try {
				ObjectMapper mapper = new ObjectMapper();
		    	Error error= mapper.readValue(asText,Error.class); 
		    	availResponse.setError(error);
		    	availResponse.setResult(ERROR);
		    } catch (IOException e) {
		    	LOGGER.severe(e.getMessage());
		    }
		}
	}
	
	public static void orderVehiclesByPrice(AvailResponse ret) {
		if(ret.getOffer()!=null&&ret.getOffer().getVehicles()!=null) {
			ret.getOffer().getVehicles().sort(Comparator.comparing(Vehicle::getBasePrice));
		}
	}

	public static AvailResponse getVendorServiceError() {
		AvailResponse ret = new AvailResponse();
		Error erro = new Error();
		erro.setCode(1);
		erro.setDescription("vendor service not available");
		ret.setError(erro);
		ret.setResult(ERROR);
		return ret;
	}

}
