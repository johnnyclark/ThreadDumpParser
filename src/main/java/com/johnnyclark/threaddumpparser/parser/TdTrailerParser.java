package com.johnnyclark.threaddumpparser.parser;

import com.sun.istack.internal.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
public class TdTrailerParser {
  private static final Pattern PATTERN = Pattern.compile("\\s*JNI global references:\\s*([0-9]+)\\s*");

  public TdTrailer parse(@NotNull String line) {
    final Matcher matcher = PATTERN.matcher(line);
    if(matcher.find()){
      return new TdTrailer(matcher.group(1));
    }
    return new TdTrailer("");
  }

  public boolean matches(@NotNull String line) {
    final Matcher matcher = PATTERN.matcher(line);
    return matcher.find();
  }
}
