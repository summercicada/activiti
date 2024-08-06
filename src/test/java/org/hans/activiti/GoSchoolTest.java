package org.hans.activiti;

import com.google.common.collect.Maps;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Event;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Slf4j
public class GoSchoolTest {


    @Resource
    private ProcessEngine processEngine;

    @Test
    void deployment() {
        log.info("start deployment Go School");
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addClasspathResource("GoSchool.bpmn20.xml");
        deploymentBuilder.name("Go-School-1");
        Deployment deploy = deploymentBuilder.deploy();

        log.info("deployment Go School id: {}", deploy.getId());
    }

    @Test
    void startGoSchool() {

        Map<String, Object> map = Maps.newHashMap();
        map.put("username", "xiaoman");
        map.put("age", 4);

        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("GoSchool", map);

        log.info("processInstance id : {}", processInstance.getId());                   //2501 act_ru_execution->id.
        log.info("processInstance id : {}", processInstance.getProcessDefinitionId());  //GoSchool:1:3
        log.info("processInstance id : {}", processInstance.getActivityId());           // null

    }

    @Test
    void getActive() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ExecutionQuery executionQuery = runtimeService.createExecutionQuery();
        executionQuery.
        for (Event event : processInstanceEvents) {
            log.info("event id : {}", event.getId());           //
        }

    }
}
