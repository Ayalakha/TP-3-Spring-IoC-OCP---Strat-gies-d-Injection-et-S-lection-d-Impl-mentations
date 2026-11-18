import config.AppConfig;
import metier.IMetier;
import metier.MetierMultiple;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Crée le conteneur Spring
        // (profils activables sans recompiler : -Dspring.profiles.active=alias ou prod)
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // Récupère le bean métier et exécute le calcul
        IMetier metier = context.getBean(IMetier.class);
        double resultat = metier.calcul();
        System.out.println("Résultat final : " + resultat);

        // Extension : agrégation de tous les DAO
        MetierMultiple multiple = context.getBean(MetierMultiple.class);
        System.out.println("Beans IDao disponibles : " + multiple.getDaoMap().keySet());
        System.out.println("Somme de tous les DAO : " + multiple.calcul());

        // Ferme le conteneur
        context.close();
    }
}
