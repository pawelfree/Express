package pl.bsb.elixir.express.domain;

import java.util.List;

/**
 *
 * @author mskorzynski
 */
public class AgentAvailability {

    private List branches;
    private List sats;
    private List satr;

    /**
     * @return the branches
     */
    public List getBranches() {
        return branches;
    }

    /**
     * @param branches the branches to set
     */
    public void setBranches(List branches) {
        this.branches = branches;
    }

    /**
     * @return the StaticAvilabilityTableSending
     */
    public List getSats() {
        return sats;
    }

    /**
     * @param sats the StaticAvilabilityTableSending to set
     */
    public void setSats(List sats) {
        this.sats = sats;
    }

    /**
     * @return the StaticAvilabilityTableReceiving
     */
    public List getSatr() {
        return satr;
    }

    /**
     * @param satr the StaticAvilabilityTableReceiving to set
     */
    public void setSatr(List satr) {
        this.satr = satr;
    }
}
