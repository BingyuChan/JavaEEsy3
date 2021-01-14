package zust.itee.se.service;

import zust.itee.se.entity.PictureEntity;
import zust.itee.se.entity.UserEntity;

import java.sql.Date;
import java.util.List;

public interface UserServiceI {
    List<UserEntity> getBigV();
    List<PictureEntity> getUserPictures(int userId, int pageNo, int pageSize);
    List<PictureEntity> getUserPictures(int userId, Date start, Date endTime, int pageNo, int pageSize);
    List<UserEntity> getFollows(int userId);
    List<PictureEntity> getFollowPictures(int userId, int pageNo, int pageSize);
    List<PictureEntity> getFollowPictures(int userId, Date start, Date endTime, int pageNo, int pageSize);
    List<PictureEntity> getRecentPicture(int pageNo, int pageSize);
    List<UserEntity> findUsersByName(String userName);
    List<PictureEntity> findPictureByName(String pictureName);
    List<PictureEntity> findPictureByTag(String tagName);
}
