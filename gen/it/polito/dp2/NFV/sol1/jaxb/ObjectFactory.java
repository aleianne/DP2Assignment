//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.02.09 alle 01:35:19 PM CET 
//


package it.polito.dp2.NFV.sol1.jaxb;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.polito.dp2.NFV.sol1.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.polito.dp2.NFV.sol1.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NFV }
     * 
     */
    public NFV createNFV() {
        return new NFV();
    }

    /**
     * Create an instance of {@link HostType }
     * 
     */
    public HostType createHostType() {
        return new HostType();
    }

    /**
     * Create an instance of {@link InfrastructureType }
     * 
     */
    public InfrastructureType createInfrastructureType() {
        return new InfrastructureType();
    }

    /**
     * Create an instance of {@link CatalogType }
     * 
     */
    public CatalogType createCatalogType() {
        return new CatalogType();
    }

    /**
     * Create an instance of {@link NFV.NffgList }
     * 
     */
    public NFV.NffgList createNFVNffgList() {
        return new NFV.NffgList();
    }

    /**
     * Create an instance of {@link NodeType }
     * 
     */
    public NodeType createNodeType() {
        return new NodeType();
    }

    /**
     * Create an instance of {@link NffgType }
     * 
     */
    public NffgType createNffgType() {
        return new NffgType();
    }

    /**
     * Create an instance of {@link ConnectionType }
     * 
     */
    public ConnectionType createConnectionType() {
        return new ConnectionType();
    }

    /**
     * Create an instance of {@link LinkType }
     * 
     */
    public LinkType createLinkType() {
        return new LinkType();
    }

    /**
     * Create an instance of {@link FunctionType }
     * 
     */
    public FunctionType createFunctionType() {
        return new FunctionType();
    }

    /**
     * Create an instance of {@link DeployedNodeType }
     * 
     */
    public DeployedNodeType createDeployedNodeType() {
        return new DeployedNodeType();
    }

    /**
     * Create an instance of {@link HostType.DeployedNodes }
     * 
     */
    public HostType.DeployedNodes createHostTypeDeployedNodes() {
        return new HostType.DeployedNodes();
    }

    /**
     * Create an instance of {@link InfrastructureType.Hosts }
     * 
     */
    public InfrastructureType.Hosts createInfrastructureTypeHosts() {
        return new InfrastructureType.Hosts();
    }

    /**
     * Create an instance of {@link InfrastructureType.Connections }
     * 
     */
    public InfrastructureType.Connections createInfrastructureTypeConnections() {
        return new InfrastructureType.Connections();
    }

}
