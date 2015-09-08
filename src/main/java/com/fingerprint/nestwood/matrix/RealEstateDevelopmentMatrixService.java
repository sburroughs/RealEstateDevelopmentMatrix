package com.fingerprint.nestwood.matrix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;


@Path("/")
public class RealEstateDevelopmentMatrixService {

    private Logger logger = LogManager.getLogger(RealEstateDevelopmentMatrixService.class);

    private DevelopmentMatrix matrix;

    public RealEstateDevelopmentMatrixService() {

        try {
            MatrixBuilder builder = new MatrixBuilder();
            DevelopmentMatrix updated = builder.buildMatrix();
            matrix = updated;
        } catch (MatrixBuildException e) {
            logger.error("Unable to build matrix", e);
        }
    }


    @GET
    @Path("/ping")
    public Response ping() {

        String sample = "I am alive and well. Thanks for asking";
        return Response.status(200).entity(sample).build();
    }

    @GET
    @Path("/headers/stage")
    public Response getStageHeaders() {

        String json = JSON.toJson(matrix.getStageHeader());

        return Response.status(200).entity(json).build();
    }

    @GET
    @Path("/headers/task")
    public Response getTaskHeaders() {

        String json = JSON.toJson(matrix.getTaskHeader());

        return Response.status(200).entity(json).build();
    }

    @GET
    @Path("/stage{stage}/task{task}")
    public Response getMatrixContent(@PathParam("stage") int stage, @PathParam("task") int task) {

        String json = JSON.toJson(matrix.getMatrix().getContent(stage, task));

        return Response.status(200).entity(json).build();
    }

//    @GET
//    @Path("/")
//    public Response getComments(@PathParam("stage") int stage, @PathParam("task") int task) {
//
//
//        return null;
//    }

//    public static void main(String[] args) throws MatrixBuildException {
//
//        MatrixBuilder builder = new MatrixBuilder();
//        DevelopmentMatrix matrix = builder.buildMatrix();
//        System.out.println(matrix.getMatrix().getContent(6, 1).getContent());
//    }


}
