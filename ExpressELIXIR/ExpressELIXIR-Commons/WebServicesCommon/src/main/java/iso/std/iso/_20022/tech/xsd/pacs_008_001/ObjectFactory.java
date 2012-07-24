
package iso.std.iso._20022.tech.xsd.pacs_008_001;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the iso.std.iso._20022.tech.xsd.pacs_008_001 package. 
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

    private final static QName _Document_QNAME = new QName("urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02", "Document");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: iso.std.iso._20022.tech.xsd.pacs_008_001
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
     * Create an instance of {@link NameAndAddress10 }
     * 
     */
    public NameAndAddress10 createNameAndAddress10() {
        return new NameAndAddress10();
    }

    /**
     * Create an instance of {@link ClearingSystemMemberIdentification2 }
     * 
     */
    public ClearingSystemMemberIdentification2 createClearingSystemMemberIdentification2() {
        return new ClearingSystemMemberIdentification2();
    }

    /**
     * Create an instance of {@link CreditTransferTransactionInformation11 }
     * 
     */
    public CreditTransferTransactionInformation11 createCreditTransferTransactionInformation11() {
        return new CreditTransferTransactionInformation11();
    }

    /**
     * Create an instance of {@link PartyIdentification32 }
     * 
     */
    public PartyIdentification32 createPartyIdentification32() {
        return new PartyIdentification32();
    }

    /**
     * Create an instance of {@link ActiveCurrencyAndAmount }
     * 
     */
    public ActiveCurrencyAndAmount createActiveCurrencyAndAmount() {
        return new ActiveCurrencyAndAmount();
    }

    /**
     * Create an instance of {@link ClearingSystemIdentification3Choice }
     * 
     */
    public ClearingSystemIdentification3Choice createClearingSystemIdentification3Choice() {
        return new ClearingSystemIdentification3Choice();
    }

    /**
     * Create an instance of {@link FIToFICustomerCreditTransferV02 }
     * 
     */
    public FIToFICustomerCreditTransferV02 createFIToFICustomerCreditTransferV02() {
        return new FIToFICustomerCreditTransferV02();
    }

    /**
     * Create an instance of {@link AccountIdentification4Choice }
     * 
     */
    public AccountIdentification4Choice createAccountIdentification4Choice() {
        return new AccountIdentification4Choice();
    }

    /**
     * Create an instance of {@link SettlementDateTimeIndication1 }
     * 
     */
    public SettlementDateTimeIndication1 createSettlementDateTimeIndication1() {
        return new SettlementDateTimeIndication1();
    }

    /**
     * Create an instance of {@link PostalAddress6 }
     * 
     */
    public PostalAddress6 createPostalAddress6() {
        return new PostalAddress6();
    }

    /**
     * Create an instance of {@link BranchAndFinancialInstitutionIdentification4 }
     * 
     */
    public BranchAndFinancialInstitutionIdentification4 createBranchAndFinancialInstitutionIdentification4() {
        return new BranchAndFinancialInstitutionIdentification4();
    }

    /**
     * Create an instance of {@link ClearingSystemIdentification2Choice }
     * 
     */
    public ClearingSystemIdentification2Choice createClearingSystemIdentification2Choice() {
        return new ClearingSystemIdentification2Choice();
    }

    /**
     * Create an instance of {@link PaymentTypeInformation21 }
     * 
     */
    public PaymentTypeInformation21 createPaymentTypeInformation21() {
        return new PaymentTypeInformation21();
    }

    /**
     * Create an instance of {@link GenericFinancialIdentification1 }
     * 
     */
    public GenericFinancialIdentification1 createGenericFinancialIdentification1() {
        return new GenericFinancialIdentification1();
    }

    /**
     * Create an instance of {@link GroupHeader33 }
     * 
     */
    public GroupHeader33 createGroupHeader33() {
        return new GroupHeader33();
    }

    /**
     * Create an instance of {@link SettlementInformation13 }
     * 
     */
    public SettlementInformation13 createSettlementInformation13() {
        return new SettlementInformation13();
    }

    /**
     * Create an instance of {@link ActiveOrHistoricCurrencyAndAmount }
     * 
     */
    public ActiveOrHistoricCurrencyAndAmount createActiveOrHistoricCurrencyAndAmount() {
        return new ActiveOrHistoricCurrencyAndAmount();
    }

    /**
     * Create an instance of {@link ServiceLevel8Choice }
     * 
     */
    public ServiceLevel8Choice createServiceLevel8Choice() {
        return new ServiceLevel8Choice();
    }

    /**
     * Create an instance of {@link FinancialInstitutionIdentification7 }
     * 
     */
    public FinancialInstitutionIdentification7 createFinancialInstitutionIdentification7() {
        return new FinancialInstitutionIdentification7();
    }

    /**
     * Create an instance of {@link RemittanceInformation5 }
     * 
     */
    public RemittanceInformation5 createRemittanceInformation5() {
        return new RemittanceInformation5();
    }

    /**
     * Create an instance of {@link PaymentIdentification3 }
     * 
     */
    public PaymentIdentification3 createPaymentIdentification3() {
        return new PaymentIdentification3();
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
    @XmlElementDecl(namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02", name = "Document")
    public JAXBElement<Document> createDocument(Document value) {
        return new JAXBElement<Document>(_Document_QNAME, Document.class, null, value);
    }

}
