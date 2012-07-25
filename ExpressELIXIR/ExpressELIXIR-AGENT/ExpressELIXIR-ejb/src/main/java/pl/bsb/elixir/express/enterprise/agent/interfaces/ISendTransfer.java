package pl.bsb.elixir.express.enterprise.agent.interfaces;

import java.io.Serializable;
import pl.bsb.elixir.express.entity.agent.TransactionOutgoing;

/**
 *
 * @author paweld
 */
public interface ISendTransfer extends Serializable {
    public boolean process(TransactionOutgoing transaction);
}
