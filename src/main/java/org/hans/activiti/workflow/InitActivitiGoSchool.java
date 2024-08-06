package org.hans.activiti.workflow;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitActivitiGoSchool {

    @Resource
    private ProcessEngine processEngine;


    //@PostConstruct
    public void init() {
        log.info("start deployment Go School");
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addClasspathResource("GoSchool.bpmn20.xml");
        Deployment deploy = deploymentBuilder.deploy();

        log.info("deployment Go School id: {}", deploy.getId());
    }
}
