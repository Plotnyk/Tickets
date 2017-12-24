package app.persistence.repository.hibernate;

import app.model.entity.geography.City;
import app.model.entity.geography.Station;
import app.model.search.criteria.StationCriteria;
import app.persistence.hibernate.SessionFactoryBuilder;
import app.persistence.repository.StationRepository;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class HibernateStationRepository implements StationRepository {

    private final SessionFactory sessionFactory;

    @Inject
    public HibernateStationRepository(SessionFactoryBuilder builder) {
        sessionFactory = builder.getSessionFactory();
    }

    @Override
    public List<Station> findAllByCriteria(StationCriteria stationCriteria) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Station.class);

            if (stationCriteria.getTransportType() != null) {
                criteria.add(Restrictions.eq(Station.FIELD_TRANSPORT_TYPE,  stationCriteria.getTransportType()));
            }

            if (!StringUtils.isEmpty(stationCriteria.getName())) {
                criteria = criteria.createCriteria(Station.FIELD_CITY);
                criteria.add(Restrictions.eq(City.FIELD_NAME,  stationCriteria.getName()));
            }

            return criteria.list();
        }
    }
}
