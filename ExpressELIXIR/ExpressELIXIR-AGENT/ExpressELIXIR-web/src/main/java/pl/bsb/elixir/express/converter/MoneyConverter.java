package pl.bsb.elixir.express.converter;

import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import pl.bsb.elixir.express.entity.agent.Money;
import pl.bsb.proELIX.common.utils.StringUtil;

/**
 *
 * @author paweld
 */
@FacesConverter(forClass = Money.class)
public class MoneyConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    if (null == value || value.length() == 0) {
      throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
              "Ciąg znaków konwertowany na kwotę jest pusty", null));
    }
    value = value.replace(",", ".");
    return new Money(new BigDecimal(value));
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    Money m = (Money) value;
    return StringUtil.getAmount(m.getAmount());
  }
}
