package com.desafiosantander.application.resource;

import com.desafiosantander.application.dto.ClienteRequest;
import com.desafiosantander.application.dto.ClienteResponse;
import com.desafiosantander.application.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService clienteService;

    @POST
    public Response criar(ClienteRequest dto) {
        ClienteResponse created = clienteService.cadastrar(dto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public Response listar() {
        return Response.ok(clienteService.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        return clienteService.buscarPorId(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, ClienteRequest dto) {
        clienteService.editar(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        clienteService.excluir(id);
        return Response.noContent().build();
    }
}
