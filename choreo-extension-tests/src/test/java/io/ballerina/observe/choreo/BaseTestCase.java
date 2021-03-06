/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.ballerina.observe.choreo;

import org.ballerinalang.test.context.BalServer;
import org.ballerinalang.test.context.BallerinaTestException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * Parent test class for all extension integration tests cases. This will provide basic
 * functionality for integration tests. This will initialize a single ballerina instance which will be used
 * by all the test cases throughout.
 */
public class BaseTestCase {
    private static final Logger LOGGER = Logger.getLogger(BaseTestCase.class.getName());
    static BalServer balServer;

    @BeforeSuite(alwaysRun = true)
    public void initialize() throws BallerinaTestException {
        balServer = new BalServer();
    }

    @AfterSuite(alwaysRun = true)
    public void destroy() throws IOException {
        Path ballerinaInternalLog = Paths.get(balServer.getServerHome(), "ballerina-internal.log");
        if (Files.exists(ballerinaInternalLog)) {
            LOGGER.severe("=== Ballerina Internal Log Start ===");
            Files.lines(ballerinaInternalLog).forEach(LOGGER::severe);
            LOGGER.severe("=== Ballerina Internal Log End ===");
        }
        balServer.cleanup();
    }
}
