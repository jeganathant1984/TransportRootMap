package in.co.galaxy.assignment;

import in.co.galaxy.assignment.config.DatasourceBean;
import in.co.galaxy.assignment.config.PersistenceBean;
import in.co.galaxy.assignment.config.WebServiceBean;
import in.co.galaxy.assignment.dao.EdgeDao;
import in.co.galaxy.assignment.dao.TrafficDao;
import in.co.galaxy.assignment.dao.VertexDao;
import in.co.galaxy.assignment.schema.GetShortestPathRequest;
import in.co.galaxy.assignment.schema.GetShortestPathResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import in.co.galaxy.assignment.config.DatasourceBean;
import in.co.galaxy.assignment.config.PersistenceBean;
import in.co.galaxy.assignment.config.WebServiceBean;
import in.co.galaxy.assignment.dao.EdgeDao;
import in.co.galaxy.assignment.dao.TrafficDao;
import in.co.galaxy.assignment.dao.VertexDao;
import in.co.galaxy.assignment.schema.GetShortestPathRequest;
import in.co.galaxy.assignment.schema.GetShortestPathResponse;
import in.co.galaxy.assignment.service.EntityManagerService;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;


/**
 * Created by Kapeshi.Kongolo on 2016/04/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatasourceBean.class, PersistenceBean.class, WebServiceBean.class,
        ShortestPathEndpoint.class, ShortestPathRepository.class, EntityManagerService.class, EdgeDao.class, VertexDao.class,
        TrafficDao.class},
        loader = AnnotationConfigContextLoader.class)
public class ShortestPathEndpointTest {

    @Autowired
    private ShortestPathEndpoint shortestPathEndpoint;

    @Test
    public void verifyThatShortestPathSOAPEndPointIsCorrect() throws Exception {
        // Set Up Fixture
        GetShortestPathRequest shortestPathRequest = new GetShortestPathRequest();
        shortestPathRequest.setName("Moon");

        StringBuilder path = new StringBuilder();
        path.append("Earth (A)\tMoon (B)\t");

        GetShortestPathResponse expectedResponse = new GetShortestPathResponse();
        expectedResponse.setPath(path.toString());

        //Test
        GetShortestPathResponse actualResponse = shortestPathEndpoint.getShortestPath(shortestPathRequest);

        // Verify
        assertThat(actualResponse, sameBeanAs(expectedResponse));
        assertThat(actualResponse.getPath(), sameBeanAs("Earth (A)\tMoon (B)\t"));
    }

}