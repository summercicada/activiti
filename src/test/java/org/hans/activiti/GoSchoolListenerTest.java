package org.hans.activiti;

import com.google.common.collect.Maps;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.hans.activiti.listener.HansTaskListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class GoSchoolListenerTest {


    @Resource
    private HansTaskListener hansTaskListener;



    @Resource
    private ProcessEngine processEngine;

    @Test
    void deployment() {
        log.info("start deployment Go School");
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addClasspathResource("GoSchoolListener.bpmn20.xml");
        deploymentBuilder.name("Go-School-listener");
        Deployment deploy = deploymentBuilder.deploy();

        log.info("deployment Go School id: {}", deploy.getId());
    }

    @Test
    void startGoSchool() {

        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("GoSchoolListener", "bk-listener-2");
        log.info("processInstance id : {}", processInstance.getId());                   //2501 act_ru_execution->id.
        log.info("processInstance id : {}", processInstance.getProcessDefinitionId());  //GoSchool:1:3
        log.info("processInstance id : {}", processInstance.getActivityId());           // null
        log.info("processInstance id : {}", processInstance.getProcessVariables());           // null


        runtimeService = processEngine.getRuntimeService();
        processInstance = runtimeService.startProcessInstanceByKey("GoSchoolListener", "bk-listener-2");
        log.info("processInstance id : {}", processInstance.getId());                   //2501 act_ru_execution->id.
        log.info("processInstance id : {}", processInstance.getProcessDefinitionId());  //GoSchool:1:3
        log.info("processInstance id : {}", processInstance.getActivityId());           // null
        log.info("processInstance id : {}", processInstance.getProcessVariables());           // null


        log.info("hansTaskListener hash : {}", hansTaskListener.hashCode());           // null



    }

    @Test
    void deleteDeployment() {

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment("77501", true);
    }

    @Test
    void sampleTest() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.getVariable("35001", "username");
        log.info("runtimeService.getVariable {}", runtimeService.getVariable("35001", "username"));
    }

    @Test
    void suspensionAll() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("GoSchool").singleResult();
        log.info("processDefinition.getId() : {}", processDefinition.getId());
        log.info("status is : {}", processDefinition.isSuspended());

        if (processDefinition.isSuspended()) {
            repositoryService.activateProcessDefinitionById(processDefinition.getId(), true, null);
        } else {
            repositoryService.suspendProcessDefinitionById(processDefinition.getId());
        }


    }


    @Test
    void getActiveTask() {
        TaskService taskService = processEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> list = taskQuery.list();
        for (Task task : list) {
            log.info("task:getId {}", task.getId());// 2507
            log.info("task:getProcessDefinitionId {}", task.getProcessDefinitionId());                    // GoSchool:1:3
            log.info("task:getName {}", task.getName());    // apply
            log.info("task:getDescription {}", task.getDescription());            // 申請入學
            log.info("task:getBusinessKey {}", task.getBusinessKey());            // 申請入學
            log.info("task:isSuspended {}", task.isSuspended());            // 申請入學

        }

    }

    @Test
    void completeApply() {
        TaskService taskService = processEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        Task task = taskQuery.active().taskName("apply").singleResult();
        String taskId = task.getId();
        log.info("task:taskId {}", taskId);
        log.info("task:getBusinessKey {}", task.getBusinessKey());
        log.info("task:getProcessVariables {}", task.getProcessVariables());
        taskService.complete(taskId);


    }
}
