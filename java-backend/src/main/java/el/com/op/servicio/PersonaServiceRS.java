package el.com.op.servicio;

import el.com.op.data.PersonaDao;
import el.com.op.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Oscar Pacheco
 */
@Stateless
@Path("/peraonas")
public class PersonaServiceRS {

    @Inject
    private PersonaDao personaDao;

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Persona> listaPersona() {
        List<Persona> personas = personaDao.all();
        System.out.println("Personas");
        System.out.println(personas);
        return personas;
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Persona personaEncontrada(@PathParam("id") int id) {
        Persona p = personaDao.findPersona(new Persona(id));
        System.out.println("Persona encontrada");
        System.out.println(p);
        return p;
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Persona agregarPersona(Persona p) {
        personaDao.savePersona(p);
        System.out.println("Persona guardada");
        System.out.println(p);
        return p;
    }

    @PUT
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response actualizarPersona(@PathParam("id") int id, Persona p) {
        Persona oldPersona = personaDao.findPersona(new Persona(id));
        if (oldPersona != null) {
            personaDao.updatePersona(p);
            System.out.println("Persona modificada");
            System.out.println(p);
            return Response.ok().entity(p).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) {
        Persona oldPersona = personaDao.findPersona(new Persona(id));
        if (oldPersona != null) {
            personaDao.deletePersona(new Persona(id));
            System.out.println("Persona eliminada con id");
            System.out.println(id);
            return Response.ok().build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
