package cz.upce.fei.inptp.zz.entity;

import java.util.Set;

/**
 * Interface for Tree-based categories. Root category has the {@code  NullCategory} parent.
 */
public interface ICategory {

    String getName();

    void setName(String name);

    ICategory getParent();

    void setParent(ICategory parent);

    Set<ICategory> getChildren();

    void setChildren(Set<ICategory> children);
}
