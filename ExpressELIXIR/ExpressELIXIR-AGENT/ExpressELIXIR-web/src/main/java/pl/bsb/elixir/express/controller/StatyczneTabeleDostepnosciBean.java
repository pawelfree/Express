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

/**
 *
 * @author mskorzynski
 */
@ManagedBean
@SessionScoped
public class StatyczneTabeleDostepnosciBean implements Serializable {
  
  private static final Logger logger = Logger.getLogger(StatyczneTabeleDostepnosciBean.class.getName());
  private static final long serialVersionUID = 3L;
  
  private Std tds = new Std();

  @PostConstruct
  public void initialize() {
    logger.info("_____INSIDE postconstruct _______");
    tds.setId(1l);
    tds.setFromDate(new Date());
    tds.setToDate(new Date());
    List<AgentAvailability> aaList = new ArrayList<>();
    AgentAvailability aa = new AgentAvailability();
    aa.setBranches(new ArrayList());
    aa.setSatr(new ArrayList());
    aa.setSats(new ArrayList());
    aaList.add(aa);
    tds.setAgentAvailability(aaList);
  }

    /**
     * @return the tds
     */
    public Std getTds() {
        return tds;
    }

    /**
     * @param tds the tds to set
     */
    public void setTds(Std tds) {
        this.tds = tds;
    }

  

}
