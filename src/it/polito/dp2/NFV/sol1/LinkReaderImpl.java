package it.polito.dp2.NFV.sol1;

import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.sol1.jaxb.*;

class LinkReaderImpl implements LinkReader {
	
	private LinkType linkElement;
	private XMLreferenceMapper refTable;
	private NodeType parentNode;
	
	protected LinkReaderImpl(LinkType link, XMLreferenceMapper refTable, NodeType node) {
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
		
		if (nodeElement == null) {
			System.out.println(linkElement.getDestinationNode() + " doesn't exist");
		}
		
		NodeReaderImpl nr = new NodeReaderImpl(nodeElement, refTable);						// encapsulate the node into the interface
		return nr;
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
		NodeReaderImpl nr = new NodeReaderImpl(parentNode, refTable);					// encapsulate the node into the interface
		return nr;
	}

	@Override
	public float getThroughput() {
		
		if (linkElement.getThroughput() == null) 
			return (float) 0;															// if the value is not specified return 0
		
		return linkElement.getThroughput();												// return the throughput
	}

}
