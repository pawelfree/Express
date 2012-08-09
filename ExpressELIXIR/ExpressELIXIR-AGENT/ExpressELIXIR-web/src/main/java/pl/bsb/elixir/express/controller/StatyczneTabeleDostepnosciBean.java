package pl.bsb.elixir.express.controller;

import pl.bsb.elixir.express.domain.Std;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.bsb.elixir.express.domain.AgentAvailability;
import pl.bsb.elixir.express.domain.StdDataModel;

/**
 *
 * @author mskorzynski
 */
@ManagedBean
@SessionScoped
public class StatyczneTabeleDostepnosciBean implements Serializable {

    private static final Logger logger = Logger.getLogger(StatyczneTabeleDostepnosciBean.class.getName());
    private static final long serialVersionUID = 3L;
    private Std sampleStd;
    private List<Std> sampleStdList;
    private Std selectedStd;
    private StdDataModel stdDataModel;

    @PostConstruct
    public void initialize() {
        logger.info("___ INSIDE postconstruct ___");
        sampleStdList = new ArrayList<>();
        sampleStd = new Std();
        sampleStd.setId(1l);
        sampleStd.setFromDate(new Date());
        sampleStd.setToDate(new Date());
        List<AgentAvailability> aaList = new ArrayList<>();
        AgentAvailability aa = new AgentAvailability();
        aa.setBranches(new ArrayList());
        aa.setSatr(new ArrayList());
        aa.setSats(new ArrayList());
        aaList.add(aa);
        sampleStd.setAgentAvailability(aaList);
        sampleStdList.add(sampleStd);
    }

    public StdDataModel getStd() {
        logger.info("___ INSIDE getStd ___");
        stdDataModel = new StdDataModel(sampleStdList);
        return stdDataModel;
    }

  
    /**
     * @return the selectedStd
     */
    public Std getSelectedStd() {
        logger.info("___ INSIDE getSelectedStd ___");
        return selectedStd;
    }

    /**
     * @param selectedStd the selectedStd to set
     */
    public void setSelectedStd(Std selectedStd) {
        logger.info("___ INSIDE setSelectedStd ___");
        this.selectedStd = selectedStd;
    }
    

}
