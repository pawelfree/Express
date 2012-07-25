/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.bsb.elixir.express.util;

import java.util.GregorianCalendar;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author paweld
 */
public class CalendarHelper {

    private static final Logger logger = Logger.getLogger(CalendarHelper.class.getName());
    
    public static XMLGregorianCalendar getCalendar(long timeInMillis) throws DatatypeConfigurationException{
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(timeInMillis);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    }
    
}
