package org.hans.activiti.listener;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.hans.activiti.service.MyTestService;
import org.springframework.stereotype.Service;


@Slf4j
@Service(value = "hansTaskListener")
public class HansTaskListener implements ExecutionListener {

    @PostConstruct
    public void init() {
        log.info("----------------------");
    }

    @Resource
    private MyTestService myTestService;

    @Override
    public void notify(DelegateExecution execution) {
        log.info("hashCode: {}", this.getClass().hashCode());
        log.info("getId: {}", execution.getId());
        log.info("getEventName: {}", execution.getEventName());
        myTestService.test();

    }
}
