package pl.bsb.elixir.express.domain;

import java.util.List; 
import java.util.logging.Logger;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel; 

/**
 *
 * @author mskorzynski
 */
public class StdDataModel  extends ListDataModel<Std> implements SelectableDataModel<Std> {
    
    private static final Logger logger = Logger.getLogger(StdDataModel.class.getName());  
    public StdDataModel() {   
    }   
  
    public StdDataModel(List<Std> data) {   
        super(data);   
    }   
       
    @Override  
    public Std getRowData(String rowKey) {
        logger.info("___ INSIDE getRowData ___");
        List<Std> stdList = (List<Std>) getWrappedData();   
           
        for(Std std : stdList) {   
            if(std.getId().equals(Long.parseLong(rowKey))) {
                return std;   
            }
        }   
        return null;   
    }   
  
    @Override  
    public Object getRowKey(Std std) {   
        logger.info("___ INSIDE getRowKey ___");
        return std.getId();
    }   
}
