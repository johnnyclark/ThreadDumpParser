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
import java.util.Set;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */

@Entity
@Table(name="TD")
public class TdEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="header_line")
  private String headerLine;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "td_id")
  private Set<ThdStackEntity> thdStacks;



  public String getHeaderLine() {
    return headerLine;
  }

  public void setHeaderLine(String headerLine) {
    this.headerLine = headerLine;
  }

  @Column(name="trailer_line")
  private String trailerLine;

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

  public static TdEntity fromTd(Td td) {
    TdEntity tdEntity = new TdEntity();
    tdEntity.setHeaderLine(td.getTdHeader().getFullContent());
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
