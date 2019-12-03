package com.johnnyclark.threaddumpparser.parser;

import com.sun.istack.internal.NotNull;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 12/2/2019
 */
public class TdTrailer {
  private final @NotNull String fullContent;
  public TdTrailer(String fullContent) {
    this.fullContent = fullContent;
  }

  public String getFullContent() {
    return fullContent;
  }
}
