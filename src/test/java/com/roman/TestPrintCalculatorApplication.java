package com.roman;

import org.springframework.boot.SpringApplication;

public class TestPrintCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.from(PrintCalculatorApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
