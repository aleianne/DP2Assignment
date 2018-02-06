package it.polito.dp2.NFV.sol1;

import java.math.BigInteger;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;

import it.polito.dp2.NFV.*;
import it.polito.dp2.NFV.sol1.jaxb.*;

public class NffgGenerator {
	
	private NffgReader nfgr;
	private NffgType graph;
	private ObjectFactory objFactory;
	
	protected NffgGenerator(NffgReader nfgr) {
		this.nfgr = nfgr;
		objFactory = new ObjectFactory();
	}
	
	// generate an XML tree that contain the information about the nffg graph read from the nfv interface
	protected NffgType generateGraph() {
		
		graph = objFactory.createNffgType();				// create a new nffg instance			
		Set<NodeReader> nodeSet = nfgr.getNodes();		
		
		// if the XML file doesn't contain any deployed graph return null in order to not create graph elements
		if (nodeSet.isEmpty()) {
			System.out.println("XMlL file doesn't containt any deployed graph...");
			return null;
		}	
		
		System.out.println("begin to create " + nfgr.getName() + " graph element");
		
		// set Nffg element attribute
		try {
			graph.setName(nfgr.getName());																	
			graph.setDeployTime(CalendarXMLconverter.toXMLGregorianCalendar(nfgr.getDeployTime()));			
		} catch (DatatypeConfigurationException de) {
			System.out.println("the date cannot be converted into XML gregorian calendar");
			graph.setDeployTime(null);
		}
		
		// set the inner node element into the nffg graph
		for(NodeReader nr: nodeSet) {					
			System.out.println("begin to read " + nr.getName() + " node information");
			graph.getNode().add(generateNode(nr));				
		}
		
		return graph;
	}
	
	// generate a node element instance, it contains link elements that connect this node with other nodes
	private NodeType generateNode(NodeReader nr) {
		
		NodeType newNode = objFactory.createNodeType();			// instantiate a new node
		Set<LinkReader> linkSet = nr.getLinks();				// get the set of link reader from the node reader interface

		newNode.setName(nr.getName());							// set Node attributes
		newNode.setHostname(nr.getHost().getName());
		newNode.setVNF(nr.getFuncType().getName());
		newNode.setNfFg(nr.getNffg().getName());
		
		if (linkSet.isEmpty()) {
			System.out.println("this node doesn't contain any link");
			return newNode;
		}
		
		for(LinkReader lr: linkSet) {							// for each link reader element create a new linkType instance		
			newNode.getLink().add(generateLink(lr));			// add the linkType instance into node's list of links
		}
		
		return newNode;
	}
	
	// generate link element starting from the link reader interface
	private LinkType generateLink(LinkReader lr) {
		LinkType newLink = objFactory.createLinkType();
	
		if (lr.getLatency() != 0) {											// if the interface return 0 the latency attribute will not be specified
			newLink.setLatency(BigInteger.valueOf(lr.getLatency()));
		} else {
			newLink.setLatency(null);
		}
		
		if (lr.getThroughput() != 0) {										// if the interface return 0 the throughput attribute will not be specified
			newLink.setThroughput(lr.getThroughput());
		} else {
			newLink.setThroughput(null);
		}
		
		// set the other link information
		newLink.setLinkName(lr.getName());
		newLink.setDestinationNode(lr.getDestinationNode().getName());
		newLink.setSourceNode(lr.getSourceNode().getName());
		
		return newLink;
	}
	
	 
	
}
