package com.desafiosantander.infrastructure;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/pedidos")
public interface PedidoClient {

    @PUT
    @Path("/{id}/status")
    @Consumes(MediaType.TEXT_PLAIN) //
    void atualizarStatus(@PathParam("id") Long id, String status);
}
