package pl.bsb.elixir.express.enterprise.agent;

import iso.std.iso._20022.tech.xsd.pacs_002_001.Document;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.xml.ws.WebServiceRef;
import pl.bsb.elixir.express.enterprise.agent.interfaces.IAgentSender;
import pl.bsb.elixir.express.interceptor.SendTransferInterceptor;
import pl.com.kir.srpn.soap.Fault;
import pl.com.kir.srpn.soap.SRPNService;
import pl.com.kir.srpn.soap.SRPNService_Service;

/**
 *
 * @author paweld
 */
@Stateless
@Local
public class AgentSender implements IAgentSender {

  private static final Logger logger = Logger.getLogger(AgentSender.class.getName());
  static final long serialVersionUID = 1L;

  @WebServiceRef(wsdlLocation= "META-INF/wsdl/SRPNService.wsdl")
//  @WebServiceRef(wsdlLocation= "http://localhost:8080/SRPNService/SRPNService?wsdl")
  SRPNService_Service srpnService;

   
  @Override
  @Interceptors(SendTransferInterceptor.class)
  public Document sendTransferDocument(iso.std.iso._20022.tech.xsd.pacs_008_001.Document sendDocument) {
    logger.log(Level.INFO, "Starting to send transfer document");
    try {
      if (srpnService != null) {
        SRPNService srpnPort = srpnService.getSRPNServicePort();
        return srpnPort.sendTransfer(sendDocument);
      }
      logger.log(Level.WARNING, "AgentSender - Service reference is null");
    } catch (RuntimeException | Fault ex) {
      logger.log(Level.SEVERE, "Failed to send transfer with message : {0}", ex.getMessage());
    }
    return null;
  }
}
