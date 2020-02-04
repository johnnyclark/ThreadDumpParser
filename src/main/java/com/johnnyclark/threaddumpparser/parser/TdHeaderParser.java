package com.johnnyclark.threaddumpparser.parser;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
public class TdHeaderParser {

  public TdHeader parse(@NotNull String paragraph) {
    final List<String> firstParagraphLines = Arrays.asList(paragraph.split("\\r?\\n"));
    if (firstParagraphLines.size() == 2)
      return new TdHeader(firstParagraphLines.get(0), firstParagraphLines.get(1));
    else
      return new TdHeader("1973-02-22 00:00:00", "");  // If the thread dump was produced via Intellij debugger (or some other means) then it might not have the correct header/trailer
  }
}
