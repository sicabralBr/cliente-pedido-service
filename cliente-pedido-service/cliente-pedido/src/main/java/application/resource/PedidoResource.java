package application.resource;

import com.desafiosantander.application.dto.PedidoRequest;
import com.desafiosantander.application.service.PedidoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    @POST
    public Response criar(PedidoRequest request) {
        var created = pedidoService.criar(request);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public Response listar() {
        return Response.ok(pedidoService.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        return pedidoService.buscarPorId(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        pedidoService.excluir(id);
        return Response.noContent().build();
    }
}
