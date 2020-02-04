package com.johnnyclark.threaddumpparser.parser;

import com.johnnyclark.threaddumpparser.model.TdEntity;
import com.johnnyclark.threaddumpparser.repository.TdRepository;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 12/2/2019
 */
public class TdParserDriver {
  private static final Logger logger = LoggerFactory.getLogger(TdParserDriver.class);
  public void process(@NotNull TdRepository tdRepository) throws IOException {
    for (String fileName : listFilesUsingDirectoryStream("C:\\IdeaProjects\\threaddumpparser\\TestInput")) {
      processTdFile(fileName, tdRepository);
    }
  }

  private void processTdFile(@NotNull String fileName, @NotNull TdRepository tdRepository) throws IOException {
    try {
      String fullFileName = "C:\\IdeaProjects\\threaddumpparser\\TestInput\\" + fileName;
      logger.info("Processing File " + fullFileName);
      String contents = new String(Files.readAllBytes(Paths.get(fullFileName)));
      TdParser tdParser = new TdParser();
      Td td = tdParser.parse(fileName, contents);
      TdEntity tdEntity = TdEntity.fromTd(td);

      tdRepository.save(tdEntity);
    } catch (IOException e) {
      logger.error("Exception thrown while processing " + fileName);
      e.printStackTrace();
      throw e;
    }
  }

  private Set<String> listFilesUsingDirectoryStream(String dir) throws IOException {
    Set<String> fileList = new HashSet<>();
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
      for (Path path : stream) {
        if (!Files.isDirectory(path)) {
          fileList.add(path.getFileName()
                           .toString());
        }
      }
    }
    return fileList;
  }
}
