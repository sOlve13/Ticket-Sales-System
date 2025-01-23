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








    public void send_text(String text) {
        try {
            this.output.writeObject(text);
            this.output.flush();
        } catch (Exception e) {
        }
    }


    public String[] get_free_tickets(){
        try {
            String[] free_tickets = (String[]) this.input.readObject();
            //System.out.println(free_tickets[0]);
            return free_tickets;
        } catch (Exception e) {
        }
        return null;
    }





    public boolean  get_bool(){
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
