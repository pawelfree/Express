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
import pl.bsb.elixir.express.entity.agent.TransactionOutgoing;
import pl.bsb.elixir.express.entity.agent.provider.AccountProvider;
import pl.bsb.elixir.express.entity.agent.provider.TransactionOutgoingProvider;
import pl.bsb.elixir.express.util.CreditTransferV02DocumentCreator;
import pl.bsb.elixir.express.util.Instruction;

/**
 *
 * @author paweld
 */
@RunWith(MockitoJUnitRunner.class)
public class AcknowledgeDebitTest {

    @Mock
    TransactionOutgoingProvider transactionOutgoingProvider;
    @Mock
    AccountProvider accountProvider;
    @Mock
    TransactionOutgoing transactionOutgoing;
    
    AcknowledgeDebit acknowledgeDebit;
    static pl.com.kir.srpn.soap.SendTransfer sendTransferDocument = new pl.com.kir.srpn.soap.SendTransfer();
    static pl.com.kir.srpn.soap.AcknowledgeDebit acknowledgeDebitDocument = new pl.com.kir.srpn.soap.AcknowledgeDebit();
    static long transactionId;

    public AcknowledgeDebitTest() {
    }

    @Before
    public void setUp() {
        acknowledgeDebit = new AcknowledgeDebit();
        acknowledgeDebit.setTransactionOutgoingProvider(transactionOutgoingProvider);
        acknowledgeDebit.setAccountProvider(accountProvider);
        transactionId = System.currentTimeMillis();
        sendTransferDocument.setDocument(CreditTransferV02DocumentCreator.createSendTransferDocument(
                TestData.KNR_1, String.valueOf(transactionId), BigDecimal.TEN,
                TestData.IBAN_1, TestData.KNR_1, TestData.KNR_2, TestData.IBAN_2));
        acknowledgeDebitDocument.setDocument(CreditTransferV02DocumentCreator.createAcknowledgeDebitDocument(sendTransferDocument.getDocument()));

    }

    @Test
    public void testWrongInstruction() {
        //by default mock transactionOutgoingProvider.getTransactionById(xxx) returns null
        acknowledgeDebitDocument.getDocument().getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().setInstrId(Instruction.ackCrdt.toString());
        iso.std.iso._20022.tech.xsd.pacs_002_001.Document result = acknowledgeDebit.process(acknowledgeDebitDocument.getDocument());
        assertTrue(result.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.RJCT));
    }

    @Test
    public void testCantFindTransaction() {
        //by default mock transactionOutgoingProvider.getTransactionById(xxx) returns null
        iso.std.iso._20022.tech.xsd.pacs_002_001.Document result = acknowledgeDebit.process(acknowledgeDebitDocument.getDocument());
        assertTrue(result.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.RJCT));
    }

    @Test
    public void testDuplicateTransaction() {
        Mockito.when(transactionOutgoing.getStatus()).thenReturn(InternalStatus.ACKNOWLEDGE_DEBIT_ACCEPTED);
        Mockito.when(transactionOutgoingProvider.getTransactionById(String.valueOf(transactionId))).thenReturn(transactionOutgoing);
        iso.std.iso._20022.tech.xsd.pacs_002_001.Document result = acknowledgeDebit.process(acknowledgeDebitDocument.getDocument());
        assertTrue(result.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.ACSC));
    }

    @Test
    public void testTransactionAmountDiffer() {
        Mockito.when(transactionOutgoing.getStatus()).thenReturn(InternalStatus.ACCEPTED);
        Mockito.when(transactionOutgoing.getTransactionAmount()).thenReturn(new Money(BigDecimal.ONE));
        Mockito.when(transactionOutgoingProvider.getTransactionById(String.valueOf(transactionId))).thenReturn(transactionOutgoing);
        iso.std.iso._20022.tech.xsd.pacs_002_001.Document result = acknowledgeDebit.process(acknowledgeDebitDocument.getDocument());
        assertTrue(result.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.RJCT));
    }

    @Test
    public void testTransactionWithWrongState() {
        Mockito.when(transactionOutgoing.getStatus()).thenReturn(InternalStatus.ACKNOWLEDGE_DEBIT_REJECTED);
        Mockito.when(transactionOutgoing.getTransactionAmount()).thenReturn(new Money(BigDecimal.ONE));
        Mockito.when(transactionOutgoingProvider.getTransactionById(String.valueOf(transactionId))).thenReturn(transactionOutgoing);
        iso.std.iso._20022.tech.xsd.pacs_002_001.Document result = acknowledgeDebit.process(acknowledgeDebitDocument.getDocument());
        assertTrue(result.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.RJCT));
    }

    @Test
    public void testCorrectTransaction() {
        Mockito.when(transactionOutgoing.getStatus()).thenReturn(InternalStatus.ACCEPTED);
        Mockito.when(transactionOutgoing.getTransactionAmount()).thenReturn(new Money(BigDecimal.TEN));
        Mockito.when(transactionOutgoing.getTransactionId()).thenReturn(String.valueOf(transactionId));
        Mockito.when(transactionOutgoingProvider.getTransactionById(String.valueOf(transactionId))).thenReturn(transactionOutgoing);
        Account account = new Account();
        account.setIban(TestData.IBAN_1);
        Mockito.when(transactionOutgoing.getSenderAccount()).thenReturn(account);
        Mockito.doNothing().when(accountProvider).debitTransaction(transactionOutgoing);
        iso.std.iso._20022.tech.xsd.pacs_002_001.Document result = acknowledgeDebit.process(acknowledgeDebitDocument.getDocument());
        assertTrue(result.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.ACSC));
    }
}
