package com.johnnyclark.threaddumpparser.parser;

import com.sun.istack.internal.NotNull;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/28/2019
 */
public class ThdStackLockLine {

  private final @NotNull String content;
  private final int stackFramePosition;

  public ThdStackLockLine(@NotNull String content, int stackFramePosition) {
    this.content = content;
    this.stackFramePosition = stackFramePosition;
  }

  public String getContent() {
    return content;
  }

  public int getStackFramePosition() {
    return stackFramePosition;
  }
}
