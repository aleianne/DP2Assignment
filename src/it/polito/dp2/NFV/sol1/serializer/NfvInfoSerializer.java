package it.polito.dp2.NFV.sol1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import it.polito.dp2.NFV.NfvReaderException;

public class NfvInfoSerializer {

	public static void main(String[] args) {	
		
		String filename = args[0];
		Schema schema;
		
		try(FileOutputStream xmlFileStream = new FileOutputStream(filename)){
			
			// define the entry point by setting the package of the binded XML classes
			JAXBContext context = JAXBContext.newInstance("it.polito.dp2.NFV.sol1.jaxb");			
			Marshaller m = context.createMarshaller();
			
			try {
				
				SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				schema = schemaFactory.newSchema(new File("xsd" + File.separator + "nfvInfo.xsd"));
				m.setSchema(schema);			
		
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);					
				m.setEventHandler(new ValidationEventHandler() {										// this class handle all the validation event occurred during the unmarshalling

					@Override
					public boolean handleEvent(ValidationEvent event) {									// use this function to print all the validation error 
						if (event.getSeverity() != ValidationEvent.WARNING) {
	                        ValidationEventLocator vel = event.getLocator();
	                        
	                        System.out.println("Line:Col[" + vel.getLineNumber() +
	                            ":" + vel.getColumnNumber() +
	                            "]:" + event.getMessage());
	                    }	
						return true;
					}
					
				});
				
			} catch (SAXException se) {
				System.err.print("SAX exception: ");
				System.err.println(se.getMessage());
				se.printStackTrace();
			}
			
			NfvObjectGenerator dataGenerated = new NfvObjectGenerator();							// generate the NFV object
			m.marshal(dataGenerated.getNFV(), xmlFileStream);										// marshall the data into the xml file
			
		} catch (NfvReaderException nre) {															
			System.err.println("impossible to read the data from the interface: ");
			System.err.print(nre.getMessage());
			nre.printStackTrace();
			System.exit(1);
		} catch (JAXBException e) {
			System.err.print("error: ");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		} catch (IllegalArgumentException | FileNotFoundException fe) {
			System.err.print("the file specified is null or doesn't exist: ");
			System.err.println(fe.getMessage());
			fe.printStackTrace();
			System.exit(1);
		} catch (IOException ioe) {
			// quit exception
		}
	}

}
