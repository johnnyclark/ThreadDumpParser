package com.johnnyclark.threaddumpparser.model;

import com.johnnyclark.threaddumpparser.parser.ThdStack;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 12/3/2019
 */

@Entity
@Table(name = "ThdStack")
public class ThdStackEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "threadId")
  private Long threadId;

  @Column(name = "daemonStatus")
  private String daemonStatus;

  @Column(name = "priority")
  private String priority;

  @Column(name = "osThreadPriority")
  private String osThreadPriority;

  @Column(name = "address")
  private String address;

  @Column(name = "osThreadId")
  private String osThreadId;

  @Column(name = "threadStatus")
  private String threadStatus;

  @Column(name = "lastKnownJavaStackPointer")
  private String lastKnownJavaStackPointer;

  @Column(name = "threadStateOptionalInfo")
  private String threadStateOptionalInfo;

  @Column(name = "lockedOwnableSynchronizerLines")
  private String lockedOwnableSynchronizerLines;

  @ManyToOne
  @JoinColumn(name = "td_id")
  private TdEntity tdEntity;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "thd_stack_id")
  private Set<ThdStackFrameEntity> thdStackFrameEntities;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "thd_stack_id")
  private Set<ThdStackLockLineEntity> thdStackLockLineEntities;

  public static ThdStackEntity fromThdStack(ThdStack thdStack, TdEntity tdEntity) {
    ThdStackEntity thdStackEntity = new ThdStackEntity();
    thdStackEntity.setTdEntity(tdEntity);
    thdStackEntity.setName(thdStack.getName());
    thdStackEntity.setThreadId(thdStack.getThreadId());
    thdStackEntity.setDaemonStatus(thdStack.getDaemonStatus());
    thdStackEntity.setPriority(thdStack.getPriority());
    thdStackEntity.setOsThreadPriority(thdStack.getOsThreadPriority());
    thdStackEntity.setAddress(thdStack.getAddress());
    thdStackEntity.setOsThreadId(thdStack.getOsThreadId());
    thdStackEntity.setThreadStatus(thdStack.getThreadStatus());
    thdStackEntity.setLastKnownJavaStackPointer(thdStack.getLastKnownJavaStackPointer());
    thdStackEntity.setThreadStateOptionalInfo(thdStack.getThreadStateOptionalInfo());
    thdStackEntity.setLockedOwnableSynchronizerLines(thdStack.getLockedOwnableSynchronizerLines());
    thdStackEntity.setThdStackFrameEntities(thdStack.getThdStackFrameEntities());
    thdStackEntity.setThdStackLockLineEntities(thdStack.getThdStackLockLineEntities());
    return thdStackEntity;
  }

  public TdEntity getTdEntity() {
    return tdEntity;
  }

  public void setTdEntity(TdEntity tdEntity) {
    this.tdEntity = tdEntity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getThreadId() {
    return threadId;
  }

  public void setThreadId(Long threadId) {
    this.threadId = threadId;
  }

  public String getDaemonStatus() {
    return daemonStatus;
  }

  public void setDaemonStatus(String daemonStatus) {
    this.daemonStatus = daemonStatus;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public String getOsThreadPriority() {
    return osThreadPriority;
  }

  public void setOsThreadPriority(String osThreadPriority) {
    this.osThreadPriority = osThreadPriority;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getOsThreadId() {
    return osThreadId;
  }

  public void setOsThreadId(String osThreadId) {
    this.osThreadId = osThreadId;
  }

  public String getThreadStatus() {
    return threadStatus;
  }

  public void setThreadStatus(String threadStatus) {
    this.threadStatus = threadStatus;
  }

  public String getLastKnownJavaStackPointer() {
    return lastKnownJavaStackPointer;
  }

  public void setLastKnownJavaStackPointer(String lastKnownJavaStackPointer) {
    this.lastKnownJavaStackPointer = lastKnownJavaStackPointer;
  }

  public String getThreadStateOptionalInfo() {
    return threadStateOptionalInfo;
  }

  public void setThreadStateOptionalInfo(String threadStateOptionalInfo) {
    this.threadStateOptionalInfo = threadStateOptionalInfo;
  }

  public String getLockedOwnableSynchronizerLines() {
    return lockedOwnableSynchronizerLines;
  }

  public void setLockedOwnableSynchronizerLines(String lockedOwnableSynchronizerLines) {
    this.lockedOwnableSynchronizerLines = lockedOwnableSynchronizerLines;
  }

  public Set<ThdStackFrameEntity> getThdStackFrameEntities() {
    return thdStackFrameEntities;
  }

  public void setThdStackFrameEntities(Set<ThdStackFrameEntity> thdStackFrameEntities) {
    this.thdStackFrameEntities = thdStackFrameEntities;
  }

  public Set<ThdStackLockLineEntity> getThdStackLockLineEntities() {
    return thdStackLockLineEntities;
  }

  public void setThdStackLockLineEntities(Set<ThdStackLockLineEntity> thdStackLockLineEntities) {
    this.thdStackLockLineEntities = thdStackLockLineEntities;
  }
}
