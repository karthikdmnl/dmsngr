package org.dmnl.dmsngr.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.dmnl.dmsngr.models.HttpErrorResponse;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException e) {
        HttpErrorResponse httpErrorResponse = new HttpErrorResponse(400, "input not valid!");
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(httpErrorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
