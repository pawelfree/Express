
package iso.std.iso._20022.tech.xsd.pacs_002_001;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the iso.std.iso._20022.tech.xsd.pacs_002_001 package. 
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

    private final static QName _Document_QNAME = new QName("urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03", "Document");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: iso.std.iso._20022.tech.xsd.pacs_002_001
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link FIToFIPaymentStatusReportV03 }
     * 
     */
    public FIToFIPaymentStatusReportV03 createFIToFIPaymentStatusReportV03() {
        return new FIToFIPaymentStatusReportV03();
    }

    /**
     * Create an instance of {@link StatusReasonInformation8 }
     * 
     */
    public StatusReasonInformation8 createStatusReasonInformation8() {
        return new StatusReasonInformation8();
    }

    /**
     * Create an instance of {@link OriginalTransactionReference13 }
     * 
     */
    public OriginalTransactionReference13 createOriginalTransactionReference13() {
        return new OriginalTransactionReference13();
    }

    /**
     * Create an instance of {@link AmountType3Choice }
     * 
     */
    public AmountType3Choice createAmountType3Choice() {
        return new AmountType3Choice();
    }

    /**
     * Create an instance of {@link PaymentTransactionInformation26 }
     * 
     */
    public PaymentTransactionInformation26 createPaymentTransactionInformation26() {
        return new PaymentTransactionInformation26();
    }

    /**
     * Create an instance of {@link AccountIdentification4Choice }
     * 
     */
    public AccountIdentification4Choice createAccountIdentification4Choice() {
        return new AccountIdentification4Choice();
    }

    /**
     * Create an instance of {@link GroupHeader37 }
     * 
     */
    public GroupHeader37 createGroupHeader37() {
        return new GroupHeader37();
    }

    /**
     * Create an instance of {@link StatusReason6Choice }
     * 
     */
    public StatusReason6Choice createStatusReason6Choice() {
        return new StatusReason6Choice();
    }

    /**
     * Create an instance of {@link BranchAndFinancialInstitutionIdentification4 }
     * 
     */
    public BranchAndFinancialInstitutionIdentification4 createBranchAndFinancialInstitutionIdentification4() {
        return new BranchAndFinancialInstitutionIdentification4();
    }

    /**
     * Create an instance of {@link GenericFinancialIdentification1 }
     * 
     */
    public GenericFinancialIdentification1 createGenericFinancialIdentification1() {
        return new GenericFinancialIdentification1();
    }

    /**
     * Create an instance of {@link OriginalGroupInformation20 }
     * 
     */
    public OriginalGroupInformation20 createOriginalGroupInformation20() {
        return new OriginalGroupInformation20();
    }

    /**
     * Create an instance of {@link ActiveOrHistoricCurrencyAndAmount }
     * 
     */
    public ActiveOrHistoricCurrencyAndAmount createActiveOrHistoricCurrencyAndAmount() {
        return new ActiveOrHistoricCurrencyAndAmount();
    }

    /**
     * Create an instance of {@link FinancialInstitutionIdentification7 }
     * 
     */
    public FinancialInstitutionIdentification7 createFinancialInstitutionIdentification7() {
        return new FinancialInstitutionIdentification7();
    }

    /**
     * Create an instance of {@link CashAccount16 }
     * 
     */
    public CashAccount16 createCashAccount16() {
        return new CashAccount16();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Document }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03", name = "Document")
    public JAXBElement<Document> createDocument(Document value) {
        return new JAXBElement<Document>(_Document_QNAME, Document.class, null, value);
    }

}
