package com.innovationchef.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Optional;

@Repository
public class DatabaseAccessObject {

    @Value("${connect4.db.test-conn-query}")
    private String testConnQuery;

    private SessionFactory sessionFactory;

    public DatabaseAccessObject(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> void persist(T object) {
        this.sessionFactory.getCurrentSession().save(object);
    }

    public <T extends Serializable, U> Optional<U> fetch(T key, U clazz) {
        return (Optional<U>) this.sessionFactory.getCurrentSession().bySimpleNaturalId(clazz.getClass()).loadOptional(key);
    }

    public <T> T merge(T object) {
        return (T) this.sessionFactory.getCurrentSession().merge(object);
    }

    @Transactional
    public void dbHealthCheck() {
        this.sessionFactory.getCurrentSession().createSQLQuery(testConnQuery).getFirstResult();
    }
}
