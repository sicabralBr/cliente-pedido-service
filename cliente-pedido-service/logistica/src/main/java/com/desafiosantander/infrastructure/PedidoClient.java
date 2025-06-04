package com.desafiosantander.infrastructure;

import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/pedidos")
public interface PedidoClient {

    @PUT
    @Path("/{id}/status")
    void atualizarStatus(@PathParam("id") Long id, String status);
}
