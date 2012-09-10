/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.bsb.elixir.express.enterprise.agent;

import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 *
 * @author paweld
 */
@Singleton
@LocalBean
public class UniqueTransactionId {

        /** place to store count */  
    private long theCount;   
       
    @PostConstruct
    public void initialize() {
        Calendar now = Calendar.getInstance();   
        theCount = now.getTimeInMillis();          
    }
    /**  
     * gets the next value of the counter. If you call it frequently, it will use the incremented  
     * count, but if time elapses, it uses the current time  
     * @return  the next value of the timer  
     */  
    private synchronized Long next() {   
        return next(0);   
    } 

    /**  
     * gets the next value of the counter. If you call it frequently, it will use the incremented  
     * count, but if time elapses, it uses the current time  
     * @param delay millisecond increment  
     * @return the next value of the timer  
     */  
    private synchronized Long next(int delay) {   
        Calendar now = Calendar.getInstance();   
        long tempTime = now.getTimeInMillis() + delay;   
        theCount++;   
        if ( theCount < tempTime) {   
             theCount = tempTime;   
        }    
        return theCount;   
    }    
 
    public String nextMessageId(String knr) {
        return "000".concat(knr).concat(Long.toString(next()).substring(1));
    }
}
