package zust.itee.se.dao;

import org.springframework.stereotype.Repository;
import zust.itee.se.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ConcernDao {

    @PersistenceContext
    EntityManager entityManager;


    public Integer getFans(Integer num) {

        String sql = "from ConcernEntity tconcern where concernedId =:userId";
        Query query = entityManager.createQuery(sql);
        query.setParameter("userId", num);
        List resultList = query.getResultList();
        return resultList.size();
    }

    public UserEntity getUserById(Integer id) {
        String sql = "from UserEntity tuser where id = :userId";
        Query query = entityManager.createQuery(sql);
        query.setParameter("userId", id);
        UserEntity user = (UserEntity) query.getSingleResult();
        return user;
    }
}

