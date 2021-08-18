import java.net.*;
import java.io.*;

public class ChatClient
{
    public static void main(String args[]) throws Exception
    {
        try 
        {
            Socket socket = new Socket("localhost",1234); //Connect to the server via TCP sockets
            System.out.println("\t\t\t\t...Start Chatting...");

            // BufferedReader to get input from client console
            BufferedReader client_input = new BufferedReader(new InputStreamReader(System.in));

            // PrintWriter to send info to the Server via Socket
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);

            // BufferedReader to get data from Server via Socket
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader server_input = new BufferedReader(inputStreamReader);

            String send, receive;

            // Infinite loop to check for data
            while(true)
            {
                send = client_input.readLine();
                pw.println(send);
                if((receive=server_input.readLine())!=null)
                {
                    System.out.println("Server: "+receive);
                    if(receive.equals("quit"))
                    {
                        System.out.println("Exitting Client...");
                        break;
                    }
                }
            }

            // Closing open connections
            socket.close();
            server_input.close();
            client_input.close();
            pw.close();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}