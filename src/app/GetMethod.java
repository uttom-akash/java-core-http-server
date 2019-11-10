/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.FileNotFoundException;
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
public class GetMethod {
    
    public void getIndex(InputStream inputStream,OutputStream outputStream){
            PrintWriter out=new PrintWriter(outputStream);
            
            out.println("HTTP/1.1 200 OK");
            out.println("Server: Socket Server");
            out.println("Date: " + new Date());
            out.println("Content-type: " +"text/html");
            out.println("Content-length: " +30);
            out.println();
            out.println("welcome Universe..!!");
            out.flush(); 
            out.close();     
    }
    
    public void getAllBook(InputStream inputStream,OutputStream outputStream) throws IOException, FileNotFoundException, ClassNotFoundException{
            PrintWriter out=new PrintWriter(outputStream);
            System.out.print("all");
            ArrayList<Book> books=ObjectIO.read();
            System.out.print(books);
            StringBuffer sb=new StringBuffer();
            sb.append("[");
            for(int i=0;i<books.size();i++){
                String json=Mapper.javaToJson(books.get(i));
                sb.append(json);
            }
            sb.append("]");
            out.println("HTTP/1.1 200 OK");
            out.println("Server: Socket Server");
            out.println("Date: " + new Date());
            out.println("Content-type: " +"application/json");
            out.println("Content-length: " +sb.length());
            out.println();
//            
            
            out.println(sb.toString());
//            
            out.flush(); 
            out.close(); 
        
    }
    
    public void getBook(InputStream inputStream,OutputStream outputStream,String json) throws JsonProcessingException, IOException{
            PrintWriter out=new PrintWriter(outputStream);
            Book book=Mapper.jsonToJava(json,Book.class);
            
            for(int i=0;i<ObjectIO.objects.size();i++){
                   Book  temp=ObjectIO.objects.get(i);
                   if(temp.id.equals(book.id))
                   {
                       book=temp;
                       break;
                   }
                       
            }
            
            StringBuffer sb=new StringBuffer();
            String resj=Mapper.javaToJson(book);
            sb.append(resj);
            
            ObjectIO.write(book);
            out.println("HTTP/1.1 200 OK");
            out.println("Server: Socket Server");
            out.println("Date: " + new Date());
            out.println("Content-type: " +"application/json");
            out.println("Content-length: " +resj.length());
            out.println();
            out.println(resj);
            out.flush(); 
            out.close(); 
        
    }
    public void insertBook(InputStream inputStream,OutputStream outputStream,String query) throws IOException{
            PrintWriter out=new PrintWriter(outputStream);
            
            Book book=Mapper.jsonToJava(query,Book.class);
            ObjectIO.write(book);
            
            System.out.println(book.name);
            out.println("HTTP/1.1 201 created");
            out.println("Server: Socket Server");
            out.println("Date: " + new Date());
            out.println("Content-type: " +"application/json");
            out.println("Content-length: " +0);
            out.println();
            out.flush(); 
            out.close(); 
        
    }
}
