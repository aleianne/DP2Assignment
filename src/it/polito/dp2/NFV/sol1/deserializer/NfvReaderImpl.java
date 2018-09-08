package it.polito.dp2.NFV.sol1.deserializer;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFV.ConnectionPerformanceReader;
import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.sol1.CalendarXMLconverter;
import it.polito.dp2.NFV.sol1.jaxb.*;

public class NfvReaderImpl implements NfvReader {
	
	private NFV newNfv;
	
	private Set<NffgReader> graphSet;	
	private Set<HostReader> hostSet; 
	private Set<VNFTypeReader> functionSet; 
	
	// table of reference 
	private XmlReferenceMap refTable;
	
	protected NfvReaderImpl(NFV newNFV, XmlReferenceMap refTable) {
		this.newNfv = newNFV;
		this.refTable = refTable;
		graphSet = new HashSet<NffgReader> ();
		hostSet = new HashSet<HostReader> ();
		functionSet = new HashSet<VNFTypeReader> ();
	}

	@Override
	public ConnectionPerformanceReader getConnectionPerformance(HostReader host1, HostReader host2) {
		if(host1 == null || host2 == null)
			return null;
		
		ConnectionType connectionElement = refTable.getConnection(host1.getName(), host2.getName());		// get the  connection element
		if(connectionElement == null)
			return null;

		return new ConnReaderImpl(connectionElement);
	}

	@Override
	public HostReader getHost(String hostName) {
		if (hostName == null) 
			return null;
		
		HostType hostElement = refTable.getHost(hostName);	
		
		// check if the element exist or not
		if (hostElement == null) 
			return null;
		
		// create a new host reader interface
		return new HostReaderImpl(hostElement, refTable);
	}

	@Override
	public Set<HostReader> getHosts() {
		if(!hostSet.isEmpty())
			return hostSet;
		
		List<HostType> hostList = newNfv.getInfNet().getHosts().getHost();		// get the list of host contained into the Infrastructure network
		for(HostType hostElement: hostList) {
			// declaration of the interface for host info reading
			hostSet.add(new HostReaderImpl(hostElement, refTable));
		}

		return hostSet;																				// return the interface set
	}

	@Override
	public NffgReader getNffg(String nffgName) {		
		if (nffgName == null) 
			return null;
		
		NffgType nffgElement = refTable.getGraph(nffgName);
		if (nffgElement == null) 
			return null;
		
		return new NffgReaderImpl(nffgElement, refTable);
	}

	@Override
	public Set<NffgReader> getNffgs(Calendar date) {											
		List<NffgType> graphList = newNfv.getNffgList().getNffg();																// get the list of graph contained into the NFV element implementation
		
		if(date == null) {
			// if the argument is null return all nffg contained inside the nfv

			for(NffgType nffgElement: graphList)
				graphSet.add(new NffgReaderImpl(nffgElement, refTable));

		} else {
			// if a date is specified, get all the nffgs that have been deployed before the date

			try {
				XMLGregorianCalendar XMLcalendar = CalendarXMLconverter.toXMLGregorianCalendar(date);
				for(NffgType nffgElement: graphList) {
					if(nffgElement.getDeployTime().compare(XMLcalendar) ==  DatatypeConstants.GREATER) 		// check if the date is greater or lesser than the graph deploy date
						graphSet.add(new NffgReaderImpl(nffgElement, refTable));
				}
			} catch(DatatypeConfigurationException de) {
				System.err.print("the date cannot be converted into a XML calendar instance: ");
				System.err.println(de.getMessage());
				//de.printStackTrace();
				return null;
			}
		}

		return graphSet;
	}

	@Override
	public Set<VNFTypeReader> getVNFCatalog() {
		if(!functionSet.isEmpty())
			return functionSet;
		
		List<FunctionType> VNFElementList = newNfv.getCatalog().getFunction();												// get the list of VNF 
		for(FunctionType VNFelement: VNFElementList) {
			// add the function interface into the set
			functionSet.add(new VNFTypeReaderImpl(VNFelement));
		}

		return functionSet;																				
	}

}
