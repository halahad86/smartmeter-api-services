package com.smartmeter.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class SmartMeterReading {

    @Id
    @Column(name="ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name="GAS_ID")
    private String gasId;

    @Column(name="ELEC_ID")
    private String elecId;

    @Column(name="ELEC_SMART_READ")
    private String elecSmartRead;

    @Column(name="GAS_SMART_READ")
    private String gasSmartRead;

    public SmartMeterReading(){}

}