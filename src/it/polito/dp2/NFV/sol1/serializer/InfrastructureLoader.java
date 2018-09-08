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
	private List<ConnectionType> ConnList;
	
	protected InfrastructureLoader(NfvReader nfvr, ObjectFactory objFactory) {
		this.nfvr = nfvr;
		this.objFactory = objFactory;
	}
	
	// instantiate a new infrastructure network for the XML serialization
	protected InfrastructureType generateNetwork() {
		infrastructure = objFactory.createInfrastructureType();							// create a new inf net instance
		infrastructure.setHosts(new InfrastructureType.Hosts());				// instantiate the hostGroup attribute inside InfNet

		Set<HostReader> hostSet = nfvr.getHosts();
		List<HostType> hostList = infrastructure.getHosts().getHost();							// get the host list reference used for insert new host element
		
		System.out.println("begin to load the infrastructure network information");
		
		for(HostReader hr: hostSet) {
			HostType newHost = objFactory.createHostType();					

			getDeployedNode(hr, newHost);										// fill the list of deployed-node using the HostReader interface
			
			// set the information inside an hostType class instance
			newHost.setHostname(hr.getName());
			newHost.setMaxVNF(BigInteger.valueOf(hr.getMaxVNFs()));
			newHost.setAvailableStorage(BigInteger.valueOf(hr.getAvailableStorage()));
			newHost.setAvailableMemory(BigInteger.valueOf(hr.getAvailableMemory()));
			hostList.add(newHost);												// finally add the host element into the list of host 
		}
		
		infrastructure.setConnections(new InfrastructureType.Connections());			// instantiate the connections attribute inside InfNet
		ConnList = infrastructure.getConnections().getConnection();						// get the list of connection reference
		
 		getConnectionPerformance(hostSet);
 		
 		System.out.println("infrastructure network information read correctly!\n");
		return infrastructure;													// return InfNet instace updated wth all the data read from the interface
	}
	
	// put into the host element all the deployed nffg node
	private void getDeployedNode(HostReader hr, HostType hostElement) {
		Set<NodeReader> nrSet = hr.getNodes();
		
		if (nrSet.isEmpty()) {													
			System.out.println("the host " + hr.getName() + " hasn't any node deployed");
			return;
		}
		
		hostElement.setDeployedNodes(new HostType.DeployedNodes());
		List<DeployedNodeType> nodeList = hostElement.getDeployedNodes().getNode();

		// for each node inside the set put the node name into the hos
		for (NodeReader node: nrSet) {
			DeployedNodeType newDeployedNodeElement = objFactory.createDeployedNodeType();
			newDeployedNodeElement.setNodeName(node.getName());
			nodeList.add(newDeployedNodeElement);
		}
	}
	
	// insert into the connection list all the performances between two hosts 
	private void getConnectionPerformance(Set<HostReader> hrSet) {
		ConnectionPerformanceReader connPerf;							
		
		for(HostReader outerHost: hrSet) {												// for all the host check the connection performance reader
			for(HostReader innerHost: hrSet) {
				
				if ((connPerf = nfvr.getConnectionPerformance(outerHost, innerHost)) != null) {
					ConnectionType newConn = objFactory.createConnectionType();			// create a new instance for the connection element
					newConn.setHostname1(outerHost.getName());
					newConn.setHostname2(innerHost.getName());
					newConn.setLatency(BigInteger.valueOf(connPerf.getLatency()));
					newConn.setThroughput(connPerf.getThroughput());
					ConnList.add(newConn);												// add a new connection element into the connection lists
				} 
			}
		}
	}

}
