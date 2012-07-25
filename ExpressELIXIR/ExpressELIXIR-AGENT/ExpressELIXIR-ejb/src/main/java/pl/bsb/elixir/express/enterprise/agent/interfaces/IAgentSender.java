package pl.bsb.elixir.express.enterprise.agent.interfaces;

import java.io.Serializable;

/**
 *
 * @author paweld
 */
public interface IAgentSender extends Serializable {
    
    public iso.std.iso._20022.tech.xsd.pacs_002_001.Document sendTransferDocument(iso.std.iso._20022.tech.xsd.pacs_008_001.Document sendDocument);
}
