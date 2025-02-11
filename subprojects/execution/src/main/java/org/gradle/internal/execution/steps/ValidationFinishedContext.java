/*
 * Copyright 2020 the original author or authors.
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

package org.gradle.internal.execution.steps;

import com.google.common.collect.ImmutableList;
import org.gradle.api.problems.interfaces.Problem;

public class ValidationFinishedContext extends BeforeExecutionContext {

    private final ImmutableList<Problem> validationProblems;

    public ValidationFinishedContext(BeforeExecutionContext parent, ImmutableList<Problem> validationProblems) {
        super(parent);
        this.validationProblems = validationProblems;
    }

    protected ValidationFinishedContext(ValidationFinishedContext parent) {
        this(parent, parent.getValidationProblems());
    }

    /**
     * Returns the list of validation warnings encountered so far.
     */
    public ImmutableList<Problem> getValidationProblems() {
        return validationProblems;
    }
}
