package com.recruitment.handler;

import com.recruitment.exception.ResourceNotFoundException;
import com.recruitment.dto.ErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionHandler implements ExceptionMapper<ResourceNotFoundException> {

    @Override
    public Response toResponse(ResourceNotFoundException e) {
        ErrorDTO errorDTO = new ErrorDTO(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(errorDTO).build();
    }
}
