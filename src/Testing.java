import static  org.junit.Assert.assertEquals;
import org.junit.Test;

public class Testing {
    @Test
    public void testLoginAdmin(){
        Client testingSocket = new Client();
        testingSocket.sendTextToServer("login" + "::" + "admin" + "::" + "admin");
        Boolean adminrules = testingSocket.getBoolean();
        assertEquals(true, adminrules);
    }
    @Test
    public void testLoginUser(){
        Client testingSocket = new Client();
        testingSocket.sendTextToServer("login" + "::" + "Testing" + "::" + "test");
        Boolean adminrules = testingSocket.getBoolean();
        assertEquals(false, adminrules);
    }
    @Test
    public void testIsAlreadyBoughtTicket(){
        Client testingSocket = new Client();
        testingSocket.sendTextToServer("login" + "::" + "User" + "::" + "User");
        Boolean adminrules = testingSocket.getBoolean();
        testingSocket.sendTextToServer("isalredaybought" + "::" + "Warsaw-Katowice, 2025-04-12(13:45) - 2025-04-13(18:30), First Class, S7, 200"); 
        assertEquals(true, testingSocket.getBoolean());
    }
    
    

}
