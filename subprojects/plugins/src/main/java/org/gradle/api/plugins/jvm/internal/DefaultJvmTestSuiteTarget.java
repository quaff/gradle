/*
 * Copyright 2021 the original author or authors.
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

package org.gradle.api.plugins.jvm.internal;

import org.gradle.api.Buildable;
import org.gradle.api.internal.tasks.AbstractTaskDependency;
import org.gradle.api.internal.tasks.TaskDependencyResolveContext;
import org.gradle.api.internal.tasks.testing.filter.DefaultTestFilter;
import org.gradle.api.internal.tasks.testing.junit.JUnitTestFramework;
import org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestFramework;
import org.gradle.api.plugins.jvm.JunitPlatformTestingFramework;
import org.gradle.api.plugins.jvm.JvmTestSuiteTarget;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.TaskDependency;
import org.gradle.api.tasks.TaskProvider;
import org.gradle.api.tasks.testing.Test;

import javax.inject.Inject;

public abstract class DefaultJvmTestSuiteTarget implements JvmTestSuiteTarget, Buildable {
    private final String name;
    private final TaskProvider<Test> testTask;

    @Inject public DefaultJvmTestSuiteTarget(String name, TaskContainer tasks) {
        this.name = name;

        // Might not always want Test type here?
        testTask = tasks.register(name, Test.class, t -> {
            t.getTestFrameworkProperty().convention(getTestingFramework().map(framework -> {
                if (framework instanceof JunitPlatformTestingFramework) {
                    return new JUnitPlatformTestFramework((DefaultTestFilter) t.getFilter());
                } else {
                    return new JUnitTestFramework(t, (DefaultTestFilter) t.getFilter());
                }
            }));
        });
    }

    @Override
    public String getName() {
        return name;
    }

    public TaskProvider<Test> getTestTask() {
        return testTask;
    }

    @Override
    public TaskDependency getBuildDependencies() {
        return new AbstractTaskDependency() {
            @Override
            public void visitDependencies(TaskDependencyResolveContext context) {
                context.add(getTestTask());
            }
        };
    }
}
