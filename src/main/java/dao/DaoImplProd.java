package dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/** Bean enregistré uniquement quand le profil "prod" est actif. */
@Component("daoProd")
@Profile("prod")
public class DaoImplProd implements IDao {
    @Override
    public double getValue() {
        System.out.println("[DAO] Base de données de production (profil prod)");
        return 100.0;
    }
}
