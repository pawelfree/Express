package pl.bsb.elixir.express.entity.srpn;

/**
 *
 * @author paweld
 */
public enum SRPNInternalStatus {
  /**
   * Status techniczy w przypadku braku statusu (dla acknowledgeCredit i acknowledgeDebit)
   */
  NO_STATUS,
  /**
   * Żądanie wykonania przelewu
   */
  SENDTRANSFER,
  /**
   * Przyjęte przez SRPN żądanie wykonania przelewu
   */
  ACSP,
  /**
   * Odrzucone przez SRPN żądanie wykonania przelewu
   */
  RJCT,
  /**
   * Żądanie autoryzacji przelewu
   */
  AUTHORIZE,
  /**
   * Pozytywna odpowiedź na żądanie autoryzacji przelewu
   */
  ACCP_AU,
  /**
   * Negatywna odpowiedź na żądanie autoryzacji przelewu
   */
  RJCT_AU,
  /**
   * Żądanie odrzucenia przelewu
   */
  REJECT_TRANSFER,
  /**
   * Przyjęte żądanie odrzucenia przelewu
   */
  ACSC_RTR, 
  /**
   * Odrzucone żądanie odrzucenia przelewu
   */
  RJCT_RTR,
  /**
   * Wysłane żądania uznania rachunku
   */
  ACKNOWLEDGE_CREDIT,
  /**
   * Żądanie uznania rachunku zostało przyjęte
   */
  ACKNOWLEDGE_CREDIT_ACCEPTED,
  /*
   * Żądanie uznania rachunku zostało odrzucone
   */
  ACKNOWLEDGE_CREDIT_REJECTED,
    /**
   * Wysłane żądania obciążenia rachunku
   */
  ACKNOWLEDGE_DEBIT,
  /**
   * Żądanie obciążenia rachunku zostało przyjęte
   */
  ACKNOWLEDGE_DEBIT_ACCEPTED,
  /*
   * Żądanie obciążenia rachunku zostało odrzucone
   */
  ACKNOWLEDGE_DEBIT_REJECTED;

  public String value() {
    return name();
  }

  public static SRPNInternalStatus fromValue(String v) {
    return valueOf(v);
  }
}
