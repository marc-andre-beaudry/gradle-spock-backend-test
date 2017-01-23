package com.marc.stock.service;

import java.math.BigDecimal;

public class CommissionServiceImpl implements CommissionService {

    private static final BigDecimal MIN_ORDER_PRICE = BigDecimal.valueOf(0.35);
    private static final BigDecimal FLAT_RATE_PER_SHARE = BigDecimal.valueOf(0.0035);
    private static final BigDecimal MAX_PER_ORDER_VALUE = BigDecimal.valueOf(0.005);

    // https://www.interactivebrokers.com/en/index.php?f=commission&p=stocks2
    @Override
    public BigDecimal estimate(int size, BigDecimal pricePaid) {
        if (size == 0) {
            return BigDecimal.ZERO;
        }
        size = Math.abs(size);

        BigDecimal tradeValue = getTradeValue(BigDecimal.valueOf(size), pricePaid);
        BigDecimal linearCommission = getLinearCommission(BigDecimal.valueOf(size));
        BigDecimal maxCommission = getMaxCommission(tradeValue);
        if (linearCommission.compareTo(MIN_ORDER_PRICE) < 0) {
            return MIN_ORDER_PRICE;
        }
        if (linearCommission.compareTo(maxCommission) > 0) {
            return maxCommission;
        }
        return linearCommission;
    }

    private BigDecimal getTradeValue(BigDecimal size, BigDecimal pricePaid) {
        return pricePaid.multiply(size);
    }

    private BigDecimal getLinearCommission(BigDecimal size) {
        return FLAT_RATE_PER_SHARE.multiply(size);
    }

    private BigDecimal getMaxCommission(BigDecimal tradeValue) {
        return MAX_PER_ORDER_VALUE.multiply(tradeValue);
    }
}
