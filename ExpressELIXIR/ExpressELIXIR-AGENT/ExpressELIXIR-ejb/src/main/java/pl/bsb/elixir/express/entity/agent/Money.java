package pl.bsb.elixir.express.entity.agent;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dlesniewska
 */
@Embeddable
public class Money implements Serializable {

  private static final long serialVersionUID = 8L;
  @Min(0)
  @NotNull
  private final BigDecimal amount;
  @Transient
  private final String currency;

  /**
   *
   */
  protected Money() {
    this.amount = BigDecimal.ZERO;
    this.currency = "PLN";
  }

  /**
   * Porównuje dwie instancje pod względem wartości i waluty. Jeśli waluty są różne porównanie kończy się wyjątkiem
   * IllegalArgumentException
   *
   * @param val wartość do porówniania
   * @return -1, 0 , 1 jeśli val jest mniejszy, równy, większy
   */
  public int compareTo(Money val) {
    if (!this.currency.equalsIgnoreCase(val.currency)) {
      throw new IllegalArgumentException("Different currencies to compare - ".concat(currency).concat(" : ").concat(val.currency));
    }
    return this.amount.compareTo(val.getAmount());
  }

  /**
   * Odejmuje od siebie dwie waluty o tej samej walucie ( :-) ) Jeśli waluty są różne np. PLN i EUR odejmowanie kończy
   * się wyjątkiem IllegalArgumentException
   *
   * @param subtrahend wartość do odjęcia
   * @return nowa instancja waluty będąca wynikiem odejmowania
   */
  public Money subtract(final Money subtrahend) {
    if (this.currency.compareTo(subtrahend.getCurrency()) == 0) {
      return new Money(this.amount.subtract(subtrahend.amount), this.currency);
    }
    throw new IllegalArgumentException("Different currencies can't be subtracted.");
  }

  /**
   * Dodaje do siebie dwie waluty o tej samej walucie ( :-) ) Jeśli waluty są różne np. PLN i EUR dodawanie kończy się
   * wyjątkiem IllegalArgumentException
   *
   * @param augend wartość do dodania
   * @return nowa instancja waluty będąca wynikiem dodawania
   */
  public Money add(final Money augend) {
    if (this.currency.compareTo(augend.getCurrency()) == 0) {
      return new Money(this.amount.add(augend.amount), this.currency);
    }
    throw new IllegalArgumentException("Different currencies can't be added.");
  }

  /**
   * Konstruktor, więc chyba wszystko jasne :-)
   * 
   * @param amount wartość 
   * @param currency waluta
   */
  public Money(BigDecimal amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  /**
   * Konstruktor tworzy pieniądze o walucie PLN
   * 
   * @param amount wartość
   */
  public Money(BigDecimal amount) {
    this.amount = amount;
    this.currency = "PLN";
  }
  
  /**
   *
   * @return
   */
  public BigDecimal getAmount() {
    return amount;
  }

  /**
   *
   * @return
   */
  public String getCurrency() {
    return currency;
  }

  @Override
  public String toString() {
    return "Money{" + "amount=" + amount + ", currency=" + currency + '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Money other = (Money) obj;
    if (this.amount != other.amount && (this.amount == null || !this.amount.equals(other.amount))) {
      return false;
    }
    return this.currency.equalsIgnoreCase(other.currency);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 11 * hash + (this.amount != null ? this.amount.hashCode() : 0);
    hash = 11 * hash + (this.currency != null ? this.currency.hashCode() : 0);
    return hash;
  }
}
