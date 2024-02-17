package com.example.demo.repositories;

import com.example.demo.data.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Here we extend from JpaRepository & CityCustomRepository by this way we can use both
 * JpaRepository methods & EntityManager methods. Or we can only extend JpaRepository if we don't want to use EntityManager native queries.
 *
 */

/**
 * @Query: we can write native sql query in JpaRepository with using @Query notation.
 */

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    /**
     * Note: we have to use the name of class not the name of database table
     */

    @Query("select c from City c where c.title like %:text%")
    List<City> findCityByTitle (String text);

    /**
     * Note:
     */
    @Modifying
    @Query("UPDATE City c SET c.title = :title, c.body = :body WHERE c.id = :cityId")
    int update(String title, String body, Long cityId);
}
