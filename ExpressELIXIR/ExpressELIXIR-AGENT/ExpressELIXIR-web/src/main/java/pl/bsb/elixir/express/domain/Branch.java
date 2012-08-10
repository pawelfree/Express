package pl.bsb.elixir.express.domain;

import java.util.Date;

/**
 *
 * @author mskorzynski
 */
public class Branch {
    private String knr;
    private boolean defaultBranch;
    private Date validFrom;
    private Date validTo;

    /**
     * @return the knr
     */
    public String getKnr() {
        return knr;
    }

    /**
     * @param knr the knr to set
     */
    public void setKnr(String knr) {
        this.knr = knr;
    }

    /**
     * @return the defaultBranch
     */
    public boolean isDefaultBranch() {
        return defaultBranch;
    }

    /**
     * @param defaultBranch the defaultBranch to set
     */
    public void setDefaultBranch(boolean defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    /**
     * @return the validFrom
     */
    public Date getValidFrom() {
        return validFrom;
    }

    /**
     * @param validFrom the validFrom to set
     */
    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * @return the validTo
     */
    public Date getValidTo() {
        return validTo;
    }

    /**
     * @param validTo the validTo to set
     */
    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
}
