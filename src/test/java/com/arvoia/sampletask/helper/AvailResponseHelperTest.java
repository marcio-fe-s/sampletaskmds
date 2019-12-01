package com.arvoia.sampletask.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.arvoia.sampletask.model.AvailResponse;
import com.arvoia.sampletask.model.Error;
import com.arvoia.sampletask.model.Offer;
import com.arvoia.sampletask.model.Vehicle;

public class AvailResponseHelperTest {

	@Test
	public void testParseSuccess() {
		AvailResponse underTest = AvailResponseHelper.parse("{\"error\":null,\"vehicles\":[{\"brand\":\"Volvo\",\"category\":\"Economy\",\"basePrice\":520.0,\"color\":\"grey\",\"doors\":3},{\"brand\":\"BMV\",\"category\":\"Economy\",\"basePrice\":530.0,\"color\":\"white\",\"doors\":5},{\"brand\":\"Audi\",\"category\":\"Economy\",\"basePrice\":190.0,\"color\":\"red\",\"doors\":4},{\"brand\":\"Toyota\",\"category\":\"Luxury\",\"basePrice\":220.0,\"color\":\"grey\",\"doors\":5}]}");
		Offer offer = underTest.getOffer();
		Vehicle firstVehicle = offer.getVehicles().get(0);

		assertEquals("success",underTest.getResult());
		assertEquals(new Integer(4), offer.getAvailable());
		assertEquals("Volvo", firstVehicle.getBrand());
		assertEquals("Economy", firstVehicle.getCategory());
		assertEquals(new Double(520.0), firstVehicle.getBasePrice());
		assertNull(underTest.getError());
	}
	
	@Test
	public void testParseErrorMsg() {
		AvailResponse underTest = AvailResponseHelper.parse("{\"error\":{\"code\":400,\"description\":\"illegal access\"},\"vehicles\":null}");
		assertNull(underTest.getOffer());
		Error error = underTest.getError();
		assertEquals("error",underTest.getResult());
		assertEquals(new Integer(400), error.getCode());
		assertEquals("illegal access", error.getDescription());
	}

	@Test
	public void testParseVehicles() {		
		AvailResponse underTest = new AvailResponse();
		AvailResponseHelper.parseVehicles(underTest ,
				"[{\"brand\":\"BMV\",\"category\":\"Luxury\",\"basePrice\":980.0,\"color\":\"black\",\"doors\":4},{\"brand\":\"Toyota\",\"category\":\"Economy\",\"basePrice\":130.0,\"color\":\"red\",\"doors\":4},{\"brand\":\"Volvo\",\"category\":\"Economy\",\"basePrice\":360.0,\"color\":\"black\",\"doors\":5},{\"brand\":\"Audi\",\"category\":\"Luxury\",\"basePrice\":420.0,\"color\":\"red\",\"doors\":5},{\"brand\":\"Audi\",\"category\":\"Luxury\",\"basePrice\":200.0,\"color\":\"red\",\"doors\":3}]");

		Offer offer = underTest.getOffer();
		assertEquals(new Integer(5), offer.getAvailable());
		Vehicle firstVehicle = offer.getVehicles().get(0);
		assertEquals("BMV", firstVehicle.getBrand());
		assertEquals("Luxury", firstVehicle.getCategory());
		assertEquals(new Double(980.0), firstVehicle.getBasePrice());
	}

	@Test
	public void testParseError() {
		AvailResponse underTest = new AvailResponse();
		AvailResponseHelper.parseError(underTest, "{\"code\":600,\"description\":\"system maintenance\"}");
		Error error = underTest.getError();
		assertEquals(new Integer(600), error.getCode());
		assertEquals("system maintenance", error.getDescription());
	}

	@Test
	public void testGetError() {
		AvailResponse avail = AvailResponseHelper.getVendorServiceError();
		Error error = avail.getError();
		assertEquals(new Integer(1), error.getCode());
		assertEquals("error", avail.getResult());
		assertEquals("vendor service not available", error.getDescription());
	}
	
	@Test
	public void testOrderVehiclesByPrice() {
		AvailResponse underTest = Mockito.mock(AvailResponse.class);
		Offer offer = Mockito.mock(Offer.class);
		Mockito.when(underTest.getOffer()).thenReturn(offer);
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand("Toyota");
		vehicle.setCategory("A");
		vehicle.setBasePrice(155.0);
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setBrand("BMW");
		vehicle2.setCategory("economy");
		vehicle2.setBasePrice(10.0);
		Vehicle vehicle3 = new Vehicle();
		vehicle3.setBrand("Mercedes");
		vehicle3.setCategory("B");
		vehicle3.setBasePrice(1000.0);
		vehicles.add(vehicle);
		vehicles.add(vehicle2);
		vehicles.add(vehicle3);
		Mockito.when(offer.getVehicles()).thenReturn(vehicles);
		
		AvailResponseHelper.orderVehiclesByPrice(underTest);
		
		assertEquals("BMW", vehicles.get(0).getBrand());
		assertEquals("economy", vehicles.get(0).getCategory());
		assertEquals(new Double(10.0), vehicles.get(0).getBasePrice());
	}
}
