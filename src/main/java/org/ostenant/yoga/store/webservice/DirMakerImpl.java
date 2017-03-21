
package org.ostenant.yoga.store.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DirMakerImpl", targetNamespace = "http://webservice.yoga-store.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DirMakerImpl {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "makeDir", targetNamespace = "http://webservice.yoga-store.com/", className = "com.yoga-store.webservice.MakeDir")
    @ResponseWrapper(localName = "makeDirResponse", targetNamespace = "http://webservice.yoga-store.com/", className = "com.yoga-store.webservice.MakeDirResponse")
    @Action(input = "http://webservice.yoga-store.com/DirMakerImpl/makeDirRequest", output = "http://webservice.yoga-store.com/DirMakerImpl/makeDirResponse")
    public String makeDir(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteImage", targetNamespace = "http://webservice.yoga-store.com/", className = "com.yoga-store.webservice.DeleteImage")
    @ResponseWrapper(localName = "deleteImageResponse", targetNamespace = "http://webservice.yoga-store.com/", className = "com.yoga-store.webservice.DeleteImageResponse")
    @Action(input = "http://webservice.yoga-store.com/DirMakerImpl/deleteImageRequest", output = "http://webservice.yoga-store.com/DirMakerImpl/deleteImageResponse")
    public boolean deleteImage(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}