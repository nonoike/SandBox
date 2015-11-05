package exercise19_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public List<Customer> readCustomers(String fileName) throws UnsupportedEncodingException, IOException {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                CsvReader.class.getClassLoader().getResourceAsStream(fileName), "utf-8"))) {
            br.lines().forEach(e -> {
                String[] elements = e.split(",", -1);
                Customer customer = new Customer();
                customer.setFirstName(elements[0]);
                customer.setLastName(elements[1]);
                customer.setEmail(elements[2]);
                customers.add(customer);
            });
        }
        return customers;
    }
}
