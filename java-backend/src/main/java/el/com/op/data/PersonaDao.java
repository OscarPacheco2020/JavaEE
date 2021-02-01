package el.com.op.data;

import el.com.op.domain.Persona;
import java.util.List;

/**
 *
 * @author Oscar Pacheco
 */

public interface PersonaDao {
    
    public List<Persona> all();
    
    public Persona findPersona(Persona p);
    
    public void savePersona(Persona p);
    
    public void updatePersona(Persona p);
    
    public void deletePersona(Persona p);

}
