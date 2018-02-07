package it.polito.dp2.NFV.sol1;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import it.polito.dp2.NFV.*;
import it.polito.dp2.NFV.sol1.jaxb.*;

class XMLMapper {

	private static final String property = "it.polito.dp2.NFV.sol1.NfvInfo.file";
	private NFV unmarshalledNFV = null;

	protected XMLMapper() throws NfvReaderException {
		
		String fileName = System.getProperty(property);				// read the filename from a system property
		
		if (fileName == null) {
			throw new NfvReaderException("the file name is null");
		}
		
		try(FileInputStream xmlFile = new FileInputStream(fileName)) {
			
			JAXBContext context = JAXBContext.newInstance("it.polito.dp2.NFV.sol1.jaxb");
			
			// instantiate the unmarshaller to deserialize the data from a XML file
			Unmarshaller um = context.createUnmarshaller();	
			
			try {
					
				// create a new schema factory for the XML validation with respect to the XML schema file
				SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);					
				Schema XMLschema = sf.newSchema(new File("xsd" + File.separator + "nfvInfo.xsd"));					// load the schema 
				
				um.setSchema(XMLschema);														// set the reference schema for validation
				um.setEventHandler(new ValidationEventHandler() {								// this class handle all the validation event occurred during the unmarshalling

					@Override
					public boolean handleEvent(ValidationEvent event) {							// use this function to print all the validation error 
						if (event.getSeverity() != ValidationEvent.WARNING) {
                            ValidationEventLocator vel = event.getLocator();
                            
                            System.out.println("Line:Col[" + vel.getLineNumber() +
                                ":" + vel.getColumnNumber() +
                                "]:" + event.getMessage());
                        }	
						return true;
					}
					
				});
				
			} catch(SAXException se) {
				System.out.println("Validation error:");
				se.printStackTrace();
				throw new NfvReaderException();
			}
			
			unmarshalledNFV = (NFV) um.unmarshal(xmlFile);				// return an unmarshalled object, converted to NFV with a cast 
			
		} catch(UnmarshalException ue) {
			System.err.print("Unmarshall error: ");
			System.err.println(ue.getMessage());
			ue.printStackTrace();
			throw new NfvReaderException();
			
		} catch(IllegalArgumentException | FileNotFoundException fe) {
			System.err.print("file opening exception: ");
			System.err.println(fe.getMessage());
			fe.printStackTrace();
			throw new NfvReaderException();
		}
		
		catch(JAXBException je) {
			System.err.print("JAXB error: ");
			System.err.println(je.getMessage());
			je.printStackTrace();
			throw new NfvReaderException();
		} catch(IOException ioe) {
			// quiet the io exception
		}
	}
	
	//  return null if the XML file isn't unmarshalled
	protected NFV getNFVobject() {								
		return unmarshalledNFV;
	}
}
