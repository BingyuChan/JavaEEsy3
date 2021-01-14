package zust.itee.se;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zust.itee.se.dao.ConcernDao;
import zust.itee.se.entity.PictureEntity;
import zust.itee.se.entity.UserEntity;
import zust.itee.se.service.Impl.UserServiceImpl;

import java.sql.Date;
import java.util.*;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Autowired
    ConcernDao concernDao;
    @Autowired
    UserServiceImpl impl;
    @Test
    public void testUser() {
        List<UserEntity> follows = impl.getFollows(3);
        for (UserEntity follow : follows) {
            System.out.println(follow.getId());
        }
    }
    @Test
    public void testPic() {
        List<PictureEntity> followPictures = impl.getUserPictures(1, new Date(119,05,9), new Date(119, 05, 11), 1, 2);
        System.out.println("图片名称:" + followPictures.get(0).getName());
    }

    @Test
    public void testPicByTime() {
        List<PictureEntity> followPictures = impl.getFollowPictures(4, new Date(119, 05, 9), new Date(119, 05, 11), 0, 2);
        for (PictureEntity followPic : followPictures) {
            System.out.println(followPic.getName());
        }
    }

    @Test
    public void testRecentPic() {
        List<PictureEntity> recentPicture = impl.getRecentPicture(0, 2);
        for (PictureEntity pic : recentPicture) {
            System.out.println(pic.getName());
        }
    }

    @Test
    public void testGetUserByName() {
        List<UserEntity> users = impl.findUsersByName("da");
        for (UserEntity user : users) {
            System.out.println(user.getId()+" "+user.getName());
        }
    }

    @Test
    public void test() {
        List<PictureEntity> pictures = impl.findPictureByName("n");
        for (PictureEntity pic : pictures) {
            System.out.println(pic.getName());
        }
    }

    @Test
    public void testGetPicByTag() {
        List<PictureEntity> pictures = impl.findPictureByTag("davinci");
        for (PictureEntity pic : pictures) {
            System.out.println(pic.getName());
        }
    }

    @Test
    public void testBigV() {
        List<UserEntity> bigV = impl.getBigV();
        for (UserEntity tuser : bigV) {
            System.out.println(tuser.getId());
        }
    }
    @Test
    public void testShowAllFollows() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConcernDao concernDao = ac.getBean(ConcernDao.class);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            map.put(i, concernDao.getFans(i));
        }
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        int num = 1;
        for (Map.Entry<Integer, Integer> entry : list) {
            if (num++ <= 10) {
                System.out.println("id为"+entry.getKey() + "的用户拥有" + entry.getValue()+"位粉丝");
//                System.out.println(concernDao.getUserById(entry.getKey()));
            }else break;
        }
    }
    @Test
    public void testGetPicByUserId() {
        List<PictureEntity> followPictures = impl.getFollowPictures(4, 1, 2);
        for (PictureEntity followPic : followPictures) {
            System.out.println(followPic.getName());
        }
    }
}
