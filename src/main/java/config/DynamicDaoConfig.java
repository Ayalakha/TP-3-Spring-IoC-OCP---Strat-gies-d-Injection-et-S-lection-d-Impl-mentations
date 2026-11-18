package config;

import dao.DaoApi;
import dao.DaoFile;
import dao.DaoImpl;
import dao.DaoImpl2;
import dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Stratégie C : sélection par la propriété "dao.target".
 * Source : app.properties, surchargeable par -Ddao.target=... ou par la variable
 * d'environnement DAO_TARGET. Active par défaut (sauf avec le profil "alias").
 */
@Configuration
@Profile("!alias")
public class DynamicDaoConfig {

    @Value("${dao.target:dao}")
    private String daoTarget;

    @Bean
    @Primary
    public IDao dynamicDao() {
        switch (daoTarget) {
            case "dao": return new DaoImpl();
            case "dao2": return new DaoImpl2();
            case "daoFile": return new DaoFile();
            case "daoApi": return new DaoApi();
            default: throw new IllegalArgumentException("Cible DAO inconnue: " + daoTarget);
        }
    }
}
