import csc4700.exceptions.InvalidCountException;
import org.junit.Test;
import csc4700.*;
import org.omg.CORBA.DynAnyPackage.Invalid;

import static org.junit.Assert.*;

public class CartItemTests {

    //used at the beginning of most tests
    public CartItem createCartItem() {
        Item testItem = new Item();
        CartItem testCartItem = new CartItem(testItem);

        return testCartItem;
    }

    @Test
    public void cartItemConstructorTest() {
        //setup
        Item testItem = new Item();

        //test
        CartItem testCartItem = new CartItem(testItem);

        //verify
        assertNotNull(testCartItem);
    }

    @Test
    public void incrementTest() {
        //setup
        CartItem testCartItem = createCartItem();

        //test
        testCartItem.incrementCountByOne();

        //verify
        assertEquals(1, testCartItem.getCount());
    }

    @Test
    public void decrementTest() {
        //setup
        CartItem testCartItem = createCartItem();
        //to avoid exception
        testCartItem.setCount(2);

        //test
        testCartItem.decrementCountByOne();

        //verify
        assertEquals(1, testCartItem.getCount());
    }

    @Test
    public void hashCodeTest() {
        //setup
        String testName = "test";
        CartItem testCartItem = createCartItem();
        testCartItem.getItem().setName(testName);

        //test
        int result = testCartItem.hashCode();

        //verify
        assertEquals(testName.hashCode(), result);
    }

    @Test
    public void getItemTest() {
        //setup
        Item testItem = new Item();
        CartItem testCartItem = new CartItem(testItem);

        //test
        Item result = testCartItem.getItem();

        //verify
        assertEquals(testItem, result);
    }

    @Test
    public void setItemTest() {
        //setup
        Item testItem1 = new Item();
        Item testItem2 = new Item();
        CartItem testCartItem = new CartItem(testItem1);

        //test
        testCartItem.setItem(testItem2);

        //result
        assertEquals(testItem2, testCartItem.getItem());
    }

    @Test
    public void invalidCountTest() {
        //setup
        CartItem testCartItem = createCartItem();

        try {
            testCartItem.decrementCountByOne();
        } catch (InvalidCountException e) {
            //expected
        }
    }

    @Test
    public void equalsTrueTest() {
        //setup
        CartItem testCartItem = createCartItem();

        //test
        boolean result = testCartItem.equals(testCartItem);

        //verify
        assertTrue(result);
    }

    @Test
    public void equalsNullTest() {
        //setup
        CartItem testCartItem1 = createCartItem();
        CartItem testCartItem2 = null;

        //test
        boolean result = testCartItem1.equals(testCartItem2);

        //verify
        assertFalse(result);
    }

    @Test
    public void equalsNameTest() {
        //setup
        Item item = new Item();
        CartItem testCartItem1 = new CartItem(item);
        CartItem testCartItem2 = new CartItem(item);

        //test
        boolean result = testCartItem1.equals(testCartItem2);

        //verify
        assertTrue(result);
    }
}
