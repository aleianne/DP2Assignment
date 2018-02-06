package it.polito.dp2.NFV.sol1;

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

class NffgReaderImpl implements NffgReader {
	
	private XMLreferenceMapper refTable;
	private NffgType nffgElement;
	private Set<NodeReader> nodeSet;
	private List<NodeType> nodeList;

	protected NffgReaderImpl(NffgType nffg, XMLreferenceMapper refTable) {
		this.nffgElement = nffg;
		this.refTable = refTable;
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
		
		if (arg0 == null) return null;										// check if the arg0 is null
		
		NodeType nodeElement = refTable.getNodeInsideNffg(arg0, nffgElement.getName());		// take as parameter the nffg graph where the node should be searched and the node name		
	
		if (nodeElement == null) return null;								
		
		NodeReaderImpl nr = new NodeReaderImpl(nodeElement, refTable);		// create the node reader interface 
		return nr;
	}

	@Override
	public Set<NodeReader> getNodes() {
		nodeList = nffgElement.getNode();									// get the list of nodes inside this graph
		nodeSet = new HashSet<NodeReader> ();								// create a new set of node reader interface
		
		for (NodeType nodeElement: nodeList) {								
			NodeReaderImpl nr = new NodeReaderImpl(nodeElement, refTable);	// create a new node reader interface
			nodeSet.add(nr);												// insert the interface inside the set
		}
		return nodeSet;
	}
	
	 

}
