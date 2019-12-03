package com.johnnyclark.threaddumpparser.parser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.johnnyclark.threaddumpparser.model.TdEntity;
import com.johnnyclark.threaddumpparser.model.ThdStackEntity;
import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:johnny.clark@sap.com">Johnny Clark</a>
 * Creation Date: 11/27/2019
 */
public class Td {
  private final @NotNull TdHeader tdHeader;
  private final @NotNull List<ThdStack> thdStacks;
  private final @NotNull TdTrailer tdTrailer;

  public Td(TdHeader tdHeader, List<ThdStack> thdStacks, TdTrailer tdTrailer) {
    this.tdHeader = tdHeader;
    this.thdStacks = ImmutableList.copyOf(thdStacks);
    this.tdTrailer = tdTrailer;
  }

  public TdHeader getTdHeader() {
    return tdHeader;
  }

  public List<ThdStack> getThdStacks() {
    return thdStacks;
  }

  public TdTrailer getTdTrailer() {
    return tdTrailer;
  }

  public Set<ThdStackEntity> getThdStackEntities(TdEntity tdEntity) {
    Set<ThdStackEntity> entities = Sets.newHashSet();
    for (ThdStack thdStack : thdStacks) {
      entities.add(ThdStackEntity.fromThdStack(thdStack, tdEntity));
    }
    return entities;
  }
}
