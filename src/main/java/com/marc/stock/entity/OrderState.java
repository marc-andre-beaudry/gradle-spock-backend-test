package com.marc.stock.entity;

public enum OrderState {
    Accepted,
    Rejected,
    CancelPending,
    Canceled,
    PartialCanceled,
    Partial,
    Executed
}
