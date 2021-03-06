/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.egit.internal.postprocessor;

import java.io.File;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.compare.DifferenceSource;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.ResourceAttachmentChange;
import org.eclipse.emf.compare.diff.DiffBuilder;
import org.eclipse.emf.compare.diff.IDiffProcessor;
import org.eclipse.emf.compare.ide.internal.utils.StoragePathAdapter;
import org.eclipse.emf.compare.postprocessor.IPostProcessor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Post-processor to add potential {@link ResourceAttachmentChange} of kind Move.
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 */
@SuppressWarnings("restriction")
public class EgitPostProcessor implements IPostProcessor {

	/**
	 * The diff processor that will be used by this post-processor. Should be passed by the constructor and
	 * accessed by {@link #getDiffProcessor()}.
	 */
	private IDiffProcessor diffProcessor;

	/**
	 * Default Constructor.
	 */
	public EgitPostProcessor() {
		this(new DiffBuilder());
	}

	/**
	 * Constructor.
	 * 
	 * @param processor
	 *            this instance will be called for each detected difference.
	 */
	public EgitPostProcessor(IDiffProcessor processor) {
		this.diffProcessor = processor;
	}

	/**
	 * {@inheritDoc} .
	 * 
	 * @see org.eclipse.emf.compare.postprocessor.IPostProcessor#postMatch(org.eclipse.emf.compare.Comparison,
	 *      org.eclipse.emf.common.util.Monitor)
	 */
	public void postMatch(Comparison comparison, Monitor monitor) {
		// Nothing to do here for now.

	}

	/**
	 * {@inheritDoc} .
	 * 
	 * @see org.eclipse.emf.compare.postprocessor.IPostProcessor#postDiff(org.eclipse.emf.compare.Comparison,
	 *      org.eclipse.emf.common.util.Monitor)
	 */
	public void postDiff(Comparison comparison, Monitor monitor) {
		for (Match rootMatch : comparison.getMatches()) {
			checkForDifferences(rootMatch, monitor);
		}
	}

	/**
	 * {@inheritDoc} .
	 * 
	 * @see org.eclipse.emf.compare.postprocessor.IPostProcessor#postRequirements(org.eclipse.emf.compare.Comparison
	 *      , org.eclipse.emf.common.util.Monitor)
	 */
	public void postRequirements(Comparison comparison, Monitor monitor) {
		// Nothing to do here for now.

	}

	/**
	 * {@inheritDoc} .
	 * 
	 * @see org.eclipse.emf.compare.postprocessor.IPostProcessor#postEquivalences(org.eclipse.emf.compare.Comparison
	 *      , org.eclipse.emf.common.util.Monitor)
	 */
	public void postEquivalences(Comparison comparison, Monitor monitor) {
		// Nothing to do here for now.

	}

	/**
	 * {@inheritDoc} .
	 * 
	 * @see org.eclipse.emf.compare.postprocessor.IPostProcessor#postConflicts(org.eclipse.emf.compare.Comparison,
	 *      org.eclipse.emf.common.util.Monitor)
	 */
	public void postConflicts(Comparison comparison, Monitor monitor) {
		// Nothing to do here for now.

	}

	/**
	 * {@inheritDoc} .
	 * 
	 * @see org.eclipse.emf.compare.postprocessor.IPostProcessor#postComparison(org.eclipse.emf.compare.Comparison,
	 *      org.eclipse.emf.common.util.Monitor)
	 */
	public void postComparison(Comparison comparison, Monitor monitor) {
		// Nothing to do here for now.

	}

	/**
	 * This will return the diff processor that has been created through {@link #createDiffProcessor()} for
	 * this differencing process.
	 * 
	 * @return The diff processor to notify of difference detections.
	 */
	protected IDiffProcessor getDiffProcessor() {
		return diffProcessor;
	}

	/**
	 * Check all matches.
	 * 
	 * @param match
	 *            The match that is to be checked.
	 * @param monitor
	 *            The monitor to report progress or to check for cancellation.
	 */
	protected void checkForDifferences(Match match, Monitor monitor) {
		checkResourceAttachment(match, monitor);
		for (Match subMatch : match.getSubmatches()) {
			checkForDifferences(subMatch, monitor);
		}
	}

	/**
	 * Checks whether the given {@link Match}'s sides have changed resources of kind Move. This will only be
	 * called for {@link Match} elements referencing the root(s) of an EMF Resource. We also create resource
	 * attachment of kind Move only for non-local comparison. This is why this check is made in the EMf
	 * compare Egit support plugin. The reason is a local comparison between /MyPath/left.model &
	 * /MyPath/right.model, will always result in {@link ResourceAttachmentChange}s of kind Move, and we don't
	 * want to have {@link ResourceAttachmentChange}s in these cases.
	 * 
	 * @param match
	 *            The match that is to be checked.
	 * @param monitor
	 *            The monitor to report progress or to check for cancellation.
	 */
	protected void checkResourceAttachment(Match match, Monitor monitor) {
		final Comparison comparison = match.getComparison();

		if (comparison.getMatchedResources().isEmpty()) {
			// This is a comparison of EObjects, do not go up to the resources
			return;
		}

		final EObject left = match.getLeft();
		final EObject right = match.getRight();

		boolean threeWay = comparison.isThreeWay();
		if (threeWay) {
			final EObject origin = match.getOrigin();
			if (!isLocalComparison(left, origin) && areNotRootsOfSameResource(left, origin)) {
				final String uri = left.eResource().getURI().toString();
				getDiffProcessor().resourceAttachmentChange(match, uri, DifferenceKind.MOVE,
						DifferenceSource.LEFT);
			}
			if (!isLocalComparison(right, origin) && areNotRootsOfSameResource(right, origin)) {
				final String uri = right.eResource().getURI().toString();
				getDiffProcessor().resourceAttachmentChange(match, uri, DifferenceKind.MOVE,
						DifferenceSource.RIGHT);
			}
		} else {
			if (!isLocalComparison(left, right) && areNotRootsOfSameResource(left, right)) {
				final String uri = right.eResource().getURI().toString();
				getDiffProcessor().resourceAttachmentChange(match, uri, DifferenceKind.MOVE,
						DifferenceSource.LEFT);
			}
		}
	}

	/**
	 * Check if the left and right objects are involved in a local comparison.
	 * 
	 * @param left
	 *            the first object to check.
	 * @param right
	 *            the second object to check.
	 * @return true, if it is a local comparison. false otherwise.
	 */
	protected boolean isLocalComparison(EObject left, EObject right) {
		final Resource leftResource;
		final Resource rightResource;
		if (left != null) {
			leftResource = left.eResource();
		} else {
			leftResource = null;
		}
		if (right != null) {
			rightResource = right.eResource();
		} else {
			rightResource = null;
		}

		boolean leftIsLocal = true;
		boolean rightIsLocal = true;
		if (leftResource != null) {
			Adapter leftAdapter = EcoreUtil.getAdapter(leftResource.eAdapters(), StoragePathAdapter.class);
			if (leftAdapter instanceof StoragePathAdapter) {
				leftIsLocal = ((StoragePathAdapter)leftAdapter).isLocal();
			}
		}
		if (rightResource != null) {
			Adapter rightAdapter = EcoreUtil.getAdapter(rightResource.eAdapters(), StoragePathAdapter.class);
			if (rightAdapter instanceof StoragePathAdapter) {
				rightIsLocal = ((StoragePathAdapter)rightAdapter).isLocal();
			}
		}

		if (!leftIsLocal || !rightIsLocal) {
			return false;
		}

		return true;
	}

	/**
	 * Only return false if left and right objects are roots of the "same" resource, i.e. of resources that
	 * match.
	 * 
	 * @param left
	 *            the first object to check.
	 * @param right
	 *            the second object to check.
	 * @return <code>true</code> UNLESS left and right objects are roots of the same resource.
	 */
	protected boolean areNotRootsOfSameResource(EObject left, EObject right) {
		URI leftURI = getDirectResourceURI(left);
		URI rightURI = getDirectResourceURI(right);
		if (leftURI != null && rightURI != null) {
			if (leftURI.equals(rightURI) || isMatchingResourceAndFileURI(leftURI, rightURI)
					|| isMatchingResourceAndFileURI(rightURI, leftURI)) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * Indicate whether the 2 provided URIs reprensent the same resource with different schemes.
	 * 
	 * @param leftURI
	 *            uri that will be checked if it's a platform resource URI
	 * @param rightURI
	 *            uri that will be checked if it's a file URI
	 * @return <code>true</code> if rightURI is a file URI that ends with leftURI.
	 */
	private boolean isMatchingResourceAndFileURI(URI leftURI, URI rightURI) {
		return leftURI.isPlatformResource() && rightURI.isFile() && rightURI.toFileString()
				.replace(File.separatorChar, '/').endsWith(leftURI.toPlatformString(true));
	}

	/**
	 * Provide the URI of the given EObject's direct reource. This will be non-null only if the given EObject
	 * is a root of a resource.
	 * 
	 * @param o
	 *            The EObject
	 * @return The given EObject's direct resource URI, can be null.
	 */
	private URI getDirectResourceURI(EObject o) {
		if (!(o instanceof InternalEObject)) {
			return null; // includes cases where o is null
		}
		final Resource directResource = ((InternalEObject)o).eDirectResource();
		if (directResource != null) {
			return directResource.getURI();
		}
		return null;
	}
}
