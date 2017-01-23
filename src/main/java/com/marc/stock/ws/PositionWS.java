package com.marc.stock.ws;

import com.marc.stock.domain.PositionBean;
import lombok.Data;

@Data
public class PositionWS {
    public static PositionWS fromBean(PositionBean executionBean) {
        PositionWS positionWS = new PositionWS();
        return positionWS;
    }
}
