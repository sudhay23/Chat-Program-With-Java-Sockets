import java.net.*;
import java.io.*;

public class ChatServer
{
    public static void main(String args[]) throws Exception
    {
        try 
        {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("Server Started...");
            // When client gets connected
            Socket socket = ss.accept();
            System.out.println("Client Connected...");

            // Setup BufferedReader to read from Console
            BufferedReader server_input = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);  // PrintWriter to send to the client

            // Setup BufferedReader with InputStreamReader to receive data from Client
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader client_input = new BufferedReader(inputStreamReader);

            String send, receive;


            // Infinite loop to check if anything is to be sent or was received from client
            while(true)
            {
                if((receive=client_input.readLine())!=null)
                {
                    System.out.println("Client: "+receive);
                }

                send = server_input.readLine();  //Inputs from Server console
                System.out.println(send);
                pw.println(send); // Sending the line read from console to client

                if(send.equals("quit"))
                {
                    System.out.println("Exitting Server...");
                    break;
                }

            }

            // Closing open connections
            ss.close();
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