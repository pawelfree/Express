package pl.bsb.elixir.express.domain;

import java.util.List; 
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel; 
import pl.bsb.elixir.express.entity.agent.Transaction;
/**
 *
 * @author paweld
 */
public class TransactionDataModel  extends ListDataModel<Transaction> implements SelectableDataModel<Transaction> {     
  
    public TransactionDataModel() {   
    }   
  
    public TransactionDataModel(List<Transaction> data) {   
        super(data);   
    }   
       
    @Override  
    public Transaction getRowData(String rowKey) {   
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data   
           
        List<Transaction> transactions = (List<Transaction>) getWrappedData();   
           
        for(Transaction transaction : transactions) {   
            if(transaction.getTransactionId().equals(rowKey)) {
                return transaction;   
            }
        }   
        return null;   
    }   
  
    @Override  
    public Object getRowKey(Transaction transaction) {   
        return transaction.getTransactionId();
    }   
}
