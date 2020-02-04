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
@Table(name = "StackLockLine")
public class ThdStackLockLineEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "stack_id")
  private ThdStackEntity thdStackEntity;

  @Column(name = "content")
  private String content;

  @Column(name = "status")
  private String status;

  @Column(name = "lockId")
  private String lockId;

  @Column(name = "pkgAndClassName")
  private String pkgAndClassName;

  @Column(name = "stackFramePosition")
  private Long stackFramePosition;

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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getLockId() {
    return lockId;
  }

  public void setLockId(String lockId) {
    this.lockId = lockId;
  }

  public String getPkgAndClassName() {
    return pkgAndClassName;
  }

  public void setPkgAndClassName(String pkgAndClassName) {
    this.pkgAndClassName = pkgAndClassName;
  }

  public Long getStackFramePosition() {
    return stackFramePosition;
  }

  public void setStackFramePosition(Long stackFramePosition) {
    this.stackFramePosition = stackFramePosition;
  }
}
