package pl.bsb.elixir.express.enterprise.agent.interfaces;

import java.io.Serializable;

/**
 *
 * @author paweld
 */
public interface IRejectTransfer extends Serializable {
    public iso.std.iso._20022.tech.xsd.pacs_002_001.Document process(iso.std.iso._20022.tech.xsd.pacs_004_001.Document document);
}
