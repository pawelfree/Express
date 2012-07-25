package pl.bsb.elixir.express.entity.agent;

import java.io.Serializable;
import java.net.URL;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author paweld
 */
@Entity
@Table(name="Participants")
public class Participant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotNull
    @Size(min=8,max=8)
    private String mainKNR;
    @Column
    private String description;
    @Column
    @NotNull
    private URL expressElixirUrl;
    @Column
    @NotNull
    private URL participantURL;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participant)) {
            return false;
        }
        Participant other = (Participant) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "pl.bsb.elixir.express.entity.Participant[ id=" + id + " ]";
    }

    public String getMainKNR() {
        return mainKNR;
    }

    public void setMainKNR(String mainKNR) {
        this.mainKNR = mainKNR;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getExpressElixirUrl() {
        return expressElixirUrl;
    }

    public void setExpressElixirUrl(URL expressElixirUrl) {
        this.expressElixirUrl = expressElixirUrl;
    }

    public URL getParticipantURL() {
        return participantURL;
    }

    public void setParticipantURL(URL participantURL) {
        this.participantURL = participantURL;
    }
    
}
