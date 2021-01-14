package zust.itee.se;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zust.itee.se.dao.ConcernDao;
import zust.itee.se.entity.ConcernEntity;
import zust.itee.se.entity.PictureEntity;
import zust.itee.se.entity.UserEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

public class JpaTest {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    @Test
    public void testAdd() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        entityManager = entityManagerFactory.createEntityManager();
//        Tuser tuser = entityManager.find(Tuser.class, 1);
        entityManager.getTransaction().begin();
        UserEntity tuser = new UserEntity();
//        tuser.setPassword("123456");
//        tuser.setName("admin");
//        tuser.setIntro("x");
//        tuser.setGender("x");
//        tuser.setProvince("Zhejiang");
//        tuser.setCity("Hangzhou");
//        tuser.setRegistTime("2019-10-28");
//        tuser.setType("0");
//        tuser.setEmail("admin@qq.com");
//        tuser.setMobile("14275455231");
//        tuser.setQq(53412);
//        tuser.setStatus("1");tuser.setUsername("tomcat");
        tuser.setUsername("admin");
        tuser.setPassword("123");
        tuser.setName("tomMao");
        tuser.setIntro("Server");
        tuser.setGender("unKnown");
        tuser.setProvince("Texas");
        tuser.setCity("Huston");
        tuser.setRegistTime("2019-10-28");
        tuser.setType("1");
        tuser.setEmail("tomcat@foxmail.com");
        tuser.setMobile("12345678901");
        tuser.setQq(543123);
        tuser.setStatus("1");
//
        entityManager.persist(tuser);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testDelete() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        UserEntity tuser = entityManager.find(UserEntity.class, 10);
        entityManager.remove(tuser);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testUpdate() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//        UserEntity tuser = entityManager.find(UserEntity.class, 7);
        UserEntity tuser =entityManager.find(UserEntity.class,15);
        tuser.setGender("unKnown");
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testShowAllUsers() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        entityManager = entityManagerFactory.createEntityManager();
        String sql = "from UserEntity tuser";
        Query query = entityManager.createQuery(sql);
        List<UserEntity> resultList = query.getResultList();
        for (UserEntity result : resultList) {
            System.out.println(result.getName());
        }
    }

    @Test
    public void testAddPicture() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//        PictureEntity tpicture = new PictureEntity("Apple", "IPhone", 1, "phone",
//                "new", new Date(2019-06-10), 1);
        PictureEntity tpicture = new PictureEntity();
        tpicture.setName("monalisa2");
        tpicture.setFname("MONALISA2");
        tpicture.setUserId(5);
        tpicture.setIntro("miracle");
        tpicture.setTags("pic");
        tpicture.setUploadTime("2019-10-28");
        tpicture.setClickNum(0);
        entityManager.persist(tpicture);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testShowAllPictures() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        entityManager = entityManagerFactory.createEntityManager();
        String sql = "from PictureEntity tpicture where userId = 1";
        Query query = entityManager.createQuery(sql);
        List<PictureEntity> resultList = query.getResultList();
        for (PictureEntity result : resultList) {
            System.out.println(result.getName());
        }
    }

    @Test
    public void testAddConcern() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        ConcernEntity   tconcern = new ConcernEntity();
        tconcern.setConcernerId(4);
        tconcern.setConcernedId(5);
        tconcern.setConcernTime("2019-10-28");
        entityManager.persist(tconcern);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testDeleteConcern() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        ConcernEntity tconcern = entityManager.find(ConcernEntity.class, 3);
        entityManager.remove(tconcern);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testShowAllConcerned() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        entityManager = entityManagerFactory.createEntityManager();
        String sql = "from ConcernEntity tconcern where concernerId = 4";
        Query query = entityManager.createQuery(sql);
        List<ConcernEntity> resultList = query.getResultList();
        for (ConcernEntity result : resultList) {
            System.out.println(result.getConcernerUser().getName() +
                    " 的的粉丝为 " +
                    result.getConcernedUser().getName());
        }
    }

    @Test
    public void testShowAllConcerners() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        entityManager = entityManagerFactory.createEntityManager();
        String sql = "from ConcernEntity tconcern where concernerId = 4";
        Query query = entityManager.createQuery(sql);
        List<ConcernEntity> resultList = query.getResultList();
        for (ConcernEntity result : resultList) {
            System.out.println(result.getConcernedUser().getName() +
                    " 关注了 " +
                    result.getConcernerUser().getName());
        }
    }

    @Test
    public void test() {

    }
}
