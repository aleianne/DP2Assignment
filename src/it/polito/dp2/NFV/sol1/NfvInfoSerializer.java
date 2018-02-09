package it.polito.dp2.NFV.sol1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import it.polito.dp2.NFV.NfvReaderException;
import it.polito.dp2.NFV.sol1.serializer.NfvLoader;

public class NfvInfoSerializer {

	public static void main(String[] args) {	
		
		String filename = args[0];
		Schema schema;
		
		try(FileOutputStream xmlFileStream = new FileOutputStream(filename)){
			
			// define the entry point by setting the package of the binded XML classes
			JAXBContext context = JAXBContext.newInstance("it.polito.dp2.NFV.sol1.jaxb");			
			Marshaller m = context.createMarshaller();
			
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			
			/*try(FileInputStream xmlSchema1 = new FileInputStream("xsd/nfvInfo.xsd"); 
					FileInputStream xmlSchema2 = new FileInputStream("xsd/nfvDataTypes.xsd")) {*/
			try(FileInputStream xmlSchema = new FileInputStream("xsd/nfvInfo.xsd")) {
				
				// create a new schema
				//schema = schemaFactory.newSchema(new Source[] {new StreamSource(xmlSchema2), new StreamSource(xmlSchema1)});
				schema = schemaFactory.newSchema(new StreamSource(xmlSchema));
				m.setSchema(schema);			
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);		
				
			} catch(SAXException | FileNotFoundException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			
			NfvLoader nfvLoader = new NfvLoader();												// generate the NFV object
			m.marshal(nfvLoader.getNFV(), xmlFileStream);										// marshall the data into the xml file
			
		} catch(NfvReaderException nre) {															
			System.err.println("impossible to read data from the interface: ");
			System.err.print(nre.getMessage());
			nre.printStackTrace();
			System.exit(1);
		} catch(JAXBException je) {
			System.err.print("JAXB returned an exception: ");
			System.err.println(je.getMessage());
			je.printStackTrace();
			System.exit(1);
		} catch(IllegalArgumentException | FileNotFoundException fe) {
			System.err.print("the file specified is null or doesn't exist: ");
			System.err.println(fe.getMessage());
			fe.printStackTrace();
			System.exit(1);
		} catch(IOException ioe) {
			// quit exception
		}
	}

}
