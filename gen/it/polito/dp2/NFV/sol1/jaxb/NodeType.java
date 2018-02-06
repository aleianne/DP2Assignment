//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.01.16 alle 06:15:27 PM CET 
//


package it.polito.dp2.NFV.sol1.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per nodeType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="nodeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="link" type="{}linkType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{}node-name" />
 *       &lt;attribute name="VNF" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="hostname" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nf-fg" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nodeType", propOrder = {
    "link"
})
public class NodeType {

    protected List<LinkType> link;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "VNF", required = true)
    protected String vnf;
    @XmlAttribute(name = "hostname", required = true)
    protected String hostname;
    @XmlAttribute(name = "nf-fg", required = true)
    protected String nfFg;

    /**
     * Gets the value of the link property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the link property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkType }
     * 
     * 
     */
    public List<LinkType> getLink() {
        if (link == null) {
            link = new ArrayList<LinkType>();
        }
        return this.link;
    }

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietà vnf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVNF() {
        return vnf;
    }

    /**
     * Imposta il valore della proprietà vnf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVNF(String value) {
        this.vnf = value;
    }

    /**
     * Recupera il valore della proprietà hostname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Imposta il valore della proprietà hostname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostname(String value) {
        this.hostname = value;
    }

    /**
     * Recupera il valore della proprietà nfFg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNfFg() {
        return nfFg;
    }

    /**
     * Imposta il valore della proprietà nfFg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNfFg(String value) {
        this.nfFg = value;
    }

}
