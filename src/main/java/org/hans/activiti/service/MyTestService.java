package org.hans.activiti.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyTestService {

    public void test() {
      log.info("this is my test");
    }

}
