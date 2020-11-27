package cz.upce.fei.inptp.zz.entity;

import java.util.HashSet;
import java.util.Set;

public class NullCategory implements ICategory{

    private static NullCategory instance;

    private NullCategory(){}

    public static NullCategory getInstance(){
        if (instance == null) {
            instance = new NullCategory();
        }
        return instance;
    }

    @Override
    public String getName() {
        return "";
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
    public Set<ICategory> getChildren() {
        return new HashSet<>();
    }

    @Override
    public void setChildren(Set<ICategory> children) {
    }
}
