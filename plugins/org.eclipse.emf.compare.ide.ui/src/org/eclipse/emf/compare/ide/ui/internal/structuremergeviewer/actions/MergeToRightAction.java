/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.ide.ui.internal.structuremergeviewer.actions;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.ide.ui.internal.EMFCompareIDEUIMessages;
import org.eclipse.emf.compare.ide.ui.internal.EMFCompareIDEUIPlugin;
import org.eclipse.emf.compare.ide.ui.internal.structuremergeviewer.actions.util.EMFCompareUIActionUtil;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Action that manages a merge from left to right of a difference in case of both sides of the comparison are
 * editable.
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 * @since 3.0
 */
public class MergeToRightAction extends AbstractMergeAction {

	/**
	 * Constructor.
	 * 
	 * @param configuration
	 *            The compare configuration object.
	 */
	public MergeToRightAction(CompareConfiguration configuration) {
		super(configuration);
		setToolTipText(EMFCompareIDEUIMessages.getString("merged.to.right.tooltip")); //$NON-NLS-1$
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(EMFCompareIDEUIPlugin.PLUGIN_ID,
				"icons/full/toolb16/merge_to_right.gif")); //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.ide.ui.internal.structuremergeviewer.actions.AbstractMergeAction#copyDiff(org.eclipse.emf.compare.Diff)
	 */
	@Override
	protected void copyDiff(Diff diff) {
		EMFCompareUIActionUtil.copyDiff(diff, true, getConfiguration());
	}

}