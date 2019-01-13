package com.kiwi.resource.customer.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
public class CustomerController {

    @GetMapping("/customers")
    @PreAuthorize("#oauth2.hasScope('customer_read')")
    public String getCustomers() {
        String customers = null;
        try {
            customers = loadFile("customer-repo.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    private static String loadFile(String filename) throws Exception {
        InputStream inputStream = CustomerController.class.getClassLoader().getResourceAsStream(filename);
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }
}
