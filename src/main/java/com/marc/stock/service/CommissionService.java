package com.marc.stock.service;

import java.math.BigDecimal;

public interface CommissionService {

    BigDecimal estimate(int size, BigDecimal pricePaid);
}
