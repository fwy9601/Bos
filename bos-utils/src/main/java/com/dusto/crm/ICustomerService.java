
package com.dusto.crm;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ICustomerService", targetNamespace = "http://service.crm.dusto.com/")
@XmlSeeAlso({
    //ObjectFactory.class
})
public interface ICustomerService {


    /**
     * 
     * @return
     *     returns java.util.List<com.dusto.crm.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://service.crm.dusto.com/", className = "com.dusto.crm.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://service.crm.dusto.com/", className = "com.dusto.crm.FindAllResponse")
    public List<Customer> findAll();

    /**
     * 
     * @return
     *     returns java.util.List<com.dusto.crm.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findListNotAssociation", targetNamespace = "http://service.crm.dusto.com/", className = "com.dusto.crm.FindListNotAssociation")
    @ResponseWrapper(localName = "findListNotAssociationResponse", targetNamespace = "http://service.crm.dusto.com/", className = "com.dusto.crm.FindListNotAssociationResponse")
    public List<Customer> findListNotAssociation();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<com.dusto.crm.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findListHasAssociation", targetNamespace = "http://service.crm.dusto.com/", className = "com.dusto.crm.FindListHasAssociation")
    @ResponseWrapper(localName = "findListHasAssociationResponse", targetNamespace = "http://service.crm.dusto.com/", className = "com.dusto.crm.FindListHasAssociationResponse")
    public List<Customer> findListHasAssociation(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
