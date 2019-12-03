package com.johnnyclark.threaddumpparser.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 12/2/2019
 */
class TdParserDriverTest {

  @Test
  void process() throws IOException {
    TdParserDriver driver = new TdParserDriver();
    driver.process(null);
  }
}