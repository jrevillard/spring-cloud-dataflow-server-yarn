/*
 * Copyright 2022 the original author or authors.
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

import java.util.List;

import org.springframework.cloud.dataflow.core.AbstractTaskPlatformFactory;
import org.springframework.cloud.dataflow.core.Launcher;
import org.springframework.cloud.deployer.spi.yarn.YarnTaskLauncher;

public class YarnTaskPlatformFactory extends AbstractTaskPlatformFactory<YarnPlatformProperties> {


  private static final String YARN_PLATFORM_TYPE = "Yarn";
  private final YarnPlatformTaskLauncherProperties platformTaskLauncherProperties;
  private final YarnTaskLauncher yarnTaskLauncher;

  public YarnTaskPlatformFactory(YarnPlatformProperties platformProperties,
      YarnTaskLauncher yarnTaskLauncher, boolean schedulesEnabled,
      YarnPlatformTaskLauncherProperties yarnPlatformTaskLauncherProperties) {
    super(platformProperties, YARN_PLATFORM_TYPE);
    this.yarnTaskLauncher = yarnTaskLauncher;
    this.platformTaskLauncherProperties = yarnPlatformTaskLauncherProperties;
    if (schedulesEnabled) {
      logger.error(
          "{} platform cannot be registered: scheduled tasks not supported",
          this.platformType);
      throw new IllegalStateException(this.platformType + " platform cannot be registered:scheduled tasks not supported");
    }
  }

  @Override
  public Launcher createLauncher(String account) {
    Launcher launcher = new Launcher(account, YARN_PLATFORM_TYPE, yarnTaskLauncher);
    launcher.setDescription("Yarn Launcher.. more info later");
    return launcher;
  }

  @Override
  protected List<Launcher> createLaunchers() {
    List<Launcher> launchers = super.createLaunchers();
//    for (String account : this.platformTaskLauncherProperties.getAccounts().keySet()) {
//        try {
//            if (!this.platformProperties.accountExists(account)) {
                launchers.add(createLauncher("default"));
//            }
//        }
//        catch (Exception e) {
//            logger.error("{} platform account [{}] could not be registered: {}",
//                    this.platformType, account, e);
//            throw new IllegalStateException(e.getMessage(), e);
//        }
//    }
    return launchers;
  }

}
