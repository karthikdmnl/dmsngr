package org.dmnl.dmsngr.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.dmnl.dmsngr.models.HttpErrorResponse;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {
        HttpErrorResponse httpErrorResponse = new HttpErrorResponse(404, "resource not found!");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(httpErrorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
