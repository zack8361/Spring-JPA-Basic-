package jpabasic.ex1hellojpa.EntityEx.ItemList;

import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.service.EntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private EntityService entityService;


    @Test
    public void movieTest(){
        Movie movie = new Movie();
        movie.setName("센과 치히로");
        movie.setDirector("kong");
        movie.setActor("곽배우");
        movie.setPrice(10000);

        entityService.saveItem(movie);
    }
}