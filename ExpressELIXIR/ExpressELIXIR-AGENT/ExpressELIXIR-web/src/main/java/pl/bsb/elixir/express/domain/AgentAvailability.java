package pl.bsb.elixir.express.domain;

import java.util.List;

/**
 *
 * @author mskorzynski
 */
public class AgentAvailability {

    /**
     * Lista z elementami reprezentującymi Jednostki Uczestnika
     */
    private List<Branch> branches;
    
    /**
     * Lista z elementami Statycznej Tabeli Dostępności 
     * do nadawania Zleceń Płatności Jednostki Uczestnika
     */
    private List staticAvailabilityTableSending;
    
    /**
     * Lista z elementami Statycznej Tabeli Dostępności 
     * do odbierania Zleceń Płatności Jednostki Uczestnika
     */
    private List staticAvailabilityTableReceiving;

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
        return staticAvailabilityTableSending;
    }

    /**
     * @param sats the StaticAvilabilityTableSending to set
     */
    public void setSats(List sats) {
        this.staticAvailabilityTableSending = sats;
    }

    /**
     * @return the StaticAvilabilityTableReceiving
     */
    public List getSatr() {
        return staticAvailabilityTableReceiving;
    }

    /**
     * @param satr the StaticAvilabilityTableReceiving to set
     */
    public void setSatr(List satr) {
        this.staticAvailabilityTableReceiving = satr;
    }
}
