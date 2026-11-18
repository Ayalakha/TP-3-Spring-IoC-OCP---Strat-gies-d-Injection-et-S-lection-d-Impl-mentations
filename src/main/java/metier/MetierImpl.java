package metier;

import dao.IDao;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Classe métier : elle ne dépend que de l'interface IDao.
 * Elle ne doit JAMAIS être modifiée pour changer de source de données.
 */
@Service
public class MetierImpl implements IMetier {
    private final IDao dao;

    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    @PostConstruct
    public void init() {
        System.out.println("[TRACE] Implémentation DAO injectée : " + dao.getClass().getSimpleName());
    }

    @Override
    public double calcul() {
        // Logique métier simple : double la valeur
        return dao.getValue() * 2.0;
    }
}
