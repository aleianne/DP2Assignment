package it.polito.dp2.NFV.sol1.deserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.dp2.NFV.sol1.StringPair;
import it.polito.dp2.NFV.sol1.jaxb.ConnectionType;
import it.polito.dp2.NFV.sol1.jaxb.FunctionType;
import it.polito.dp2.NFV.sol1.jaxb.HostType;
import it.polito.dp2.NFV.sol1.jaxb.NFV;
import it.polito.dp2.NFV.sol1.jaxb.NffgType;
import it.polito.dp2.NFV.sol1.jaxb.NodeType;

public class XmlReferenceMap {

	// HashMaps declaration
	private Map<String, NodeType> nodeMap = new HashMap<String, NodeType> ();
	private Map<String, FunctionType> VNFmap = new HashMap<String, FunctionType> ();
	private Map<String, NffgType> nffgMap = new HashMap<String, NffgType> ();
	private Map<String, HostType> hostMap = new HashMap<String, HostType> ();
	
	// HashMap with two keys
	private Map<StringPair, ConnectionType> connMap = new HashMap<StringPair, ConnectionType> ();
	//private Map<StringPair, NodeType> nodeNffgMap = new HashMap<StringPair, NodeType> ();
	
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
		for (NffgType nffgElement: newNFV.getNffgList().getNffg()) {
			nffgMap.put(nffgElement.getNffgName(), nffgElement);			// put all the nffg element into the hashmap
			loadNode(nffgElement);										
		}
	}
	
	private void loadHost() {
		for (HostType hostElement: newNFV.getInfNet().getHosts().getHost()) {
			hostMap.put(hostElement.getHostname(), hostElement);			// put the element into the hashmap
		}
	}
	
	private void loadNode(NffgType newNffg) {
		for (NodeType nodeElement: newNffg.getNode()) {
			// insert the single node inside the map 
			nodeMap.put(nodeElement.getName(), nodeElement);
			/*StringPair node_nffg = new StringPair(nodeElement.getName(), newNffg.getNffgName());
			nodeNffgMap.put(node_nffg, nodeElement);*/
		}
	}
	
	private void loadVNF() {
		for (FunctionType functionElement: newNFV.getCatalog().getFunction())
			VNFmap.put(functionElement.getName(), functionElement);
	}
	
	private void loadConnection() {
		for (ConnectionType connectionElement: newNFV.getInfNet().getConnections().getConnection()) {
			StringPair hostPair = new StringPair(connectionElement.getHostname1(), connectionElement.getHostname2());
			connMap.put(hostPair, connectionElement);
		}
	}
	
}
