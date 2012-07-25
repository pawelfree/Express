package pl.bsb.elixir.express.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import pl.bsb.elixir.express.entity.agent.Account;
import pl.bsb.elixir.express.entity.agent.User;
import pl.bsb.elixir.express.entity.agent.provider.UserProvider;

/**
 *
 * @author paweld
 */
@Named
@SessionScoped
public class MainView implements Serializable {

  private static final long serialVersionUID = 2L;
  @EJB
  UserProvider userProvider;
  private User user;
  private Account selectedAccount;
  private List<Account> accounts = new ArrayList<>();
  private Account newTransactionAccount;

  public User getUser() {
    return user;
  }

  @PostConstruct
  public void initialize() {
    refreshUser();
    selectedAccount = accounts.get(0);
    setNewTransactionAccount(selectedAccount);
  }

  public void refreshUser() {
    user = userProvider.getUserByLogin(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
    accounts = user.getAccounts();
    if (selectedAccount != null) {
      for (Account account :accounts) {
        if (selectedAccount.equals(account)) {
          selectedAccount = account;
          setNewTransactionAccount(selectedAccount);
          return;
        }
      }
    }
  }

  public String getUserFirstName() {
    return user.getFirstName();
  }

  public String getUserLastName() {
    return user.getLastName();
  }

  public String getUserLogin() {
    return user.getLogin();
  }

  public String getParticipantIdentifier() {
    return user.getParticipant().getMainKNR();
  }

  public MainView() {
  }

  public Account getSelectedAccount() {
    return selectedAccount;
  }

  public void setSelectedAccount(Account selectedAccount) {
    this.selectedAccount = selectedAccount;
    setNewTransactionAccount(selectedAccount);
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }

  public Account getNewTransactionAccount() {
    return newTransactionAccount;
  }

  public void setNewTransactionAccount(Account newTransactionAccount) {
    this.newTransactionAccount = newTransactionAccount;
  }
}
