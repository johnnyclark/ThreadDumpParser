package com.johnnyclark.threaddumpparser.parser;

import com.google.common.collect.Sets;
import com.johnnyclark.threaddumpparser.model.ThdStackFrameEntity;
import com.johnnyclark.threaddumpparser.model.ThdStackLockLineEntity;
import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
public class ThdStack {
  private final @NotNull String name;
  private final @NotNull Long threadId;
  private final @NotNull String daemonStatus;
  private final @NotNull String priority;
  private final @NotNull String osThreadPriority;
  private final @NotNull String address;
  private final @NotNull String osThreadId;
  private final @NotNull String threadStatus;
  private final @NotNull String lastKnownJavaStackPointer;
  private final @NotNull Thread.State threadState;
  private final @NotNull String threadStateOptionalInfo;
  private final @NotNull List<ThdStackFrame> thdStackFrames;
  private final @NotNull List<ThdStackLockLine> thdStackLockLines;
  private final @NotNull String lockedOwnableSynchronizerLines;

  public ThdStack(@NotNull String name, @NotNull Long threadId, @NotNull String daemonStatus, @NotNull String priority, @NotNull String osThreadPriority,
                  @NotNull String address, @NotNull String osThreadId, @NotNull String threadStatus, @NotNull String lastKnownJavaStackPointer,
                  @NotNull Thread.State threadState, @NotNull String threadStateOptionalInfo,
                  @NotNull List<ThdStackFrame> thdStackFrames,
                  @NotNull List<ThdStackLockLine> thdStackLockLines,
                  @NotNull String lockedOwnableSynchronizerLines) {
    this.name = name;
    this.threadId = threadId;
    this.daemonStatus = daemonStatus;
    this.priority = priority;
    this.osThreadPriority = osThreadPriority;
    this.address = address;
    this.osThreadId = osThreadId;
    this.threadStatus = threadStatus;
    this.lastKnownJavaStackPointer = lastKnownJavaStackPointer;
    this.threadState = threadState;
    this.threadStateOptionalInfo = threadStateOptionalInfo;
    this.thdStackFrames = thdStackFrames;
    this.thdStackLockLines = thdStackLockLines;
    this.lockedOwnableSynchronizerLines = lockedOwnableSynchronizerLines;
  }

  public String getName() {
    return name;
  }

  public Long getThreadId() {
    return threadId;
  }

  public String getDaemonStatus() {
    return daemonStatus;
  }

  public String getPriority() {
    return priority;
  }

  public String getOsThreadPriority() {
    return osThreadPriority;
  }

  public String getAddress() {
    return address;
  }

  public String getOsThreadId() {
    return osThreadId;
  }

  public String getThreadStatus() {
    return threadStatus;
  }

  public String getLastKnownJavaStackPointer() {
    return lastKnownJavaStackPointer;
  }

  public Thread.State getThreadState() {
    return threadState;
  }

  public String getThreadStateOptionalInfo() {
    return threadStateOptionalInfo;
  }

  public List<ThdStackFrame> getThdStackFrames() {
    return thdStackFrames;
  }

  public List<ThdStackLockLine> getThdStackLockLines() {
    return thdStackLockLines;
  }

  public String getLockedOwnableSynchronizerLines() {
    return lockedOwnableSynchronizerLines;
  }

  public Set<ThdStackFrameEntity> getThdStackFrameEntities() {
    Set<ThdStackFrameEntity> entities = Sets.newHashSet();
    for (ThdStackFrame thdStackFrame : thdStackFrames) {
      ThdStackFrameEntity thdStackFrameEntity = new ThdStackFrameEntity();
      thdStackFrameEntity.setPkgName(thdStackFrame.getPkgName());
      thdStackFrameEntity.setFileName(thdStackFrame.getFileName());
      thdStackFrameEntity.setClassName(thdStackFrame.getClassName());
      thdStackFrameEntity.setInnerClassName(thdStackFrame.getInnerClassName());
      thdStackFrameEntity.setMethodName(thdStackFrame.getMethodName());
      thdStackFrameEntity.setLineNumber(thdStackFrame.getLineNumber());
      entities.add(thdStackFrameEntity);
    }
    return entities;
  }
  public Set<ThdStackLockLineEntity> getThdStackLockLineEntities() {
    Set<ThdStackLockLineEntity> entities = Sets.newHashSet();
    for (ThdStackLockLine thdStackLockLine : thdStackLockLines) {
      ThdStackLockLineEntity thdStackLockLineEntity = new ThdStackLockLineEntity();
      thdStackLockLineEntity.setContent(thdStackLockLine.getContent());
//      thdStackLockLineEntity.setFileName(thdStackLockLine.getFileName());
//      thdStackLockLineEntity.setClassName(thdStackLockLine.getClassName());
//      thdStackLockLineEntity.setInnerClassName(thdStackLockLine.getInnerClassName());
//      thdStackLockLineEntity.setMethodName(thdStackLockLine.getMethodName());
//      thdStackLockLineEntity.setLineNumber(thdStackLockLine.getLineNumber());
//      thdStackLockLineEntity.set(thdStackLockLine.get());
      entities.add(thdStackLockLineEntity);
    }
    return entities;
  }
}
