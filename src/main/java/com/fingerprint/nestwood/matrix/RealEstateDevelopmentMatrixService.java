package com.fingerprint.nestwood.matrix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;


@Path("/hello")
public class RealEstateDevelopmentMatrixService {

    private static final String BASE_STAGE_PATH = "/stages";

    Logger logger = LogManager.getLogger(RealEstateDevelopmentMatrixService.class);

    DevelopmentMatrix matrix;

    public RealEstateDevelopmentMatrixService() {
        try {
            MatrixBuilder builder = new MatrixBuilder();
            DevelopmentMatrix updated = builder.buildMatrix(BASE_STAGE_PATH);
            matrix = updated;
        } catch (IOException e) {
            logger.error("Unable to build matrix", e);
        }
    }

    @GET
    @Path("/matrix")
    public Response getMatrix() {

        String result = "RESULT";

        return Response.status(200).entity(result).build();
    }


}
