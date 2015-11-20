package exercise19_6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import exercise19_6.Framework.ApplicationServer;
import exercise19_6.Framework.Database;

public class FrameworkTestHelper {
    private static final String SUPPORT_FILE = "19_6.csv";

    public static Boolean isSupport(ApplicationServer server, Database database) throws Exception {
        List<Support> supports = readSupportFile();
        Support support = supports.stream()
                .filter(e -> e.getApplicationServer().equals(server) && e.getDatabase().equals(database))
                .findFirst().get();
        return support.getIsSupport();
    }

    // TODO 毎回読み込む必要はないのでstaticで結果を保持するなど状況に合わせて対応
    private static List<Support> readSupportFile() throws Exception {
        List<Support> supports = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                FrameworkTestHelper.class.getClassLoader().getResourceAsStream(SUPPORT_FILE), "utf-8"))) {
            br.lines().forEach(e -> {
                String[] items = e.split(",");
                ApplicationServer applicationServer = ApplicationServer.valueOf(items[0]);
                Database database = Database.valueOf(items[1]);
                Boolean isSupport = Boolean.valueOf(items[2]);
                supports.add(new Support(applicationServer, database, isSupport));
            });
        }
        return supports;
    }

    public static class Support {
        private ApplicationServer applicationServer;
        private Database database;
        private Boolean isSupport;

        public Support(ApplicationServer applicationServer, Database database, Boolean isSupport) {
            this.applicationServer = applicationServer;
            this.database = database;
            this.isSupport = isSupport;
        }

        public ApplicationServer getApplicationServer() {
            return applicationServer;
        }

        public Database getDatabase() {
            return database;
        }

        public Boolean getIsSupport() {
            return isSupport;
        }
    }
}
