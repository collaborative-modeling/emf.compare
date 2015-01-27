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
package org.eclipse.emf.compare.tests.performance.git.large;

import org.eclipse.emf.compare.tests.performance.AbstractEMFComparePerformanceTest;
import org.eclipse.emf.compare.tests.performance.TestMatchId;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import data.models.DataGit;
import data.models.LargeGitInputData;
import data.models.LargeSplitGitInputData;
import fr.obeo.performance.api.PerformanceMonitor;

/**
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLargeGitMatchId extends AbstractEMFComparePerformanceTest {

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.emf.compare.tests.performance.AbstractEMFComparePerformanceTest#setSUTName()
	 */
	@Override
	protected void setSUTName() {
		getPerformance().getSystemUnderTest().setName(TestMatchId.class.getSimpleName());
	}

	@Test
	public void a_matchIdUMLLarge() {
		PerformanceMonitor monitor = getPerformance().createMonitor("matchIdUMLLarge");
		
		final DataGit data = new LargeGitInputData();
		monitor.measure(warmup(), getStepsNumber(), new Runnable() {
			public void run() {
				data.match(UseIdentifiers.ONLY);
			}
		});
		data.dispose();
	}
	
	@Test
	public void b_matchIdUMLLargeSplit() {
		PerformanceMonitor monitor = getPerformance().createMonitor("matchIdUMLLargeSplit");
		
		final DataGit data = new LargeSplitGitInputData();
		monitor.measure(warmup(), getStepsNumber(), new Runnable() {
			public void run() {
				data.match(UseIdentifiers.ONLY);
			}
		});
		data.dispose();
	}
}
