/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.springframework.cloud.dataflow.server.yarn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.dataflow.autoconfigure.local.LocalDataFlowServerAutoConfiguration;
import org.springframework.cloud.dataflow.server.EnableDataFlowServer;
import org.springframework.cloud.dataflow.server.config.cloudfoundry.CloudFoundryDataFlowServerConfiguration;
import org.springframework.cloud.dataflow.server.config.cloudfoundry.CloudFoundryTaskPlatformAutoConfiguration;
import org.springframework.cloud.dataflow.server.config.kubernetes.KubernetesTaskPlatformAutoConfiguration;
import org.springframework.cloud.deployer.spi.local.LocalDeployerAutoConfiguration;

/**
 * Bootstrap class for Spring Cloud Data Flow server on Apache Hadoop YARN.
 *
 * @author Mark Fisher
 * @author Janne Valkealahti
 */
@EnableDataFlowServer
@SpringBootApplication(exclude = {ManagementWebSecurityAutoConfiguration.class,
    SecurityAutoConfiguration.class, LocalDataFlowServerAutoConfiguration.class,
    LocalDeployerAutoConfiguration.class, CloudFoundryDataFlowServerConfiguration.class,
    CloudFoundryTaskPlatformAutoConfiguration.class, KubernetesTaskPlatformAutoConfiguration.class})
public class YarnDataFlowServer {

  public static void main(String[] args) {
    SpringApplication.run(YarnDataFlowServer.class, args);
  }
}
