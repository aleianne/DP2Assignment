package it.polito.dp2.NFV.sol1;

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

import it.polito.dp2.NFV.sol1.jaxb.*;

public class NfvReaderImpl implements NfvReader {
	
	private NFV newNfv;
	
	// declaration of sets used for interface insertion
	private Set<NffgReader> graphSet;	
	private Set<HostReader> hostSet; 
	private Set<VNFTypeReader> functionSet; 
	
	// lists that contain the unmarshelled XML data
	private List<NffgType> graphList;
	private List<HostType> hostList;
	private List<FunctionType> VNFList;
	
	// table of reference 
	private XMLreferenceMapper refTable;
	
	protected NfvReaderImpl(NFV newNFV, XMLreferenceMapper refTable) {
		this.newNfv = newNFV;
		this.refTable = refTable;
	}

	@Override
	public ConnectionPerformanceReader getConnectionPerformance(HostReader arg0, HostReader arg1) {
		
		// if the parameter passed are null return null
		if (arg0 == null || arg1 == null) {
			return null;
		}
		
		ConnectionType connElement = refTable.getConnection(arg0.getName(), arg1.getName());		// get the  connection element
		ConnectionPerformanceReader cpr = null;														// if no element is found inside the connection return null
		
		if (connElement != null) {
			cpr = new ConnReaderImpl(connElement);
		}
	
		return cpr;																					// return the connection reader interface
	}

	@Override
	public HostReader getHost(String arg0) {
		
		// check if the argument passed is not null
		if (arg0 == null) 
			return null;
		
		HostType hostElement = refTable.getHost(arg0);	
		
		// chek if the element exist or not
		if (hostElement == null) 
			return null;
		
		// create a new host reader interface
		HostReaderImpl hr = new HostReaderImpl(hostElement, refTable);
		
		return hr;																					// if no host object is found, null is returned
	}

	@Override
	public Set<HostReader> getHosts() {
		hostList = newNfv.getInfNet().getHostGroup().getHost();										// get the list of host contained into the Infrastructure network
		hostSet  = new HashSet<HostReader> ();														// set declaration, it contains all the interface for host info reading
		
		
		for (HostType hostElement: hostList) {
			HostReaderImpl hr = new HostReaderImpl(hostElement, refTable);							// declaration of the interface for host info reading
			hostSet.add(hr);
		}
		return hostSet;																				// return the interface set
	}

	@Override
	public NffgReader getNffg(String arg0) {		
		
		if (arg0 == null) 
			return null;
		
		NffgType nffgElement = refTable.getGraph(arg0);
		
		if (nffgElement == null) 
			return null;
		
		NffgReaderImpl nfgr = new NffgReaderImpl(nffgElement, refTable);
		return nfgr;
	}

	@Override
	public Set<NffgReader> getNffgs(Calendar arg0) {											
		graphList = newNfv.getNfFg();																// get the list of graph contained into the NFV element implementation
		graphSet = new HashSet<NffgReader> ();
		
		if (arg0 == null) {																			// if the argument is null return all nffg contained inside the nfv
			
			for (NffgType nffgElement: graphList) {														
				NffgReaderImpl nfgr = new NffgReaderImpl(nffgElement,refTable);
				graphSet.add(nfgr);
			}
			
		} else {
		
			// if a date is specified, get all the nffgs that have been deployed before the date
			for (NffgType nffgElement: graphList) {
				
				XMLGregorianCalendar XMLcalendar;
				
				try {
					
					XMLcalendar = CalendarXMLconverter.toXMLGregorianCalendar(arg0);						// convert the calendar in gregorian calendar
					
					if (nffgElement.getDeployTime().compare(XMLcalendar) ==  DatatypeConstants.GREATER) {	// check if the date is greater or lesser than the graph deploy date
						NffgReaderImpl nfgr = new NffgReaderImpl(nffgElement,refTable);
						graphSet.add(nfgr);
					}
					
				} catch (DatatypeConfigurationException de) {
					System.err.print("the date cannot be converted into a XML calendar instance: ");
					System.err.println(de.getMessage());
					de.printStackTrace();
				}
			}
		}
		
		return graphSet;
	}

	@Override
	public Set<VNFTypeReader> getVNFCatalog() {
		VNFList = newNfv.getCatalog().getFunction();												// get the list of VNF 
		functionSet = new HashSet<VNFTypeReader> ();												// instantiate a new set that contains the interface for the function reader
		
		for (FunctionType VNFelement: VNFList) {
			VNFTypeReaderImpl vtr = new VNFTypeReaderImpl(VNFelement);								// add the function interface into the set
			functionSet.add(vtr);
		}
		
		return functionSet;																				
	}

}
