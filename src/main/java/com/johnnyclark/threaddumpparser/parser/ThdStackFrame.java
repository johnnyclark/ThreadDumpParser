package com.johnnyclark.threaddumpparser.parser;

import com.sun.istack.internal.NotNull;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
public class ThdStackFrame {
  private final @NotNull String pkgName;
  private final @NotNull String className;
  private final @NotNull String innerClassName;
  private final @NotNull String methodName;

  private final @NotNull String fileName;
  private final @NotNull Long lineNumber;
  private final int stackFramePosition;

  public ThdStackFrame(@NotNull String pkgName, @NotNull String className, @NotNull String innerClassName, @NotNull String methodName, @NotNull String fileName, @NotNull Long lineNumber, int stackFramePosition) {
    this.pkgName = pkgName;
    this.className = className;
    this.innerClassName = innerClassName;
    this.methodName = methodName;
    this.fileName = fileName;
    this.lineNumber = lineNumber;
    this.stackFramePosition = stackFramePosition;
  }

  public String getPkgName() {
    return pkgName;
  }

  public String getClassName() {
    return className;
  }

  public String getInnerClassName() {
    return innerClassName;
  }

  public String getMethodName() {
    return methodName;
  }

  public String getFileName() {
    return fileName;
  }

  public Long getLineNumber() {
    return lineNumber;
  }

  public int getStackFramePosition() {
    return stackFramePosition;
  }

  @Override
  public String toString() {
    return pkgName + ' ' + className + ' ' + (innerClassName.isEmpty() ? "--" : innerClassName) + ' ' + methodName + ' ' + fileName + ' ' + lineNumber;
  }
}
