/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.Serializable;

/**
 *
 * @author akash
 */
public class Book implements Serializable{
    String name;
    String author;
    String id;
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name=name;
    }

    public String getAuthor(){
        return this.author;
    }
    
    public void setAuthor(String author){
        this.author=author;
    }
    
    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return this.id;
    }
    
    
}
