package pl.bsb.elixir.express.entity.srpn;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author paweld
 */
@NamedQueries({
  @NamedQuery(name = "findSrpnStatementByTransactionAndStatus", query = "SELECT s FROM SRPNStatement s WHERE s.status = :status AND s.srpnTransaction = :srpnTransaction")
})
@Entity
@Table(name = "SrpnStatements")
public class SRPNStatement implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Lob
  @NotNull
  @Column(length = 4096)
  private String statementData;
  @NotNull
  @Enumerated(EnumType.STRING)
  private SRPNInternalStatus status;
  @ManyToOne
  @NotNull
  private SRPNTransaction srpnTransaction;

  public SRPNStatement() {
  }

  public SRPNStatement(String statementData, SRPNTransaction srpnTransaction) {
    this.statementData = statementData;
    this.srpnTransaction = srpnTransaction;
    this.status = srpnTransaction.getStatus();
  }

  public SRPNStatement(String statementData, SRPNTransaction srpnTransaction, SRPNInternalStatus status) {
    this.statementData = statementData;
    this.srpnTransaction = srpnTransaction;
    this.status = status;
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
    if (!(object instanceof SRPNStatement)) {
      return false;
    }
    SRPNStatement other = (SRPNStatement) object;
    return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
  }

  @Override
  public String toString() {
    return "pl.bsb.elixir.express.entity.Statement[ id=" + id + " ]";
  }

  public String getStatementData() {
    return statementData;
  }

  public void setStatementData(String statementData) {
    this.statementData = statementData;
  }

  public SRPNTransaction getTransaction() {
    return srpnTransaction;
  }

  public void setTransaction(SRPNTransaction srpnTransaction) {
    this.srpnTransaction = srpnTransaction;
  }

  public SRPNInternalStatus getStatus() {
    return status;
  }

  public void setStatus(SRPNInternalStatus status) {
    this.status = status;
  }

  public SRPNTransaction getSrpnTransaction() {
    return srpnTransaction;
  }

  public void setSrpnTransaction(SRPNTransaction srpnTransaction) {
    this.srpnTransaction = srpnTransaction;
  }
}
