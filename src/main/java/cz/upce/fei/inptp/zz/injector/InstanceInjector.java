package cz.upce.fei.inptp.zz.injector;

import com.google.inject.Guice;
import com.google.inject.Injector;
import cz.upce.fei.inptp.zz.service.crypto.CryptoFileServiceModule;
import cz.upce.fei.inptp.zz.service.json.JSONFileServiceModule;
import cz.upce.fei.inptp.zz.service.password.PasswordDatabaseServiceModule;

/**
 * It provides the dependency injector for project.
 *
 * */
public final class InstanceInjector {

    private InstanceInjector(){
    }

    /**
     * Holder for Instance injector to prevent lazy.
     *
     * */
    private static class InjectorHolder{
        public static final Injector INSTANCE_INJECTOR = Guice.createInjector(
                new PasswordDatabaseServiceModule(),
                new JSONFileServiceModule(),
                new CryptoFileServiceModule()
        );
    }

    /**
     * It will return the single instance of dependency injector.
     *
     * @return dependency injector.
     *
     * */
    public static Injector injector(){
        return InjectorHolder.INSTANCE_INJECTOR;
    }
}
