package pl.bsb.elixir.express.enterprise.srpn.interfaces;

import java.io.Serializable;

/**
 *
 * @author paweld
 */
public interface ISRPNSender extends Serializable {

  public iso.std.iso._20022.tech.xsd.pacs_002_001.Document acknowledgeDebit(String url, iso.std.iso._20022.tech.xsd.pacs_008_001.Document authorizeDocument);

  public iso.std.iso._20022.tech.xsd.pacs_002_001.Document acknowledgeCredit(String url, iso.std.iso._20022.tech.xsd.pacs_008_001.Document authorizeDocument);

  public iso.std.iso._20022.tech.xsd.pacs_002_001.Document authorizeTransfer(String url, iso.std.iso._20022.tech.xsd.pacs_008_001.Document authorizeDocument);

  public iso.std.iso._20022.tech.xsd.pacs_002_001.Document rejectTransfer(String url, iso.std.iso._20022.tech.xsd.pacs_004_001.Document rejectDocument);
}
