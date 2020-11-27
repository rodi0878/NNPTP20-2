/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Tree-based categories. Root category has the {@code  NullCategory} parent.
 */
public class Category implements ICategory {
    private String name;
    private ICategory parent;
    private Set<ICategory> children;

    public Category() {
    }

    public Category(String name, ICategory parent, Set<ICategory> children) {
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    public Category(String name, ICategory parent) {
        this.name = name;
        this.parent = parent;
        children = new HashSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ICategory getParent() {
        return parent;
    }

    @Override
    public void setParent(ICategory parent) {
        this.parent = parent;
    }

    @Override
    public Set<ICategory> getChildren() {
        return children;
    }

    @Override
    public void setChildren(Set<ICategory> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category otherCategory = (Category) o;
        if (!this.name.equals(otherCategory.name)) return false;
        return this.parent.equals(otherCategory.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }
}
