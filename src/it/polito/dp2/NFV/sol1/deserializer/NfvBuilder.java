package it.polito.dp2.NFV.sol1.deserializer;

import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.NfvReaderException;
import it.polito.dp2.NFV.sol1.jaxb.*;

public class NfvBuilder {
	
	public NfvBuilder() {
		
	}
	
	public NfvReader getNFV() throws NfvReaderException {
		NFV nfv;
		NfvInfoDeserializer um = new NfvInfoDeserializer();			// unmarshall the XML file
		
		if((nfv = um.getNFVobject()) == null) 
			throw new NfvReaderException("impossible to unmarshall the data fromt the xml file");
		
		XmlReferenceMap refTable = new XmlReferenceMap(nfv);						// create a reference table starting from the instance of the XML tree
		NfvReaderImpl nr = new NfvReaderImpl(nfv, refTable);	
		return nr;
	}

}
