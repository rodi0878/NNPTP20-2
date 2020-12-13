package cz.upce.fei.inptp.zz.entity;

import java.util.List;

/**
 * Interface for Tree-based categories. Root category has the {@code  NullCategory} parent.
 */
public interface ICategory extends Comparable<ICategory> {

    String getName();

    void setName(String name);

    ICategory getParent();

    void setParent(ICategory parent);

    List<ICategory> getChildren();

    void setChildren(List<ICategory> children);
}
