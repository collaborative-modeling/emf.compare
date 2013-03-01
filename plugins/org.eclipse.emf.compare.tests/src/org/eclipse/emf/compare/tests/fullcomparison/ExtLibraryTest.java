/**
 * Copyright (c) 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.emf.compare.tests.fullcomparison;

import static com.google.common.base.Predicates.and;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.assertTrue;
import static org.eclipse.emf.compare.utils.EMFComparePredicates.addedToReference;
import static org.eclipse.emf.compare.utils.EMFComparePredicates.changedAttribute;
import static org.eclipse.emf.compare.utils.EMFComparePredicates.changedReference;
import static org.eclipse.emf.compare.utils.EMFComparePredicates.fromSide;
import static org.eclipse.emf.compare.utils.EMFComparePredicates.removed;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Conflict;
import org.eclipse.emf.compare.ConflictKind;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceSource;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.tests.fullcomparison.data.identifier.IdentifierMatchInputData;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Note that part of this is tested in IdentifierComparisonTest, but that class is run with
 * EMFCompareTestRunner. We need more generic tests for the conflicts.
 */
@SuppressWarnings("nls")
public class ExtLibraryTest {
	private IdentifierMatchInputData inputData = new IdentifierMatchInputData();

	@Test
	@Ignore
	public void testConflicts() throws IOException {
		final Resource left = inputData.getExtlibraryLeft();
		final Resource origin = inputData.getExtlibraryOrigin();
		final Resource right = inputData.getExtlibraryRight();

		final IComparisonScope scope = EMFCompare.createDefaultScope(left, right, origin);
		final Comparison comparison = EMFCompare.builder().build().compare(scope);
		final List<Diff> differences = comparison.getDifferences();

		final Predicate<? super Diff> rightAudiovisualName = and(fromSide(DifferenceSource.RIGHT),
				changedAttribute("extlibrary.AudioVisualItem.length", "name", "minutesLength", "minutes"));
		final Predicate<? super Diff> leftAudiovisualName = and(fromSide(DifferenceSource.LEFT),
				changedAttribute("extlibrary.AudioVisualItem.length", "name", "minutesLength", "length"));

		final Diff rightAudiovisualNameDiff = Iterators.find(differences.iterator(), rightAudiovisualName);
		final Diff leftAudiovisualNameDiff = Iterators.find(differences.iterator(), leftAudiovisualName);

		final Conflict audiovisualNameConflict = rightAudiovisualNameDiff.getConflict();
		assertNotNull(audiovisualNameConflict);
		assertSame(Integer.valueOf(2), Integer.valueOf(audiovisualNameConflict.getDifferences().size()));
		assertTrue(audiovisualNameConflict.getDifferences().contains(rightAudiovisualNameDiff));
		assertTrue(audiovisualNameConflict.getDifferences().contains(leftAudiovisualNameDiff));
		assertSame(audiovisualNameConflict, leftAudiovisualNameDiff.getConflict());
		assertSame(ConflictKind.REAL, audiovisualNameConflict.getKind());

		final Predicate<? super Diff> rightRemovedReader = and(fromSide(DifferenceSource.RIGHT),
				removed("extlibrary.BookOnTape.reader"));
		final Predicate<? super Diff> rightUnsetReaderType = and(fromSide(DifferenceSource.RIGHT),
				changedReference("extlibrary.BookOnTape.reader", "eType", "extlibrary.Person", null));
		final Predicate<? super Diff> leftRemovedReader = and(fromSide(DifferenceSource.LEFT),
				removed("extlibrary.BookOnTape.reader"));
		final Predicate<? super Diff> leftUnsetReaderType = and(fromSide(DifferenceSource.LEFT),
				changedReference("extlibrary.BookOnTape.reader", "eType", "extlibrary.Person", null));

		final Diff rightRemovedReaderDiff = Iterators.find(differences.iterator(), rightRemovedReader);
		final Diff rightUnsetReaderTypeDiff = Iterators.find(differences.iterator(), rightUnsetReaderType);
		final Diff leftRemovedReaderDiff = Iterators.find(differences.iterator(), leftRemovedReader);
		final Diff leftUnsetReaderTypeDiff = Iterators.find(differences.iterator(), leftUnsetReaderType);

		final Conflict readerConflict = rightRemovedReaderDiff.getConflict();
		assertNotNull(readerConflict);
		assertSame(Integer.valueOf(4), Integer.valueOf(readerConflict.getDifferences().size()));
		assertTrue(readerConflict.getDifferences().contains(rightRemovedReaderDiff));
		assertTrue(readerConflict.getDifferences().contains(rightUnsetReaderTypeDiff));
		assertTrue(readerConflict.getDifferences().contains(leftRemovedReaderDiff));
		assertTrue(readerConflict.getDifferences().contains(leftUnsetReaderTypeDiff));
		assertSame(readerConflict, rightUnsetReaderTypeDiff.getConflict());
		assertSame(readerConflict, leftRemovedReaderDiff.getConflict());
		assertSame(readerConflict, leftUnsetReaderTypeDiff.getConflict());
		assertSame(ConflictKind.PSEUDO, readerConflict.getKind());

		final Predicate<? super Diff> rightRenamedFamilyname = and(fromSide(DifferenceSource.RIGHT),
				changedAttribute("extlibrary.Person.familyName", "name", "lastName", "familyName"));
		final Predicate<? super Diff> leftRemovedLastname = and(fromSide(DifferenceSource.LEFT),
				removed("extlibrary.Person.lastName"));

		final Diff rightRenamedFamilyNameDiff = Iterators
				.find(differences.iterator(), rightRenamedFamilyname);
		final Diff leftRemovedLastNameDiff = Iterators.find(differences.iterator(), leftRemovedLastname);

		final Conflict familyNameConflict = leftRemovedLastNameDiff.getConflict();
		assertNotNull(familyNameConflict);
		assertSame(Integer.valueOf(2), Integer.valueOf(familyNameConflict.getDifferences().size()));
		assertTrue(familyNameConflict.getDifferences().contains(leftRemovedLastNameDiff));
		assertTrue(familyNameConflict.getDifferences().contains(rightRenamedFamilyNameDiff));
		assertSame(familyNameConflict, rightRenamedFamilyNameDiff.getConflict());
		assertSame(ConflictKind.REAL, familyNameConflict.getKind());

		final Predicate<? super Diff> rightSetPeriodicalSupertype = and(fromSide(DifferenceSource.RIGHT),
				addedToReference("extlibrary.Magazine", "eSuperTypes", "extlibrary.Periodical"));
		final Predicate<? super Diff> rightRemovedTitle = and(fromSide(DifferenceSource.RIGHT),
				removed("extlibrary.Periodical.title"));
		final Predicate<? super Diff> rightUnsetTitleType = and(fromSide(DifferenceSource.RIGHT),
				changedReference("extlibrary.Periodical.title", "eType", "ecore.EString", null));
		final Predicate<? super Diff> leftRemovedPeriodical = and(fromSide(DifferenceSource.LEFT),
				removed("extlibrary.Periodical"));
		final Predicate<? super Diff> leftRemovedTitle = and(fromSide(DifferenceSource.LEFT),
				removed("extlibrary.Periodical.title"));
		final Predicate<? super Diff> leftUnsetTitleType = and(fromSide(DifferenceSource.LEFT),
				changedReference("extlibrary.Periodical.title", "eType", "ecore.EString", null));

		final Diff rightSetPeriodicalSupertypeDiff = Iterators.find(differences.iterator(),
				rightSetPeriodicalSupertype);
		final Diff rightRemovedTitleDiff = Iterators.find(differences.iterator(), rightRemovedTitle);
		final Diff rightUnsetTitleTypeDiff = Iterators.find(differences.iterator(), rightUnsetTitleType);
		final Diff leftRemovedPeriodicalDiff = Iterators.find(differences.iterator(), leftRemovedPeriodical);
		final Diff leftRemovedTitleDiff = Iterators.find(differences.iterator(), leftRemovedTitle);
		final Diff leftUnsetTitleTypeDiff = Iterators.find(differences.iterator(), leftUnsetTitleType);

		final Conflict periodicalConflict = rightSetPeriodicalSupertypeDiff.getConflict();
		assertNotNull(periodicalConflict);
		/*
		 * The test fails. An easy fix is to set the number of diffs in conflicts here to "7" instead of "6".
		 * This is wrong. The conflict detection should not set "all diffs under a containment delete" in
		 * conflict with "the containment delete". See [381143] for details and fix this test accordingly.
		 */
		assertSame(Integer.valueOf(6), Integer.valueOf(periodicalConflict.getDifferences().size()));
		assertTrue(periodicalConflict.getDifferences().contains(rightSetPeriodicalSupertypeDiff));
		assertTrue(periodicalConflict.getDifferences().contains(rightRemovedTitleDiff));
		assertTrue(periodicalConflict.getDifferences().contains(rightUnsetTitleTypeDiff));
		assertTrue(periodicalConflict.getDifferences().contains(leftRemovedPeriodicalDiff));
		assertTrue(periodicalConflict.getDifferences().contains(leftRemovedTitleDiff));
		assertTrue(periodicalConflict.getDifferences().contains(leftUnsetTitleTypeDiff));
		assertSame(ConflictKind.REAL, periodicalConflict.getKind());

		assertSame(Integer.valueOf(4), comparison.getConflicts());
	}
}