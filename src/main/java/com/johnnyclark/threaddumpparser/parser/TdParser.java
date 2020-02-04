package com.johnnyclark.threaddumpparser.parser;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
public class TdParser {
  private static final Logger logger = LoggerFactory.getLogger(TdParser.class);

  public Td parse(@NotNull String fileName, @NotNull String fullThreadDumpContent) {
    List<String> paragraphs = getParagraphs(fullThreadDumpContent);
    TdHeader tdHeader = processTdHeader(paragraphs);
    removeHeaderParagraph(paragraphs, tdHeader);
    TdTrailer tdTrailer = null;

    TdTrailerParser tdTrailerParser = new TdTrailerParser();
    ThdStackParser thdStackParser = new ThdStackParser();
    List<ThdStack> thdStacks = new ArrayList<>();
    int numParagraphs = paragraphs.size();
    if (numParagraphs > 0) {
      for (ListIterator<String> paragraphIter = paragraphs.listIterator(0); paragraphIter.hasNext(); ) {
        String paragraph = paragraphIter.next();

        if (StringUtils.isEmpty(paragraph)) {
          if (paragraphIter.hasNext()) {
            paragraph = paragraphIter.next();
          }
        }
        if (tdTrailerParser.matches(paragraph)) {
          tdTrailer = tdTrailerParser.parse(paragraph);
          break;
        }
        ThdStack thdStack = processStackParagraph(thdStackParser, paragraph, paragraphIter);
//        if(StringUtils.isBlank(thdStack.getName()))
//          System.out.printf("");
        thdStacks.add(thdStack);
      }
    }

    if (tdTrailer == null) {
      tdTrailer = new TdTrailer("");  // If the thread dump was produced via Intellij debugger (or some other means) then it might not have the correct header/trailer
    }

    return new Td(fileName, tdHeader, thdStacks, tdTrailer);
  }

  private void removeHeaderParagraph(List<String> paragraphs, TdHeader tdHeader) {
    if (tdHeader.getFullContent().isEmpty())
      return;
    ;  // If the thread dump was produced via Intellij debugger (or some other means) then it might not have the correct header/trailer
    paragraphs.remove(0);
  }

  private ThdStack processStackParagraph(ThdStackParser thdStackParser, String paragraph, ListIterator<String> paragraphIter) {
    try {
//      logger.debug("Processing paragraph: " + paragraph);
      return thdStackParser.parse(paragraph, paragraphIter);
    } catch (Exception e) {
      logger.error("Exception thrown while processing paragraph \n" + paragraph);
      e.printStackTrace();
      throw e;
    }
  }

  private TdHeader processTdHeader(List<String> paragraphs) {
    if (paragraphs.size() > 0) {
      return new TdHeaderParser().parse(paragraphs.get(0));
    }
    return null;
  }

  @NotNull
  private List<String> getParagraphs(@NotNull String fullThreadDumpContent) {
    List<String> paragraphs = new ArrayList<>();
    try (Scanner scanner = new Scanner(fullThreadDumpContent);) {
      scanner.useDelimiter("(?m:^$)");

      while (scanner.hasNext()) {
        paragraphs.add(scanner.next());
      }
    }
    return paragraphs;
  }
}
