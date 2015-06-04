//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.26 at 01:36:56 PM CEST 
//


package fr.univnantes.lina.uima.mapper.rules.JAXB;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.lina.univ-nantes.fr/uima/annotationmapper}begin" minOccurs="0"/>
 *         &lt;element ref="{http://www.lina.univ-nantes.fr/uima/annotationmapper}end" minOccurs="0"/>
 *         &lt;element ref="{http://www.lina.univ-nantes.fr/uima/annotationmapper}setFeature" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "begin",
    "end",
    "setFeature"
})
@XmlRootElement(name = "create")
public class Create {

    protected Begin begin;
    protected End end;
    protected List<SetFeature> setFeature;
    @XmlAttribute(name = "type", required = true)
    protected String type;

    /**
     * Gets the value of the begin property.
     * 
     * @return
     *     possible object is
     *     {@link Begin }
     *     
     */
    public Begin getBegin() {
        return begin;
    }

    /**
     * Sets the value of the begin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Begin }
     *     
     */
    public void setBegin(Begin value) {
        this.begin = value;
    }

    /**
     * Gets the value of the end property.
     * 
     * @return
     *     possible object is
     *     {@link End }
     *     
     */
    public End getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     * 
     * @param value
     *     allowed object is
     *     {@link End }
     *     
     */
    public void setEnd(End value) {
        this.end = value;
    }

    /**
     * Gets the value of the setFeature property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the setFeature property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSetFeature().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SetFeature }
     * 
     * 
     */
    public List<SetFeature> getSetFeature() {
        if (setFeature == null) {
            setFeature = new ArrayList<SetFeature>();
        }
        return this.setFeature;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
