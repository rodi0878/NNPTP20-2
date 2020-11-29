/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.entity;

import java.util.List;

/**
 * Tree-based categories. Root category has the {@code  null} parent.
 */
class Category {
    private String name;
    private Category parent;
    private List<Category> children;


    private Category(String name, Category parent, List<Category> children) {
        this.name = name;
        this.parent = parent;
        this.children = children;
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

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public static class CategoryBuilder {
        private String name;
        private Category parent;
        private List<Category> children;

        public CategoryBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CategoryBuilder setParent(Category parent) {
            this.parent = parent;
            return this;
        }

        public CategoryBuilder setChildren(List<Category> children) {
            this.children = children;
            return this;
        }

        public Category createCategory() {
            return new Category(name, parent, children);
        }
    }
    
}
