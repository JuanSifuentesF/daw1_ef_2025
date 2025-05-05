package pe.edu.cibertec.ef_juansifuentes.Config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseConfig {
  @Value("${DATABASE_URL}")
  private String databaseUrl;
  @Value("${DATABASE_USER}")
  private String databaseUser;
  @Value("${DATABASE_PASS}")
  private String databasePassword;
  @Value("${DATABASE_DRIVER}")
  private String dabaseDriver;

  @Bean
  public HikariDataSource hikariDataSource() {

    HikariConfig config = new HikariConfig();
    /**
     * Configurar propiedad de conexion a BD
     */
    config.setJdbcUrl(databaseUrl);
    config.setUsername(databaseUser);
    config.setPassword(databasePassword);
    config.setDriverClassName(dabaseDriver);

    /**
     * Configurar propiedades del pool de HikariCP
     * - MaximumPoolSize: Máximo # de conexiones del pool.
     * - MinimumIdle: Mínimo # de conexiones inactivas del pool.
     * - IdleTimeout: Tiempo máximo de espera para
     *     eliminar una conexión inactiva.
     * - ConnectionTimeout: Tiempo máximo de espera
     *     para conectarse a la BD.
     */

    config.setMaximumPoolSize(20);
    config.setMinimumIdle(5);
    config.setIdleTimeout(300000);
    config.setConnectionTimeout(30000);

    System.out.println("###### HikariCP initialized ######");
    return new HikariDataSource(config);
  }
}
