package it.polito.dp2.NFV.sol1.deserializer;

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
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import it.polito.dp2.NFV.*;
import it.polito.dp2.NFV.sol1.jaxb.*;

public class NfvInfoDeserializer{

	private static final String property = "it.polito.dp2.NFV.sol1.NfvInfo.file";
	private NFV unmarshalledNFV = null;

	protected NfvInfoDeserializer() throws NfvReaderException {
		
		String fileName = System.getProperty(property);				// read the filename from a system property
		
		if (fileName == null) 
			throw new NfvReaderException("the file name is null");
		
		try(FileInputStream xmlFile = new FileInputStream(fileName)) {
			
			JAXBContext context = JAXBContext.newInstance("it.polito.dp2.NFV.sol1.jaxb");
			Unmarshaller um = context.createUnmarshaller();	
			
			try(FileInputStream xmlSchema1 = new FileInputStream("xsd/nfvInfo.xsd")) {
				// create a new schema factory for the XML validation with respect to the XML schema file
				SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);					
				Schema schema = sf.newSchema(new StreamSource(xmlSchema1));					// load the schema
				um.setSchema(schema);														// set the reference schema for validation
			} catch (SAXException se)  {
				throw new NfvReaderException("impossible to read the xml schema");
			} catch (FileNotFoundException fne) {
				//e.printStackTrace();
				throw new NfvReaderException("impossible to find the xml schema file");
			}

			// return an unmarshalled object, converted to NFV with a cast
			unmarshalledNFV = (NFV) um.unmarshal(xmlFile);
			
		} catch(UnmarshalException ue) {
			/*System.err.print("Unmarshall error: ");
			System.err.println(ue.getMessage());*/
			ue.printStackTrace();
			throw new NfvReaderException("impossible to unmarshall the XML file");
		} catch(IllegalArgumentException | FileNotFoundException fe) {
			/*System.err.print("file opening exception: ");
			System.err.println(fe.getMessage());
			fe.printStackTrace();*/
			throw new NfvReaderException("impossible to find the file to be deserialized");
		} catch(JAXBException je) {
			/*System.err.print("JAXB error: ");
			System.err.println(je.getMessage());
			je.printStackTrace();*/
			throw new NfvReaderException("JAXB error");
		} catch(IOException ioe) {
			// quiet the io exception
		}
	}
	
	// return null if the XML file isn't unmarshalled
	protected NFV getNFVobject() {
		return unmarshalledNFV;
	}
}
