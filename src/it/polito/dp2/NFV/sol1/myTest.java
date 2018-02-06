package it.polito.dp2.NFV.sol1;

import java.util.Set;

import it.polito.dp2.NFV.FactoryConfigurationError;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.NfvReaderException;

public class myTest {
	
	
	public static void main(String[] args) {
		
        System.setProperty("it.polito.dp2.NFV.NfvReaderFactory", "it.polito.dp2.NFV.sol1.NfvReaderFactory");

		try {
			NfvReader newNFV =  NfvReaderFactory.newInstance().newNfvReader();
			
			
			Set<NffgReader> nfgr = newNFV.getNffgs(null);
			
			for (NffgReader nr: nfgr) {
				System.out.println(nr.getName());
				
				if (nr.getNode("ananas") != null) System.out.println("node found");
				else System.out.println("node not found");
				
			}
			
			assert (nfgr == null) : "the nffg returned is null";
		
			
		} catch (NfvReaderException nre) {
			System.err.println("error during nfv reader implementation, " + nre.getMessage());
			nre.printStackTrace();
		} catch (FactoryConfigurationError fe) {
			fe.printStackTrace();
		}
	}

}
