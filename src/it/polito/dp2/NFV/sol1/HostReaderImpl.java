package it.polito.dp2.NFV.sol1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.sol1.jaxb.HostType;
import it.polito.dp2.NFV.sol1.jaxb.NodeType;

class HostReaderImpl implements HostReader {
	
	private HostType host;
	private XMLreferenceMapper refTable;

	protected HostReaderImpl(HostType host, XMLreferenceMapper refTable) {
		System.out.println("host " + host.getHostname());
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
		Set<NodeReader> nodeReaderSet = new HashSet<NodeReader> ();
		List<HostType.DeployedNode> deployedNodeList = host.getDeployedNode();
		
		for (HostType.DeployedNode dpn: deployedNodeList) {
			NodeType node = refTable.getNode(dpn.getNodeName());					// get the referenced node using an hash table
			
			System.out.println("i'm h" + dpn.getNodeName());
			
			if(refTable == null) 
				System.out.println("ref table null");
			
			if(node == null) 
				System.out.println("nodo nullo");

			
			NodeReaderImpl nodeReader = new NodeReaderImpl(node, refTable);
			nodeReaderSet.add(nodeReader);
		}
		
		return nodeReaderSet;
	}

}
