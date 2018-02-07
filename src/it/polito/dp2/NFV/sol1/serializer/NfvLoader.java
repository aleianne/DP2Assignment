package it.polito.dp2.NFV.sol1;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.NfvReaderException;
import it.polito.dp2.NFV.NfvReaderFactory;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.sol1.jaxb.*;

// this module create the instance of the XML binded class
// starting from the information read from the nfv interface passed in the costructor
public class NfvObjectGenerator {
	
	private NfvReader monitor;
	private NFV newNFV;

	public NfvObjectGenerator() throws NfvReaderException {
		NfvReaderFactory factory = NfvReaderFactory.newInstance();					// implements a new reader factory
		monitor = factory.newNfvReader();											// implements a new NFV reader factory
		newNFV = new NFV(); 														// implements a new NFV object used for the XML marsahlling
	}
	
	public NfvObjectGenerator(NfvReader monitor) {
		super();
		this.monitor = monitor;
	}
	
	// create the instances of the nffg element contained into the NFV reader interface
	private void mapGraphs() {
		
		List<NffgType> graphs = newNFV.getNfFg();								// get the reference of the Nffg list containded inside NFV
				
		System.out.println("number of nffg: " + monitor.getNffgs(null).size());
		
		for (NffgReader nfgr: monitor.getNffgs(null)) {							// for each element in the reader set create a new Nffg XML element
			NffgGenerator gen = new NffgGenerator(nfgr);
			graphs.add(gen.generateGraph());									// add the nffg XML element into the list of nffg element
		}
	}
	
	private void mapInfrastructure() {
		InfrastructureGenerator inf = new InfrastructureGenerator(monitor);
		newNFV.setInfNet(inf.generateNetwork());
	}
	
	private void mapCatalog() {
		
		newNFV.setCatalog(new NFV.Catalog());													// instantiate a new catalog inside the NFV element
		List<FunctionType> functions = newNFV.getCatalog().getFunction();						// get the function list reference							
		
		for (VNFTypeReader vtr: monitor.getVNFCatalog()) {
			FunctionType newVNF = new FunctionType();											// create a new VNF element
			
			// set the VNF element taking as source the interface data 
			newVNF.setName(vtr.getName());
			newVNF.setRequiredMemory(BigInteger.valueOf(vtr.getRequiredMemory()));
			newVNF.setRequiredStorage(BigInteger.valueOf(vtr.getRequiredStorage()));
			newVNF.setType(FunctionEnumeration.fromValue(vtr.getFunctionalType().value()));
			functions.add(newVNF);
		}
	}
	
	public NFV getNFV() {
		mapGraphs();
		mapInfrastructure();
		mapCatalog();
		return newNFV;
	}
	
	
}
