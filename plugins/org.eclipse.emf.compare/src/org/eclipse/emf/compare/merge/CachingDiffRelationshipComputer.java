/*******************************************************************************
 * Copyright (c) 2017 EclipseSource Services GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Martin Fleck - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.merge;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.merge.IMerger.Registry;

/**
 * A computer implementation to cache the relationship of diffs. Note that the "all" relationships are not
 * cached because they would O(n^2) memory in the general case.
 * 
 * @since 3.5
 * @author Martin Fleck <mfleck@eclipsesource.com>
 * @see IMerger2
 */
public class CachingDiffRelationshipComputer extends DiffRelationshipComputer {

	/** Direct merge dependencies: right to left. */
	protected Map<Diff, Set<Diff>> directMergeDependenciesR2L = new ConcurrentHashMap<>();

	/** Direct merge dependencies: left to right. */
	protected Map<Diff, Set<Diff>> directMergeDependenciesL2R = new ConcurrentHashMap<>();

	/** Direct resulting merges: right to left. */
	protected Map<Diff, Set<Diff>> directResultingMergesR2L = new ConcurrentHashMap<>();

	/** Direct resulting merges: left to right. */
	protected Map<Diff, Set<Diff>> directResultingMergesL2R = new ConcurrentHashMap<>();

	/** Direct resulting rejections: right to left. */
	protected Map<Diff, Set<Diff>> directResultingRejectionsR2L = new ConcurrentHashMap<>();

	/** Direct resulting rejections: left to right. */
	protected Map<Diff, Set<Diff>> directResultingRejectionsL2R = new ConcurrentHashMap<>();

	/**
	 * Creates a new computer with the given registry.
	 * 
	 * @param registry
	 *            merger registry
	 */
	public CachingDiffRelationshipComputer(IMerger.Registry registry) {
		this(registry, IMergeCriterion.NONE);
	}

	/**
	 * Creates a new computer with the given registry and merge criterion.
	 * 
	 * @param registry
	 *            merger registry
	 * @param criterion
	 *            merge criterion used to get the merger from the registry, use {@link IMergeCriterion#NONE}
	 *            if no special criterion should be set.
	 */
	public CachingDiffRelationshipComputer(IMerger.Registry registry, IMergeCriterion criterion) {
		super(registry, criterion);
	}

	/**
	 * {@inheritDoc} WARNING: Setting the merger registry invalidates previously cached results, if another
	 * registry was set previously!
	 */
	@Override
	public void setMergerRegistry(Registry mergerRegistry) {
		if (getMergerRegistry() != mergerRegistry) {
			super.setMergerRegistry(mergerRegistry);
			invalidate();
		}
	}

	/**
	 * {@inheritDoc} WARNING: Setting the merge criterion invalidates previously cached results, if another
	 * criterion was set previously.
	 */
	@Override
	public void setMergeCriterion(IMergeCriterion mergeCriterion) {
		if (getMergeCriterion() != mergeCriterion) {
			super.setMergeCriterion(mergeCriterion);
			invalidate();
		}
	}

	/**
	 * Caches the given direct merge dependencies.
	 * 
	 * @param diff
	 *            diff
	 * @param mergeRightToLeft
	 *            merge direction
	 * @param directMergeDependencies
	 *            direct merge dependencies of diff
	 */
	protected void setCachedDirectMergeDependencies(Diff diff, boolean mergeRightToLeft,
			Set<Diff> directMergeDependencies) {
		if (mergeRightToLeft) {
			directMergeDependenciesR2L.put(diff, directMergeDependencies);
		} else {
			directMergeDependenciesL2R.put(diff, directMergeDependencies);
		}
	}

	/**
	 * Returns the cached direct merge dependencies.
	 * 
	 * @param diff
	 *            diff
	 * @param mergeRightToLeft
	 *            merge direction
	 * @return cached direct merge dependencies
	 */
	protected Set<Diff> getCachedDirectMergeDependencies(Diff diff, boolean mergeRightToLeft) {
		if (mergeRightToLeft) {
			return directMergeDependenciesR2L.get(diff);
		} else {
			return directMergeDependenciesL2R.get(diff);
		}
	}

	/**
	 * Computes direct merge dependencies for the given diff.
	 * 
	 * @param diff
	 *            diff
	 * @param mergeRightToLeft
	 *            merge direction
	 * @return a non-null set of direct merge dependencies
	 */
	protected Set<Diff> computeDirectMergeDependencies(Diff diff, boolean mergeRightToLeft) {
		return super.getDirectMergeDependencies(diff, mergeRightToLeft);
	}

	/**
	 * Returns the cached direct merge dependencies, if present. Otherwise, the direct merge dependencies are
	 * retrieved and cached using the given merger.
	 * 
	 * @param diff
	 *            diff
	 * @param mergeRightToLeft
	 *            merge direction
	 * @return cached direct merge dependencies
	 * @see IMerger2#getDirectMergeDependencies(Diff, boolean)
	 */
	@Override
	public Set<Diff> getDirectMergeDependencies(Diff diff, boolean mergeRightToLeft) {
		Set<Diff> directMergeDependencies = getCachedDirectMergeDependencies(diff, mergeRightToLeft);
		if (directMergeDependencies == null) {
			directMergeDependencies = computeDirectMergeDependencies(diff, mergeRightToLeft);
			setCachedDirectMergeDependencies(diff, mergeRightToLeft, asEmptySet(directMergeDependencies));
		}
		return directMergeDependencies;
	}

	/**
	 * Caches the given direct resulting merges.
	 * 
	 * @param diff
	 *            diff
	 * @param mergeRightToLeft
	 *            merge direction
	 * @param directResultingMerges
	 *            direct resulting merges
	 */
	protected void setCachedDirectResultingMerges(Diff diff, boolean mergeRightToLeft,
			Set<Diff> directResultingMerges) {
		if (mergeRightToLeft) {
			directResultingMergesR2L.put(diff, directResultingMerges);
		} else {
			directResultingMergesL2R.put(diff, directResultingMerges);
		}
	}

	/**
	 * Returns the cached direct resulting merges.
	 * 
	 * @param diff
	 *            diff
	 * @param mergeRightToLeft
	 *            merge direction
	 * @return cached direct resulting merges
	 */
	protected Set<Diff> getCachedDirectResultingMerges(Diff diff, boolean mergeRightToLeft) {
		if (mergeRightToLeft) {
			return directResultingMergesR2L.get(diff);
		} else {
			return directResultingMergesL2R.get(diff);
		}
	}

	/**
	 * Computes direct resulting merges for the given diff.
	 * 
	 * @param diff
	 *            diff
	 * @param mergeRightToLeft
	 *            merge direction
	 * @return a non-null set of all resulting merges
	 */
	protected Set<Diff> computeDirectResultingMerges(Diff diff, boolean mergeRightToLeft) {
		return super.getDirectResultingMerges(diff, mergeRightToLeft);
	}

	/**
	 * Returns the cached direct resulting merges, if present. Otherwise, the direct resulting merges are
	 * retrieved and cached using the given merger.
	 * 
	 * @param diff
	 *            diff
	 * @param mergeRightToLeft
	 *            merge direction
	 * @return cached direct resulting merges
	 * @see IMerger2#getDirectResultingMerges(Diff, boolean)
	 */
	@Override
	public Set<Diff> getDirectResultingMerges(Diff diff, boolean mergeRightToLeft) {
		Set<Diff> directResultingMerges = getCachedDirectResultingMerges(diff, mergeRightToLeft);
		if (directResultingMerges == null) {
			directResultingMerges = computeDirectResultingMerges(diff, mergeRightToLeft);
			setCachedDirectResultingMerges(diff, mergeRightToLeft, asEmptySet(directResultingMerges));
		}
		return directResultingMerges;
	}

	/**
	 * Caches the given direct resulting rejections.
	 * 
	 * @param diff
	 *            diff
	 * @param mergeRightToLeft
	 *            merge direction
	 * @param directResultingRejections
	 *            direct resulting rejections
	 */
	protected void setCachedDirectResultingRejections(Diff diff, boolean mergeRightToLeft,
			Set<Diff> directResultingRejections) {
		if (mergeRightToLeft) {
			directResultingRejectionsR2L.put(diff, directResultingRejections);
		} else {
			directResultingRejectionsL2R.put(diff, directResultingRejections);
		}
	}

	/**
	 * Returns the cached direct resulting rejections.
	 * 
	 * @param diff
	 *            diff
	 * @param mergeRightToLeft
	 *            merge direction
	 * @return cached direct resulting rejections
	 */
	protected Set<Diff> getCachedDirectResultingRejections(Diff diff, boolean mergeRightToLeft) {
		if (mergeRightToLeft) {
			return directResultingRejectionsR2L.get(diff);
		} else {
			return directResultingRejectionsL2R.get(diff);
		}
	}

	/**
	 * Computes the direct resulting rejections.
	 * 
	 * @param diff
	 *            diff
	 * @param mergeRightToLeft
	 *            merge direction
	 * @return a non-null set of direct resulting rejections
	 */
	protected Set<Diff> computeDirectResultingRejections(Diff diff, boolean mergeRightToLeft) {
		return super.getDirectResultingRejections(diff, mergeRightToLeft);
	}

	@Override
	public Set<Diff> getDirectResultingRejections(Diff diff, boolean mergeRightToLeft) {
		Set<Diff> directResultingRejections = getCachedDirectResultingRejections(diff, mergeRightToLeft);
		if (directResultingRejections == null) {
			directResultingRejections = computeDirectResultingRejections(diff, mergeRightToLeft);
			setCachedDirectResultingRejections(diff, mergeRightToLeft, asEmptySet(directResultingRejections));
		}
		return directResultingRejections;
	}

	/**
	 * Returns the argument or the empty set if the argument is empty.
	 * 
	 * @param diffs
	 *            the diffs to transform.
	 * @return the diffs or the empty set.
	 */
	private Set<Diff> asEmptySet(Set<Diff> diffs) {
		if (diffs.isEmpty()) {
			return Collections.emptySet();
		} else {
			return diffs;
		}
	}

	/**
	 * Computes the cached relationships for the give diff.
	 * 
	 * @param diff
	 *            the diff for which to cmpute the cached relationship.
	 */
	public void computeCache(Diff diff) {
		IMerger2 merger = getMerger(diff);
		if (merger != null) {
			directMergeDependenciesR2L.put(diff, asEmptySet(merger.getDirectMergeDependencies(diff, true)));
			directMergeDependenciesL2R.put(diff, asEmptySet(merger.getDirectMergeDependencies(diff, false)));
			directResultingRejectionsR2L.put(diff,
					asEmptySet(merger.getDirectResultingRejections(diff, true)));
			directResultingRejectionsL2R.put(diff,
					asEmptySet(merger.getDirectResultingRejections(diff, false)));
			directResultingMergesR2L.put(diff, asEmptySet(merger.getDirectResultingMerges(diff, true)));
			directResultingMergesL2R.put(diff, asEmptySet(merger.getDirectResultingMerges(diff, false)));
		}
	}

	/**
	 * Invalidates the complete cache, so that relationships will be re-calculated any diff the next time a
	 * respective method is called.
	 */
	public void invalidate() {
		directMergeDependenciesR2L.clear();
		directMergeDependenciesL2R.clear();
		directResultingRejectionsR2L.clear();
		directResultingRejectionsL2R.clear();
		directResultingMergesR2L.clear();
		directResultingMergesL2R.clear();
	}
}
