package pl.bsb.elixir.express.enterprise.agent.interfaces;

import java.io.Serializable;
import java.util.List;
import pl.bsb.elixir.express.entity.agent.TransactionOutgoing;

/**
 *
 * @author paweld
 */
public interface ISendTransfer extends Serializable {
    public void process(TransactionOutgoing transaction);
    public void process(List<TransactionOutgoing> transactionList);
    
}
