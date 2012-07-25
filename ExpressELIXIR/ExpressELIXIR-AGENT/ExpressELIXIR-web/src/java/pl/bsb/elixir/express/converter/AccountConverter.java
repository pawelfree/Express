package pl.bsb.elixir.express.converter;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import pl.bsb.elixir.express.entity.agent.Account;
import pl.bsb.proELIX.common.utils.StringUtil;

/**
 *
 * @author paweld
 */
@FacesConverter(value = "accountConverter")
public class AccountConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        List<Account> accounts = context.getApplication().evaluateExpressionGet(context, "#{mainView.accounts}", List.class);
        String tmp = StringUtil.stripWhitespaces(value);
        for (Account account : accounts) {
            if (account.getIban().equalsIgnoreCase(tmp)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Account) {
            String tmp = ((Account) value).getIban();
            tmp = StringUtil.stripWhitespaces(tmp);
            if (tmp.length() != 26) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Numer IBAN musi mieć dokładnie 26 znaków a ma ".concat(String.valueOf(tmp.length())), tmp));
            }
            return StringUtil.getFormattedAccountNumber(tmp);
        }
        throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                String.format("Nie można skonwertować %s do String", value.toString()), null));
    }
}
