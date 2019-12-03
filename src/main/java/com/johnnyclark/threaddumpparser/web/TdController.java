package com.johnnyclark.threaddumpparser.web;

import com.johnnyclark.threaddumpparser.model.TdEntity;
import com.johnnyclark.threaddumpparser.service.TdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
@RestController
@RequestMapping("/tds")
public class TdController {
  @Autowired
  TdService service;

  @GetMapping
  public ResponseEntity<List<TdEntity>> getAllTds() {
    List<TdEntity> list = service.getAllTds();

    return new ResponseEntity<List<TdEntity>>(list, new HttpHeaders(), HttpStatus.OK);
  }
}
