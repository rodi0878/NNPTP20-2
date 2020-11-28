package cz.upce.fei.inptp.zz.entity;

import java.util.SortedSet;
import java.util.TreeSet;

public class NullCategory implements ICategory{

    private final TreeSet<ICategory> children;
    private final String name;

    private NullCategory(){
        children = new TreeSet<>();
        name = "";
    }

    private static class NullCategoryHelper
    {
        private static final NullCategory INSTANCE = new NullCategory();
    }

    public static NullCategory getInstance(){
        return NullCategoryHelper.INSTANCE;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
    }

    @Override
    public ICategory getParent() {
        return getInstance();
    }

    @Override
    public void setParent(ICategory parent) {

    }

    @Override
    public SortedSet<ICategory> getChildren() {
        children.clear();
        return children;
    }

    @Override
    public void setChildren(SortedSet<ICategory> children) {
    }

    @Override
    public int compareTo(ICategory o) {
        return name.compareTo(o.getName());
    }
}
