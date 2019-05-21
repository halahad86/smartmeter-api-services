package com.smartmeter.api.repository;

import com.smartmeter.api.entity.SmartMeterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartMeterReadingRepository extends JpaRepository<SmartMeterReading, String> {

}
