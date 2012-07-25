package pl.bsb.elixir.express.util;

/**
 *
 * @author paweld
 */
public enum ExternalReturnReason1Code {

  /**
   * IncorrectAccountNumber Nieprawidłowy numer rachunku lub rachunek nie istnieje
   */
  AC01,
  /**
   * ClosedAccountNumber Rachunek jest zamknięty przez bank
   */
  AC04,
  /**
   * BlockedAccount Zablokowany rachunek
   */
  AC06,
  /**
   * TransactionForbidden Nieobsługiwany typ Transakcji na rachunku
   *
   */
  AG01,
  /**
   * InvalidBankOperationCode Nieprawidłowy kod operacji bankowej
   */
  AG02,
  /**
   * ZeroAmount Zerowa kwota transakcji
   */
  AM01,
  /**
   * NotAllowedAmount Kwota Transakcji powyżej górnego limitu
   */
  AM02,
  /**
   * NotAllowedCurrency Nieobsługiwana waluta Transakcji
   */
  AM03,
  /**
   * InsufficientFunds Brak wystarczających środków do realizacji Transakcji
   */
  AM04,
  /**
   * Duplication Duplikat Transakcji
   */
  AM05,
  /**
   * TooLowAmount Kwota Transakcji poniżej dolnego limitu
   */
  AM06,
  /**
   * BlockedAmount Dostępna kwota jest niewystarczająca do realizacji Transakcji
   *
   */
  AM07,
  /**
   * WrongAmount Błędna kwota Transakcji
   */
  AM09,
  /**
   * InvalidControlSum Błąd sumy kontrolnej
   */
  AM10,
  /**
   * InconsistenWithEndCustomer Dane klienta niezgodne z numerem rachunku
   */
  BE01, 
  /**
   * MissingCreditorAddress Brak wymaganego adresu beneficjenta
   */
  BE04, 
  /**
   * UnrecognisedInitiatingParty Nierozpoznana strona inicjująca Transakcję
   */
  BE05, 
  /**
   * UnknownEndCustomer Klient jest nieznany lub nieobsługiwany przez Bank
   */
  BE06, 
  /**
   * MissingDebtorAddress Brak wymaganego adresu płatnika
   */
  BE07, 
  /**
   * IncorrectCurrency Nieprawidłowa waluta Transakcji
   */
  CURR, 
  /**
   * RequestedByCustomer Żądanie anulowania Transakcji przez płatnika 
   */
  CUST, 
  /**
   * InvalidDate Błędna data
   */
  DT01, 
  /**
   * CorrespondentBankNotPossible Bank korespondent nie jest możliwy
   */
  ED01, 
  /**
   * BalanceInfoRequest Zażądano uzupełniających danych
   */
  ED03, 
  /**
   * SettlementFailed Nieudane rozliczenie Transakcji
   */
  ED05, 
  /**
   * FollowingCancellationRequest Zwrot po żądaniu anulowania
   */
  FOCR, 
  /**
   * NoMandate Brak upoważnienia
   */
  MD01,
  /**
   * MissingMandatoryInformationInMandate Brak wymaganych powiązanych danych
   */
  MD02,
  /**
   * RefundRequestByEndCustomer Zwrot środków wnioskowany przez klienta końcowego
   */
  MD06,
  /**
   * EndCustomerDeceased Klient końcowy nie żyje
   */
  MD07,
  /**
   * NotSpecifiedReasonCustomer Generated Przyczyna nie została podana przez klienta
   */
  MS02,
  /**
   * NotSpecifiedReasonAgent Generated Powód nie został podany przez Jednostkę Uczestnika
   */
  MS03,
  /**
   * Narrative Powód jest podany w formie opisowej – kod używany, gdy wystąpiła sytuacja nie opisana innymi kodami
   */
  NARR,
  /**
   * BankIdentifierIncorrect Nieprawidłowy identyfikator banku
   */
  RC01,
  /**
   * NotUniqueTransactionReference Referencja do Transakcji nie jest unikalna w Komunikacie
   */
  RF01,
  /**
   * Missing Debtor Account or Identification Brak rachunku płatnika lub jego identyfikacji
   */
  RR01,
  /**
   * Missing Debtor Name or Address Brak nazwy płatnika lub jego adresu
   */
  RR02,
  /**
   * Missing Creditor Name or Address Brak nazwy beneficjenta lub jego adresu
   */
  RR03,
  /**
   * Regulatory Reason Powód zgodny z regulacją
   */
  RR04,
  /**
   * Specific Service offered by Debtor Agent Z powodu specyfiki usługi agenta płatnika
   */
  SL01,
  /**
   * Specific Service offered by Creditor Agent Z powodu specyfiki usługi agenta beneficjenta
   */
  SL02,
  /**
   * CutOffTime Odebrano Komunikat po oczekiwanym czasie
   */
  TM01;

  /**
   *
   * @return
   */
  public String value() {
    return name();
  }

  /**
   *
   * @param v
   * @return
   */
  public static ExternalReturnReason1Code fromValue(String v) {
    return valueOf(v);
  }
}
