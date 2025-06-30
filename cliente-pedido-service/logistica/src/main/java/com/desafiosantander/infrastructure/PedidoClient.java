package com.desafiosantander.infrastructure;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

@RegisterRestClient(configKey = "pedido-api")
@Path("/pedidos")
public interface PedidoClient {

    @PUT
    @Path("/{id}/status")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON) // Opcional, depende do backend
    void atualizarStatus(@PathParam("id") Long id, String status);
}
