
package com.bea.wli.sb.context;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bea.wli.sb.context package. 
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

    private final static QName _Service_QNAME = new QName("http://www.bea.com/wli/sb/context", "service");
    private final static QName _JavaContent_QNAME = new QName("http://www.bea.com/wli/sb/context", "java-content");
    private final static QName _Attachments_QNAME = new QName("http://www.bea.com/wli/sb/context", "attachments");
    private final static QName _Transport_QNAME = new QName("http://www.bea.com/wli/sb/context", "transport");
    private final static QName _Fault_QNAME = new QName("http://www.bea.com/wli/sb/context", "fault");
    private final static QName _Endpoint_QNAME = new QName("http://www.bea.com/wli/sb/context", "endpoint");
    private final static QName _StackTrace_QNAME = new QName("http://www.bea.com/wli/sb/context", "stack-trace");
    private final static QName _Attachment_QNAME = new QName("http://www.bea.com/wli/sb/context", "attachment");
    private final static QName _Security_QNAME = new QName("http://www.bea.com/wli/sb/context", "security");
    private final static QName _BinaryContent_QNAME = new QName("http://www.bea.com/wli/sb/context", "binary-content");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bea.wli.sb.context
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SubjectType }
     * 
     */
    public SubjectType createSubjectType() {
        return new SubjectType();
    }

    /**
     * Create an instance of {@link FaultType }
     * 
     */
    public FaultType createFaultType() {
        return new FaultType();
    }

    /**
     * Create an instance of {@link BinaryContentType }
     * 
     */
    public BinaryContentType createBinaryContentType() {
        return new BinaryContentType();
    }

    /**
     * Create an instance of {@link TransportType }
     * 
     */
    public TransportType createTransportType() {
        return new TransportType();
    }

    /**
     * Create an instance of {@link ServiceType }
     * 
     */
    public ServiceType createServiceType() {
        return new ServiceType();
    }

    /**
     * Create an instance of {@link JavaContentType }
     * 
     */
    public JavaContentType createJavaContentType() {
        return new JavaContentType();
    }

    /**
     * Create an instance of {@link AttachmentType }
     * 
     */
    public AttachmentType createAttachmentType() {
        return new AttachmentType();
    }

    /**
     * Create an instance of {@link AttachmentsType }
     * 
     */
    public AttachmentsType createAttachmentsType() {
        return new AttachmentsType();
    }

    /**
     * Create an instance of {@link SecurityType }
     * 
     */
    public SecurityType createSecurityType() {
        return new SecurityType();
    }

    /**
     * Create an instance of {@link EndpointType }
     * 
     */
    public EndpointType createEndpointType() {
        return new EndpointType();
    }

    /**
     * Create an instance of {@link SubjectPropertyType }
     * 
     */
    public SubjectPropertyType createSubjectPropertyType() {
        return new SubjectPropertyType();
    }

    /**
     * Create an instance of {@link LocationType }
     * 
     */
    public LocationType createLocationType() {
        return new LocationType();
    }

    /**
     * Create an instance of {@link SubjectType.Principals }
     * 
     */
    public SubjectType.Principals createSubjectTypePrincipals() {
        return new SubjectType.Principals();
    }

    /**
     * Create an instance of {@link SubjectType.SubjectProperties }
     * 
     */
    public SubjectType.SubjectProperties createSubjectTypeSubjectProperties() {
        return new SubjectType.SubjectProperties();
    }

    /**
     * Create an instance of {@link FaultType.JavaException }
     * 
     */
    public FaultType.JavaException createFaultTypeJavaException() {
        return new FaultType.JavaException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/wli/sb/context", name = "service")
    public JAXBElement<ServiceType> createService(ServiceType value) {
        return new JAXBElement<ServiceType>(_Service_QNAME, ServiceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JavaContentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/wli/sb/context", name = "java-content")
    public JAXBElement<JavaContentType> createJavaContent(JavaContentType value) {
        return new JAXBElement<JavaContentType>(_JavaContent_QNAME, JavaContentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttachmentsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/wli/sb/context", name = "attachments")
    public JAXBElement<AttachmentsType> createAttachments(AttachmentsType value) {
        return new JAXBElement<AttachmentsType>(_Attachments_QNAME, AttachmentsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransportType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/wli/sb/context", name = "transport")
    public JAXBElement<TransportType> createTransport(TransportType value) {
        return new JAXBElement<TransportType>(_Transport_QNAME, TransportType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/wli/sb/context", name = "fault")
    public JAXBElement<FaultType> createFault(FaultType value) {
        return new JAXBElement<FaultType>(_Fault_QNAME, FaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndpointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/wli/sb/context", name = "endpoint")
    public JAXBElement<EndpointType> createEndpoint(EndpointType value) {
        return new JAXBElement<EndpointType>(_Endpoint_QNAME, EndpointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/wli/sb/context", name = "stack-trace")
    public JAXBElement<String> createStackTrace(String value) {
        return new JAXBElement<String>(_StackTrace_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttachmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/wli/sb/context", name = "attachment")
    public JAXBElement<AttachmentType> createAttachment(AttachmentType value) {
        return new JAXBElement<AttachmentType>(_Attachment_QNAME, AttachmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SecurityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/wli/sb/context", name = "security")
    public JAXBElement<SecurityType> createSecurity(SecurityType value) {
        return new JAXBElement<SecurityType>(_Security_QNAME, SecurityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryContentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/wli/sb/context", name = "binary-content")
    public JAXBElement<BinaryContentType> createBinaryContent(BinaryContentType value) {
        return new JAXBElement<BinaryContentType>(_BinaryContent_QNAME, BinaryContentType.class, null, value);
    }

}
