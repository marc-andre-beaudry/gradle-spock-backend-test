package com.marc.stock.controller;

import com.marc.stock.domain.PositionBean;
import com.marc.stock.service.PositionService;
import com.marc.stock.ws.PositionWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PositionControllerImpl implements PositionController {

    @Autowired
    private PositionService positionService;

    @Override
    @GetMapping("/positions")
    public ResponseEntity<?> getPositions() {
        List<PositionBean> positionBeanList = positionService.getPositions();
        List<PositionWS> executionWSList = positionBeanList.stream().map(positionBean -> PositionWS.fromBean(positionBean)).collect(Collectors.toList());
        return ResponseEntity.ok(executionWSList);
    }
}
