package org.maksymov.demo.controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.runner.RunWith;
import org.maksymov.demo.DemoApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestExecutionListeners(DbUnitTestExecutionListener.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class AbstractIT {

    protected static final String URL = "http://localhost:8080";
    protected static final Long DEFAULT_ID = 1L;

    protected static final TestRestTemplate REST_TEMPLATE =  new TestRestTemplate();

}
