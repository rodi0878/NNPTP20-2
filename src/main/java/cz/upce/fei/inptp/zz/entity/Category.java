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
 * Tree-based categories. Root category has the {@code  null} parent.
 */
public class Category {
    private String name;
    private Category parent;
    private Set<Category> children;

    public Category() {
    }

    public Category(String name, Category parent, Set<Category> children) {
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
        children = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category thisCategory= this;
        Category otherCategory = (Category) o;
        while (true){
            if (!thisCategory.name.equals(otherCategory.name)) return false;
            if (thisCategory.parent == null && otherCategory.parent ==null) return true;
            if (thisCategory.parent == null || otherCategory.parent == null) return false;
            thisCategory = thisCategory.parent;
            otherCategory = otherCategory.parent;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }
}
