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
        testingSocket.sendTextToServer("isalreadybought" + "::" + "Lublin-Gdansk, 2024-12-03(19:30) - 2024-12-15(22:10), Economy, S22, 16");
        assertEquals(true, testingSocket.getBoolean());
    }
    @Test
    public void testAddingTicket(){
        Client testingSocket = new Client();
        testingSocket.sendTextToServer("login" + "::" + "admin" + "::" + "admin");
        Boolean adminrules = testingSocket.getBoolean();
        testingSocket.sendTextToServer("addticket" + "::" + "Warsaw-Wroclaw, 2000-12-13(00:00) - 2000-12-29(12:30), Economy, S1, 100");
        assertEquals(false, testingSocket.getBoolean());
    }
    
    

}
