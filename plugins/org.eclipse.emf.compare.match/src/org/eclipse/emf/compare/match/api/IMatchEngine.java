/*******************************************************************************
 * Copyright (c) 2006, 2007, 2008 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.match.api;

import java.util.Map;

import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/* FIXME ResourceSetMatch **CANNOT** be implemented in a match engine! The
 * MatchService needs to parse its extension point and call the accurate match
 * method on each model (distinct extension!!!) for each resource contained
 * by the resource set!
 */ 
/**
 * A Match Engine is responsible for returning a match model from a set of models. The resulting match model
 * is then used to create a diff between the two models.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public interface IMatchEngine {
	/**
	 * This method will compare three {@link EObject}s and their direct content, ignoring the given objects'
	 * siblings and parents for the match.
	 * 
	 * @param leftObject
	 *            Left of the three objects to get compared.
	 * @param rightObject
	 *            Right of the three objects to compare.
	 * @param ancestor
	 *            Common ancestor of the two others.
	 * @param optionMap
	 *            Options to tweak the matching procedure. <code>null</code> or
	 *            {@link Collections#EMPTY_MAP} will result in the default options to be used.
	 * @return {@link MatchModel} for these three objects' comparison.
	 * @throws InterruptedException
	 *             Thrown if the options map specifies a progress monitor, and the comparison gets interrupted
	 *             somehow.
	 * @see MatchOptions
	 * @since 0.8.1
	 */
	MatchModel contentMatch(EObject leftObject, EObject rightObject, EObject ancestor,
			Map<String, Object> optionMap) throws InterruptedException;

	/**
	 * This method will compare two {@link EObject}s and their direct content, ignoring the given objects'
	 * siblings and parents for the match.
	 * 
	 * @param leftObject
	 *            Left of the two objects to get compared.
	 * @param rightRoot
	 *            Right of the two objects to compare.
	 * @param optionMap
	 *            Options to tweak the matching procedure. <code>null</code> or
	 *            {@link Collections#EMPTY_MAP} will result in the default options to be used.
	 * @return {@link MatchModel} for these two objects' comparison.
	 * @throws InterruptedException
	 *             Thrown if the options map specifies a progress monitor, and the comparison gets interrupted
	 *             somehow.
	 * @see MatchOptions
	 * @since 0.8.1
	 */
	MatchModel contentMatch(EObject leftObject, EObject rightRoot, Map<String, Object> optionMap)
			throws InterruptedException;

	/**
	 * This method returns a MatchModel for three models.
	 * 
	 * @param leftRoot
	 *            Left model for the comparison.
	 * @param rightRoot
	 *            Right model for the comparison.
	 * @param ancestor
	 *            Common ancestor of the right and left models.
	 * @param optionMap
	 *            Options to tweak the matching procedure. <code>null</code> or
	 *            {@link Collections#EMPTY_MAP} will result in the default options to be used.
	 * @return The corresponding {@link MatchModel}.
	 * @throws InterruptedException
	 *             Thrown if the options map specifies a progress monitor, and the comparison gets interrupted
	 *             somehow.
	 * @see MatchOptions
	 * @since 0.8.1
	 */
	MatchModel modelMatch(EObject leftRoot, EObject rightRoot, EObject ancestor, Map<String, Object> optionMap)
			throws InterruptedException;

	/**
	 * This method returns a MatchModel for two models.
	 * 
	 * @param leftRoot
	 *            Left model for the comparison.
	 * @param rightRoot
	 *            Right model for the comparison.
	 * @param optionMap
	 *            Options to tweak the matching procedure. <code>null</code> or
	 *            {@link Collections#EMPTY_MAP} will result in the default options to be used.
	 * @return The corresponding {@link MatchModel}.
	 * @throws InterruptedException
	 *             Thrown if the options map specifies a progress monitor, and the comparison gets interrupted
	 *             somehow.
	 * @see MatchOptions
	 * @since 0.8.1
	 */
	MatchModel modelMatch(EObject leftRoot, EObject rightRoot, Map<String, Object> optionMap)
			throws InterruptedException;

	/**
	 * This will be called with each access from the service to the singleton instance of this engine. Clients
	 * should dispose of all caches and recorded information within this method's implementation.
	 */
	void reset();

	/**
	 * This method returns a MatchModel for two resources.
	 * 
	 * @param leftResource
	 *            Left compared resource.
	 * @param rightResource
	 *            Right compared resource.
	 * @param optionMap
	 *            Options to tweak the matching procedure. <code>null</code> or
	 *            {@link Collections#EMPTY_MAP} will result in the default options to be used.
	 * @return The corresponding {@link MatchModel}.
	 * @throws InterruptedException
	 *             Thrown if the options map specifies a progress monitor, and the comparison gets interrupted
	 *             somehow.
	 * @see MatchOptions
	 * @since 0.8.1
	 */
	MatchModel resourceMatch(Resource leftResource, Resource rightResource, Map<String, Object> optionMap)
			throws InterruptedException;

	/**
	 * This method returns a MatchModel for three resources.
	 * 
	 * @param leftResource
	 *            Left compared resource.
	 * @param rightResource
	 *            Right compared resource.
	 * @param ancestorResource
	 *            Common ancestor of the two compared resources.
	 * @param optionMap
	 *            Options to tweak the matching procedure. <code>null</code> or
	 *            {@link Collections#EMPTY_MAP} will result in the default options to be used.
	 * @return The corresponding {@link MatchModel}.
	 * @throws InterruptedException
	 *             Thrown if the options map specifies a progress monitor, and the comparison gets interrupted
	 *             somehow.
	 * @see MatchOptions
	 * @since 0.8.1
	 */
	MatchModel resourceMatch(Resource leftResource, Resource rightResource, Resource ancestorResource,
			Map<String, Object> optionMap) throws InterruptedException;

	/**
	 * This method returns a MatchModel for two resourceSets.
	 * 
	 * @param leftResourceSet
	 *            Left compared resourceSet.
	 * @param rightResourceSet
	 *            Right compared resourceSet.
	 * @param optionMap
	 *            Options to tweak the matching procedure. <code>null</code> or
	 *            {@link Collections#EMPTY_MAP} will result in the default options to be used.
	 * @return The corresponding {@link MatchModel}.
	 * @throws InterruptedException
	 *             Thrown if the options map specifies a progress monitor, and the comparison gets interrupted
	 *             somehow.
	 * @see MatchOptions
	 * @since 0.8.1
	 */
	MatchModel resourceSetMatch(ResourceSet leftResourceSet, ResourceSet rightResourceSet,
			Map<String, Object> optionMap) throws InterruptedException;

	/**
	 * This method returns a MatchModel for three resourceSets.
	 * 
	 * @param leftResourceSet
	 *            Left compared resourceSet.
	 * @param rightResourceSet
	 *            Right compared resourceSet.
	 * @param ancestorResourceSet
	 *            Common ancestor of the two compared resources.
	 * @param optionMap
	 *            Options to tweak the matching procedure. <code>null</code> or
	 *            {@link Collections#EMPTY_MAP} will result in the default options to be used.
	 * @return The corresponding {@link MatchModel}.
	 * @throws InterruptedException
	 *             Thrown if the options map specifies a progress monitor, and the comparison gets interrupted
	 *             somehow.
	 * @see MatchOptions
	 * @since 0.8.1
	 */
	MatchModel resourceSetMatch(ResourceSet leftResourceSet, ResourceSet rightResourceSet,
			ResourceSet ancestorResourceSet, Map<String, Object> optionMap) throws InterruptedException;
}
