/*package com.example;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.example.model.Greeting;
import com.example.model.User;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimerHandler extends AzureSpringBootRequestHandler<User, Greeting> {

	@FunctionName("timedTrigger")
	public void timedTrigger(
			@TimerTrigger(name = "trigger", schedule = "0 *5 * * * *") String timerInfo,
			ExecutionContext context) {

		context.getLogger().info("202003150919-keepAlive Timer is triggered: " + timerInfo);
		// We need to invoke the handleRequest so our spring function (defined as a bean) will be invoked.
		Greeting greeting = handleRequest(new User("Trigger"), context);
		context.getLogger().info("202003150919-keepAlive handleRequest is done");
		context.getLogger().info("202003150919-keepAlive getMessage: " + greeting.getMessage());
	}
}*/
