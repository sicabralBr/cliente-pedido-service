@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    private final PedidoService pedidoService;

    @Inject
    public PedidoResource(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @POST
    public Response criar(@Valid PedidoRequest request) {
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

    @PUT
    @Path("/{id}/status")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response atualizarStatus(@PathParam("id") Long id, String novoStatus) {
        try {
            pedidoService.atualizarStatus(id, novoStatus);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Status inválido: " + novoStatus)
                    .build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
