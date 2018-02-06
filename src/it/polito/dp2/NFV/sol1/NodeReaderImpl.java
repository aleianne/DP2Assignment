package it.polito.dp2.NFV.sol1;

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
	
	private XMLreferenceMapper refTable;
	private NodeType nodeElement;
	private List<LinkType> linkList;
	private Set<LinkReader> linkSet;
	
	protected NodeReaderImpl(NodeType node, XMLreferenceMapper refTable) {
		this.refTable = refTable;
		this.nodeElement = node;
		
		// assert that the reference table is not null
		//assert (refTable == null) : "the reference table is null";
	}

	@Override
	public String getName() {
		return nodeElement.getName();
	}

	@Override
	public VNFTypeReader getFuncType() {
		FunctionType VNFelement = refTable.getVNF(nodeElement.getVNF());			// search the VNF inside the hashMap 
		VNFTypeReaderImpl vr = new VNFTypeReaderImpl(VNFelement);
		return vr;
	}

	@Override
	public HostReader getHost() {
		HostType hostElement = refTable.getHost(nodeElement.getHostname());			// search the hostname inside the hashMap 
		HostReaderImpl hr = new HostReaderImpl(hostElement, refTable);
		return hr;
	}

	@Override
	public Set<LinkReader> getLinks() {
		linkList = nodeElement.getLink();											// get the list of link inside the nodeElement passed as parameter
		linkSet = new HashSet<LinkReader> ();										// create  new set
		
		for (LinkType linkElement: linkList) {										// for each element in the list create a new link reader implementation
			LinkReaderImpl lr = new LinkReaderImpl(linkElement, refTable, nodeElement);
			linkSet.add(lr);
		}
		
		return linkSet;
	}

	@Override
	public NffgReader getNffg() {
		NffgType nffgElement = refTable.getGraph(nodeElement.getNfFg());			// get the nffg node using the reference table
		
		NffgReaderImpl nfgr = new NffgReaderImpl(nffgElement, refTable);			// set the nffg reader interface
		return nfgr;
	}

}
