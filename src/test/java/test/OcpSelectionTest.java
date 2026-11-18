package test;

import config.AppConfig;
import metier.IMetier;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

/**
 * Vérifie que seul le changement de configuration modifie le résultat,
 * sans jamais toucher à MetierImpl.
 */
public class OcpSelectionTest {

    @After
    public void cleanup() {
        System.clearProperty("dao.target");
    }

    private double calculAvec(String target) {
        if (target != null) {
            System.setProperty("dao.target", target); // surcharge app.properties
        }
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            return context.getBean(IMetier.class).calcul();
        }
    }

    @Test
    public void testDaoImplParDefaut() {
        // app.properties : dao.target=dao -> 100 * 2
        assertEquals(200.0, calculAvec(null), 0.001);
    }

    @Test
    public void testDao2() {
        assertEquals(300.0, calculAvec("dao2"), 0.001);
    }

    @Test
    public void testDaoFile() {
        assertEquals(360.0, calculAvec("daoFile"), 0.001);
    }

    @Test
    public void testDaoApi() {
        assertEquals(440.0, calculAvec("daoApi"), 0.001);
    }

    @Test
    public void testStrategieAlias() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("alias");
        context.register(AppConfig.class);
        context.refresh();
        assertEquals(440.0, context.getBean(IMetier.class).calcul(), 0.001);
        context.close();
    }
}
