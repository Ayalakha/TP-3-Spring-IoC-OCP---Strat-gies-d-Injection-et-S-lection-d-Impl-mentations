package config;

import dao.DaoApi;
import dao.IDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Stratégie B : bean alias. Active uniquement avec le profil "alias"
 * pour ne pas entrer en conflit avec le @Primary de DynamicDaoConfig.
 */
@Configuration
@Profile("alias")
public class DaoAliasConfig {

    @Bean
    @Primary
    public IDao daoAlias() {
        // Pointe vers l'implémentation API (remplacer par new DaoFile(), etc.)
        return new DaoApi();
    }
}
