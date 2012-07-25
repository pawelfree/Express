package pl.bsb.elixir.express.util;

/**
 *
 * @author paweld
 */
public enum Instruction {
  /**
   * Send transfer
   */
  sendTrf, 
  /**
   * Authorize transfer
   */
  authTrf,
  /**
   * Acknowledge credit
   */
  ackCrdt,
  /**
   * Acknowledge debit
   */
  ackDebt, 
  /**
   * Reject transfer
   */
  rjctTrf;

  public String value() {
    return name();
  }

  public static Instruction fromValue(String v) {
    return valueOf(v);
  }
}