package com.johnnyclark.threaddumpparser.model;

import com.google.common.collect.Sets;
import com.johnnyclark.threaddumpparser.parser.Td;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */

@Entity
@Table(name = "ThreadDump")
public class TdEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "thread_dump_id")
  private Set<ThdStackEntity> thdStacks;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "header_line")
  private String headerLine;

  @Column(name = "dumpdatetime")
  private Date dumpDateTime;

  @Column(name = "trailer_line")
  private String trailerLine;


  public String getHeaderLine() {
    return headerLine;
  }

  public void setHeaderLine(String headerLine) {
    this.headerLine = headerLine;
  }

  public String getTrailerLine() {
    return trailerLine;
  }

  public void setTrailerLine(String trailerLine) {
    this.trailerLine = trailerLine;
  }

  public Set<ThdStackEntity> getThdStacks() {
    return thdStacks;
  }

  public void setThdStacks(Set<ThdStackEntity> thdStacks) {
    this.thdStacks = thdStacks;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Date getDumpDateTime() {
    return dumpDateTime;
  }

  public void setDumpDateTime(Date dumpDateTime) {
    this.dumpDateTime = dumpDateTime;
  }

  public static TdEntity fromTd(Td td) {
    TdEntity tdEntity = new TdEntity();
    tdEntity.setFileName(td.getFileName());
    tdEntity.setHeaderLine(td.getTdHeader().getFullContent());
    tdEntity.setDumpDateTime(td.getTdHeader().getDate());
    tdEntity.setTrailerLine(td.getTdTrailer().getFullContent());
    tdEntity.setThdStacks(Sets.newHashSet(td.getThdStackEntities(tdEntity)));
    return tdEntity;
  }

  @Override
  public String toString() {
    return "TdEntity{" +
        "id=" + id +
        ", headerLine='" + headerLine+ '\'' +
        ", trailerLine='" + trailerLine + '\'' +
        '}';
  }
}
