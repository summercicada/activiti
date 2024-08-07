package org.hans.activiti.listener;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.hans.activiti.service.MyTestService;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class HansTaskListener implements TaskListener {

    @Resource
    private MyTestService myTestService;

    @Override
    public void notify(DelegateTask delegateTask) {


        log.info("hashCode: {}", this.getClass().hashCode());
        log.info("getId: {}", delegateTask.getId());
        log.info("getName: {}", delegateTask.getName());
        log.info("getEventName: {}", delegateTask.getEventName());
        log.info("getName: {}", delegateTask.getAssignee());

        delegateTask.setAssignee("天王老子");

        myTestService.test();

    }
}
