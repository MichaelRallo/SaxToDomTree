/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msr5zbsaxtodomtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.xml.sax.Attributes;

/**
 *
 * @author Mike/msr5zb/12358133
 */
public class XMLNode {
    
    private String name = "";
    private String content = "";
    
    private ArrayList<XMLProperty> properties;
    private LinkedHashMap <String, String> attributes;
    
    public String getName(){return name;}
    public String getContent(){return content;}
    public void setName(String name){this.name = name;}
    public void addContent(String content){this.content += content.trim();}
    public HashMap<String, String> getAttributes(){return this.attributes;}
    public ArrayList<XMLProperty> getProperties(){return this.properties;}
    
    public XMLNode(){
        properties = new ArrayList<>();
        attributes = new LinkedHashMap <>();
    }
    
    public void addProperty(String propertyName, XMLNode propertyNode){
        XMLProperty property = new XMLProperty(propertyName, propertyNode);
        this.properties.add(property);
    }
    
    
    public void addAttributes(Attributes attributes){
         //Set All Attributes
        for(int i = 0; i < attributes.getLength(); i++){
            this.attributes.put(attributes.getLocalName(i), attributes.getValue(i));
        }
    }
}
