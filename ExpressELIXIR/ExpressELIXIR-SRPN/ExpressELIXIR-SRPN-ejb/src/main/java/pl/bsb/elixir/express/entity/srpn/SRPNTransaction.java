package pl.bsb.elixir.express.entity.srpn;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author paweld
 */
@Entity
@Table(name = "SrpnTransactions")
public class SRPNTransaction implements Serializable {

  private static final long serialVersionUID = 11L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  @NotNull
  @Size(min = 1, max = 23)
  private String transactionId;
  @Column
  @Size(min = 8, max = 8)
  @NotNull
  private String senderKNR;
  @Column
  @Size(min = 8, max = 8)
  @NotNull
  private String receiverKNR;
  @Column
  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  private Date originalTransactionDate;
  @NotNull
  @Enumerated(EnumType.STRING)
  private SRPNInternalStatus status;
  @NotNull
  @Column
  private int acknowledgeCreditRetryCounter;
  @NotNull
  @Column
  private int acknowledgeDebitRetryCounter;
  @Column
  private String srpnRejectionCode;  
  @Column
  private String externalRejectionCode;  
  @NotNull
  @Enumerated(EnumType.STRING)
  private SRPNInternalStatus acknowledgeDebitStatus;
  @NotNull
  @Enumerated(EnumType.STRING)
  private SRPNInternalStatus acknowledgeCreditStatus;

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
    if (!(object instanceof SRPNTransaction)) {
      return false;
    }
    SRPNTransaction other = (SRPNTransaction) object;
    return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
  }

  @Override
  public String toString() {
    return "pl.bsb.elixir.express.entity.srpn.SrpnTransaction[ id=" + id + " ]";
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getSenderKNR() {
    return senderKNR;
  }

  public void setSenderKNR(String senderKNR) {
    this.senderKNR = senderKNR;
  }

  public String getReceiverKNR() {
    return receiverKNR;
  }

  public void setReceiverKNR(String receiverKNR) {
    this.receiverKNR = receiverKNR;
  }

  public Date getOriginalTransactionDate() {
    return originalTransactionDate;
  }

  public void setOriginalTransactionDate(Date originalTransactionDate) {
    this.originalTransactionDate = originalTransactionDate;
  }

  public SRPNInternalStatus getStatus() {
    return status;
  }

  public void setStatus(SRPNInternalStatus status) {
    this.status = status;
  }

  public String getSrpnRejectionCode() {
    return srpnRejectionCode;
  }

  public void setSrpnRejectionCode(String srpnRejectionCode) {
    this.srpnRejectionCode = srpnRejectionCode;
  }

  public String getExternalRejectionCode() {
    return externalRejectionCode;
  }

  public void setExternalRejectionCode(String externalRejectionCode) {
    this.externalRejectionCode = externalRejectionCode;
  }

  public int getAcknowledgeCreditRetryCounter() {
    return acknowledgeCreditRetryCounter;
  }

  public void setAcknowledgeCreditRetryCounter(int acknowledgeCreditRetryCounter) {
    this.acknowledgeCreditRetryCounter = acknowledgeCreditRetryCounter;
  }
  
  public void decreaseAcknowledgeCreditRetryCounter() {
    this.acknowledgeCreditRetryCounter--;
  }
  
  public int getAcknowledgeDebitRetryCounter() {
    return acknowledgeDebitRetryCounter;
  }

  public void setAcknowledgeDebitRetryCounter(int acknowledgeDebitRetryCounter) {
    this.acknowledgeDebitRetryCounter = acknowledgeDebitRetryCounter;
  }
  
  public void decreaseAcknowledgeDebitRetryCounter() {
    this.acknowledgeDebitRetryCounter--;
  }

  public SRPNInternalStatus getAcknowledgeDebitStatus() {
    return acknowledgeDebitStatus;
  }

  public void setAcknowledgeDebitStatus(SRPNInternalStatus acknowledgeDebitStatus) {
    this.acknowledgeDebitStatus = acknowledgeDebitStatus;
  }

  public SRPNInternalStatus getAcknowledgeCreditStatus() {
    return acknowledgeCreditStatus;
  }

  public void setAcknowledgeCreditStatus(SRPNInternalStatus acknowledgeCreditStatus) {
    this.acknowledgeCreditStatus = acknowledgeCreditStatus;
  }
}
