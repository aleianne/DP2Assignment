package it.polito.dp2.NFV.sol1.deserializer;

import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.sol1.jaxb.*;

public class LinkReaderImpl implements LinkReader {
	
	private LinkType linkElement;
	private XmlReferenceMap refTable;
	private NodeType parentNode;
	
	protected LinkReaderImpl(LinkType link, XmlReferenceMap refTable, NodeType node) {
		this.linkElement = link;
		this.refTable = refTable;
		this.parentNode = node;
	}
	
	@Override
	public String getName() {
		return linkElement.getLinkName();
	}

	@Override
	public NodeReader getDestinationNode() {
		NodeType nodeElement = refTable.getNode(linkElement.getDestinationNode());			// get the node using as reference the outboundNode name		
		return new NodeReaderImpl(nodeElement, refTable);
	}

	@Override
	public int getLatency() {
		if (linkElement.getLatency() == null) 
			return 0;																	// if the value is not specified return 0
		
		return linkElement.getLatency().intValue();										// return the int value from the BigInteger
	}

	@Override
	public NodeReader getSourceNode() {
		// since the source node is the parent nodeElement, return the parent nodeElement
		return new NodeReaderImpl(parentNode, refTable);
	}

	@Override
	public float getThroughput() {
		if (linkElement.getThroughput() == null) 
			return (float) 0;															// if the value is not specified return 0
		
		return linkElement.getThroughput();												// return the throughput
	}

}
