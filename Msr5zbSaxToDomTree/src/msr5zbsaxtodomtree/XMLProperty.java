/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msr5zbsaxtodomtree;

/**
 *
 * @author Mike/msr5zb/12358133
 */
public class XMLProperty {
    
    private String propertyName;
    private XMLNode propertyNode;
    
    public void setPropertyName(String name){this.propertyName = name;}
    public void setPropertyNode(XMLNode propertyNode){this.propertyNode = propertyNode;}
    public String getPropertyName(){return this.propertyName;}
    public XMLNode getPropertyNode(){return this.propertyNode;}
    
    public XMLProperty(String name, XMLNode node){
        this.propertyName = name;
        this.propertyNode = node;
    }
}
