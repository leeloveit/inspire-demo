package com.wgs.parrot.延时消息.时间轮;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@Endpoint(id = "time-wheel-endpoint")
public class TimeWheelEndpoint {

    @Autowired
    private TimeWheel timeWheel;


    @ReadOperation
    public Map<String, String> listTaskInfo() {
        Map<String, String> map = new HashMap<>();

        ExecutorService executorService = timeWheel.getExecutorService();
        map.put("completedTasks", String.valueOf(((ThreadPoolExecutor) executorService).getCompletedTaskCount()));
        map.put("totalTasks", String.valueOf(((ThreadPoolExecutor) executorService).getTaskCount()));
        map.put("corePoolSize", String.valueOf(((ThreadPoolExecutor) executorService).getCorePoolSize()));

        return map;
    }
}
