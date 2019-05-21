package com.smartmeter.api.representation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SmartMeterReadingResponse {

    /* Below variable names reflect the json tag values in response, hence these can be updated to conform with any upstream validation if needed */
    private String electricity_read;
    private String gas_read;

}