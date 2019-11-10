/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author akash
 */
public class PostMethod {
    public void updateBook(InputStream inputStream,OutputStream outputStream,String body) throws IOException{
            PrintWriter out=new PrintWriter(outputStream);
            Book book=Mapper.jsonToJava(body,Book.class);
            
            ObjectIO.update(book);
           
            System.out.println(body);
            out.println("HTTP/1.1 201 updated");
            out.println("Server: Socket Server");
            out.println("Date: " + new Date());
            out.println("Content-type: " +"application/json");
            out.println("Content-length: " +body.length());
            out.println();
            out.println(book.name);
            out.flush(); 
            out.close(); 
        
    }
    
    public void deleteBook(InputStream inputStream,OutputStream outputStream,String body) throws IOException{
            PrintWriter out=new PrintWriter(outputStream);
            Book book=Mapper.jsonToJava(body,Book.class);
            
            ObjectIO.delete(book);
           
            System.out.println(body);
            out.println("HTTP/1.1 201 deleted");
            out.println("Server: Socket Server");
            out.println("Date: " + new Date());
            out.println("Content-type: " +"application/json");
            out.println("Content-length: " +body.length());
            out.println();
            out.println(book.name);
            out.flush(); 
            out.close(); 
        
    }
}
