package it.polito.dp2.NFV.sol1.deserializer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.sol1.jaxb.HostType;
import it.polito.dp2.NFV.sol1.jaxb.NodeType;

class HostReaderImpl implements HostReader {
	
	private HostType host;
	private XmlReferenceMap refTable;
	private Set<NodeReader> nodeReaderSet;

	protected HostReaderImpl(HostType host, XmlReferenceMap refTable) {
		System.out.println("create interfacefor host: " + host.getHostname());
		
		// declare the node reader set 
		Set<NodeReader> nodeReaderSet = new HashSet<NodeReader> ();
		
		this.host = host;
		this.refTable = refTable;
	}
	
	@Override
	public String getName() {
		return host.getHostname();
	}

	@Override
	public int getAvailableMemory() {
		return host.getAvailableMemory().intValue();
	}

	@Override
	public int getAvailableStorage() {
		return host.getAvailableStorage().intValue();
	}

	@Override
	public int getMaxVNFs() {
		return host.getMaxVNF().intValue();
	}

	@Override
	public Set<NodeReader> getNodes() {
		
		// used for multiple calls optimization, in order to not recompute the set at every invocation
		if(!nodeReaderSet.isEmpty()) {
			return nodeReaderSet;
		}
		
		List<HostType.DeployedNode> deployedNodeList = host.getDeployedNode();
		
		// create the nodeReader interface set
		for(HostType.DeployedNode dpn: deployedNodeList) {
			// get the referenced node using an hash table
			NodeType node = refTable.getNode(dpn.getNodeName());					
			NodeReaderImpl nodeReader = new NodeReaderImpl(node, refTable);
			nodeReaderSet.add(nodeReader);
		}
		
		return nodeReaderSet;
	}

}
