package it.polito.dp2.NFV.sol1.deserializer;

import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.NfvReaderException;
import it.polito.dp2.NFV.sol1.jaxb.*;

public class NfvBuilder {
	
	public NfvBuilder() {}
	
	public NfvReader getNFV() throws NfvReaderException {
		NfvInfoDeserializer um = new NfvInfoDeserializer();			// unmarshall the XML file
		NFV nfv = um.getNFVobject();

		XmlReferenceMap refTable = new XmlReferenceMap(nfv);						// create a reference table starting from the XML tree instance
		return new NfvReaderImpl(nfv, refTable);
	}

}
