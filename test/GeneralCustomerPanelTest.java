package test;


import org.junit.Before;
import org.junit.Test;
import userInterface.GeneralCustomerPanel;


import static org.junit.Assert.assertEquals;


public class GeneralCustomerPanelTest {

    private GeneralCustomerPanel generalCustomerPanel;

    @Before
    public void setUp() {
        generalCustomerPanel = new GeneralCustomerPanel();
    }

    @Test
    public void testGetAndSetLastName() {
        generalCustomerPanel.setLastName("Doe");
        assertEquals("Doe", generalCustomerPanel.getLastName());
    }

    @Test
    public void testGetAndSetFirstName() {
        generalCustomerPanel.setFirstName("John");
        assertEquals("John", generalCustomerPanel.getFirstName());
    }

    @Test
    public void testGetAndSetGender() {
        generalCustomerPanel.setGender("H");
        assertEquals("M", generalCustomerPanel.getGender());
        generalCustomerPanel.setGender("F");
        assertEquals("F", generalCustomerPanel.getGender());
        generalCustomerPanel.setGender("X");
        assertEquals("X", generalCustomerPanel.getGender());
    }

    @Test
    public void testGetAndSetTelNumber() {
        generalCustomerPanel.setTelNumber("0123456789");
        assertEquals("+32123456789", generalCustomerPanel.getTelNumber());
    }

    @Test
    public void testGetAndSetMailAddress() {
        generalCustomerPanel.setMailAddress("john.doe@example.com");
        assertEquals("john.doe@example.com", generalCustomerPanel.getMailAddress());
    }


    @Test
    public void testGetAndSetIsMarried() {
        generalCustomerPanel.setIsMarried(true);
        assertEquals(true, generalCustomerPanel.getIsMarried());
        generalCustomerPanel.setIsMarried(false);
        assertEquals(false, generalCustomerPanel.getIsMarried());
    }



}
