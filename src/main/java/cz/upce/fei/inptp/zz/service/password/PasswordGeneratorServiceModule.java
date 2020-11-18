package cz.upce.fei.inptp.zz.service.password;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Dependency injector module for {@link PasswordDatabaseService} nad {@link JSONPasswordDatabaseService}
 *
 * */
public class PasswordGeneratorServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PasswordGeneratorService.class).to(PasswordSecureGeneratorService.class).in(Singleton.class);
    }
}
