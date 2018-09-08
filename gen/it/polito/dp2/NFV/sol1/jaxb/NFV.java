//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.1-b171012.0423 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.08.21 alle 04:23:46 PM CEST 
//


package it.polito.dp2.NFV.sol1.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="catalog" type="{http://www.example.com/nfv}catalogType"/&gt;
 *         &lt;element name="nffg-list"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="nffg" type="{http://www.example.com/nfv}nffgType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="inf-net" type="{http://www.example.com/nfv}infrastructureType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "catalog",
    "nffgList",
    "infNet"
})
@XmlRootElement(name = "NFV")
public class NFV {

    @XmlElement(required = true)
    protected CatalogType catalog;
    @XmlElement(name = "nffg-list", required = true)
    protected NFV.NffgList nffgList;
    @XmlElement(name = "inf-net", required = true)
    protected InfrastructureType infNet;

    /**
     * Recupera il valore della proprietà catalog.
     * 
     * @return
     *     possible object is
     *     {@link CatalogType }
     *     
     */
    public CatalogType getCatalog() {
        return catalog;
    }

    /**
     * Imposta il valore della proprietà catalog.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogType }
     *     
     */
    public void setCatalog(CatalogType value) {
        this.catalog = value;
    }

    /**
     * Recupera il valore della proprietà nffgList.
     * 
     * @return
     *     possible object is
     *     {@link NFV.NffgList }
     *     
     */
    public NFV.NffgList getNffgList() {
        return nffgList;
    }

    /**
     * Imposta il valore della proprietà nffgList.
     * 
     * @param value
     *     allowed object is
     *     {@link NFV.NffgList }
     *     
     */
    public void setNffgList(NFV.NffgList value) {
        this.nffgList = value;
    }

    /**
     * Recupera il valore della proprietà infNet.
     * 
     * @return
     *     possible object is
     *     {@link InfrastructureType }
     *     
     */
    public InfrastructureType getInfNet() {
        return infNet;
    }

    /**
     * Imposta il valore della proprietà infNet.
     * 
     * @param value
     *     allowed object is
     *     {@link InfrastructureType }
     *     
     */
    public void setInfNet(InfrastructureType value) {
        this.infNet = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="nffg" type="{http://www.example.com/nfv}nffgType" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nffg"
    })
    public static class NffgList {

        protected List<NffgType> nffg;

        /**
         * Gets the value of the nffg property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the nffg property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNffg().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link NffgType }
         * 
         * 
         */
        public List<NffgType> getNffg() {
            if (nffg == null) {
                nffg = new ArrayList<NffgType>();
            }
            return this.nffg;
        }

    }

}
