package it.polito.dp2.NFV.sol1.deserializer;

import it.polito.dp2.NFV.*;
import it.polito.dp2.NFV.sol1.jaxb.NFV;

public class NfvReaderFactory extends it.polito.dp2.NFV.NfvReaderFactory {

	private XmlReferenceMap refTable;	
	
	public NfvReaderFactory() {
		
	}
	
	@Override
	public NfvReader newNfvReader() throws NfvReaderException {
		NfvInfoDeserializer um = new NfvInfoDeserializer();			// unmarshall the XML file
		NFV nfv;	
		
		if((nfv = um.getNFVobject()) == null) 
			throw new NfvReaderException();
		
		refTable = new XmlReferenceMap(nfv);				// create a reference table starting from the instance of the XML tree
		NfvReaderImpl nr = new NfvReaderImpl(nfv, refTable);	
		return nr;
	}

}
