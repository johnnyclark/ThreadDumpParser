package com.johnnyclark.threaddumpparser.parser;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
class TdParserTest {
  @Test
  void parse() {
    TdParser parser = new TdParser();
    parser.parse("2019-11-21 14:30:03\n" +
                     "Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.121-b13 mixed mode):\n" +
                     "\n" +
                     "\"ActiveMQ InactivityMonitor Worker\" #17377 daemon prio=5 os_prio=0 tid=0x00007f33ec272800 nid=0x2062 runnable [0x0000000000000000]\n" +
                     "   java.lang.Thread.State: RUNNABLE\n" +
                     "\n" +
                     "   Locked ownable synchronizers:\n" +
                     "\t- None");
  }
}