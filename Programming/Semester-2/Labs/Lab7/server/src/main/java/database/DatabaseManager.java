package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        try {
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
                if (input == null) throw new RuntimeException("database.properties not found");
                props.load(input);
            }
            String url = String.format("jdbc:postgresql://%s:%s/%s",
                    props.getProperty("db.host"),
                    props.getProperty("db.port"),
                    props.getProperty("db.name"));
            connection = DriverManager.getConnection(url,
                    props.getProperty("db.user"),
                    props.getProperty("db.password"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) instance = new DatabaseManager();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}