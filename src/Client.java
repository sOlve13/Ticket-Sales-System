import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    public static boolean isConnected;

    public Client() {
        try {
            Socket cs = new Socket("localhost", 3555);
            this.output = new ObjectOutputStream(cs.getOutputStream());
            this.input = new ObjectInputStream(cs.getInputStream());
            isConnected = true;
        } catch (Exception e) {
            isConnected = false;
        }
    }








    public void sendTextToServer(String text) {
        try {
            this.output.writeObject(text);
            this.output.flush();
        } catch (Exception e) {
        }
    }


    public String[] getTicketsString(){
        try {
            String[] tickets = (String[]) this.input.readObject();
            //System.out.println(tickets[0]);
            return tickets;
        } catch (Exception e) {
        }
        return null;
    }





    public boolean  getBoolean(){
        try {
            Boolean input = (boolean) this.input.readObject();
            return input;
        } catch (Exception e) {
        }
        return false;
    }




    public static void main(String[] args) {

    }
}
