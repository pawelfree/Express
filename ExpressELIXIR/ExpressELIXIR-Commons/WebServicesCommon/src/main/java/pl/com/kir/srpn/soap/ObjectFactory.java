
package pl.com.kir.srpn.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.com.kir.srpn.soap package. 
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

    private final static QName _PingResponse_QNAME = new QName("http://soap.srpn.kir.com.pl/", "pingResponse");
    private final static QName _AcknowledgeDebitResponse_QNAME = new QName("http://soap.srpn.kir.com.pl/", "acknowledgeDebitResponse");
    private final static QName _RejectTransferResponse_QNAME = new QName("http://soap.srpn.kir.com.pl/", "rejectTransferResponse");
    private final static QName _AuthorizeTransferResponse_QNAME = new QName("http://soap.srpn.kir.com.pl/", "authorizeTransferResponse");
    private final static QName _AcknowledgeCredit_QNAME = new QName("http://soap.srpn.kir.com.pl/", "acknowledgeCredit");
    private final static QName _AcknowledgeCreditResponse_QNAME = new QName("http://soap.srpn.kir.com.pl/", "acknowledgeCreditResponse");
    private final static QName _Ping_QNAME = new QName("http://soap.srpn.kir.com.pl/", "ping");
    private final static QName _RejectTransfer_QNAME = new QName("http://soap.srpn.kir.com.pl/", "rejectTransfer");
    private final static QName _AcknowledgeDebit_QNAME = new QName("http://soap.srpn.kir.com.pl/", "acknowledgeDebit");
    private final static QName _AuthorizeTransfer_QNAME = new QName("http://soap.srpn.kir.com.pl/", "authorizeTransfer");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.com.kir.srpn.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthorizeTransfer }
     * 
     */
    public AuthorizeTransfer createAuthorizeTransfer() {
        return new AuthorizeTransfer();
    }

    /**
     * Create an instance of {@link AcknowledgeCreditResponse }
     * 
     */
    public AcknowledgeCreditResponse createAcknowledgeCreditResponse() {
        return new AcknowledgeCreditResponse();
    }

    /**
     * Create an instance of {@link RejectTransfer }
     * 
     */
    public RejectTransfer createRejectTransfer() {
        return new RejectTransfer();
    }

    /**
     * Create an instance of {@link AcknowledgeDebit }
     * 
     */
    public AcknowledgeDebit createAcknowledgeDebit() {
        return new AcknowledgeDebit();
    }

    /**
     * Create an instance of {@link AcknowledgeCredit }
     * 
     */
    public AcknowledgeCredit createAcknowledgeCredit() {
        return new AcknowledgeCredit();
    }

    /**
     * Create an instance of {@link AuthorizeTransferResponse }
     * 
     */
    public AuthorizeTransferResponse createAuthorizeTransferResponse() {
        return new AuthorizeTransferResponse();
    }

    /**
     * Create an instance of {@link RejectTransferResponse }
     * 
     */
    public RejectTransferResponse createRejectTransferResponse() {
        return new RejectTransferResponse();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link AcknowledgeDebitResponse }
     * 
     */
    public AcknowledgeDebitResponse createAcknowledgeDebitResponse() {
        return new AcknowledgeDebitResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.srpn.kir.com.pl/", name = "pingResponse")
    public JAXBElement<PingResponse> createPingResponse(PingResponse value) {
        return new JAXBElement<PingResponse>(_PingResponse_QNAME, PingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcknowledgeDebitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.srpn.kir.com.pl/", name = "acknowledgeDebitResponse")
    public JAXBElement<AcknowledgeDebitResponse> createAcknowledgeDebitResponse(AcknowledgeDebitResponse value) {
        return new JAXBElement<AcknowledgeDebitResponse>(_AcknowledgeDebitResponse_QNAME, AcknowledgeDebitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RejectTransferResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.srpn.kir.com.pl/", name = "rejectTransferResponse")
    public JAXBElement<RejectTransferResponse> createRejectTransferResponse(RejectTransferResponse value) {
        return new JAXBElement<RejectTransferResponse>(_RejectTransferResponse_QNAME, RejectTransferResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthorizeTransferResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.srpn.kir.com.pl/", name = "authorizeTransferResponse")
    public JAXBElement<AuthorizeTransferResponse> createAuthorizeTransferResponse(AuthorizeTransferResponse value) {
        return new JAXBElement<AuthorizeTransferResponse>(_AuthorizeTransferResponse_QNAME, AuthorizeTransferResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcknowledgeCredit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.srpn.kir.com.pl/", name = "acknowledgeCredit")
    public JAXBElement<AcknowledgeCredit> createAcknowledgeCredit(AcknowledgeCredit value) {
        return new JAXBElement<AcknowledgeCredit>(_AcknowledgeCredit_QNAME, AcknowledgeCredit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcknowledgeCreditResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.srpn.kir.com.pl/", name = "acknowledgeCreditResponse")
    public JAXBElement<AcknowledgeCreditResponse> createAcknowledgeCreditResponse(AcknowledgeCreditResponse value) {
        return new JAXBElement<AcknowledgeCreditResponse>(_AcknowledgeCreditResponse_QNAME, AcknowledgeCreditResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ping }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.srpn.kir.com.pl/", name = "ping")
    public JAXBElement<Ping> createPing(Ping value) {
        return new JAXBElement<Ping>(_Ping_QNAME, Ping.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RejectTransfer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.srpn.kir.com.pl/", name = "rejectTransfer")
    public JAXBElement<RejectTransfer> createRejectTransfer(RejectTransfer value) {
        return new JAXBElement<RejectTransfer>(_RejectTransfer_QNAME, RejectTransfer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcknowledgeDebit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.srpn.kir.com.pl/", name = "acknowledgeDebit")
    public JAXBElement<AcknowledgeDebit> createAcknowledgeDebit(AcknowledgeDebit value) {
        return new JAXBElement<AcknowledgeDebit>(_AcknowledgeDebit_QNAME, AcknowledgeDebit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthorizeTransfer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.srpn.kir.com.pl/", name = "authorizeTransfer")
    public JAXBElement<AuthorizeTransfer> createAuthorizeTransfer(AuthorizeTransfer value) {
        return new JAXBElement<AuthorizeTransfer>(_AuthorizeTransfer_QNAME, AuthorizeTransfer.class, null, value);
    }

}
