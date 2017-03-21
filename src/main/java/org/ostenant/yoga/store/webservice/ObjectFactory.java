
package org.ostenant.yoga.store.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.yoga-store.webservice package. 
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

    private final static QName _MakeDir_QNAME = new QName("http://webservice.yoga-store.com/", "makeDir");
    private final static QName _MakeDirResponse_QNAME = new QName("http://webservice.yoga-store.com/", "makeDirResponse");
    private final static QName _DeleteImageResponse_QNAME = new QName("http://webservice.yoga-store.com/", "deleteImageResponse");
    private final static QName _DeleteImage_QNAME = new QName("http://webservice.yoga-store.com/", "deleteImage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.yoga-store.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MakeDir }
     * 
     */
    public MakeDir createMakeDir() {
        return new MakeDir();
    }

    /**
     * Create an instance of {@link MakeDirResponse }
     * 
     */
    public MakeDirResponse createMakeDirResponse() {
        return new MakeDirResponse();
    }

    /**
     * Create an instance of {@link DeleteImageResponse }
     * 
     */
    public DeleteImageResponse createDeleteImageResponse() {
        return new DeleteImageResponse();
    }

    /**
     * Create an instance of {@link DeleteImage }
     * 
     */
    public DeleteImage createDeleteImage() {
        return new DeleteImage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeDir }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.yoga-store.com/", name = "makeDir")
    public JAXBElement<MakeDir> createMakeDir(MakeDir value) {
        return new JAXBElement<MakeDir>(_MakeDir_QNAME, MakeDir.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeDirResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.yoga-store.com/", name = "makeDirResponse")
    public JAXBElement<MakeDirResponse> createMakeDirResponse(MakeDirResponse value) {
        return new JAXBElement<MakeDirResponse>(_MakeDirResponse_QNAME, MakeDirResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.yoga-store.com/", name = "deleteImageResponse")
    public JAXBElement<DeleteImageResponse> createDeleteImageResponse(DeleteImageResponse value) {
        return new JAXBElement<DeleteImageResponse>(_DeleteImageResponse_QNAME, DeleteImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.yoga-store.com/", name = "deleteImage")
    public JAXBElement<DeleteImage> createDeleteImage(DeleteImage value) {
        return new JAXBElement<DeleteImage>(_DeleteImage_QNAME, DeleteImage.class, null, value);
    }

}
