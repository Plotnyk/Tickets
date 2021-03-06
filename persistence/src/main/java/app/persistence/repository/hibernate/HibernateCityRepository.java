package app.persistence.repository.hibernate;

import app.model.entity.base.AbstractEntity;
import app.model.entity.geography.City;
import app.persistence.hibernate.SessionFactoryBuilder;
import app.persistence.repository.CityRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Plotnyk
 * */
public class HibernateCityRepository implements CityRepository {
    private final SessionFactory sessionFactory;

    @Inject
    public HibernateCityRepository(SessionFactoryBuilder builder) {
        this.sessionFactory = builder.getSessionFactory();
    }

    @Override
    public void save(City city) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(city);
        }
    }

    @Override
    public City findById(int cityId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(City.class,cityId);
        }
    }

    @Override
    public void delete(int cityId) {
        try (Session session = sessionFactory.openSession()) {
            City city = session.get(City.class, cityId);
            if (city != null) {
                session.delete(city);
            }
        }
    }

    @Override
    public List<City> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createCriteria(City.class).list();
        }
    }
}
