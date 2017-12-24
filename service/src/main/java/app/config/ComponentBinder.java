package app.config;


import app.persistence.repository.CityRepository;
import app.persistence.hibernate.SessionFactoryBuilder;
import app.persistence.repository.StationRepository;
import app.persistence.repository.hibernate.HibernateCityRepository;
import app.persistence.repository.hibernate.HibernateStationRepository;
import app.persistence.repository.inmemory.InMemoryCityRepository;
import app.service.GeographicService;
import app.service.impl.GeographicServiceImpl;
import app.service.transform.Transformer;
import app.service.transform.impl.SimpleDTOTransformer;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

/**
 * Binds bean implementation and implemented interfaces
 * @author Plotnyk
 * */
public class ComponentBinder extends AbstractBinder{

    @Override
    protected void configure() {
        bind(HibernateCityRepository.class).to(CityRepository.class).in(Singleton.class);
        bind(HibernateStationRepository.class).to(StationRepository.class).in(Singleton.class);
        bind(SimpleDTOTransformer.class).to(Transformer.class).in(Singleton.class);
        bind(GeographicServiceImpl.class).to(GeographicService.class).in(Singleton.class);
        bind(SessionFactoryBuilder.class).to(SessionFactoryBuilder.class).in(Singleton.class);
    }
}
