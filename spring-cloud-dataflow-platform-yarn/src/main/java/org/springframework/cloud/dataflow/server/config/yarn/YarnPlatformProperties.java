/*
 * Copyright 2017-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.dataflow.server.config.yarn;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.dataflow.core.AbstractPlatformProperties;
import org.springframework.cloud.deployer.spi.yarn.YarnDeployerProperties;

@ConfigurationProperties("spring.cloud.dataflow.task.platform.yarn")
public class YarnPlatformProperties extends AbstractPlatformProperties<YarnDeployerProperties> {
  
  private String testPlatformProperties = "testPlatformProperties";

  public String getTestPlatformProperties() {
    return testPlatformProperties;
  }

  public void setTestPlatformProperties(String testPlatformProperties) {
    this.testPlatformProperties = testPlatformProperties;
  }
}
