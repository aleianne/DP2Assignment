package it.polito.dp2.NFV.sol1.serializer;

import it.polito.dp2.NFV.sol1.jaxb.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import it.polito.dp2.NFV.*;

class InfrastructureLoader {
	
	private NfvReader nfvr;
	private InfrastructureType infrastructure;
	private ObjectFactory objFactory;
	private List<HostType> hostList;
	private List<ConnectionType> ConnList;
	
	protected InfrastructureLoader(NfvReader nfvr) {
		this.nfvr = nfvr;
		objFactory = new ObjectFactory();
	}
	
	// instantiate a new infrastructure network for the XML serialization
	protected InfrastructureType generateNetwork() {
		
		infrastructure = objFactory.createInfrastructureType();							// create a new inf net instance
		Set<HostReader> hostSet = nfvr.getHosts();				
		
		infrastructure.setHosts(new InfrastructureType.Hosts());				// instantiate the hostGroup attribute inside InfNet
		hostList = infrastructure.getHosts().getHost();						// get the host list reference used for insert new host element
		
		System.out.println("begin to load the infrastructure network information");
		
		for(HostReader hr: hostSet) {
			HostType newHost = objFactory.createHostType();					
			
			getDeployedNode(hr, newHost);										// fill the list of deployed-node using the HostReader interface
			
			// set the information inside an hostType instance
			newHost.setHostname(hr.getName());
			newHost.setMaxVNF(BigInteger.valueOf(hr.getMaxVNFs()));
			newHost.setAvailableStorage(BigInteger.valueOf(hr.getAvailableStorage()));
			newHost.setAvailableMemory(BigInteger.valueOf(hr.getAvailableMemory()));
			hostList.add(newHost);												// finally add the host element into the list of host 
		}
		
		infrastructure.setConnections(new InfrastructureType.Connections());			// instantiate the connections attribute inside InfNet
		ConnList = infrastructure.getConnections().getConnection();				// get the list of connection reference 
		
 		getConnectionPerformance(hostSet);
 		
 		System.out.println("infrastructure network information read correclty!\n");
		return infrastructure;													// return InfNet instace updated wth all the data read from the interface
	}
	
	// put into the host element all the deployed nffg node
	private void getDeployedNode(HostReader hr, HostType hostElement) {
		Set<NodeReader> nrSet = hr.getNodes();
		
		if (nrSet.isEmpty()) {													
			System.out.println("the deployed-node set of host" + hr.getName() + " is empty");
			return;
		}
		
		// for each node inside the node reader set put into the host element a new deployed node element
		for (NodeReader node: nrSet) {
			HostType.DeployedNode dp_node = new HostType.DeployedNode();
			dp_node.setNodeName(node.getName());
			hostElement.getDeployedNode().add(dp_node);
		}
	}
	
	// insert into the connection list all the performances between two hosts 
	private void getConnectionPerformance(Set<HostReader> hrSet) {
		ConnectionPerformanceReader connPerf;							
		
		for(HostReader outer_host: hrSet) {												// for all the host check the connection performance reader 					
			for(HostReader inner_host: hrSet) {
				
				if ((connPerf = nfvr.getConnectionPerformance(outer_host, inner_host)) != null) {
					ConnectionType newConn = objFactory.createConnectionType();			// create a new instance for the connection element
					newConn.setHostname1(outer_host.getName());
					newConn.setHostname2(inner_host.getName());
					newConn.setLatency(BigInteger.valueOf(connPerf.getLatency()));
					newConn.setThroughput(connPerf.getThroughput());
					ConnList.add(newConn);												// add a new connection element into the connection lists
				} 
			}
		}
		
		
	}

}
