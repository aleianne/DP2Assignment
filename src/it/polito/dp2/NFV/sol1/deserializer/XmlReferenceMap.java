package it.polito.dp2.NFV.sol1.deserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.dp2.NFV.sol1.jaxb.ConnectionType;
import it.polito.dp2.NFV.sol1.jaxb.FunctionType;
import it.polito.dp2.NFV.sol1.jaxb.HostType;
import it.polito.dp2.NFV.sol1.jaxb.NFV;
import it.polito.dp2.NFV.sol1.jaxb.NffgType;
import it.polito.dp2.NFV.sol1.jaxb.NodeType;
import it.polito.dp2.NFV.sol1.serializer.StringPair;

class XmlReferenceMap {

	// HashMaps declaration
	private Map<String, NodeType> nodeMap = new HashMap<String, NodeType> ();
	private Map<String, FunctionType> VNFmap = new HashMap<String, FunctionType> ();
	private Map<String, NffgType> nffgMap = new HashMap<String, NffgType> ();
	private Map<String, HostType> hostMap = new HashMap<String, HostType> ();
	
	// HashMap with two keys
	private Map<StringPair, ConnectionType> connMap = new HashMap<StringPair, ConnectionType> ();
	private Map<StringPair, NodeType> nodeNffgMap = new HashMap<StringPair, NodeType> ();
	
	// NFV reference
	private NFV newNFV;
	
	protected XmlReferenceMap(NFV newNFV) {
		this.newNFV = newNFV;									// get the NFV unmarshalled object and create a reference table
		
		// load the data in the hashMaps
		loadNffg();
		loadHost();
		loadVNF();
		loadConnection();
	}
	
	protected NodeType getNode(String nodeName) {					
		return nodeMap.get(nodeName);
	}
	
	protected NffgType getGraph(String graphName) {					
		return nffgMap.get(graphName);
	}
	
	protected FunctionType getVNF(String functionName) {			
		return VNFmap.get(functionName);
	}
	
	protected HostType getHost(String hostName) {					
		return hostMap.get(hostName);
	}
	
	protected ConnectionType getConnection(String hostname1, String hostname2) {	
		StringPair hostPair = new StringPair(hostname1, hostname2);
		return connMap.get(hostPair);
	}
	
	private void loadNffg() {											
		List<NffgType> graphList = newNFV.getNfFg();					// get the list of nffg and load it into the hashMap
		
		for (NffgType nffgElement: graphList) {
			nffgMap.put(nffgElement.getNffgName(), nffgElement);			// put all the nffg element into the hashmap
			loadNode(nffgElement);										
		}
	}
	
	private void loadHost() {
		List<HostType> hostList = newNFV.getInfNet().getHosts().getHost();		// get the list of host 
		
		// load the hostList into the the hashMap
		for (HostType hostElement: hostList) {																
			hostMap.put(hostElement.getHostname(), hostElement);					// put the element into the hashmap
		}
	}
	
	private void loadNode(NffgType newNffg) {
		List<NodeType> nodeList = newNffg.getNode();					// get the list of node and load it into the hashMap
		
		for (NodeType nodeElement: nodeList) {
			// insert the single node inside the map 
			nodeMap.put(nodeElement.getName(), nodeElement);
			StringPair node_nffg = new StringPair(nodeElement.getName(), newNffg.getNffgName());
			nodeNffgMap.put(node_nffg, nodeElement);
		}
	}
	
	private void loadVNF() {
		List<FunctionType> functionList = newNFV.getCatalog().getFunction();		// get the list of function 
		
		// load the function List into the hashMap
		for (FunctionType f: functionList) {
			VNFmap.put(f.getName(), f);
		}
	}
	
	private void loadConnection() {
		List<ConnectionType> connList = newNFV.getInfNet().getConnections().getConnection();
		
		// load the connection performance into the hashmap
		for (ConnectionType c: connList) {
			StringPair hostPair = new StringPair(c.getHostname1(), c.getHostname2());
			connMap.put(hostPair, c);
		}
		
	}
	
}
