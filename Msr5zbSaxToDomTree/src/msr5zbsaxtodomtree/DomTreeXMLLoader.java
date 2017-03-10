/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msr5zbsaxtodomtree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author Mike/msr5zb/12358133
 * http://www.saxproject.org/apidoc/org/xml/sax/helpers/DefaultHandler.html
 * https://docs.oracle.com/javase/8/docs/api/org/xml/sax/SAXException.html#SAXException--
 */
public class DomTreeXMLLoader {
    
    public static XMLNodeTree load(File xmlCourseFile) throws Exception {
        XMLNodeTree domTree = new XMLNodeTree();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler() {
                
                //Handler Vars
                XMLNode root = null;
                List<XMLNode> stack = new ArrayList<>();
                XMLNode currentNode = null;
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    
                    //Create New Node with Start of Element
                    XMLNode workingNode = new XMLNode();
                    workingNode.setName(qName);
                    workingNode.addAttributes(attributes);
                    //Add it to the Stack
                    stack.add(workingNode);
                    
                    //Check if has parent
                    if(currentNode != null){
                        currentNode.addProperty(qName, workingNode);
                    }
                    
                    //Set Current
                    currentNode = workingNode;
                    
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    //Check if stack is not empty, pop ending element off list.
                    if(stack != null){
                        XMLNode poppedNode = stack.remove(stack.size()-1);
                        if(poppedNode != null){
                            if(stack.isEmpty()){
                                root = poppedNode;
                                currentNode = null;
                            } else {
                                currentNode = stack.get(stack.size()-1);
                            }
                        }
                    }
                    
                    //ReSet Root
                    domTree.setRoot(root);
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    //Add Content
                    String content = new String(ch, start, length);
                    currentNode.addContent(content);                
                }
            };
            
            saxParser.parse(xmlCourseFile.getAbsoluteFile(), handler);
            
        } catch (Exception e) {
            throw e;
        }
        
      return domTree; 
    }
}
