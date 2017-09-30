/*
 * Copyright 2016 the original author or authors.
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

package org.gradle.internal.component.external.model;

import com.google.common.collect.ImmutableList;
import org.gradle.internal.component.external.descriptor.Artifact;
import org.gradle.internal.component.model.Exclude;

import javax.annotation.Nullable;

public interface MutableIvyModuleResolveMetadata extends MutableModuleComponentResolveMetadata {
    /**
     * {@inheritDoc}
     */
    @Override
    IvyModuleResolveMetadata asImmutable();

    /**
     * Returns the Ivy-like definitions for artifacts of this module. This method is here to allow us to migrate away from the Ivy model and will be removed.
     */
    ImmutableList<Artifact> getArtifactDefinitions();

    /**
     * Returns the Ivy-like excludes of this component. This method is here to allow us to migrate away from the Ivy types and will be removed.
     */
    ImmutableList<Exclude> getExcludes();

    /**
     * Replaces the excludes of this component.
     */
    void setExcludes(Iterable<? extends Exclude> excludes);

    @Nullable
    String getBranch();

    void setBranch(@Nullable String branch);
}
