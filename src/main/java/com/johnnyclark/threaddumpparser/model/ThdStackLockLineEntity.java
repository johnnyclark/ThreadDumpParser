package com.johnnyclark.threaddumpparser.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 12/3/2019
 */

@Entity
@Table(name = "ThdStackLockLine")
public class ThdStackLockLineEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "content")
  private String content;

  @ManyToOne
  @JoinColumn(name = "thd_stack_id")
  private ThdStackEntity thdStackEntity;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public ThdStackEntity getThdStackEntity() {
    return thdStackEntity;
  }

  public void setThdStackEntity(ThdStackEntity thdStackEntity) {
    this.thdStackEntity = thdStackEntity;
  }
}
