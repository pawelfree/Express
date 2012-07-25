package pl.bsb.elixir.express.util;

/**
 *
 * @author paweld
 */
public enum SRPNRejectionReasonCode {

CANM, //Creditor Agent Not active system Member - Odbiorca Zlecenia Płatności nie jest aktywną Jednostką Uczestnika systemu Express ELIXIR
DANM, //Debtor Agent Not active system Member - Nadawca Zlecenia Płatności nie jest aktywną Jednostką Uczestnika systemu Express ELIXIR
CASU, //Creditor Agent Statically Unavailable - JO czasowo Niedostępna z powodu braku oznaczenia dostępności w Tabeli Dostępności Statycznej
CADU, //Creditor Agent Dynamically Unavailable - JO Nieosiągalna lub oznaczona w Tabeli Dostępności Dynamicznej jako Niedostępna
DATU, //Debtor Agent Temporarily Unavailable - JN czasowo Niedostępna
DTNR, //Duplicated Transaction Not Recognized - DUPLIKAT: Duplikat Fałszywy
DTCP, //Duplicated Transaction Completed - DUPLIKAT: Transakcja zakończona – zrealizowana
DTRJ, //Duplicated Transaction Rejected - DUPLIKAT: Transakcja zakończona – odrzucona
DTIP, //Duplicated Transaction In Progress - DUPLKAT: Transakcja w realizacji
DTCL, //Duplicated Transaction In Claim - DUPLIKAT: Transakcja w reklamacji
DTRF, //Duplicated Transaction Refused - DUPLIKAT: Transakcja nieprzyjęta
MVER, //Message Validation Error - Błąd walidacji Komunikatu9
IVTX, //Invalid Transaction Id - Błąd walidacji Identyfikatora Transakcji
TAPF; //Transaction Authorization Process Failure - Błąd w trakcie procesu Autoryzacji Transakcji
  
  public String value() {
    return name();
  }

  public static SRPNRejectionReasonCode fromValue(String v) {
    return valueOf(v);
  }
}
