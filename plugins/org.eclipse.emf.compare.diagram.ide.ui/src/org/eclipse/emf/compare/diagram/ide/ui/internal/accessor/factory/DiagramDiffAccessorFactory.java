/*******************************************************************************
 * Copyright (c) 2013, 2107 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.diagram.ide.ui.internal.accessor.factory;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.diagram.ide.ui.internal.accessor.DiagramDiffAccessorImpl;
import org.eclipse.emf.compare.diagram.internal.CompareDiagramUtil;
import org.eclipse.emf.compare.diagram.internal.extensions.DiagramDiff;
import org.eclipse.emf.compare.rcp.ui.contentmergeviewer.accessor.legacy.ITypedElement;
import org.eclipse.emf.compare.rcp.ui.internal.contentmergeviewer.accessor.factory.impl.AbstractAccessorFactory;
import org.eclipse.emf.compare.rcp.ui.mergeviewer.IMergeViewer.MergeViewerSide;

/**
 * Factory for graphical differences accessors.
 * 
 * @author <a href="mailto:cedric.notot@obeo.fr">Cedric Notot</a>
 */
public class DiagramDiffAccessorFactory extends AbstractAccessorFactory {

	/**
	 * It creates a graphical accessor from the given selected difference and expected side.
	 * 
	 * @param diff
	 *            The selected difference.
	 * @param side
	 *            The side of the input to create.
	 * @return The accessor.
	 */
	private ITypedElement createAccessor(Diff diff, MergeViewerSide side) {
		return new DiagramDiffAccessorImpl(diff, side);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.rcp.ui.contentmergeviewer.accessor.factory.IAccessorFactory#isFactoryFor(java.lang.Object)
	 */
	public boolean isFactoryFor(Object target) {
		return target instanceof DiagramDiff || concernsDiagram(target);
	}

	/**
	 * Specifies whether the given <code>target</code> is a diff on a match that represents a diagram element.
	 * 
	 * @param target
	 *            The object to test.
	 * @return <code>true</code> if <code>target</code> is a diff on a diagram match, <code>false</code>
	 *         otherwise.
	 */
	private boolean concernsDiagram(Object target) {
		if (target instanceof Diff) {
			Diff diff = (Diff)target;
			return CompareDiagramUtil.isDiagramMatch(diff.getMatch());
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.rcp.ui.contentmergeviewer.accessor.factory.IAccessorFactory#createLeft(org.eclipse.emf.common.notify.AdapterFactory,
	 *      java.lang.Object)
	 */
	public ITypedElement createLeft(AdapterFactory adapterFactory, Object target) {
		return createAccessor((Diff)target, MergeViewerSide.LEFT);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.rcp.ui.contentmergeviewer.accessor.factory.IAccessorFactory#createRight(org.eclipse.emf.common.notify.AdapterFactory,
	 *      java.lang.Object)
	 */
	public ITypedElement createRight(AdapterFactory adapterFactory, Object target) {
		return createAccessor((Diff)target, MergeViewerSide.RIGHT);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.rcp.ui.contentmergeviewer.accessor.factory.IAccessorFactory#createAncestor(org.eclipse.emf.common.notify.AdapterFactory,
	 *      java.lang.Object)
	 */
	public ITypedElement createAncestor(AdapterFactory adapterFactory, Object target) {
		return createAccessor((Diff)target, MergeViewerSide.ANCESTOR);
	}

}
