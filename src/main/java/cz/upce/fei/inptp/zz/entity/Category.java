package cz.upce.fei.inptp.zz.entity;

import java.util.List;
import java.util.Objects;

/**
 * Tree-based categories. Root category has the {@code  null} parent.
 */
public class Category {

    private String name;
    private Category parent;
    private List<Category> children;

    public Category() {
        name = "Unnamed Category";
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }

    public Category(String name, Category parent, List<Category> children) {
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    @Override
    public String toString() {
        String resultText = "";
        resultText += "Category: " + name + "\n";
        if (parent != null) {
            resultText += "Parent: " + parent.name + "\n";
        }
        if (children == null) {
            return resultText;
        }
        resultText += "Childrens: | ";
        for (Category category : children) {
            resultText += category.name + " | ";
        }
        return resultText;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() == obj.getClass()) {
            return this.equals((Category) obj);
        }
        return false;
    }

    public boolean equals(Category other) {
        return name.equals(other.name);
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

    public boolean hasParent() {
        return parent != null;
    }
}
