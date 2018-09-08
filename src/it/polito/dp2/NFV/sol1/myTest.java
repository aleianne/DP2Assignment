package it.polito.dp2.NFV.sol1;

import java.util.Set;

import it.polito.dp2.NFV.*;

public class myTest {
	
	
	public static void main(String[] args) {
		
        System.setProperty("it.polito.dp2.NFV.NfvReaderFactory", "it.polito.dp2.NFV.sol1.NfvReaderFactory");
		System.setProperty("it.polito.dp2.NFV.sol1.NfvInfo.file", "file1.xml");

		try {
			NfvReader newNFV =  NfvReaderFactory.newInstance().newNfvReader();
			Set<NffgReader> nfgr = newNFV.getNffgs(null);
			
			for (NffgReader nr: nfgr) {
				System.out.println(nr.getName());
				NodeReader nodeReader = nr.getNode("NATe4Nffg1");
				if (nodeReader != null) {
					// print all the link into the node to stdout
					for (LinkReader lr: nodeReader.getLinks()) {
						System.out.print("the info about the link are: ");
						System.out.print(lr.getName() + " ");
						System.out.print(lr.getDestinationNode().getName() + " ");
						System.out.print(lr.getSourceNode().getName() + " ");
						System.out.print(lr.getLatency() + " ");
						System.out.println(lr.getThroughput());
					}
				}
				else System.out.println("node not found");
			}


			HostReader hostReader = newNFV.getHost("H0");
			if (hostReader != null) {
				for (NodeReader nr : hostReader.getNodes()) {
					System.out.print("the node allocated is: ");
					System.out.print(nr.getFuncType().getName() + " ");
					System.out.println(nr.getNffg().getName());
				}
			} else
				System.out.print("the host doesn't exist into NFV");


		} catch (NfvReaderException nre) {
			System.err.println("error during nfv reader implementation, " + nre.getMessage());
			nre.printStackTrace();
		} catch (FactoryConfigurationError fe) {
			System.err.println("error during the factory implementation" + fe.getMessage());
			fe.printStackTrace();
		}
	}

}
