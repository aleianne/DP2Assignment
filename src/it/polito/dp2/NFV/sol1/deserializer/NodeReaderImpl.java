package it.polito.dp2.NFV.sol1.deserializer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NamedEntityReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.sol1.jaxb.*;

public class NodeReaderImpl implements NodeReader {
	
	private XmlReferenceMap refTable;
	private NodeType nodeElement;
	
	private Set<LinkReader> linkSet;
	
	protected NodeReaderImpl(NodeType node, XmlReferenceMap refTable) {
		this.refTable = refTable;
		this.nodeElement = node;
		linkSet = new HashSet<LinkReader> ();
	}

	@Override
	public String getName() {
		return nodeElement.getName();
	}

	@Override
	public VNFTypeReader getFuncType() {
		FunctionType VNFelement = refTable.getVNF(nodeElement.getVNF());			// search the VNF inside the hashMap 
		return new VNFTypeReaderImpl(VNFelement);
	}

	@Override
	public HostReader getHost() {
		HostType hostElement = refTable.getHost(nodeElement.getHostname());			// search the hostname inside the hashMap 
		return new HostReaderImpl(hostElement, refTable);
	}

	@Override
	public Set<LinkReader> getLinks() {

		if(!linkSet.isEmpty())
			return linkSet;
		
		List<LinkType> linkList = nodeElement.getLink();											// get the list of link inside the nodeElement passed as parameter		
		for (LinkType linkElement: linkList) {
			// for each element in the list create a new link reader implementation
			linkSet.add(new LinkReaderImpl(linkElement, refTable, nodeElement));
		}
		return linkSet;
	}

	@Override
	public NffgReader getNffg() {
		NffgType nffgElement = refTable.getGraph(nodeElement.getNfFg());			// get the nffg node using the reference table
		return new NffgReaderImpl(nffgElement, refTable);			// set the nffg reader interface
	}

}
