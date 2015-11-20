package exercise19_6;

import static exercise19_6.Framework.Database.*;

public class Framework {
    public enum ApplicationServer {
        GLASS_FISH, J_BOSS, TOMCAT
    }

    public enum Database {
        ORACLE, DB2, POSTGRE_SQL, MY_SQL
    }


    public boolean isSupport(ApplicationServer applicationServer, Database database) {
        switch (applicationServer) {
            case GLASS_FISH:
                return true;
            case J_BOSS:
                return database.equals(DB2) || database.equals(POSTGRE_SQL);
            case TOMCAT:
                return database.equals(MY_SQL);
            default:
                throw new IllegalArgumentException();
        }
    }
}
