package com.bci.evaluation;

import com.bci.evaluation.commons.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class EvaluationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvaluationApplication.class, args);
    }

}
