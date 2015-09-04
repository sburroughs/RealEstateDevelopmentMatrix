package com.fingerprint.nestwood.matrix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;


@Path("/")
public class RealEstateDevelopmentMatrixService {

    private static final String BASE_STAGE_PATH = "stages";

    Logger logger = LogManager.getLogger(RealEstateDevelopmentMatrixService.class);

    DevelopmentMatrix matrix;

    public RealEstateDevelopmentMatrixService() {
        try {
            MatrixBuilder builder = new MatrixBuilder();
            DevelopmentMatrix updated = builder.buildMatrix(BASE_STAGE_PATH);
            matrix = updated;
        } catch (IOException | URISyntaxException e) {
            logger.error("Unable to build matrix", e);
        }
    }

    @GET
    @Path("/matrix")
    public Response getMatrix() {

        String result = matrix.generateRequest();


        String sample = "{ \"matrix\":[ { \"stage\":{ \"title\":\"Stage 1 Title\", \"tasks\":[ { \"task\":{ \"title\":\"Acquisition Tasks in Land Banking\", \"content\":\"Task 1 Content\" } }, { \"task\":{ \"title\":\"Financing in Land Banking\", \"content\":\"Task 2 Content\" } }, { \"task\":{ \"title\":\"Market Studies and Marketing Strategies in Land Banking\", \"content\":\"Task 3 Content\" } } ] } }, { \"stage\":{ \"title\":\"Stage 2 Title\", \"tasks\":[ { \"task\":{ \"title\":\"Acquisition Land Packaging Stage\", \"content\":\"Task 1 Content\" } }, { \"task\":{ \"title\":\"Financing in Land Packaging Stage\", \"content\":\"Task 2 Content\" } }, { \"task\":{ \"title\":\"Market Studies and Marketing Strategies Land Packaging\", \"content\":\"Task 3 Content\" } } ] } }, { \"stage\":{ \"title\":\"Stage 3 Title\", \"tasks\":[ { \"task\":{ \"title\":\"Acquisition Development Stage\", \"content\":\"<ol><li>First Thing</li><li>Second Thing</li><li>Third Thing</li></ol>\" } }, { \"task\":{ \"title\":\"Financing in Land Development Stage\", \"content\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc et orci tincidunt, interdum nisl a, sodales enim. Donec neque erat, cursus sed euismod id, fermentum vitae ipsum. Nulla imperdiet, diam et finibus lacinia, augue nunc commodo leo, sed malesuada ante lacus eu sapien. Nunc congue vehicula nunc, vel commodo magna vestibulum eget. Suspendisse dui ligula, tempor et efficitur ac, aliquam a nulla. Nulla facilisi. Quisque sed diam non lacus dignissim laoreet. Donec quis consectetur tellus. Nunc auctor turpis turpis, vitae sagittis erat tempus at. Vestibulum et ullamcorper dui. Etiam vitae metus sed odio ultrices consectetur. Phasellus felis nisl, auctor quis lacus vitae, aliquet rhoncus orci. Fusce consequat felis diam. Phasellus posuere vehicula massa sed dictum. Nam et iaculis odio, quis elementum nulla. Vivamus elit ligula, interdum et velit at, interdum lacinia ex.<br>Nam euismod egestas lorem, et auctor est eleifend ut. Quisque feugiat enim fringilla diam tincidunt, vel faucibus libero congue. Aenean cursus iaculis risus quis vehicula. In molestie efficitur nisi a tempor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vivamus sodales quam tellus, vitae porttitor purus dapibus id. Donec mattis neque ac erat aliquet, a iaculis erat finibus. Donec eget iaculis metus.<br>Fusce fermentum eu lectus ut commodo. Vestibulum sit amet diam consequat, feugiat elit ut, ultrices mauris. Duis euismod urna mattis blandit facilisis. Aenean viverra leo eget enim volutpat porta et id lacus. Proin vehicula sem sit amet est commodo, egestas luctus justo accumsan. Cras urna est, auctor ac pulvinar ac, porttitor auctor metus. Fusce nec diam in felis facilisis varius. In vitae cursus lacus. Maecenas in nisi est. Aliquam erat volutpat. Etiam eget viverra ipsum. Vestibulum sed enim mauris.<br>Vivamus semper, mauris a hendrerit ultrices, risus sapien gravida purus, eget commodo justo orci ut nulla. Suspendisse nunc ipsum, vestibulum vel faucibus nec, vehicula ac velit. Quisque placerat nisi a odio dictum semper. Aenean aliquam elit sed ligula lacinia laoreet. Sed vel ante eu metus tincidunt gravida. Fusce eu metus orci. Vestibulum elit elit, pretium ut congue sed, rutrum ac sem. Sed vitae libero turpis. Curabitur vel pharetra tortor, quis aliquam metus. Praesent ornare molestie enim ut convallis.<br>In ac interdum sem. Etiam quis hendrerit ipsum, quis iaculis enim. Aliquam erat volutpat. Curabitur id aliquet metus. In in orci hendrerit ante bibendum bibendum. Suspendisse quis tristique arcu. Praesent vehicula sagittis convallis. Maecenas congue tristique massa id egestas\" } }, { \"task\":{ \"title\":\"Marketing Studies and Marketing Strategies in Land Development Stage\", \"content\":\"Task 3\" } } ] } } ] }";

        return Response.status(200).entity(sample).build();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        MatrixBuilder builder = new MatrixBuilder();
        DevelopmentMatrix matrix = builder.buildMatrix(BASE_STAGE_PATH);
        System.out.println(matrix.generateRequest());
    }


}
