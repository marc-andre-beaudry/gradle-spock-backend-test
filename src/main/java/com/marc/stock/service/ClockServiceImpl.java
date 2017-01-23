package com.marc.stock.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClockServiceImpl implements ClockService {

    @Override
    public Date now() {
        return new Date();
    }
}
