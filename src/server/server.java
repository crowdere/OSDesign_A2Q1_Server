
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author eddyc
 * @version 0.01
 * @description Opens a connection for the client to connect to. Echos back all details and 
 *              exits when the client terminates the connection.
 */
public class server
{
    server(int PORT)
    {
        String client_id = null;
        System.out.println("Server started on port " + PORT);
        try
        {
          ServerSocket server = new ServerSocket(PORT);  
            try (Socket client = server.accept()) 
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter( new OutputStreamWriter(client.getOutputStream()));
                out.flush();
                
                while(true)
                {
                    String received = in.readLine();
                    if(received == null)
                    {
                        System.out.println("waiting....");
                        break;
                    }
                    else
                    {
                        if(client_id == null)
                        {
                            client_id = received;
                            System.out.println("Client " + received + " connected!");
                        }
                        else
                        {
                            System.out.println("Client: " + received);
                            out.println("echo: " + received);
                            out.flush();   
                        }
                      
                    }
                    if(received.equals("."))
                    {
                        break;
                    }
                } 
            }
            catch(SocketException e)
            {
                System.out.println("Client " + client_id + " has disconnected!");
            }
        }
        catch (IOException ioex)
        {

        }
    }
}
