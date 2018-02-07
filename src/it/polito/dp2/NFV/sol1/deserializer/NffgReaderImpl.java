package it.polito.dp2.NFV.sol1.deserializer;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.sol1.jaxb.NffgType;
import it.polito.dp2.NFV.sol1.jaxb.NodeType;
import it.polito.dp2.NFV.sol1.serializer.CalendarXMLconverter;

class NffgReaderImpl implements NffgReader {
	
	private XmlReferenceMap refTable;
	private NffgType nffgElement;
	private List<NodeType> nodeList;
	
	private Set<NodeReader> nodeReaderSet;

	protected NffgReaderImpl(NffgType nffg, XmlReferenceMap refTable) {
		this.nffgElement = nffg;
		this.refTable = refTable;
		
		Set<NodeReader> nodeReaderSet = new HashSet<NodeReader> ();
	}
	
	@Override
	public String getName() {
		return nffgElement.getName();
	}

	@Override
	public Calendar getDeployTime() {					// if is not possible to convert the data return null
		try {
			return CalendarXMLconverter.fromXMLGregorianCalendar(nffgElement.getDeployTime());			// convert the date from XMLGregorian Calendar to Calendar
		} catch (DatatypeConfigurationException dce) {
			dce.printStackTrace();
			return null;
		}
	}

	@Override
	public NodeReader getNode(String arg0) {
		
		if(arg0 == null) {
			System.out.println("the argument passed is null");
			return null;
		}
		
		NodeType nodeElement = refTable.getNode(arg0);
		if (nodeElement == null) {
			System.out.println("there is no node called " + arg0);
			return null;								
		}
		
		// create and return the node reader interface 
		NodeReaderImpl nr = new NodeReaderImpl(nodeElement, refTable);		
		return nr;
	}

	@Override
	public Set<NodeReader> getNodes() {
		
		if(!nodeReaderSet.isEmpty()) 										// return the set, if is already filled, in order to optimize successive invokation of the method
			return nodeReaderSet;
		
		nodeList = nffgElement.getNode();									// get the list of nodes inside this graph
		for (NodeType nodeElement: nodeList) {								
			NodeReaderImpl nr = new NodeReaderImpl(nodeElement, refTable);	// create a new node reader interface
			nodeReaderSet.add(nr);											// insert the interface inside the set
		}
		return nodeReaderSet;
	}
	
	 

}
