package ru.dsoccer1980.lottery_adviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.lottery_adviser.model.Numbers;

import java.util.List;

@Transactional(readOnly = true)
public interface NumbersRepository extends JpaRepository<Numbers, Integer> {

    @Override
    @Transactional
    Numbers save(Numbers numbers);

    @Override
    List<Numbers> findAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Numbers n WHERE n.drawNumber=:drawNumber")
    int delete(@Param("drawNumber") int drawNumber);

    List<Numbers> findByNumberOrderByDrawNumber(int number);

    Numbers findTopByNumberOrderByDrawNumberDesc(int number);

    List<Numbers> findByNumberBetween(int value, int value2);

}
