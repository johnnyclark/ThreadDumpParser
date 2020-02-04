package com.johnnyclark.threaddumpparser.parser;

import com.sun.istack.internal.NotNull;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/28/2019
 */
public class ThdStackLockLine {
  private final @NotNull String status;
  private final @NotNull String lockId;
  private final @NotNull String pkgAndClassName;
  private final @NotNull String content;

  private final Long stackFramePosition;

  public ThdStackLockLine(final @NotNull String status, final @NotNull String lockId, final @NotNull String pkgAndClassName, final @NotNull String content, Long stackFramePosition) {
    this.status = status;
    this.lockId = lockId;
    this.pkgAndClassName = pkgAndClassName;
    this.stackFramePosition = stackFramePosition;
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public String getStatus() {
    return status;
  }

  public String getLockId() {
    return lockId;
  }

  public String getPkgAndClassName() {
    return pkgAndClassName;
  }

  public Long getStackFramePosition() {
    return stackFramePosition;
  }
}
