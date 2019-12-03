package com.johnnyclark.threaddumpparser.parser;

import com.sun.istack.internal.NotNull;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
public class TdHeaderParser {

  public TdHeader parse(@NotNull String paragraph){
    return new TdHeader(paragraph);
  }
}
