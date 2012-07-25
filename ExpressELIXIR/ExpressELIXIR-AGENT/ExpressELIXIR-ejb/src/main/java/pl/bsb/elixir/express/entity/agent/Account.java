package pl.bsb.elixir.express.entity.agent;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.bsb.proELIX.common.utils.StringUtil;

/**
 *
 * @author paweld
 */
@NamedQueries({
  @NamedQuery(name = "findAccountByIBAN", query = "SELECT a FROM Account a WHERE a.iban = :iban")
})
@Entity
@Table(name = "Accounts", uniqueConstraints =
@UniqueConstraint(columnNames = {"iban"}))
public class Account implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  @NotNull
  @Size(min = 26, max = 26)
  private String iban;
  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "amount", column =
    @Column(name = "balance"))})
  private Money balance;
  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "amount", column =
    @Column(name = "blockedBalance"))})
  private Money blockedBalance;

  /**
   *
   * @return
   */
  public Money getBlockedBalance() {
    return blockedBalance;
  }

  public void debit(Money amount) {
    balance = balance.subtract(amount);
  }
  
  public void credit(Money amount) {
    balance = balance.add(amount);
  }
  
  public void subtractFromBlockedBalance(Money amount) {
    blockedBalance = blockedBalance.subtract(amount);
  } 

  public void addToBlockedBalance(Money amount) {
    blockedBalance = blockedBalance.add(amount);
  }
          
          
  /**(
   * Sprawdza czy można zablokować środki - czy pozostajce środki wolne są większe od planowanego zwiększenia blokady.
   * 
   * @param blockadeAmount wartość blokady
   * @return true jeśli możliwe było zwiększenie blokady, false w przeciwnym wypadku
   */
  public boolean canIncreaseBlockedAmount(Money blockadeAmount) {
     return balance.subtract(blockedBalance).compareTo(blockadeAmount) >= 0;
  }
  
  /**
   *
   * @return
   */
  public Long getId() {
    return id;
  }

  /**
   *
   * @param id
   */
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
    if (!(object instanceof Account)) {
      return false;
    }
    Account other = (Account) object;
    return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
  }

  @Override
  public String toString() {
    return "pl.bsb.elixir.express.entity.Account[ id=" + id + " ]";
  }

  /**
   *
   * @return
   */
  public String getIban() {
    return iban;
  }

  /**
   *
   * @param iban
   */
  public void setIban(String iban) {
    this.iban = iban;
  }

  /**
   *
   * @return
   */
  public Money getBalance() {
    return balance;
  }

  /**
   *
   * @return
   */
  public String getFormattedAccountNumber() {
    return StringUtil.getFormattedAccountNumber(iban);
  }
}
