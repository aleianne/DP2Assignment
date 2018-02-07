package it.polito.dp2.NFV.sol1;

import it.polito.dp2.NFV.FunctionalType;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.sol1.jaxb.FunctionType;

public class VNFTypeReaderImpl implements VNFTypeReader{
	
	private FunctionType VNF;
	
	public VNFTypeReaderImpl(FunctionType VNF) {
		this.VNF = VNF;
	}

	@Override
	public String getName() {
		return VNF.getName();
	}

	@Override
	public FunctionalType getFunctionalType() {
		return FunctionalType.valueOf(VNF.getType().toString());				// cast my enum functionType to interface FunctionalType 
	}

	@Override
	public int getRequiredMemory() {
		return VNF.getRequiredMemory().intValue();								// return the int value encapsulated in BigInteger
	}

	@Override
	public int getRequiredStorage() {
		return VNF.getRequiredStorage().intValue();								// return the int value encapsulated in BigInteger
	}

}
