/*******************************************************************************
 * Copyright (c) 2013, 2017 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *     Christian W. Damus - bug 525286
 *******************************************************************************/
package org.eclipse.emf.compare.diagram.internal.factories;

import com.google.common.base.Predicate;

import java.util.List;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.ReferenceChange;
import org.eclipse.emf.compare.diagram.internal.CompareDiagramUtil;
import org.eclipse.emf.compare.diagram.internal.extensions.DiagramDiff;
import org.eclipse.emf.compare.internal.postprocessor.factories.AbstractChangeFactory;
import org.eclipse.emf.compare.internal.utils.ComparisonUtil;
import org.eclipse.emf.compare.utils.ReferenceUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Factory of diagram difference extensions.
 * 
 * @author <a href="mailto:cedric.notot@obeo.fr">Cedric Notot</a>
 */
public abstract class AbstractDiagramChangeFactory extends AbstractChangeFactory {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.internal.postprocessor.factories.IChangeFactory#create(org.eclipse.emf.compare.Diff)
	 */
	@SuppressWarnings("restriction")
	@Override
	public Diff create(Diff input) {
		Diff ret = super.create(input);
		if (ret instanceof DiagramDiff) {
			setView((DiagramDiff)ret, input);
			((DiagramDiff)ret).setSemanticDiff(getSemanticDiff(input));
		}
		return ret;
	}

	/**
	 * Get the view and set the given extension with it, from the given refining one.
	 * 
	 * @param extension
	 *            The extension to set.
	 * @param refiningDiff
	 *            The difference refining the given extension.
	 * @return The view.
	 */
	public EObject setView(DiagramDiff extension, Diff refiningDiff) {
		EObject view = CompareDiagramUtil.getView(refiningDiff);
		extension.setView(view);
		return view;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.internal.postprocessor.factories.IChangeFactory#getParentMatch(org.eclipse.emf.compare.Diff)
	 */
	@Override
	public Match getParentMatch(Diff input) {
		return getParentMatch(ComparisonUtil.getComparison(input), input);
	}

	/**
	 * Get the match in which the given difference should be added.
	 * 
	 * @param comparison
	 *            The current comparison.
	 * @param input
	 *            The difference to locate.
	 * @return The containing match.
	 */
	public static Match getParentMatch(Comparison comparison, Diff input) {
		if (input.getKind() == DifferenceKind.CHANGE) {
			return comparison.getMatch(getViewContainer(input));
		} else {
			return input.getMatch();
		}
	}

	/**
	 * Get the GMF view which represents a semantic element and containing the given difference.
	 * 
	 * @param input
	 *            The difference.
	 * @return The found view.
	 */
	private static View getViewContainer(Diff input) {
		final Match match = input.getMatch();
		return getViewContainer(match);
	}

	/**
	 * From the one of the matched objects of the given match, it returns the first found parent GMF view
	 * which represents a semantic element.
	 * 
	 * @param match
	 *            The given match.
	 * @return The found view.
	 */
	private static View getViewContainer(Match match) {
		EObject result = match.getLeft();
		if (result == null) {
			result = match.getRight();
		}

		// Be careful: a view can have the element explicitly set to null, which means that
		// it is a free-standing shape. This happens, for example, with GeoShapes and
		// certain views in Papyrus diagrams (such as applied-stereotype comments). These
		// explicitly nulls do not indicate inheritance of semantic element from the parent
		if (!(result instanceof View && result.eIsSet(NotationPackage.Literals.VIEW__ELEMENT))
				&& match.eContainer() instanceof Match) {
			result = getViewContainer((Match)match.eContainer());
		}
		return (View)result;
	}

	/**
	 * From a given diagram difference extension, get the related semantic difference.
	 * 
	 * @param input
	 *            The diagram difference extension.
	 * @return The semantic one.
	 */
	private Diff getSemanticDiff(Diff input) {
		if (input instanceof ReferenceChange && ((ReferenceChange)input).getValue() instanceof View) {
			final View view = (View)((ReferenceChange)input).getValue();
			final Object element = ReferenceUtil.safeEGet(view, NotationPackage.Literals.VIEW__ELEMENT);
			if (element instanceof EObject) {
				final List<Diff> diffs = findCrossReferences(ComparisonUtil.getComparison(input),
						(EObject)element, new Predicate<Diff>() {
							public boolean apply(Diff diff) {
								return diff instanceof ReferenceChange
										&& ((ReferenceChange)diff).getReference().isContainment();
							}
						});
				if (diffs.size() > 0) {
					return diffs.get(0);
				}
			}
		}
		return null;
	}

}
