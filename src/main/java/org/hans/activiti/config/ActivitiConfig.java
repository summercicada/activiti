package org.hans.activiti.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivitiConfig {

    //@Resource
    //private DataSource dataSource;

    @Bean
    public ProcessEngineConfiguration processEngineConfiguration() {
        ProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        configuration.setJdbcUrl("jdbc:postgresql://localhost:5432/beauty");
        configuration.setJdbcUsername("postgres");
        configuration.setJdbcPassword("admin");
        configuration.setJdbcDriver("org.postgresql.Driver");
        //configuration.
        //processEngineConfiguration.setDataSource(dataSource);
        configuration.setDatabaseSchemaUpdate("true");
        return configuration;
    }

    @Bean
    public ProcessEngine processEngine(@Qualifier("processEngineConfiguration") ProcessEngineConfiguration configuration) {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        return processEngine;
    }
}
