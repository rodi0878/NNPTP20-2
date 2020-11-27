package cz.upce.fei.inptp.zz.entity;

import junit.framework.TestCase;

public class CategoryTest extends TestCase {

    private Category testCategory;
    private Category controlCategory;
    private Category differentCategory;

    @Override
    public void setUp(){
        Category testRootCategory = new Category("test", NullCategory.getInstance());
        testCategory = new Category("work", testRootCategory);
        testRootCategory.getChildren().add(testCategory);
        Category controlRootCategory = new Category("test", NullCategory.getInstance());
        controlCategory = new Category("work", controlRootCategory);
        controlCategory.getChildren().add(controlCategory);
        Category differentRootCategory = new Category("different", NullCategory.getInstance());
        differentCategory = new Category("work", differentRootCategory);
        differentCategory.getChildren().add(differentCategory);
    }

    public void testEqualsSame() {
        assertTrue(testCategory.equals(controlCategory));
    }

    public void testEqualsDifferent() {
        assertFalse(testCategory.equals(differentCategory));
    }


    public void testHashCodeSame() {
        assertEquals(testCategory.hashCode(), controlCategory.hashCode());
    }
    public void testHashCodeDifferent() {
        assertNotSame(testCategory.hashCode(), differentCategory.hashCode());
    }
}