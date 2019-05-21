package com.smartmeter.api.resource;

import com.smartmeter.api.exception.ReadingNotFoundException;
import com.smartmeter.api.representation.SmartMeterReadingResponse;
import com.smartmeter.api.service.SmartMeterApiService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * This controller provides REST services for Smart Meter API
 * 
 * @author Matt Frost
 *
 */
@Slf4j
@RestController
public class SmartMeterApiResource {
	
	@Autowired
	private SmartMeterApiService smartMeterApiService;

	/**
	 * get Smart Meter reading by account number.
	 * Request Variables:
	 * 				String ACCOUNT_ID
	 */
	@GetMapping("/api/smart/reads/{account_no}")
	public SmartMeterReadingResponse getSmartMeterReadingByAccountNumber (@PathVariable String account_no) throws ReadingNotFoundException {

		log.info("Processing Smart Meter reading request for Account Number [{}]", account_no);
		return smartMeterApiService.getSmartMeterReading(account_no);
	}
	
}

