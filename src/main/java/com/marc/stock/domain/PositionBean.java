package com.marc.stock.domain;

import com.marc.stock.entity.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionBean {

    public static PositionBean fromPosition(Position position) {
        PositionBean positionBean = new PositionBean();
        return positionBean;
    }
}
