package util;

import pasw.Contrasenya;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolConexiones {
    private static final HikariDataSource dataSource;

    // Inicializamos el pool
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/academia");  // URL BBDD
        config.setUsername("root");                                 // Usuario
        config.setPassword(Contrasenya.pasw);                       // Contraseña
        config.setMaximumPoolSize(10);                              // Máximo de conexiones en el pool
        config.setMinimumIdle(2);                                   // Conexiones mínimas inactivas
        config.setIdleTimeout(30000);                               // Tiempo máximo de inactividad (ms)
        config.setConnectionTimeout(20000);                         // Tiempo de espera para obtener conexión (ms)
        config.setLeakDetectionThreshold(60000);                    // Detecta conexiones que no se cierran

        dataSource = new HikariDataSource(config);
    }

    // Método para obtener una conexión del pool
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Cerrar el pool al terminar la aplicación
    public static void cerrarPool() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}