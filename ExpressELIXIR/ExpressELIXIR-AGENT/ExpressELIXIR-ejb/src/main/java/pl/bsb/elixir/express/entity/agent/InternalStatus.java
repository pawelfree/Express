package pl.bsb.elixir.express.entity.agent;

/**
 *
 * @author paweld
 */
public enum InternalStatus {
  /**
   * Przelew wychodzący wychodząca czeka na wysłanie
   */
  WAITING_TO_SEND,
  /**
   * Przelew wychodzący został wysłany i przyjęty przez SRPN
   */
  ACCEPTED,
  /**
   * Przelew wychodzący został wysłany i odrzucony przez SRPN
   */
  REJECTED, 
  /**
   * Żądanie autoryzacji przelewu przychodzącego
   */
  AUTHORIZE_TRANSFER,
  /**
   * Udzielona pozytywna odpowiedź na żądanie autoryzacji przelewu 
   */
  AUTHORIZE_ACCEPTED,
  /**
   * Udzielona negatywna odpowiedź na żądanie autoryzacji przelewu
   */
  AUTHORIZE_REJECTED,
  /**
   * Żadanie odrzucenia przelewu (wcześniej musiało przyjść żądanie autoryzacji przelewu przychodzącego)
   */
  REJECT_TRANSFER, 
  /**
   * Żądanie odrzucenia przelewu zakończyło się błedem - nie można odrzucić przelewu
   */
  REJECT_TRANSFER_REJECTED, 
  /**
   * Żądanie odrzucenia przelewu zostało przyjęte i zrealizowane
   */
  REJECT_TRANSFER_ACCEPTED,
  /**
   * Żądanie uznania rachunku przelewem
   */
  ACKNOWLEDGE_CREDIT,
  /*
   *  Żądanie uznania rachunku zostało przyjęte i zrealizowane
   */
  ACKNOWLEDGE_CREDIT_ACCEPTED,
  /*
   *  Żądanie uznania rachunku zostało odrzucone
   */
  ACKNOWLEDGE_CREDIT_REJECTED,
  /**
   * Żądanie obciążenia rachunku 
   */
  ACKNOWLEDGE_DEBIT,  
  /*
   *  Żądanie obciążenia rachunku zostało przyjęte i zrealizowane
   */
  ACKNOWLEDGE_DEBIT_ACCEPTED,
  /*
   *  Żądanie obciążenia rachunku zostało odrzucone
   */
  ACKNOWLEDGE_DEBIT_REJECTED;
  
  public String value() {
    return name();
  }

  public static InternalStatus fromValue(String v) {
    return valueOf(v);
  }
}
