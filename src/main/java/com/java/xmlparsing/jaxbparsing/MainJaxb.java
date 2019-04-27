package com.java.xmlparsing.jaxbparsing;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;

public class MainJaxb {
    public static void main(String[] args) {
        try{
           //The Java DOM and SAX parsing APIs are lower-level APIs to parse XML documents, while JAXB (Java API for XML Binding)
            // is a higher-level API for converting XML elements and attributes to a Java object hierarchy (and vice versa).
            // Implementations of JAXB will most likely use a DOM or SAX parser behind the scenes to do the actual parsing of the
            // XML input data.


            //===========================Marshlling===================
            //JAXBContext, Marshaller
            JAXBContext context = JAXBContext.newInstance(Employee.class); //Need to provide base class
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Employee emp1=new Employee(1,"Vimal Jaiswal",50000); // Providing actual object
            marshaller.marshal(emp1, new FileOutputStream("employee.xml"));

            //==========================Unmarshalling=================
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Employee emp = (Employee)unmarshaller.unmarshal(new File("employee.xml"));
            System.out.println(emp);

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
