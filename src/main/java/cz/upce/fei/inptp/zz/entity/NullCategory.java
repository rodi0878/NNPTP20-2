package cz.upce.fei.inptp.zz.entity;

import java.util.*;

public class NullCategory implements ICategory{

    private final List<ICategory> children;
    private final String name;

    private NullCategory(){
        children = Collections.unmodifiableList(new ArrayList<>());
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
    public List<ICategory> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<ICategory> children) {
    }

    @Override
    public int compareTo(ICategory o) {
        return name.compareTo(o.getName());
    }
}
