package zust.itee.se.dao;


import org.springframework.stereotype.Repository;
import zust.itee.se.entity.ConcernEntity;
import zust.itee.se.entity.UserEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDao {
    @PersistenceContext
    EntityManager entityManager;

    public List<ConcernEntity> getFollowById(int userId) {
        String sql = "from ConcernEntity tconcern where tconcern.concernerId = :userId";
        Query query = entityManager.createQuery(sql);
        query.setParameter("userId", userId);
        List resultList = query.getResultList();
        return resultList;
    }

    public UserEntity getUserById(int userId) {
        String sql = "from UserEntity tuser where id = :userId";
        Query query = entityManager.createQuery(sql);
        query.setParameter("userId", userId);
        UserEntity result = (UserEntity) query.getSingleResult();
        return result;
    }

    public List<UserEntity> getUserByName(String userName) {
        String sql = "from UserEntity user where username like '%" + userName + "%'";
        Query query = entityManager.createQuery(sql);
        List<UserEntity> resultList = query.getResultList();
        return resultList;
    }
}
