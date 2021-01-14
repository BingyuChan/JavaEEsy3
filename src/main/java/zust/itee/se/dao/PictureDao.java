package zust.itee.se.dao;

import org.springframework.stereotype.Repository;
import zust.itee.se.entity.PictureEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

@Repository
public class PictureDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<PictureEntity> getPicById(int userId) {
        String sql = "from PictureEntity tpicture where userId =?1";
        Query query = entityManager.createQuery(sql);
        query.setParameter(1, userId);
        List<PictureEntity> result = query.getResultList();
        return result;
    }

    public List<PictureEntity> getPicByTime(int userId, Date begin, Date end, int pageNo, int pageSize) {
        String sql = "from PictureEntity tpicture where userId =:userId and uploadTime between ?1 and ?2";
        Query query = entityManager.createQuery(sql);
        query.setParameter("userId", userId);
        query.setParameter(1, begin);
        query.setParameter(2, end);
        query.setFirstResult(pageNo);
        query.setMaxResults(pageSize);
        List resultList = query.getResultList();
        return resultList;
    }

    public List<PictureEntity> getPictureByTime(int userId, Date start, Date endTime) {
        String sql = "from PictureEntity tpicture where userId =?1 and uploadTime between ?2 and ?3";
        Query query = entityManager.createQuery(sql);
        query.setParameter(1, userId);
        query.setParameter(2, start);
        query.setParameter(3, endTime);
        List resultList = query.getResultList();
        return resultList;
    }

    public List<PictureEntity> getRecPicture(int pageNo, int pageSize) {
        String sql = "from PictureEntity tpicture order by uploadTime";
        Query query = entityManager.createQuery(sql);
        query.setFirstResult(pageNo);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public List<PictureEntity> getPicByName(String pictureName) {
        String sql = "from PictureEntity tpicture where tpicture.name like '%" + pictureName + "%'";
        Query query = entityManager.createQuery(sql);
        List<PictureEntity> resultList = query.getResultList();
        return resultList;
    }

    public List<PictureEntity> getPicByTag(String tags) {
        String sql = "from PictureEntity tpicture where tpicture.tags like '%" + tags + "%'";
        Query query = entityManager.createQuery(sql);
        List<PictureEntity> resultList = query.getResultList();
        return resultList;
    }
}
