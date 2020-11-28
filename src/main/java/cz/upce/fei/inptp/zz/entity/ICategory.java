package cz.upce.fei.inptp.zz.entity;

import java.util.SortedSet;

/**
 * Interface for Tree-based categories. Root category has the {@code  NullCategory} parent.
 */
public interface ICategory extends Comparable<ICategory> {

    String getName();

    void setName(String name);

    ICategory getParent();

    void setParent(ICategory parent);

    SortedSet<ICategory> getChildren();

    void setChildren(SortedSet<ICategory> children);
}
