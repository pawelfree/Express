package pl.bsb.elixir.express.enterprise.agent.webservice;

import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author paweld
 */
@Stateless
@WebService(serviceName = "AgentService", 
        portName = "AgentServicePort", 
        endpointInterface = "pl.com.kir.srpn.soap.AgentService", 
        targetNamespace = "http://soap.srpn.kir.com.pl/", 
        wsdlLocation = "META-INF/wsdl/AgentService.wsdl")
public class AgentService60000001 extends AgentService {
    
}
