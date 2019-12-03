package com.johnnyclark.threaddumpparser.parser;

import com.sun.istack.internal.NotNull;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
public class TdHeader {
  private final @NotNull String fullContent;


  public TdHeader(String fullContent) {
    this.fullContent = fullContent;
  }

  public String getFullContent() {
    return fullContent;
  }
}
