package com.marc.stock.service;

import com.marc.stock.domain.PositionBean;

import java.util.List;
import java.util.Optional;

public interface PositionService {
    List<PositionBean> getPositions();
    Optional<PositionBean> getPosition();
    PositionBean createPosition(PositionBean positionBean);

}
