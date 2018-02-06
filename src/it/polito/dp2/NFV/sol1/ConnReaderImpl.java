package it.polito.dp2.NFV.sol1;

import it.polito.dp2.NFV.ConnectionPerformanceReader;
import it.polito.dp2.NFV.sol1.jaxb.ConnectionType;

class ConnReaderImpl implements ConnectionPerformanceReader{

	private ConnectionType conn;
	
	protected ConnReaderImpl(ConnectionType conn) {
		this.conn = conn;
	}
	
	@Override
	public int getLatency() {							// return the connection latency, from BigInteger to int
		return conn.getLatency().intValue();		
	}

	@Override
	public float getThroughput() {						// return the connection throughput 
		return conn.getThroughput();
	}

}
