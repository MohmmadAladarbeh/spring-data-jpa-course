package com.example.demo.impl;


import com.example.demo.data.City;
import com.example.demo.repositories.CityCustomRepository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * 'EntityManager'
        is a key interface in the Java Persistence API (JPA), which is part of the Java EE (Enterprise Edition)
        and Jakarta EE (formerly Java EE) specifications. It's used to interact with the persistence context,
        which represents the set of managed entities (objects) that are currently associated with the underlying database.
 */

/**
 * 'TransactionTemplate'
        is a utility class provided by the Spring Framework that simplifies transaction management in Spring applications.
        It allows you to execute a series of operations within a transactional context programmatically,
        without the need for explicit transaction management code.

        2) Perform any transactional operations, such as database updates or queries.
        The transaction will be automatically committed or rolled back based on the outcome of the operations.
 */

public class CityCustomRepositoryImpl implements CityCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;

    public CityCustomRepositoryImpl(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }


    @Override
    public City findCityById(Long cityId) {
        try{
            City city = (City) entityManager.createNativeQuery("SELECT * FROM CITY WHERE ID=:cityId", City.class)
                    .setParameter("cityId", cityId)
                    .getSingleResult();
            return city;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("fetched data from db");
        return null;
    }

    @Override
    public List<City> getAllCities() {

        try{
            List<City> citiesList = entityManager.createNativeQuery("SELECT * FROM CITY", City.class).getResultList();
            return citiesList;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean createCity(City city) {

        try{

            /**
             * We wrap the query execution with transactionTemplate.execute() method.
             * Because for any update/delete query we need to use transaction.
             */
            transactionTemplate.execute(transactionStatus -> {
                entityManager.createNativeQuery("INSERT INTO CITY (title,body) VALUES (:title, :body)")
                        .setParameter("title", city.getTitle())
                        .setParameter("body", city.getBody())
                        .executeUpdate();
                transactionStatus.flush();
                return null;
            });
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
