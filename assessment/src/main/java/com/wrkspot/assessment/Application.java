package com.wrkspot.assessment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.support.GenericWebApplicationContext;

@SpringBootApplication
@EnableJpaRepositories("com.wrkspot.assessment")
@EntityScan(basePackages = "com.wrkspot.assessment")
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    static TypeFilter removeModelAndEntitiesFilter() {
        return (MetadataReader mr, MetadataReaderFactory mrf) -> {
            if ((mr.getClassMetadata().hasSuperClass() && mr.getClassMetadata().getSuperClassName().equals(Record.class.getName()))
                    || mr.getClassMetadata().getClassName().endsWith("Model")
                    || mr.getClassMetadata().getClassName().endsWith("Enum") || mr.getClassMetadata().getClassName().endsWith("Entity") || mr.getClassMetadata().getClassName().endsWith("Issues") || mr.getClassMetadata().getClassName().contains("Request") || mr.getClassMetadata().getClassName().contains("Exception")) {
                LOG.info("Ignoring class: {}", mr.getClassMetadata().getClassName());
                return false;
            } else {
                LOG.info("Including class: {}", mr.getClassMetadata().getClassName());
                return true;
            }
        };
    }

    @Bean
    BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext beanRegistry) {
        return beanFactory -> {
            genericApplicationContext(
                    (BeanDefinitionRegistry) ((GenericWebApplicationContext) beanRegistry)
                            .getBeanFactory());
        };
    }

    void genericApplicationContext(BeanDefinitionRegistry beanRegistry) {
        ClassPathBeanDefinitionScanner beanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanRegistry);
        beanDefinitionScanner.addIncludeFilter(removeModelAndEntitiesFilter());
        beanDefinitionScanner.scan("com.wrkspot.assessment");
    }
}