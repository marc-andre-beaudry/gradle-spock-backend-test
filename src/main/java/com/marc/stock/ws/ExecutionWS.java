package com.marc.stock.ws;

import com.marc.stock.domain.ExecutionBean;
import lombok.Data;

@Data
public class ExecutionWS {

    public static ExecutionWS fromBean(ExecutionBean executionBean) {
        ExecutionWS executionWS = new ExecutionWS();
        return executionWS;
    }
}
