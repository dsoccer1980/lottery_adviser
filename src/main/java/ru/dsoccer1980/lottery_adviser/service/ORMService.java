package ru.dsoccer1980.lottery_adviser.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


import ru.dsoccer1980.lottery_adviser.model.Numbers;



@Repository
@Transactional
public class ORMService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Numbers> queryFindAllNumbers() {
        System.out.println("ORMService queryFindAllNumbers is called");
        String query = "from Numbers order by drawnumber";
        TypedQuery<Numbers> typedQuery = entityManager.createQuery(query, Numbers.class);
        return typedQuery.getResultList();
    }

}


