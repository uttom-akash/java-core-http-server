/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author akash
 */
public class Server {
    ServerSocket server;
    private int PORT;
    public Server(int port) throws IOException{
        PORT=port;
    }
    
    public void listen() throws IOException{
        server=new ServerSocket(PORT,10);
        System.out.println("Server is listening on "+"localhost"+":"+PORT);
        ExecutorService requestPool=Executors.newFixedThreadPool(20);
        while(true){
            Socket client=server.accept();          
            requestPool.execute(new RequestHandler(client.getInputStream(),client.getOutputStream()));
   
        }
    }
}
