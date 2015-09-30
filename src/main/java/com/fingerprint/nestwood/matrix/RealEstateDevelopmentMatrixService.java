package com.fingerprint.nestwood.matrix;

import com.fingerprint.nestwood.DevelopmentMatrixFactory;
import com.fingerprint.nestwood.matrix.messages.DevelopmentMatrix;
import com.fingerprint.nestwood.util.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Path("/")
public class RealEstateDevelopmentMatrixService {

    private Logger logger = LogManager.getLogger(RealEstateDevelopmentMatrixService.class);
    private DevelopmentMatrix matrix = DevelopmentMatrixFactory.getInstance();

    @GET
    @Path("/ping")
    public Response ping() {
        String sample = "I am alive and well. Thanks for asking";
        return Response.status(200).entity(sample).build();
    }

    @GET
    @Path("/refresh")
    public Response refresh() {
        DevelopmentMatrixFactory.refresh();
        String sample = "Development Matrix has ben refreshed.";
        return Response.status(200).entity(sample).build();
    }

    @GET
    @Path("/headers/stage")
    public Response getStageHeaders() {
        String json = JSON.toJson(matrix.getStageHeader());
        return Response.status(200).entity(json).build();
    }

    @GET
    @Path("/headers/stage/{stage}")
    public Response getStageHeader(@PathParam("stage") int stage) {
        String json = JSON.toJson(matrix.getStageHeader().getHeaders().get(stage - 1));
        return Response.status(200).entity(json).build();
    }

    @GET
    @Path("/headers/task")
    public Response getTaskHeaders() {
        String json = JSON.toJson(matrix.getTaskHeader());
        return Response.status(200).entity(json).build();
    }

    @GET
    @Path("/headers/task/{task}")
    public Response getTaskHeader(@PathParam("task") int task) {
        String json = JSON.toJson(matrix.getTaskHeader().getHeaders().get(task - 1));
        return Response.status(200).entity(json).build();
    }


    @GET
    @Path("/stage{stage}/task{task}")
    public Response getMatrixContent(@PathParam("stage") int stage, @PathParam("task") int task) {
        String json = JSON.toJson(matrix.getMatrix().getContent(stage, task));
        return Response.status(200).entity(json).build();
    }

}
