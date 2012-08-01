package pl.bsb.elixir.express.domain;

import java.util.Date;
import java.util.List;

/**
 *
 * @author mskorzynski
 */
public class Std {
    private Long id;
    private Date fromDate;
    private Date toDate;
    private List<AgentAvailability> agentAvailability;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Początek okresu obowiązywania zbiorów bazowych
     * @return the fromDate
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Koniec okresu obowiązywania zbiorów bazowych
     * @return the toDate
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the agentAvailability
     */
    public List<AgentAvailability> getAgentAvailability() {
        return agentAvailability;
    }

    /**
     * @param agentAvailability the agentAvailability to set
     */
    public void setAgentAvailability(List<AgentAvailability> agentAvailability) {
        this.agentAvailability = agentAvailability;
    }
    
}
