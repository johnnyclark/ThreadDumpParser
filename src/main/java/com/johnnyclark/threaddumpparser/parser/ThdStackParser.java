package com.johnnyclark.threaddumpparser.parser;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
class ThdStackParser {
  private static final Logger logger = LoggerFactory.getLogger(ThdStackParser.class);

  ThdStack parse(String firstParagraph, ListIterator<String> paragraphIter) {
    final List<String> firstParagraphLines = Arrays.asList(firstParagraph.split("\\r?\\n"));
    try {
      final Iterator<String> p1LinesIter = firstParagraphLines.iterator();
      final ThdStackRegExHeaderLine headerLineParser = new ThdStackRegExHeaderLine(p1LinesIter);
      final ThdStackRegExStateLine stateLineParser = new ThdStackRegExStateLine(p1LinesIter);

      final List<String> frameAndLockLines = buildFrameAndLockLines(p1LinesIter);
      final List<ThdStackFrame> thdStackFrames = parseThdStackFrames(frameAndLockLines);
      final List<ThdStackLockLine> thdStackLockLines = parseThdStackLockLines(frameAndLockLines);
      final ThdStackRegExLockedOwnableSynchronizerLines lockedOwnableSynchronizerLinesParser = new ThdStackRegExLockedOwnableSynchronizerLines(paragraphIter);

      return new ThdStack(headerLineParser.threadName, headerLineParser.threadId, headerLineParser.daemonFlag, headerLineParser.priority, headerLineParser.osPriority,
                          headerLineParser.address, headerLineParser.osThreadId, headerLineParser.threadStatus, headerLineParser.lastKnownJavaStackPointer,
                          stateLineParser.state, stateLineParser.optionalInfo,
                          thdStackFrames,
                          thdStackLockLines,
                          lockedOwnableSynchronizerLinesParser.content);
    } catch (Exception e) {
      logger.error("Exception thrown while processing first paragraph " + firstParagraph);
      e.printStackTrace();
      throw e;
    }
  }

  private List<String> buildFrameAndLockLines(Iterator<String> p1Lines) {
    final List<String> frameAndLockLines = new ArrayList<>();
    while (p1Lines.hasNext()) {
      String line = p1Lines.next();
      frameAndLockLines.add(line);
    }
    return frameAndLockLines;
  }

  private List<ThdStackFrame> parseThdStackFrames(List<String> frameAndLockLines) {
    final List<ThdStackFrame> thdStackFrames = new ArrayList<>();
    int stackFramePosition = 1;
    for (String frameAndLockLine : frameAndLockLines) {
      final ThdStackRegExFrameLines thdStackRegExFrameLines = new ThdStackRegExFrameLines(frameAndLockLine);
      if (thdStackRegExFrameLines.isMatch)
        thdStackFrames.add(thdStackRegExFrameLines.buildThdStackFrame(stackFramePosition));
      stackFramePosition++;
    }
    return thdStackFrames;
  }

  private List<ThdStackLockLine> parseThdStackLockLines(List<String> frameAndLockLines) {
    final List<ThdStackLockLine> thdStackLockLines = new ArrayList<>();
    int stackFramePosition = 1;
    for (String frameAndLockLine : frameAndLockLines) {
      ThdStackRegExLockLine thdStackRegExLockLine = new ThdStackRegExLockLine(frameAndLockLine);
      if (thdStackRegExLockLine.isMatch)
        thdStackLockLines.add(thdStackRegExLockLine.buildThdStackLockLine(stackFramePosition));
      stackFramePosition++;
    }
    return thdStackLockLines;
  }

  public static class ThdStackRegExHeaderLine {
    // "ActiveMQ BrokerService[localhost] Task-69"    #2364 daemon prio=5  os_prio=0 tid=0x00007f334810d000 nid=0x21a3 waiting on condition [0x00007f341f1ad000]
    // "Java2D Disposer"                              #1491 daemon prio=10 os_prio=0 tid=0x00007f336421e000 nid=0x152e in Object.wait() [0x00007f329a4a3000]
    // "DefaultQuartzScheduler_QuartzSchedulerThread" #325         prio=5  os_prio=0 tid=0x00007f33bcfc5800 nid=0x5fb sleeping[0x00007f33c28f9000]


    private static final Pattern PATTERN = Pattern.compile("\"([^\"]*)\"\\s+#([0-9]*)\\s*(daemon)*\\sprio=([0-9]*)\\sos_prio=([0-9]*)\\stid=([A-Za-z0-9]*)\\snid=([A-Za-z0-9]*)\\s+([A-Za-z.() ]*)\\s*\\[(.*)]");

    // "VM Thread" os_prio=0 tid=0x00007f3454086800 nid=0x79ac runnable

    private static final Pattern OTHER_PATTERN = Pattern.compile("\"([^\"]*)\"\\s*(daemon)*\\sos_prio=([0-9]*)\\stid=([A-Za-z0-9]*)\\snid=([A-Za-z0-9]*)\\s+([A-Za-z.() ]*)\\s*(.*)");

    private final @NotNull String threadName;
    private final @NotNull Long threadId;
    private final @NotNull String daemonFlag;
    private final @NotNull String priority;
    private final @NotNull String osPriority;
    private final @NotNull String address;
    private final @NotNull String osThreadId;
    private final @NotNull String threadStatus;
    private final @NotNull String lastKnownJavaStackPointer;

    ThdStackRegExHeaderLine(final @NotNull Iterator<String> linesIter) {
      String line = "";
      if (linesIter.hasNext()) {
        line = linesIter.next();
        if (StringUtils.isBlank(line) && linesIter.hasNext()) {
          line = linesIter.next();
        }
      }

      Matcher matcher = PATTERN.matcher(line);
      boolean isMatch = matcher.find();
      if (isMatch) {
        threadName = matcher.group(1);
        threadId = Long.parseLong(matcher.group(2));
        daemonFlag = (matcher.group(3) == null ? "" : matcher.group(3));
        priority = matcher.group(4);
        osPriority = matcher.group(5);
        address = matcher.group(6);
        osThreadId = matcher.group(7);
        threadStatus = matcher.group(8);
        lastKnownJavaStackPointer = matcher.group(9);
      } else {
        Matcher otherMatcher = OTHER_PATTERN.matcher(line);
        if (otherMatcher.find()) {
          threadName = otherMatcher.group(1);
          threadId = -1L;
          daemonFlag = (otherMatcher.group(2) == null ? "" : otherMatcher.group(2));
          priority = "";
          osPriority = otherMatcher.group(3);
          address = otherMatcher.group(4);
          osThreadId = otherMatcher.group(5);
          threadStatus = otherMatcher.group(6);
          lastKnownJavaStackPointer = otherMatcher.group(7);
        } else {
          threadName = "";
          threadId = 0L;
          daemonFlag = "";
          priority = "";
          osPriority = "";
          address = "";
          osThreadId = "";
          threadStatus = "";
          lastKnownJavaStackPointer = "";
        }
      }
    }

  }

  public static class ThdStackRegExStateLine {
    private static final Pattern PATTERN = Pattern.compile("\\s*java\\.lang\\.Thread\\.State:\\s*([A-Z_]+)\\s*\\(*([A-Za-z]*)\\)*\\s*");

    private final @NotNull Matcher matcher;

    private final @NotNull Thread.State state;
    private final @NotNull String optionalInfo;

    ThdStackRegExStateLine(final @NotNull Iterator<String> linesIter) {

      String line = "";
      if (linesIter.hasNext()) {
        line = linesIter.next();
        if (StringUtils.isBlank(line) && linesIter.hasNext()) {
          line = linesIter.next();
        }
      }

      matcher = PATTERN.matcher(line);
      boolean isMatch = matcher.find();
      if (isMatch) {
        state = Thread.State.valueOf(matcher.group(1));
        optionalInfo = matcher.group(2);
      } else {
        state = Thread.State.NEW;
        optionalInfo = "";
      }
    }

  }

  public static class ThdStackRegExFrameLines {
    // at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
    // at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
    private static final Pattern STD_PATTERN = Pattern.compile("\\s*at\\s*([A-Za-z0-9.$]+)\\(([A-Za-z0-9]+).java:([0-9]+)\\)");
    // at sun.misc.Unsafe.park(Native Method)
    private static final Pattern NATIVE_METHOD_PATTERN = Pattern.compile("\\s*at\\s*([A-Za-z0-9.$]+)\\(Native Method\\)");

    private boolean isMatch;

    private final @NotNull String pkgClassInnerClassAndMethod;
    private final @NotNull String fileName;
    private final @NotNull Long lineNumber;

    private final @NotNull String pkgName;
    private final @NotNull String className;
    private final @NotNull String innerClassName;
    private final @NotNull String methodName;

    public ThdStackRegExFrameLines(final @NotNull String line) {
      Matcher matcher = STD_PATTERN.matcher(line);
      isMatch = matcher.find();
      if (isMatch) {
        pkgClassInnerClassAndMethod = matcher.group(1);
        fileName = matcher.group(2);
        lineNumber = Long.parseLong(matcher.group(3));
      } else {
        matcher = NATIVE_METHOD_PATTERN.matcher(line);
        isMatch = matcher.find();
        if (isMatch) {
          pkgClassInnerClassAndMethod = matcher.group(1);
          fileName = "";
          lineNumber = -1L;
        } else {
          pkgClassInnerClassAndMethod = "";
          fileName = "";
          lineNumber = -1L;
        }
      }
      methodName = StringUtils.substringAfterLast(pkgClassInnerClassAndMethod, ".");
      final String pkgClassInnerClass = StringUtils.substringBeforeLast(pkgClassInnerClassAndMethod, ".");
      innerClassName = StringUtils.substringAfterLast(pkgClassInnerClass, "$");
      final String pkgClass = StringUtils.substringBeforeLast(pkgClassInnerClass, "$");
      className = StringUtils.substringAfterLast(pkgClass, ".");
      pkgName = StringUtils.substringBeforeLast(pkgClass, ".");
    }

    ThdStackFrame buildThdStackFrame(int stackFramePosition) {
      return new ThdStackFrame(pkgName, className, innerClassName, methodName, fileName, lineNumber, stackFramePosition);
    }
  }


  public static class ThdStackRegExLockLine {
    private static final Pattern PATTERN = Pattern.compile("\\s*-(.*)\\s*");
    private final boolean isMatch;

    private final @NotNull String content;

    ThdStackRegExLockLine(final @NotNull String line) {
      Matcher matcher = PATTERN.matcher(line);
      isMatch = matcher.find();
      if (isMatch) {
        content = matcher.group(1);
      } else {
        content = "";
      }
    }

    ThdStackLockLine buildThdStackLockLine(int stackFramePosition) {
      return new ThdStackLockLine(content, stackFramePosition);
    }
  }


  public static class ThdStackRegExLockedOwnableSynchronizerLines {
    // at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
    // at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
    private static final Pattern PATTERN = Pattern.compile("\\s*Locked ownable synchronizers:");

    private @NotNull String content;

    ThdStackRegExLockedOwnableSynchronizerLines(final @NotNull ListIterator<String> paragraphIter) {
      content = "";
      if (!paragraphIter.hasNext()) {
        return;
      }
      final String nextParagraph = paragraphIter.next();
      Iterator<String> linesIter = Arrays.asList(nextParagraph.split("\\r?\\n")).iterator();
      if (!linesIter.hasNext()) {
        paragraphIter.previous();
        return;
      }

      String firstLine = linesIter.next();
      if (firstLine.isEmpty()) {
        if (linesIter.hasNext())
          firstLine = linesIter.next();
      }
      final Matcher matcher = PATTERN.matcher(firstLine);
      boolean isMatch = matcher.find();
      if (isMatch) {
        StringBuilder sb = new StringBuilder();
        while (linesIter.hasNext()) {
          sb.append(linesIter.next());
        }
        content = sb.toString();
      } else {
        paragraphIter.previous();
      }
    }
  }
}