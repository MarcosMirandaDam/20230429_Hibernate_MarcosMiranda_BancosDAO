
package principal;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Marcos Miranad
 */
public class ZHibernateBancos {

    public static void main(String[] args) {
       // Configurar Hibernate
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        
        
        
        
        
    }
    
}
