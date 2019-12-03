package com.johnnyclark.threaddumpparser.service;

import com.johnnyclark.threaddumpparser.model.TdEntity;
import com.johnnyclark.threaddumpparser.repository.TdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
@Service
public class TdService {

  @Autowired
  TdRepository repository;

  public List<TdEntity> getAllTds() {
    List<TdEntity> employeeList = repository.findAll();

    if (employeeList.size() > 0) {
      return employeeList;
    } else {
      return new ArrayList<TdEntity>();
    }
  }
}
