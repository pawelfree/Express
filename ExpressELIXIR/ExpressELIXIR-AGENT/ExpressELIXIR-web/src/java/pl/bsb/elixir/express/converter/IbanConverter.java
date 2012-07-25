package pl.bsb.elixir.express.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import pl.bsb.proELIX.common.utils.StringUtil;

/**
 *
 * @author paweld
 */
@FacesConverter(value = "ibanConverter")
@FacesValidator(value = "ibanValidator")
public class IbanConverter implements Converter, Validator {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        return value.replaceAll("\\s+", "");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof String) {
            String tmp = (String) value;
            tmp = StringUtil.stripWhitespaces(tmp);
            if (tmp.length() != 26) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Numer IBAN musi mieć dokładnie 26 znaków a ma ".concat(String.valueOf(tmp.length())), tmp));
            }
            return StringUtil.getFormattedAccountNumber(tmp);
        }
        throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                String.format("Nie można skonwertować %s do String", value.toString()), null));
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (null == value) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Walidowany obiekt jest pusty (null)", null));
        }
        if (value instanceof String) {
            String str = (String) value;
            if (str.length() != 26) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Numer IBAN musi mieć dokładnie 26 znaków", null));
            }
        } else {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Walidowany obiekt nie jest stringiem", null));
        }
    }
}
