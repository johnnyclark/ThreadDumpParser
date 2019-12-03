package com.johnnyclark.threaddumpparser.repository;

import com.johnnyclark.threaddumpparser.model.TdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
@Repository
public interface TdRepository extends JpaRepository<TdEntity, Long> {


}

