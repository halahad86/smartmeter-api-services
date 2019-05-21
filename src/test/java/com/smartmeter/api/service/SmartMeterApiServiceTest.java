package com.smartmeter.api.service;

import com.smartmeter.api.entity.SmartMeterReading;
import com.smartmeter.api.exception.ReadingNotFoundException;
import com.smartmeter.api.repository.SmartMeterReadingRepository;
import com.smartmeter.api.representation.SmartMeterReadingResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SmartMeterApiServiceTest {

    @InjectMocks
    private SmartMeterApiService smartMeterApiService;

    @Mock
    private SmartMeterReadingRepository smartMeterReadingRepositoryMock;

    private SmartMeterReadingResponse smartMeterReadingResponse;


    @Before
    public void setUp() {
        smartMeterReadingResponse = new SmartMeterReadingResponse("ElecSmartRead", "GasSmartRead");

    }


    @Test
    public void findByAccountNumberSuccess() {
        Optional<SmartMeterReading> smartMeterReading =
                Optional.of( new SmartMeterReading("AccountNumber","GasId","ElecId","ElecSmartRead","GasSmartRead"));

        when(smartMeterReadingRepositoryMock.findById(anyString())).thenReturn(smartMeterReading);

        SmartMeterReadingResponse actual = smartMeterApiService.getSmartMeterReading("AccountNumber");

        verify(smartMeterReadingRepositoryMock, times(1)).findById(anyString());
        verifyNoMoreInteractions(smartMeterReadingRepositoryMock);

        assertEquals(smartMeterReadingResponse.getElectricity_read(), actual.getElectricity_read());
        assertEquals(smartMeterReadingResponse.getGas_read(), actual.getGas_read());
    }


    @Test(expected = ReadingNotFoundException.class)
    public void findByAccountNotFound() throws ReadingNotFoundException {
        Mockito.when(smartMeterReadingRepositoryMock.findById(anyString())).thenReturn(Optional.empty());

        SmartMeterReadingResponse actual = smartMeterApiService.getSmartMeterReading("UnknownAccountNumber");

        verify(smartMeterReadingRepositoryMock, times(1)).findById(anyString());
        verifyNoMoreInteractions(smartMeterReadingRepositoryMock);

        assertNull(actual);
    }

}
