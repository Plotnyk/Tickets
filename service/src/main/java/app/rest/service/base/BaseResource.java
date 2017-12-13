package app.rest.service.base;

import javax.ws.rs.core.Response;

/**
 * Base class for all REST web-service
 * @author Plotnyk
 * */
public abstract class BaseResource {
    /**
     * Shared Response that should be returned if requested operation
     * returns no data
     * */
    protected final Response NOT_FOUND;

    /**
     * Returned if client send request in invalid or unsupported format
     * */
    protected final Response BAD_REQUEST;

    public BaseResource() {
        NOT_FOUND = Response.status(Response.Status.NOT_FOUND).build();
        BAD_REQUEST = Response.status(Response.Status.BAD_REQUEST).build();
    }

    protected Response ok(Object result) {
        return Response.ok(result).build();
    }

}
