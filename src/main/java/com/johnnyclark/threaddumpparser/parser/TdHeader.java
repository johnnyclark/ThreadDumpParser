package com.johnnyclark.threaddumpparser.parser;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
public class TdHeader {
  private final @NotNull Date date;
  private final @NotNull String fullContent;
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  private static final Logger log = LoggerFactory.getLogger(TdHeader.class);

  public TdHeader(String dateTime, String fullContent) {
    this.fullContent = fullContent;
    try {
      this.date = DATE_FORMAT.parse(dateTime);
    } catch (ParseException e) {
      log.error("Exception while parsing ThreadDump Header Date/Time " + dateTime);
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public String getFullContent() {
    return fullContent;
  }

  public Date getDate() {
    return date;
  }
}
