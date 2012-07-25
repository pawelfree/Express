package pl.bsb.elixir.express.entity.agent.provider;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import pl.bsb.elixir.express.entity.agent.User;
import pl.bsb.proELIX.common.utils.criteria.BaseSearchCriteria;

/**
 *
 * @author paweld
 */
@Stateless
@LocalBean
public class UserProvider extends Provider<User,BaseSearchCriteria> {

    private static final long serialVersionUID = 7L;
    
    public UserProvider() {
        super(User.class);
    }
    
    public User getUserByLogin(String login) {
        Query queryUserByLogin = this.getEntityManager().createNamedQuery("findUserByLogin");
        queryUserByLogin.setParameter("login", login);
        return (User) queryUserByLogin.getSingleResult();
    }
    
}
