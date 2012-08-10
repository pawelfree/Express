package pl.bsb.elixir.express.enterprise.agent;

import iso.std.iso._20022.tech.xsd.pacs_002_001.TransactionIndividualStatus3Code;
import java.math.BigDecimal;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.bsb.elixir.express.TestData;
import pl.bsb.elixir.express.entity.agent.Account;
import pl.bsb.elixir.express.entity.agent.InternalStatus;
import pl.bsb.elixir.express.entity.agent.Money;
import pl.bsb.elixir.express.entity.agent.TransactionIncoming;
import pl.bsb.elixir.express.entity.agent.provider.TransactionIncomingProvider;
import pl.bsb.elixir.express.util.CreditTransferV02DocumentCreator;
import pl.bsb.elixir.express.util.Instruction;

/**
 *
 * @author paweld
 */
@RunWith(MockitoJUnitRunner.class)
public class AcknowledgeCreditTest {
    
    @Mock
    TransactionIncomingProvider transactionIncomingProvider;
    
    AcknowledgeCredit acknowledgeCredit;
    
    static pl.com.kir.srpn.soap.SendTransfer sendTransferDocument = new pl.com.kir.srpn.soap.SendTransfer(); 
    static pl.com.kir.srpn.soap.AcknowledgeCredit acknowledgeCreditDocument = new pl.com.kir.srpn.soap.AcknowledgeCredit();
    static long transactionId;
    @Mock
    TransactionIncoming transactionIncoming;
    
    public AcknowledgeCreditTest() {
    }
    
    @Before
    public void setUp() {
        acknowledgeCredit = new AcknowledgeCredit();
        acknowledgeCredit.setTransactionIncomingProvider(transactionIncomingProvider);
        transactionId = System.currentTimeMillis();
        sendTransferDocument.setDocument(CreditTransferV02DocumentCreator.createSendTransferDocument(
                TestData.KNR_1, String.valueOf(transactionId), BigDecimal.TEN,
                TestData.IBAN_1, TestData.KNR_1, TestData.KNR_2, TestData.IBAN_2));   
        acknowledgeCreditDocument.setDocument(CreditTransferV02DocumentCreator.createAcknowledgeCreditDocument(sendTransferDocument.getDocument()));
        
    }

    @Test
    public void testWrongInstruction() {
        //by default mock transactionOutgoingProvider.getTransactionById(xxx) returns null
        acknowledgeCreditDocument.getDocument().getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().setInstrId(Instruction.ackDebt.toString());
        iso.std.iso._20022.tech.xsd.pacs_002_001.Document result = acknowledgeCredit.process(acknowledgeCreditDocument.getDocument());
        assertTrue(result.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.RJCT));
    }
    
    @Test
    public void testCantFindTransaction() {
        //by default mock transactionOutgoingProvider.getTransactionById(xxx) returns null
        iso.std.iso._20022.tech.xsd.pacs_002_001.Document result = acknowledgeCredit.process(acknowledgeCreditDocument.getDocument());
        assertTrue(result.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.RJCT));
    }

  
    @Test
    public void testDuplicateTransaction() {
        Mockito.when(transactionIncoming.getStatus()).thenReturn(InternalStatus.ACKNOWLEDGE_CREDIT_ACCEPTED);
        Mockito.when(transactionIncomingProvider.getTransactionById(String.valueOf(transactionId))).thenReturn(transactionIncoming);
        iso.std.iso._20022.tech.xsd.pacs_002_001.Document result = acknowledgeCredit.process(acknowledgeCreditDocument.getDocument());
        assertTrue(result.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.ACSC));
    }
  
    @Test
    public void testTransactionAmountDiffer() {
        Mockito.when(transactionIncoming.getStatus()).thenReturn(InternalStatus.AUTHORIZE_ACCEPTED);
        Mockito.when(transactionIncoming.getTransactionAmount()).thenReturn(new Money(BigDecimal.ONE));
        Mockito.when(transactionIncomingProvider.getTransactionById(String.valueOf(transactionId))).thenReturn(transactionIncoming);
        iso.std.iso._20022.tech.xsd.pacs_002_001.Document result = acknowledgeCredit.process(acknowledgeCreditDocument.getDocument());
        assertTrue(result.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.RJCT));
    }    
   
    @Test
    public void testTransactionWithWrongState() {
        Mockito.when(transactionIncoming.getStatus()).thenReturn(InternalStatus.ACKNOWLEDGE_DEBIT_REJECTED);
        Mockito.when(transactionIncoming.getTransactionAmount()).thenReturn(new Money(BigDecimal.ONE));
        Mockito.when(transactionIncomingProvider.getTransactionById(String.valueOf(transactionId))).thenReturn(transactionIncoming);
        iso.std.iso._20022.tech.xsd.pacs_002_001.Document result = acknowledgeCredit.process(acknowledgeCreditDocument.getDocument());
        assertTrue(result.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.RJCT));
    }     
    
    @Test
    public void testCorrectTransaction() {
        Mockito.when(transactionIncoming.getStatus()).thenReturn(InternalStatus.AUTHORIZE_ACCEPTED);
        Mockito.when(transactionIncoming.getTransactionAmount()).thenReturn(new Money(BigDecimal.TEN));
        Mockito.when(transactionIncomingProvider.getTransactionById(String.valueOf(transactionId))).thenReturn(transactionIncoming);
        Mockito.when(transactionIncoming.credit(new Money(BigDecimal.TEN))).thenReturn(null);
        Account account = new Account();
        account.setIban(TestData.IBAN_2);
        Mockito.when(transactionIncoming.getReceiverAccount()).thenReturn(account);
        iso.std.iso._20022.tech.xsd.pacs_002_001.Document result = acknowledgeCredit.process(acknowledgeCreditDocument.getDocument());
        assertTrue(result.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.ACSC));        
    }
   
}
