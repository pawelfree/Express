package pl.bsb.elixir.express.enterprise.srpn;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import pl.bsb.elixir.express.enterprise.srpn.interfaces.IPingClient;
import pl.com.kir.srpn.soap.AgentService;
import pl.com.kir.srpn.soap.AgentService_Service;

/**
 *
 * @author paweld
 */
@Singleton
@LocalBean
public class PollClients {

  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  private static final Logger logger = Logger.getLogger(PollClients.class.getName());

  private AgentService_Service getSRPNService(String numerKNR) {
    try {
      return new AgentService_Service(new URL("http://localhost:8080/AgentService/AgentService".concat(numerKNR).concat("?wsdl")));
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Cant construct service ".concat(numerKNR).concat(" reference : {0}"), ex.getMessage());
    }
    return null;
  }

  @Asynchronous
  private void pollClient(String numerKNR) {
    AgentService_Service service;
    if ((service = getSRPNService(numerKNR)) != null) {
      AgentService port = service.getAgentServicePort();
      logger.log(Level.INFO, "Polling agent "
              .concat(numerKNR)
              .concat(" at ")
              .concat(simpleDateFormat.format(Calendar.getInstance().getTime()))
              .concat(" {0} result = {0}"), port.ping());
      return;
    }
    logger.log(Level.SEVERE, "Polling agent ".concat(numerKNR).concat(" failed. Service reference is null"));
  }

  @Schedule(minute = "*/3", second = "0", dayOfMonth = "*", month = "*", year = "*", hour = "*", dayOfWeek = "*")
  public void myTimer() {
    new IPingClient() {
      @Override
      public void execute() {
        pollClient("19606211");
        pollClient("19609443");
      }
    }.execute();

  }
}
