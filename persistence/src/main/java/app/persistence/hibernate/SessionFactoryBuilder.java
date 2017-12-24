package app.persistence.hibernate;

import app.infra.exception.PersistenceException;
import app.model.entity.geography.Address;
import app.model.entity.geography.City;
import app.model.entity.geography.Coordinate;
import app.model.entity.geography.Station;
import app.model.entity.person.Account;
import app.persistence.hibernate.interceptor.TimestampInterceptor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Component that is responsible for managing
 * Hibernate session factory
 * @author Plotnyk
 * */
public class SessionFactoryBuilder {
    private SessionFactory sessionFactory;

    public SessionFactoryBuilder() {
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(loadProperties()).build();

        MetadataSources sources = new MetadataSources(registry);

        sources.addAnnotatedClass(City.class);
        sources.addAnnotatedClass(Station.class);
        sources.addAnnotatedClass(Coordinate.class);
        sources.addAnnotatedClass(Address.class);
        sources.addAnnotatedClass(Account.class);

        org.hibernate.boot.SessionFactoryBuilder builder = sources.getMetadataBuilder().build().
                getSessionFactoryBuilder().applyInterceptor(new TimestampInterceptor());

        this.sessionFactory = builder.build();
    }

    private Properties loadProperties() {
        try {
            InputStream in = SessionFactoryBuilder.class
                    .getClassLoader().getResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        } catch (IOException e) {
            throw new PersistenceException("Error setting access to the database",e);
        }
    }

    /**
     * Return sigle instance of session  factory
     * @return
     * */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @PreDestroy
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
