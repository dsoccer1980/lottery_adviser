package ru.dsoccer1980.lottery_adviser.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import ru.dsoccer1980.lottery_adviser.model.Numbers;



@Repository
@Transactional(readOnly = true)
public class ORMService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Numbers> queryFindAllNumbers() {
        System.out.println("ORMService queryFindAllNumbers is called");
        String query = "from Numbers order by drawnumber";
        TypedQuery<Numbers> typedQuery = entityManager.createQuery(query, Numbers.class);
        return typedQuery.getResultList();
    }

    @Transactional
    public void ormAddNumbers(List<Integer> list) {
        System.out.println("ORMService ormAddNumbers is called");
        Numbers numbers = new Numbers(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6));
        entityManager.persist(numbers);
    }
}


