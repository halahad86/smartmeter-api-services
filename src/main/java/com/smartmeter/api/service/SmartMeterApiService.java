package com.smartmeter.api.service;

import com.smartmeter.api.exception.ReadingNotFoundException;
import com.smartmeter.api.repository.SmartMeterReadingRepository;
import com.smartmeter.api.entity.SmartMeterReading;
import com.smartmeter.api.representation.SmartMeterReadingResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
public class SmartMeterApiService {

    @Autowired
    private SmartMeterReadingRepository smartMeterReadingRepository;


    public SmartMeterReadingResponse getSmartMeterReading(String accountNumber) {
        log.info("Searching repository for Smart Meter using ID [{}]", accountNumber);
        Optional<SmartMeterReading> smartMeterReading = smartMeterReadingRepository.findById(accountNumber);

        if (!smartMeterReading.isPresent()) {
            log.error("Cannot find reading for Account Number [{}] ", accountNumber);

            /* This exception message string is returned to client/requester, hence can be modified to ensure that it conforms to upstream error reporting standards */
            throw new ReadingNotFoundException("Cannot find reading for Account Number: " + accountNumber);
        }

        log.info("Found reading for Account Number [{}]", accountNumber);
        return new SmartMeterReadingResponse(smartMeterReading.get().getElecSmartRead(), smartMeterReading.get().getGasSmartRead());

    }
}
