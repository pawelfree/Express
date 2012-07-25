package pl.bsb.elixir.express.enterprise.srpn.webservice;

import iso.std.iso._20022.tech.xsd.pacs_002_001.TransactionIndividualStatus3Code;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import pl.bsb.elixir.express.enterprise.srpn.SRPNTransactionHelper;
import pl.bsb.elixir.express.enterprise.srpn.interfaces.ISRPNProcessor;
import pl.bsb.elixir.express.entity.srpn.SRPNInternalStatus;
import pl.bsb.elixir.express.entity.srpn.SRPNTransaction;
import pl.bsb.elixir.express.util.ResponseDocumentCreator;

/**
 *
 * @author paweld
 */
@Stateless
@WebService(serviceName = "SRPNService",
portName = "SRPNServicePort",
endpointInterface = "pl.com.kir.srpn.soap.SRPNService",
targetNamespace = "http://soap.srpn.kir.com.pl/",
wsdlLocation = "META-INF/wsdl/SRPNService.wsdl")
public class SRPNService {

  private static final Logger logger = Logger.getLogger(SRPNService.class.getName());
  @EJB
  SRPNTransactionHelper transactionHelper;
  @EJB
  ISRPNProcessor sRPNProcessor;
  
  /**
   * Odsługa poleceń przelewu przez system centralny
   * 
   * @param document żadanie przelewu 
   * @return status realizacji żądania (przyjęte/odrzucone)
   * @throws pl.com.kir.srpn.soap.Fault
   */
  public iso.std.iso._20022.tech.xsd.pacs_002_001.Document sendTransfer(iso.std.iso._20022.tech.xsd.pacs_008_001.Document document) throws pl.com.kir.srpn.soap.Fault {

    logger.log(Level.FINE, "Starting to service new request : {0}", document.getFIToFICstmrCdtTrf().getGrpHdr().getMsgId());

    iso.std.iso._20022.tech.xsd.pacs_002_001.Document response = null;

    try {
      SRPNTransaction transaction = transactionHelper.createSRPNTransaction(document);

      if (transaction == null) {
        throw new RuntimeException("Transaction - id : ".concat(document.getFIToFICstmrCdtTrf().getGrpHdr().getMsgId()).concat(" not persisted."));
      }

      TransactionIndividualStatus3Code responseStatus;
      //TODO jest losowo a powinna byc weryfikacja
      //TODO mozna odrzucac bledne KNRY
      long i = System.currentTimeMillis();
      if (i % 3 == 0) {
        responseStatus = TransactionIndividualStatus3Code.RJCT;
      } else {
        responseStatus = TransactionIndividualStatus3Code.ACSP;
      }
      //TODO odpowiedz z wygenerowanym wyżej statusem
      response = ResponseDocumentCreator.createSendTransferResponse(document.getFIToFICstmrCdtTrf(), responseStatus);
      transaction.setStatus(SRPNInternalStatus.fromValue(responseStatus.name()));
      transaction = transactionHelper.updateTransactionStatusAndResponse(transaction, response);
      if (transaction == null) {
        logger.log(Level.WARNING, "Response prepared but not persisted");
      }
      else if (responseStatus == TransactionIndividualStatus3Code.ACSP){
        sRPNProcessor.processTransfer(document, transaction);
      }
    } catch (RuntimeException ex) {
      logger.log(Level.SEVERE, "Cant prepare response.", ex.getMessage());
    }
    return response;
  }
}