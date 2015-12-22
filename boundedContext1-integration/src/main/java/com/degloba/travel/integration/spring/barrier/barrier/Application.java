/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.degloba.travel.integration.spring.barrier.barrier;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Gary Russell
 * @since 4.2
 */


/*
 *  @SpringBootApplication is a convenience annotation that adds all of the following:
	@Configuration tags the class as a source of bean definitions for the application context.
    @EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
    Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.
    @ComponentScan tells Spring to look for other components, configurations, and services in the the hello package, allowing it to find the HelloController.
 */

@SpringBootApplication
@ImportResource("/META-INF/spring/server-context.xml")
public class Application {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext server = SpringApplication.run(Application.class, args);

		// https://github.com/spring-projects/spring-boot/issues/3945
		CachingConnectionFactory connectionFactory = server.getBean(CachingConnectionFactory.class);
		connectionFactory.setPublisherConfirms(true);
		connectionFactory.resetConnection();
		// https://github.com/spring-projects/spring-boot/issues/3945

		ConfigurableApplicationContext client
			= new SpringApplicationBuilder("/META-INF/spring/client-context.xml")
				.web(false)
				.bannerMode(Mode.OFF)
				.run(args);
		RequestGateway requestGateway = client.getBean("requestGateway", RequestGateway.class);
		String request = "A,B,C";
		System.out.println("\n\n++++++++++++ Sending: " + request + " ++++++++++++\n");
		String reply = requestGateway.echo(request);
		System.out.println("\n\n++++++++++++ Replied with: " + reply + " ++++++++++++\n");
		client.close();
		server.close();
		System.exit(0); // AMQP-519
	}


}
