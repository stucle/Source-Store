package spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommonDbHelper {

    public static String hostName = "jdbc:mysql://localhost:3306/groot?useUnicode=true&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static String defaultDbName = "groot";

    public static String userName="groot";

    public static String password="groot";

    public static Connection getMySQLConnection()
            throws SQLException, ClassNotFoundException     {
        return DriverManager.getConnection(hostName, userName, password);
    }

}
   