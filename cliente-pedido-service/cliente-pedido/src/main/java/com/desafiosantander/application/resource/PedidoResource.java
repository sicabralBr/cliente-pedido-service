package com.desafiosantander.application.resource;

import com.desafiosantander.application.dto.PedidoRequest;
import com.desafiosantander.application.service.PedidoService;
import com.desafiosantander.domain.StatusPedido;
import com.desafiosantander.domain.model.Pedido;
import com.desafiosantander.domain.repository.PedidoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    @Inject
    PedidoRepository pedidoRepository;

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

    @PUT
    @Path("/{id}/status")
    @Transactional
    @Consumes(MediaType.TEXT_PLAIN)
    public Response atualizarStatus(@PathParam("id") Long id, String novoStatus) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Pedido pedido = optionalPedido.get();

        try {
            pedido.setStatus(StatusPedido.valueOf(novoStatus));
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Status inválido: " + novoStatus)
                    .build();
        }

        return Response.noContent().build();
    }
}

