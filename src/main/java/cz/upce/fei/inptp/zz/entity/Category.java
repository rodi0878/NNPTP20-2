/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.entity;

import java.util.*;

/**
 * Tree-based categories. Root category has the {@code  NullCategory} parent.
 */

public class Category implements ICategory {

    private String name;
    private ICategory parent;
    private List<ICategory> children;


    private Category(String name, ICategory parent, List<ICategory> children) {
        this.name = name;
        this.parent = parent;
        this.children = children;
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
    public List<ICategory> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<ICategory> children) {
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

    @Override
    public int compareTo(ICategory o) {
        if (getName().compareTo(o.getName()) == 0){
            return  getParent().compareTo(o.getParent());
        }
        return getName().compareTo(o.getName());
    }
    public static class CategoryBuilder {
        private String name;
        private ICategory parent;
        private List<ICategory> children = new ArrayList<>();

        public CategoryBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CategoryBuilder setParent(ICategory parent) {
            this.parent = parent;
            return this;
        }

        public CategoryBuilder setChildren(List<ICategory> children) {
            this.children = children;
            return this;
        }

        public Category createCategory() {
            return new Category(name, parent, children);
        }
    }
}
