package com.johnnyclark.threaddumpparser.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 12/3/2019
 */

@Entity
@Table(name = "StackFrame")
public class ThdStackFrameEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "stack_id")
  private ThdStackEntity thdStackEntity;

  @Column(name = "pkgName")
  private String pkgName;

  @Column(name = "className")
  private String className;

  @Column(name = "innerClassName")
  private String innerClassName;

  @Column(name = "methodName")
  private String methodName;

  @Column(name = "fileName")
  private String fileName;

  @Column(name = "lineNumber")
  private Long lineNumber;

  @Column(name = "stackFramePosition")
  private Long stackFramePosition;


  public String getPkgName() {
    return pkgName;
  }

  public void setPkgName(String pkgName) {
    this.pkgName = pkgName;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getInnerClassName() {
    return innerClassName;
  }

  public void setInnerClassName(String innerClassName) {
    this.innerClassName = innerClassName;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Long getLineNumber() {
    return lineNumber;
  }

  public void setLineNumber(Long lineNumber) {
    this.lineNumber = lineNumber;
  }

  public Long getStackFramePosition() {
    return stackFramePosition;
  }

  public void setStackFramePosition(Long stackFramePosition) {
    this.stackFramePosition = stackFramePosition;
  }
}
