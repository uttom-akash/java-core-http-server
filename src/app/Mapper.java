/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;
/**
 *
 * @author akash
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class Mapper {
    public static ObjectMapper mapper;
    static{
        mapper=new ObjectMapper();
    }
    
    public static String javaToJson(Object object) throws JsonProcessingException{
        String jsonString="";
        jsonString=mapper.writeValueAsString(object);
        return jsonString;
    }
    
    public static <T> T jsonToJava(String jsonString,Class<T> cls) throws IOException{
        T object=null;
        object=mapper.readValue(jsonString, cls);
        return object;
    }
    
}
