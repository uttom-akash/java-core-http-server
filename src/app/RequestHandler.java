/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akash
 */
public class RequestHandler implements Runnable{
    private InputStream inputStream;
    private OutputStream outputStream;
    private BufferedReader reader;
    
    public RequestHandler(InputStream inputStream,OutputStream outputStream){
        this.inputStream=inputStream;
        this.outputStream=outputStream;
    }
    
    @Override
    public void run(){
        try {
            
            
            reader=new BufferedReader(new InputStreamReader(this.inputStream));
            String line=null;
            line=reader.readLine();
            
            if(line!=null){
                StringTokenizer tokens=new StringTokenizer(line," ");
                String method=tokens.nextToken();
                String api="http://localhost:8081"+tokens.nextToken();
                System.out.println(method);
                
                switch(method){
                    case "GET": this.getSelector(api);break;
                    case "POST": this.postSelector(api);break;
                }
            
 
            }
            else{
                
                PrintWriter out=new PrintWriter(outputStream);
                out.println("HTTP/1.1 501 Not Implemented");
                out.println("Server: Socket Server");
                out.println("Date: " + new Date());
                out.println("Content-type: " +"text/html");
                out.println("Content-length: " +0);
                out.println();
                out.flush();  
                out.close();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public HashMap<String,String> processQuery(String q){
        String[] queries=q.split("&");
        HashMap<String,String> Query=new HashMap<String,String>();
        
        for(String query:queries){
            String[] keyval=query.split("=");
            Query.put(keyval[0],keyval[1]);
        }
        return Query;
    }
    
    
            
    public void getSelector(String api) throws MalformedURLException, IOException, FileNotFoundException, ClassNotFoundException{
        URL url=new URL(api);
        String path=url.getPath();
        String queryString=url.getQuery();
        
        HashMap<String,String> Query=null;
        StringBuffer json=new StringBuffer();
        if(queryString!=null){
            Query=processQuery(queryString);
        json.append("{");
        for(String key:Query.keySet()){
            json.append("\""+key+"\":"+"\""+Query.get(key)+"\",");
        }
        
        int len=json.length();
        json.replace(len-1,len,"}");
        }
        System.out.println(path);
        System.out.println(json);
        switch(path){
            case "/retriveallbooks":new GetMethod().getAllBook(inputStream, outputStream);break;
            case "/retrivebook":new GetMethod().getBook(inputStream, outputStream,json.toString());break;    
            case "/insertbook":new GetMethod().insertBook(inputStream, outputStream,json.toString());break;    
            default:new GetMethod().getIndex(inputStream, outputStream);
                
        }   
    }
    
    public String getPostBody() throws IOException{
        StringBuffer sb=new StringBuffer();
        String line =null;
        Boolean flag=false;
        Boolean hasNewBodyLine = true;
        
//        while((line=reader.readLine())!=null){
//            if(line.contains("{"))
//                flag=true;
//            else if("\"end\":\"end\"".equals(line))
//                break;
//
//            if(flag)
//                sb.append(line);
//
//        }

        do{
            line = bufferedReader.readLine();



            if(line.contains("{")){
                flag = true;
            }

            if(flag && !line.contains(",") && !line.contains("{")){
                System.out.println("line: "+line);
                hasNewBodyLine = false;
            }

            if(flag){
                sb.append(line);
            }



        }while (hasNewBodyLine);

//        int len=sb.length();
//        sb.replace(len-1, len,"}");
        
        sb.append("}");

        return sb.toString();   
    }
    
    public void postSelector(String api) throws MalformedURLException, IOException{
        URL url=new URL(api);
        String path=url.getPath();
        String queryString=url.getQuery();
        
        HashMap<String,String> Query=null;
        if(queryString!=null)
            Query=processQuery(queryString);
        
        //        body
        String body=this.getPostBody();
        
        switch(path){
            case "/updateBook":new PostMethod().updateBook(inputStream, outputStream,body);break;
            case "/deleteBook":new PostMethod().deleteBook(inputStream, outputStream,body);break;
            default:new GetMethod().getIndex(inputStream, outputStream);
                
        }   
    }
    
    
}
