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

package org.springframework.cloud.dataflow.server.yarn;

import java.util.List;

import org.springframework.cloud.dataflow.core.AbstractTaskPlatformFactory;
import org.springframework.cloud.dataflow.core.Launcher;
import org.springframework.cloud.deployer.spi.yarn.YarnTaskLauncher;


public class YarnTaskPlatformFactory extends AbstractTaskPlatformFactory<YarnPlatformProperties> {


  private static final String YARN_PLATFORM_TYPE = "Yarn";

  private final YarnTaskLauncher yarnTaskLauncher;
  private final boolean schedulesEnabled;

  public YarnTaskPlatformFactory(YarnPlatformProperties platformProperties,
      YarnTaskLauncher yarnTaskLauncher, boolean schedulesEnabled) {
    super(platformProperties, YARN_PLATFORM_TYPE);
    this.yarnTaskLauncher = yarnTaskLauncher;
    this.schedulesEnabled = schedulesEnabled;
  }

  @Override
  public Launcher createLauncher(String account) {
    Launcher launcher = new Launcher(account, YARN_PLATFORM_TYPE, yarnTaskLauncher);
    launcher.setDescription("Yarn Launcher.. more info later");
    return launcher;
  }

  @Override
  protected List<Launcher> createLaunchers() {
    String account = "Yarn";
    List<Launcher> launchers = super.createLaunchers();
    if (schedulesEnabled) {
      logger.error(
          "{} platform account [{}] could not be registered: scheduled tasks not supported",
          this.platformType, account);
      throw new IllegalStateException(this.platformType + " platform account [" + account
          + "] could not be registered:scheduled tasks not supported");
    }
    launchers.add(createLauncher(account));
    return launchers;
  }

}
