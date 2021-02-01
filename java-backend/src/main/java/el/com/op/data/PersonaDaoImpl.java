package el.com.op.data;

import el.com.op.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Oscar Pacheco
 */

@Stateless
public class PersonaDaoImpl implements PersonaDao{

    @PersistenceContext (unitName = "PersonaPU")
    EntityManager em;
    
    @Override
    public List<Persona> all() {
        return em.createNamedQuery("Persona.allPersona").getResultList();
    }

    @Override
    public Persona findPersona(Persona p) {
        return em.find(Persona.class, p.getIdPersona());
    }

    @Override
    public void savePersona(Persona p) {
        em.persist(p);
        //Retorna la persona con el nuevo id
        em.flush();
    }

    @Override
    public void updatePersona(Persona p) {
        em.merge(p);
    }

    @Override
    public void deletePersona(Persona p) {
        //prime se actualiza y después se remueve
        em.remove(em.merge(p));
    }
    
}
