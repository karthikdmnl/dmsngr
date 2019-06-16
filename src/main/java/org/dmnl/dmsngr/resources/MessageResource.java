package org.dmnl.dmsngr.resources;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import org.dmnl.dmsngr.models.Message;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.dmnl.dmsngr.repositories.MessageDaoImpl;
//import org.dmnl.dmsngr.exceptions.NotFoundException;
import org.dmnl.dmsngr.exceptions.BadRequestException;
import org.dmnl.dmsngr.models.HttpErrorResponse;

@Path("messages")
public class MessageResource {

    private final MessageDaoImpl messageDaoImpl;

    @Inject
    public MessageResource(MessageDaoImpl messageDaoImpl) {
        this.messageDaoImpl = messageDaoImpl;
    }

    @GET
    public Response getMessages() {
        List<Message> messages = this.messageDaoImpl.findAll();
        GenericEntity<List<Message>> list
                = new GenericEntity<List<Message>>(messages) {
                };
        return Response
                .status(Response.Status.OK)
                .entity(list)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @Path("{id}")
    @GET
    public Response getMessage(@PathParam("id") Long id)
            throws NotFoundException, BadRequestException {
        HttpErrorResponse httpErrorResponse = new HttpErrorResponse(404, "resource_not_found");
        Response r = Response.status(Response.Status.NOT_FOUND).entity(httpErrorResponse).build();
        if (id <= 0) {
            throw new BadRequestException();
        } else {
            Message message = this.messageDaoImpl.findOne(id);
            if (message == null) {
                throw new NotFoundException(r);
            } else {
                return Response
                        .status(Response.Status.OK)
                        .entity(message)
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMessage(Message message) {
        System.out.print("Hi" + message);
        System.out.print(message == null);
        this.messageDaoImpl.save(message);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteMessage(@PathParam("id") Long id) throws NotFoundException {
        Message message = this.messageDaoImpl.findOne(id);
        if (message == null) {
            throw new NotFoundException();
        } else {
            this.messageDaoImpl.delete(id);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }
    }

}
