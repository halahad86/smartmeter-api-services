package com.smartmeter.api.resource;

import com.smartmeter.api.exception.ReadingNotFoundException;
import com.smartmeter.api.representation.SmartMeterReadingResponse;
import com.smartmeter.api.service.SmartMeterApiService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SmartMeterApiResource.class, secure = false)
public class SmartMeterApiResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SmartMeterApiService smartMeterApiService;
    private SmartMeterReadingResponse smartMeterReadingResponse;
    private String exampleSuccessResponseJson;


    @Before
    public void setUp() {
        smartMeterReadingResponse = new SmartMeterReadingResponse("ElecSmartRead", "GasSmartRead");
        exampleSuccessResponseJson = "{\"electricity_read\":\"ElecSmartRead\",\"gas_read\":\"GasSmartRead\"}";
    }


    @Test
    public void getReadingForAccountNumberSuccess() throws Exception {
        when(smartMeterApiService.getSmartMeterReading(anyString())).thenReturn(smartMeterReadingResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/smart/reads/AccountNumber").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(exampleSuccessResponseJson, result.getResponse().getContentAsString());
    }


    @Test
    public void getReadingForAccountNumberNotFound() throws Exception {
        when(smartMeterApiService.getSmartMeterReading(anyString())).thenThrow(new ReadingNotFoundException("Not Found"));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/smart/reads/AccountNumber").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(404, result.getResponse().getStatus());
    }

}