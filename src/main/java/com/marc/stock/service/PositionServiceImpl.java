package com.marc.stock.service;

import com.marc.stock.domain.PositionBean;
import com.marc.stock.entity.Position;
import com.marc.stock.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PositionBean> getPositions() {
        List<PositionBean> positionBeanList = new ArrayList<>();
        for (Position position : positionRepository.findAll()) {
            positionBeanList.add(PositionBean.fromPosition(position));
        }
        return positionBeanList;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PositionBean> getPosition() {
        return null;
    }

    @Override
    @Transactional
    public PositionBean createPosition(PositionBean positionBean) {
        return null;
    }
}
