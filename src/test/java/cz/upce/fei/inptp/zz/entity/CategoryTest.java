package cz.upce.fei.inptp.zz.entity;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CategoryTest {

    private Category category;
    private Category parentCategory;
    private List<Category> children;

    @Before
    public void setUp() {
        children = new ArrayList<>();
        parentCategory = new Category();
        category = new Category("TestCategory", parentCategory, children);
    }

    @Test
    public void testGetName() {
        assertEquals(category.getName(), "TestCategory");
    }

    @Test
    public void testSetName() {
        category.setName("RenameCategory");
        assertEquals(category.getName(), "RenameCategory");
    }

    @Test
    public void testGetParent() {
        assertEquals(category.getParent(), parentCategory);
    }

    @Test
    public void testSetParent() {
        Category testParent = new Category();
        category.setParent(testParent);
        assertEquals(category.getParent(), testParent);
    }

    @Test
    public void testGetChildren() {
        assertEquals(category.getChildren(), children);
    }

    @Test
    public void testSetChildren() {
        List<Category> testChildrenList = new ArrayList<>();
        category.setChildren(testChildrenList);
        assertEquals(category.getChildren(), testChildrenList);
    }
}