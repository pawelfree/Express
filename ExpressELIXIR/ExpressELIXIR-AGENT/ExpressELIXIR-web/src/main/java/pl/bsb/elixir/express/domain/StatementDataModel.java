package pl.bsb.elixir.express.domain;

import java.util.List; 
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel; 
import pl.bsb.elixir.express.entity.agent.Statement;

/**
 *
 * @author paweld
 */
public class StatementDataModel  extends ListDataModel<Statement> implements SelectableDataModel<Statement> {     
  
    public StatementDataModel() {   
    }   
  
    public StatementDataModel(List<Statement> data) {   
        super(data);   
    }   
       
    @Override  
    public Statement getRowData(String rowKey) {   
        //TODO In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data   
           
        List<Statement> statements = (List<Statement>) getWrappedData();   
           
        for(Statement statement : statements) {   
            if(statement.getId().equals(Long.parseLong(rowKey))) {
                return statement;   
            }
        }   
        return null;   
    }   
  
    @Override  
    public Object getRowKey(Statement statement) {   
        return statement.getId();
    }   
}
