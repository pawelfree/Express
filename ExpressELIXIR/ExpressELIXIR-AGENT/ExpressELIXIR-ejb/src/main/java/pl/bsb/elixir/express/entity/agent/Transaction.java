package pl.bsb.elixir.express.entity.agent;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author paweld
 */
@Entity
@Table(name = "Transactions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="direction",
        discriminatorType= DiscriminatorType.STRING
        )
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column =
        @Column(name = "transactionAmount"))})
    private Money transactionAmount;
    @Column
    @NotNull
    @Size(min = 1, max = 23)
    private String transactionId;
    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    @Enumerated(EnumType.STRING)
    private InternalStatus status;
    @Column
    @NotNull
    @Size(min = 26, max = 26)
    private String receiverIban;
    @Column
    @NotNull
    @Size(min = 26, max = 26)
    private String senderIban;    
    
    public String getReceiverIban() {
        return receiverIban;
    }

    public void setReceiverIban(String receiverIban) {
        this.receiverIban = receiverIban;
    }        

    public InternalStatus getStatus() {
        return status;
    }

    public void setStatus(InternalStatus status) {
        this.status = status;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Money getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Money transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
   
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
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "TransactionId : ".concat(this.transactionId);
    }

  public String getSenderIban() {
    return senderIban;
  }

  public void setSenderIban(String senderIban) {
    this.senderIban = senderIban;
  }

}
