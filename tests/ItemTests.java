import org.junit.Test;
import csc4700.Item;

import static org.junit.Assert.*;

public class ItemTests {

    @Test
    public void hashCodeTest() {
        //setup
        Item testItem = new Item();
        String testName = "name";
        testItem.setName(testName);

        //test
        int result = testItem.hashCode();

        //verify
        assertEquals(testName.hashCode(), result);
    }

    @Test
    public void getNameTest() {
        //setup
        Item testItem = new Item();
        String testName = "name";
        testItem.setName(testName);

        //test
        String result = testItem.getName();

        //verify
        assertEquals(result, testName);
    }

    @Test
    public void setNameTest() {
        //setup
        Item testItem = new Item();
        String testName = "name";

        //test
        testItem.setName(testName);

        //verify
        assertEquals(testName, testItem.getName());
    }

    @Test
    public void getCostTest() {
        //setup
        Item testItem = new Item();
        int testCost = 50;
        testItem.setCost(testCost);

        //test
        int result = testItem.getCost();

        //verify
        assertEquals(result, testCost);
    }

    @Test
    public void setCostTest() {
        //setup
        Item testItem = new Item();
        int testCost = 50;

        //test
        testItem.setCost(testCost);

        //verify
        assertEquals(testCost, testItem.getCost());
    }

    @Test
    public void setDescriptionTest() {
        //setup
        Item testItem = new Item();
        String testDescription = "description";

        //test
        testItem.setDescription(testDescription);

        //verify
        assertEquals(testDescription, testItem.getDescription());
    }

    @Test
    public void getDescriptionTest() {
        //setup
        Item testItem = new Item();
        String testDescription = "description";
        testItem.setDescription(testDescription);

        //test
        String result = testItem.getDescription();

        //verify
        assertEquals(testDescription, result);
    }

    @Test
    public void equalsTrueTest() {
        //setup
        Item testItem = new Item();
        testItem.setName("test");

        //test
        boolean result = testItem.equals(testItem);

        //verify
        assertTrue(result);
    }

    @Test
    public void equalsNullTest() {
        //setup
        String testName = "test";
        Item testItem1 = new Item();
        Item testItem2 = null;
        testItem1.setName(testName);

        //test
        boolean result = testItem1.equals(testItem2);

        //verify
        assertFalse(result);
    }

    @Test
    public void equalsNameTest() {
        //setup
        String testName = "test";
        Item testItem1 = new Item();
        Item testItem2 = new Item();
        testItem1.setName(testName);
        testItem2.setName(testName);

        //test
        boolean result = testItem1.equals(testItem2);

        //verify
        assertTrue(result);
    }
}
