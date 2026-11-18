package metier;

import dao.IDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Extension : injection de toutes les implémentations de IDao.
 * Volontairement, cette classe n'implémente pas IMetier : sinon
 * context.getBean(IMetier.class) deviendrait ambigu (2 beans IMetier).
 */
@Service
public class MetierMultiple {
    private final List<IDao> daos;
    private final Map<String, IDao> daoMap;

    public MetierMultiple(List<IDao> daos, Map<String, IDao> daoMap) {
        this.daos = daos;
        this.daoMap = daoMap;
    }

    /** Somme des valeurs de toutes les implémentations. */
    public double calcul() {
        return daos.stream().mapToDouble(IDao::getValue).sum();
    }

    public Map<String, IDao> getDaoMap() {
        return daoMap;
    }
}
