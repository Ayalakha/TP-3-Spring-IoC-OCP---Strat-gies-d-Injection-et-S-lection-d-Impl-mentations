package dao;

import org.springframework.stereotype.Component;

/**
 * Stratégie A (@Primary) : pour la tester seule, ajoutez @Primary sur cette classe
 * et désactivez DynamicDaoConfig (un seul bean @Primary est autorisé par type,
 * sinon Spring lève "more than one 'primary' bean found").
 * Dans la version finale, c'est la stratégie C (propriétés) qui est active.
 */
@Component("dao")
public class DaoImpl implements IDao {
    @Override
    public double getValue() {
        System.out.println("[DAO] Lecture depuis la base de données (prod)");
        return 100.0;
    }
}
