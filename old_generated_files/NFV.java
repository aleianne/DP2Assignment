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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="catalog">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="function" type="{}functionType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="nf-fg" type="{}nffgType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="inf-net" type="{}inf-netType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "catalog",
    "nfFg",
    "infNet"
})
@XmlRootElement(name = "NFV")
public class NFV {

    @XmlElement(required = true)
    protected NFV.Catalog catalog;
    @XmlElement(name = "nf-fg")
    protected List<NffgType> nfFg;
    @XmlElement(name = "inf-net", required = true)
    protected InfNetType infNet;

    /**
     * Recupera il valore della proprietà catalog.
     * 
     * @return
     *     possible object is
     *     {@link NFV.Catalog }
     *     
     */
    public NFV.Catalog getCatalog() {
        return catalog;
    }

    /**
     * Imposta il valore della proprietà catalog.
     * 
     * @param value
     *     allowed object is
     *     {@link NFV.Catalog }
     *     
     */
    public void setCatalog(NFV.Catalog value) {
        this.catalog = value;
    }

    /**
     * Gets the value of the nfFg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nfFg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNfFg().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NffgType }
     * 
     * 
     */
    public List<NffgType> getNfFg() {
        if (nfFg == null) {
            nfFg = new ArrayList<NffgType>();
        }
        return this.nfFg;
    }

    /**
     * Recupera il valore della proprietà infNet.
     * 
     * @return
     *     possible object is
     *     {@link InfNetType }
     *     
     */
    public InfNetType getInfNet() {
        return infNet;
    }

    /**
     * Imposta il valore della proprietà infNet.
     * 
     * @param value
     *     allowed object is
     *     {@link InfNetType }
     *     
     */
    public void setInfNet(InfNetType value) {
        this.infNet = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="function" type="{}functionType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "function"
    })
    public static class Catalog {

        @XmlElement(required = true)
        protected List<FunctionType> function;

        /**
         * Gets the value of the function property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the function property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFunction().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FunctionType }
         * 
         * 
         */
        public List<FunctionType> getFunction() {
            if (function == null) {
                function = new ArrayList<FunctionType>();
            }
            return this.function;
        }

    }

}
