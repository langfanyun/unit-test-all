package com.ebanma.cloud.demo.domain.service;

import com.ebanma.cloud.demo.util.FieldHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;

@RunWith(PowerMockRunner.class)
public class LoggerServiceTest {

    @Mock
    private Logger LOGGER;
    @InjectMocks
    private LoggerService loggerService;

    @Before
    public void setUp() throws Exception {
        FieldHelper.setStaticFinalField(LoggerService.class, "LOGGER", LOGGER);
    }

    @Test
    public void testSaveLog1() {
        loggerService.saveLog(1);
        Mockito.verify(LOGGER).info("执行分支1");
    }

    @Test
    public void testSaveLog2() {
        loggerService.saveLog(2);
        Mockito.verify(LOGGER).info("执行分支2");
    }

    @Test
    public void testSaveLogWithDefault() {
        loggerService.saveLog(3);
        Mockito.verify(LOGGER).info("执行默认分支");
    }
}