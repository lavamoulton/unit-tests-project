import org.junit.Test;
import csc4700.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShoppingCartTests {

    @Test
    public void addItemNullTest() {
        //setup
        ShoppingCart testCart = new ShoppingCart();
        Item testItem = null;

        //test
        try {
            testCart.addItem(testItem);
        } catch (NullPointerException e) {
            //expected
        }
    }

    @Test
    public void addItemTest() {
        //setup
        ShoppingCart testCart = new ShoppingCart();
        Item testItem = new Item();
        CartItem testCartItem = new CartItem(testItem);

        //test
        testCart.addItem(testItem);

        //verify
        CartItem cartItem = testCart.getCartItems().get(0);
        assertEquals(cartItem, testCartItem);
    }

    @Test
    public void addItemCountTest() {
        //setup
        ShoppingCart testCart = new ShoppingCart();
        Item testItem = new Item();
        CartItem testCartItem = new CartItem(testItem);
        testCartItem.incrementCountByOne();

        //test
        testCart.addItem(testItem);

        //verify
        int count = testCart.getCartItems().get(0).getCount();
        assertEquals(count, testCartItem.getCount());
    }

    @Test
    public void deleteItemNullTest() {
        //setup
        ShoppingCart testCart = new ShoppingCart();
        Item testItem = new Item();
        testCart.addItem(testItem);
        testItem = null;

        //test
        try {
            testCart.deleteItem(testItem);
        } catch (NullPointerException e) {
            //expected
        }
    }

    @Test
    public void deleteItemNoneTest() {
        //setup
        ShoppingCart testCart = new ShoppingCart();
        Item testItem = new Item();

        //test
        testCart.deleteItem(testItem);
    }

    @Test
    public void deleteItemLastTest() {
        //setup
        ShoppingCart testCart = new ShoppingCart();
        Item testItem = new Item();
        testCart.addItem(testItem);

        //test
        testCart.deleteItem(testItem);

        //verify
        assertTrue(testCart.getCartItems().isEmpty());
    }

    @Test
    public void deleteItemTest() {
        //setup
        ShoppingCart testCart = new ShoppingCart();
        Item testItem = new Item();
        testCart.addItem(testItem);
        testCart.addItem(testItem);
        CartItem testCartItem = new CartItem(testItem);
        testCartItem.incrementCountByOne();

        //test
        testCart.deleteItem(testItem);

        //verify
        int result = testCart.findCartItem(testItem).getCount();
        assertEquals(testCartItem.getCount(), result);
    }

    @Test
    public void findCartItemNullTest() {
        //setup
        ShoppingCart testCart = new ShoppingCart();
        Item testItem = new Item();

        //test
        CartItem testCartItem = testCart.findCartItem(testItem);

        //verify
        assertEquals(null, testCartItem);
    }
}
