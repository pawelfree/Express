/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.bsb.elixir.express.enterprise.srpn.interfaces;

import iso.std.iso._20022.tech.xsd.pacs_008_001.Document;
import pl.bsb.elixir.express.entity.srpn.SRPNTransaction;

/**
 *
 * @author paweld
 */
public interface ISRPNProcessor {
  
  public void processTransfer(Document sendTransferDocument, SRPNTransaction transaction);
  
}
