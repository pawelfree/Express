package pl.bsb.elixir.express.enterprise.agent;

import iso.std.iso._20022.tech.xsd.pacs_002_001.Document;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.xml.ws.WebServiceRef;
import org.slf4j.LoggerFactory;
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

  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AgentSender.class);    
  static final long serialVersionUID = 1L;

//  @WebServiceRef(wsdlLocation= "META-INF/wsdl/SRPNService.wsdl")
  @WebServiceRef(wsdlLocation= "http://localhost:8080/SRPNService/SRPNService?wsdl")
  SRPNService_Service srpnService;

   
  @Override
  @Interceptors(SendTransferInterceptor.class)
  public Document sendTransferDocument(iso.std.iso._20022.tech.xsd.pacs_008_001.Document sendDocument) {
    logger.debug("Starting to send transfer document");
    try {
      if (srpnService != null) {
        SRPNService srpnPort = srpnService.getSRPNServicePort();
        return srpnPort.sendTransfer(sendDocument);
      }
      logger.warn("AgentSender - Service reference is null");
    } catch (RuntimeException | Fault ex) {
      logger.error("Failed to send transfer", ex);
    }
    return null;
  }
}
