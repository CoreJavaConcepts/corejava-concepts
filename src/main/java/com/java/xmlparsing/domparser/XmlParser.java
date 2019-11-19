package com.java.xmlparsing.domparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlParser {
    public static void main(String[] args) {

        try{
            //------DOM Parser
            // Main things DocumentBuilderFactory, DocumentBuilder.parse, Document
            // NodeList, Node, Element
            //doc.getElementsByTagName
            File file = new File("F:\\Tutorials\\Java\\CoreJavaConcepts\\src\\com\\java\\xmlparsing\\domparser\\student.xml");

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();// check import, its javax.xml
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(file); // Check import its org.w3c
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("student");//-------------------method getElementsByTagName

            for(int i=0; i < nList.getLength() ; i++){
                Node node = nList.item(i);//-----------------.item
                System.out.println("\nCurrent Element :" + node.getNodeName());

                if(node.getNodeType() == Node.ELEMENT_NODE){//..............getNodeType
                    Element eElement = (Element) node;
                    System.out.println("Student roll no : "
                            + eElement.getAttribute("rollno"));//------------------method getAttribute
                    System.out.println("First Name : "
                            + eElement
                            .getElementsByTagName("firstname")//-------------------method getElementsByTagName
                            .item(0)//-------------------method item
                            .getTextContent());//------------------method getTextContent
                    System.out.println("Last Name : "
                            + eElement
                            .getElementsByTagName("lastname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Nick Name : "
                            + eElement
                            .getElementsByTagName("nickname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Marks : "
                            + eElement
                            .getElementsByTagName("marks")
                            .item(0)
                            .getTextContent());
                }

            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
