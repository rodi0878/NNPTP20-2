package cz.upce.fei.inptp.zz.entity;

import junit.framework.TestCase;

public class CategoryTest extends TestCase {

    private Category testCategory;
    private Category controlCategory;
    private Category differentCategory;

    @Override
    public void setUp(){
        Category testRootCategory = new Category.CategoryBuilder()
                .setName("test")
                .setParent(NullCategory.getInstance())
                .createCategory();
        testCategory = new Category.CategoryBuilder()
                .setName("work")
                .setParent(testRootCategory)
                .createCategory();
        testRootCategory.getChildren().add(testCategory);
        Category controlRootCategory = new Category.CategoryBuilder()
                .setName("test")
                .setParent(NullCategory.getInstance())
                .createCategory();
        controlCategory = new Category.CategoryBuilder()
                .setName("work")
                .setParent(controlRootCategory)
                .createCategory();
        controlCategory.getChildren().add(controlCategory);
        Category differentRootCategory = new Category.CategoryBuilder()
                .setName("different")
                .setParent(NullCategory.getInstance())
                .createCategory();
        differentCategory = new Category.CategoryBuilder()
                .setName("work")
                .setParent(differentRootCategory)
                .createCategory();
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

    public void testCompareToSame(){
        assertEquals(0, testCategory.compareTo(controlCategory));
    }

    public void testCompareToLesser(){
        assertTrue(differentCategory.compareTo(testCategory) <0);
    }

    public void testCompareToGreater(){
        assertTrue(testCategory.compareTo(differentCategory) >0);
    }
}