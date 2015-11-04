package exercise18_1;

public class Exercise1 {

    public static String toSnakeCase(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }
}
