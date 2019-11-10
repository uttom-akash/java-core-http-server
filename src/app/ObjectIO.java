/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author akash
 */
public class ObjectIO {
    
  
    public static ArrayList<Book> objects;
    
    static{
       
        objects=new ArrayList<Book>();
    }
    public static  void write(Book object){
        
        
        objects.add(object);
//        try {
//            
//            FileOutputStream fos=new FileOutputStream("savedUser3.ser");
//            ObjectOutputStream oos=new ObjectOutputStream(fos);
//            oos.writeObject(objects);
//            oos.flush();
//            oos.close();
//        } catch (IOException ex) {
//            Logger.getLogger(ObjectIO.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    
    public static ArrayList<Book> read(){
//        try {
//            FileInputStream fis=new FileInputStream("savedUser3.ser");
//            ObjectInputStream ois=new ObjectInputStream(fis);
//            objects=(Book[])ois.readObject();
//        } catch (IOException | ClassNotFoundException ex) {
//            Logger.getLogger(ObjectIO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        return objects;
    }
    
    public static void update(Book b){
        for(int i=0;i<objects.size();i++){
            Book book=objects.get(i);
            
            if(book.id.equals(b.id))
            {
                if(b.name!=null)
                    book.name=b.name;
                if(b.author!=null)
                    book.author=b.author;
                
                    
                objects.remove(i);
                objects.add(book);
                break;
            }    
        }
    }
    
    public static void delete(Book b){
        for(int i=0;i<objects.size();i++){
            Book book=objects.get(i);
            
            if(book.id.equals(b.id))
            {
                objects.remove(i);
                break;
            }    
        }
    }
}