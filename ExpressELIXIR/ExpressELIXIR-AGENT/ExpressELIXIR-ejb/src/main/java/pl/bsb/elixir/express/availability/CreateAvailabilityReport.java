/*
 * Klasa generuje przykładową Tabelę Dostepności Statycznej
 * 
 */
package pl.bsb.elixir.express.availability;
import pl.com.kir.srpn.soap.availability.*;

/**
 *
 * @author mskorzynski
 */
public class CreateAvailabilityReport {
        
    public String create(){
        prepareObject();
        return "TODO: zwrócić XML";
    }

    private void prepareObject() {
        ObjectFactory of = new ObjectFactory();
        
        //TODO ustawić pola obiektu
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
