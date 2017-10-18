/*******************************************************************************
 * Copyright (c) 2017 EclipseSource Services GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Philip Langer - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.diagram.internal;

import org.eclipse.emf.compare.AttributeChange;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.ReferenceChange;
import org.eclipse.emf.compare.ResourceAttachmentChange;
import org.eclipse.emf.compare.utils.MatchUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Utilities related to GMF diagrams.
 * 
 * @author Philip Langer <planger@eclipsesource.com>
 */
public final class CompareDiagramUtil {

	/**
	 * Predicate on the namespace URI to create a graphical match accessor and so to be able to open a diagram
	 * comparison on the selection of a graphical match.
	 */
	private static final String NS_URI_PATTERN = "http://www.eclipse.org/gmf/runtime/\\d.\\d.\\d/notation"; //$NON-NLS-1$

	/**
	 * Hidden constructor.
	 */
	private CompareDiagramUtil() {
		// hidden constructor
	}

	/**
	 * Specifies whether the given match represents a diagram element.
	 * <p>
	 * A diagram element is an element that has an EClass of the GMF package.
	 * </p>
	 * 
	 * @param match
	 *            The match to test.
	 * @return <code>true</code> if it is a match of a diagram element, <code>false</code> otherwise.
	 */
	public static boolean isDiagramMatch(Match match) {
		final EObject eObject = getEObjectFromAnySide(match);
		final EPackage ePackage = eObject.eClass().getEPackage();
		return ePackage == NotationPackage.eINSTANCE || ePackage.getNsURI().matches(NS_URI_PATTERN);
	}

	/**
	 * Returns any non-null EObject of the given match, being it left, right, or origin. If all are
	 * <code>null</code>, this method returns <code>null</code>.
	 * 
	 * @param match
	 *            The match to get the EObject from.
	 * @return Any non-null EObject of the given match.
	 */
	private static EObject getEObjectFromAnySide(Match match) {
		if (match.getLeft() != null) {
			return match.getLeft();
		} else if (match.getRight() != null) {
			return match.getRight();
		}
		return match.getOrigin();
	}

	/**
	 * Returns the EObject that represents the view of the given diagram diff.
	 * 
	 * @param diff
	 *            The diff to get the view object from.
	 * @return The view object.
	 */
	public static EObject getView(Diff diff) {
		if (diff instanceof ReferenceChange) {
			return ((ReferenceChange)diff).getValue();
		} else if (diff instanceof AttributeChange) {
			Comparison comparison = diff.getMatch().getComparison();
			EObject view = MatchUtil.getContainer(comparison, diff);
			while (view != null && !(view instanceof View)) {
				view = view.eContainer();
			}
			return view;
		} else if (diff instanceof ResourceAttachmentChange) {
			Comparison comparison = diff.getMatch().getComparison();
			return MatchUtil.getContainer(comparison, diff);
		}
		return null;
	}

}
