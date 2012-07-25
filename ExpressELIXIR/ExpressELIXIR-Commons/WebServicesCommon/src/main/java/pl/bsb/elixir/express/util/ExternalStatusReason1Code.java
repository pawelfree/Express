package pl.bsb.elixir.express.util;

/**
 *
 * @author paweld
 */
public enum ExternalStatusReason1Code {

  /**
   * SignerCertificateNotValid - Błąd uwierzytelnienia
   */
  DS0D,
  /**
   * Invalid File Format - Nierozpoznany typ Komunikatu – w odpowiedzi na Komunikat wyslijPrzelew lub Niezrozumiały
   * Komunikat lub błąd walidacji – w odpowiedzi na dowolny Komunikat inny niż wyslijPrzelew
   */
  FF01;

  public String value() {
    return name();
  }

  public static ExternalStatusReason1Code fromValue(String v) {
    return valueOf(v);
  }
}
