/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msr5zbsaxtodomtree;

import javafx.scene.control.TreeItem;

/**
 *
 * @author Mike/msr5zb/12358133
 */
public class XMLNodeTree {
    
    private XMLNode root;
    
    public void setRoot(XMLNode root){this.root = root;}
    public XMLNode getRoot(){return this.root;}
    public TreeItem<String> createTree(XMLNode workingNode, TreeItem<String> rootItem){
                
                //Set Root is null provided.
                if(rootItem == null){
                    rootItem = new TreeItem<> (workingNode.getName());
                }
                
                //Add Contents if any Exists
                TreeItem<String> item = new TreeItem<> (workingNode.getContent()); 
                if(!workingNode.getContent().equals("")){
                    rootItem.getChildren().add(item);
                }
                rootItem.setExpanded(true);
                
                //Look at Children
                for(XMLProperty property: workingNode.getProperties()){
                    
                    //Add Child to Root
                    item = new TreeItem<> (property.getPropertyName());
                    item.setExpanded(true);
                    rootItem.getChildren().add(item);
                     
                    //Manage Child's Contents and Repeat
                    createTree(property.getPropertyNode(), item);
                }
 
                return rootItem;
    }
    
}
