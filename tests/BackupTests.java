import csc4700.exceptions.SerializedFormatException;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.*;
import csc4700.*;

import java.io.*;

public class BackupTests {

    private File testFile;

    @Before
    public void createTempFile() throws IOException {
        File tempFile = File.createTempFile("BackupTests", "");
        this.testFile = tempFile;

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.testFile));
        bw.write("Test Data");
        bw.close();
    }

    @After
    public void deleteTempFile() {
        File f = new File(this.testFile.getAbsolutePath());
        f.delete();
    }

    @Test
    public void saveShoppingCartTest() throws IOException {
        //setup
        Backup testBackup = new Backup();
        ShoppingCart testCart = new ShoppingCart();
        Item testItem = new Item();
        String testName = "test";
        int testCost = 50;
        String testDescrip = "description";
        testItem.setName(testName);
        testItem.setCost(testCost);
        testItem.setDescription(testDescrip);
        testCart.addItem(testItem);

        //test
        testBackup.saveShoppingCart(testCart, this.testFile);

        //verify
        ShoppingCart result = new ShoppingCart();
        try {
            result = testBackup.loadShoppingCart(this.testFile);
        } catch (SerializedFormatException e) {
            //not expected
        }
        assertEquals(testCart.getCartItems(), result.getCartItems());
    }

    @Test
    public void loadShoppingCartExceptionTest() throws IOException, SerializedFormatException {
        //setup
        Backup testBackup = new Backup();
        deleteTempFile();

        //test
        try {
            testBackup.loadShoppingCart(this.testFile);
        } catch (FileNotFoundException e) {
            //expected
        }
    }

    @Test
    public void serializeShoppingCartNullTest() {
        //setup
        ShoppingCart testCart = new ShoppingCart();
        Backup testBackup = new Backup();
        testCart = null;

        //test
        try {
            testBackup.serializeShoppingCart(testCart);
        } catch (NullPointerException e) {
            //expected
        }
    }

    @Test
    public void serializeShoppingCartTest() {
        //setup
        ShoppingCart testCart = new ShoppingCart();
        Item testItem = new Item();
        String testName = "test";
        int testCost = 50;
        String testDescrip = "description";
        testItem.setName(testName);
        testItem.setCost(testCost);
        testItem.setDescription(testDescrip);
        testCart.addItem(testItem);
        Backup testBackup = new Backup();
        String expected = testName + testBackup.FIELD_SEPARATOR + testCost + testBackup.FIELD_SEPARATOR;
        expected += testDescrip + testBackup.FIELD_SEPARATOR + testCart.findCartItem(testItem).getCount();
        expected += testBackup.LINE_SEPARATOR;

        //test
        String result = testBackup.serializeShoppingCart(testCart);

        //verify
        assertEquals(result, expected);
    }

    @Test
    public void deserializeShoppingCartTest() {
        //setup
        ShoppingCart testCart = new ShoppingCart();
        Item testItem = new Item();
        String testName = "test";
        int testCost = 50;
        String testDescrip = "description";
        testItem.setName(testName);
        testItem.setCost(testCost);
        testItem.setDescription(testDescrip);
        testCart.addItem(testItem);
        Backup testBackup = new Backup();
        String serialize = testBackup.serializeShoppingCart(testCart);
        ShoppingCart resultCart = new ShoppingCart();

        //test
        try {
            resultCart = testBackup.deserializeShoppingCart(serialize);
        } catch (SerializedFormatException e) {
            //not expected
        }

        //verify
        assertEquals(resultCart.getCartItems(), testCart.getCartItems());
    }

    @Test
    public void deserializeShoppingCartNullTest() {
        //setup
        String s = null;
        Backup testBackup = new Backup();

        //test
        try {
            try {
                testBackup.deserializeShoppingCart(s);
            } catch (SerializedFormatException e) {
                //not expected
            }
        } catch (NullPointerException e) {
            //expected
        }
    }

    @Test
    public void deserializeShoppingCartExceptionTest() {
        //setup
        String s = "Hello,This,Is,A,Test";
        Backup testBackup = new Backup();

        //test
        try {
            testBackup.deserializeShoppingCart(s);
        } catch (SerializedFormatException e) {
            //expected
        }
    }
}