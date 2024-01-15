package org.example.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemTest {
    private Item item;

    @BeforeEach
    public void init() {
        item = new Item("Test Item", 5, 10.0);
    }

    @AfterEach
    public void cleanUp(){
        item=null;
    }
    @Test
    public void testGetName() {
        Assert.assertEquals("Test Item", item.getName());
    }

    @Test
    public void testSetName() {
        item.setName("New Test Item");
        Assert.assertEquals("New Test Item", item.getName());
    }

    @Test
    public void testGetQuantity() {
        Assert.assertEquals(5, item.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        item.setQuantity(10);
        Assert.assertEquals(10, item.getQuantity());
    }

    @Test
    public void testGetPrice() {
        Assert.assertEquals(10.0, item.getPrice(),0.01);
    }

    @Test
    public void testSetPrice() {
        item.setPrice(15.0);
        Assert.assertEquals(15.0, item.getPrice(),15.0);
    }

    @Test
    public void testIsRestricted() {
        Assert.assertEquals(false, item.isRestricted());
    }

    @Test
    public void testSetRestricted() {
        item.setRestricted(true);
        Assert.assertTrue( item.isRestricted());
    }

    @Test
    public void testGetRestrictedAge() {
        Assert.assertEquals(0, item.getRestrictedAge());
    }

    @Test
    public void testSetRestrictedAge() {
        item.setRestrictedAge(18);
        Assert.assertEquals(18, item.getRestrictedAge());
    }
}
