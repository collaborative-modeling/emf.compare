/*******************************************************************************
 * Copyright (C) 2015, 2018 Obeo.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.compare.egit.internal.merge;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.IModelProviderDescriptor;
import org.eclipse.core.resources.mapping.ModelProvider;
import org.eclipse.core.resources.mapping.RemoteResourceMappingContext;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.resources.mapping.ResourceMappingContext;
import org.eclipse.core.resources.mapping.ResourceTraversal;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.egit.core.Activator;

/**
 * Provides a set of utility functions to traverse and discover the logical models corresponding to workspace
 * resource. Take note that this class' model lookups will ignore a set of ModelProvider that only provide
 * default information with no value regarding "merging" or "comparison".
 *
 * @see #ignoredModelDescriptors
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 * @author <a href="mailto:laurent.delaigue@obeo.fr">Laurent Delaigue</a>
 */
@SuppressWarnings("restriction")
public final class LogicalModels {
	/** ID of the EMFModelProvider. */
	private static final String EMF_MODEL_PROVIDER_ID = "org.eclipse.emf.compare.model.provider"; //$NON-NLS-1$

	/** Cache of the discovered models. */
	private Map<IResource, Set<IResource>> models = new HashMap<>();

	/**
	 * Iterate over the resources in the given set to discover and cache the logical model of each.
	 * <p>
	 * Resources that do not exist locally will be excluded from the lookup to avoid NPEs from the expression
	 * framework (model providers look for the resource's content type, which fails for remote resources). If
	 * these resources _are_ part of a larger model, it will be detected through the "other parts" of said
	 * model.
	 * </p>
	 * <p>
	 * Models provided by the {@link #ignoredModelDescriptors} will be ignored from this lookup.
	 * </p>
	 *
	 * @param resources
	 * @param remoteMappingContext
	 */
	public void build(Set<IResource> resources, RemoteResourceMappingContext remoteMappingContext) {
		for (IResource supervisedResource : resources) {
			if (supervisedResource instanceof IFile && !models.containsKey(supervisedResource)) {
				final Set<IResource> model = discoverModel(supervisedResource, remoteMappingContext);
				for (IResource resourceInModel : model) {
					models.put(resourceInModel, model);
				}
			}
		}
	}

	/**
	 * Returns the model which the given resource is a component of.
	 * <p>
	 * Note that this will always return <code>null</code> unless
	 * {@link #build(Set, RemoteResourceMappingContext)} has been called beforehand.
	 * </p>
	 *
	 * @param resource
	 *            The resource which logical model we need.
	 * @return The logical model previously discovered for this resource through
	 *         {@link #build(Set, RemoteResourceMappingContext)}.
	 */
	public Set<IResource> getModel(IResource resource) {
		return models.get(resource);
	}

	/**
	 * This will check whether the given resource is a part of a logical model as described by the
	 * ModelProviders extension point.
	 *
	 * @param resource
	 * @param mappingContext
	 * @return the model which this resource is a part of.
	 */
	public static Set<IResource> discoverModel(IResource resource, ResourceMappingContext mappingContext) {
		final Set<IResource> model = new LinkedHashSet<>();

		Set<IResource> newResources = new LinkedHashSet<>();
		newResources.add(resource);
		do {
			final Set<IResource> temp = newResources;
			newResources = new LinkedHashSet<>();
			for (IResource res : temp) {
				final Set<ResourceMapping> mappings = getResourceMappings(Collections.singleton(res),
						mappingContext);
				newResources.addAll(collectResources(mappings, mappingContext));
			}
		} while (model.addAll(newResources));

		return model;
	}

	/**
	 * Try and find a model provider that matches the whole given logical model and that provides an adapter
	 * of the specific given type.
	 * <p>
	 * Models providers matching one of the {@link #ignoredModelDescriptors} will be ignored from this lookup.
	 * </p>
	 *
	 * @param model
	 *            The logical model we need a merger for.
	 * @param adapterClass
	 *            Kind of adapter we need.
	 * @return An adapter of the desired type for the provided logical model if any, <code>null</code> if
	 *         none.
	 * @throws CoreException
	 *             Thrown if we cannot query one or more of the model providers.
	 */
	public static <T> T findAdapter(Set<IResource> model, Class<T> adapterClass) throws CoreException {
		if (model.isEmpty()) {
			return null;
		}
		final IResource[] modelArray = model.toArray(new IResource[model.size()]);

		final IModelProviderDescriptor descriptor = getEMFModelProvider();
		if (descriptor != null) {
			final IResource[] matchingResources = descriptor.getMatchingResources(modelArray);
			if (matchingResources.length == modelArray.length) {
				final ModelProvider provider = descriptor.getModelProvider();
				T adapter = (T)provider.getAdapter(adapterClass);
				if (adapter != null) {
					return adapter;
				}
			}
		}

		return null;
	}

	/**
	 * @param model
	 * @param mappingContext
	 * @return all resource mappings related to the IResources of the given model.
	 */
	public static Set<ResourceMapping> getResourceMappings(Set<IResource> model,
			ResourceMappingContext mappingContext) {
		final Set<ResourceMapping> allMappings = new LinkedHashSet<>();
		final IResource[] modelArray = model.toArray(new IResource[model.size()]);

		final IModelProviderDescriptor descriptor = getEMFModelProvider();
		if (descriptor != null) {
			try {
				final IResource[] matchingResources = descriptor.getMatchingResources(modelArray);
				if (matchingResources.length > 0) {
					final ModelProvider modelProvider = descriptor.getModelProvider();
					final ResourceMapping[] modelMappings = modelProvider.getMappings(modelArray,
							mappingContext, new NullProgressMonitor());
					allMappings.addAll(Arrays.asList(modelMappings));
				}
			} catch (CoreException e) {
				Activator.logError(e.getMessage(), e);
			}
		}

		return allMappings;
	}

	/**
	 * Return the ModelProvider to be used
	 * 
	 * @return EMFModelProvider's descriptor
	 */
	private static IModelProviderDescriptor getEMFModelProvider() {
		return ModelProvider.getModelProviderDescriptor(EMF_MODEL_PROVIDER_ID);
	}

	private static Set<IResource> collectResources(Set<ResourceMapping> mappings,
			ResourceMappingContext mappingContext) {
		final Set<IResource> resources = new LinkedHashSet<>();
		for (ResourceMapping mapping : mappings) {
			try {
				final ResourceTraversal[] traversals = mapping.getTraversals(mappingContext,
						new NullProgressMonitor());
				for (ResourceTraversal traversal : traversals) {
					resources.addAll(Arrays.asList(traversal.getResources()));
				}
			} catch (CoreException e) {
				Activator.logError(e.getMessage(), e);
			}
		}
		return resources;
	}
}
