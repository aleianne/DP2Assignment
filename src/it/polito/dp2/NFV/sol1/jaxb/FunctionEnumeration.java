//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.1-b171012.0423 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.08.21 alle 04:23:46 PM CEST 
//


package it.polito.dp2.NFV.sol1.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per function_enumeration.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="function_enumeration"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="VPN"/&gt;
 *     &lt;enumeration value="WEB_SERVER"/&gt;
 *     &lt;enumeration value="WEB_CLIENT"/&gt;
 *     &lt;enumeration value="SPAM"/&gt;
 *     &lt;enumeration value="NAT"/&gt;
 *     &lt;enumeration value="MAIL_SERVER"/&gt;
 *     &lt;enumeration value="MAIL_CLIENT"/&gt;
 *     &lt;enumeration value="FW"/&gt;
 *     &lt;enumeration value="DPI"/&gt;
 *     &lt;enumeration value="CACHE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "function_enumeration")
@XmlEnum
public enum FunctionEnumeration {

    VPN,
    WEB_SERVER,
    WEB_CLIENT,
    SPAM,
    NAT,
    MAIL_SERVER,
    MAIL_CLIENT,
    FW,
    DPI,
    CACHE;

    public String value() {
        return name();
    }

    public static FunctionEnumeration fromValue(String v) {
        return valueOf(v);
    }

}
