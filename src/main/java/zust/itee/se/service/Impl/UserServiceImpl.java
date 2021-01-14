package zust.itee.se.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import zust.itee.se.dao.ConcernDao;
import zust.itee.se.dao.PictureDao;
import zust.itee.se.dao.UserDao;
import zust.itee.se.entity.ConcernEntity;
import zust.itee.se.entity.PictureEntity;
import zust.itee.se.entity.UserEntity;
import zust.itee.se.service.UserServiceI;

import java.sql.Date;
import java.util.*;

@Component
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserServiceImpl implements UserServiceI {
    @Autowired
    UserDao userDao;
    @Autowired
    PictureDao pictureDao;
    @Autowired
    ConcernDao concernDao;
    @Autowired
    UserServiceImpl userService;

    public UserServiceImpl() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }@Override
    public List<UserEntity> getBigV() {
//        System.out.println(concernDao.getFans(7));
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 13; i++) {
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
        ArrayList<UserEntity> tusers = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            if (num++ <= 10) {
//                System.out.println(entry.getKey() + "号有粉丝数" + entry.getValue());
//                System.out.println(concernDao.getUserById(entry.getKey()));
                tusers.add(concernDao.getUserById(entry.getKey()));
            }else break;
        }
        return tusers;
//        return null;
    }

    @Override
    public List<PictureEntity> getUserPictures(int userId, int pageNo, int pageSize) {
        List<PictureEntity> pictures = pictureDao.getPicById(userId);
        List<PictureEntity> tpicture = pictures.subList(((pageNo - 1) * pageSize + 1), pageNo * pageSize);
        return tpicture;
    }

    @Override
    public List<PictureEntity> getUserPictures(int userId, java.sql.Date start,
                                              java.sql.Date endTime, int pageNo, int pageSize) {
        System.out.println(new java.sql.Date(119, 05, 9));
        List<PictureEntity> tpictureByTime = pictureDao.getPictureByTime(1, start, endTime);
        return tpictureByTime;
    }

    @Override
    public List<UserEntity> getFollows(int userId) {
        List<ConcernEntity> follows = userDao.getFollowById(userId);
        List<UserEntity> userFollows = new ArrayList<>();
        for (ConcernEntity follow : follows) {
            UserEntity followUser = userDao.getUserById(follow.getConcernedId());
            userFollows.add(followUser);
        }
        return userFollows;
    }

    @Override
    public List<PictureEntity> getFollowPictures(int userId, int pageNo, int pageSize) {
        List<UserEntity> follows = userService.getFollows(userId);
        ArrayList<PictureEntity> tpictures = new ArrayList<>();
        for (UserEntity follow : follows) {
            List<PictureEntity> pictureById = pictureDao.getPicById(follow.getId());
            tpictures.addAll(pictureById);
        }
        List<PictureEntity> subList = tpictures.subList(((pageNo - 1) * pageSize + 1), pageNo * pageSize);
        return subList;
    }

    @Override
    public List<PictureEntity> getFollowPictures(int userId, java.sql.Date start, Date endTime, int pageNo, int pageSize) {
        List<UserEntity> follows = userService.getFollows(userId);
        ArrayList<PictureEntity> tpictures = new ArrayList<>();
        for (UserEntity follow : follows) {
//            List<Tpicture> pictureById = pictureDao.getPicById(follow.getId());
            List<PictureEntity> picByTime = pictureDao.getPicByTime(follow.getId(), start, endTime, pageNo, pageSize);
            tpictures.addAll(picByTime);
        }
        return tpictures;
    }

    @Override
    public List<PictureEntity> getRecentPicture(int pageNo, int pageSize) {
        List<PictureEntity> recentlyPic = pictureDao.getRecPicture(pageNo, pageSize);
        return recentlyPic;
    }

    @Override
    public List<UserEntity> findUsersByName(String userName) {
        List<UserEntity> userByName = userDao.getUserByName(userName);
        return userByName;
    }

    @Override
    public List<PictureEntity> findPictureByName(String pictureName) {
        List<PictureEntity> pictureByName = pictureDao.getPicByName(pictureName);
        return pictureByName;
    }

    @Override
    public List<PictureEntity> findPictureByTag(String tagName) {
        List<PictureEntity> pictureByTag = pictureDao.getPicByTag(tagName);
        return pictureByTag;
    }
}