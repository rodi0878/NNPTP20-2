package cz.upce.fei.inptp.zz.service.json;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Dependency injector module for {@link JSONService} and {@link JSONFileService}.
 *
 * */
public class JSONFileServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(JSONService.class).to(JSONFileService.class).in(Singleton.class);
    }
}
