package it.polito.dp2.NFV.sol1;

import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.NfvReaderException;
import it.polito.dp2.NFV.sol1.deserializer.*;
import it.polito.dp2.NFV.sol1.jaxb.NFV;

public class NfvReaderFactory extends it.polito.dp2.NFV.NfvReaderFactory {

	private XmlReferenceMap refTable;	
	
	public NfvReaderFactory() {
		
	}
	
	@Override
	public NfvReader newNfvReader() throws NfvReaderException {
		NfvBuilder nfvBuilder = new NfvBuilder();
		return nfvBuilder.getNFV();
	}

}
