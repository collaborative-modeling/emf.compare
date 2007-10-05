/*******************************************************************************
 * Copyright (c) 2006, 2007 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.tests.unit.core.fastmap;

import java.util.HashSet;

import junit.framework.TestCase;

import org.eclipse.emf.compare.util.FastMap;

// TODO implement and test equals and hashCode
/**
 * Tests the behavior of {@link FastMap#clear()}, {@link FastMap#clone()}, {@link FastMap#size()} and
 * {@link FastMap#isEmpty()} so that they behave the same as their {@link HashMap} counterparts.
 * 
 * @author Laurent Goubet <a href="mailto:laurent.goubet@obeo.fr">laurent.goubet@obeo.fr</a>
 */
@SuppressWarnings({"unchecked", "nls", })
public class TestMethods extends TestCase {
	/** Input keys for a set map. */
	private static final Object[] KEY_SET = {null, "", "Alize", "Bise", "Boree", "Chinook", "Eurus",
			"Hurricane", "Noroit", "Rafale", "Sirocco", "Tourbillon", "Typhon", new Integer(0), new Long(10),
			new Float(20), new Double(30), new Boolean(false), new HashSet(), 5, 15L, 25f, 35d, '\u00ab',
			true, };

	/** String displayed when an unexpected {@link UnsupportedOperationException} is thrown. */
	private static final String MESSAGE_UNSUPPORTED = "threw an unexpected UnsupportedOperationException.";

	/** Input values for a set map. */
	private static final Object[] VALUE_SET = {null, "", "Aquilon", "Blizzard", "Brise", "Cyclone", "Foehn",
			"Mistral", "Notus", "Simoon", "Suroit", "Tramontane", "Zephyr", new Integer(0), new Long(10),
			new Float(20), new Double(30), new Boolean(true), new HashSet(), 5, 15L, 25f, 35d, '\u00aa',
			false, };

	/** Map that will be used for all these tests. */
	private final FastMap testedMap = new FastMap();

	/**
	 * Tests {@link FastMap#clear()} with sizes growing from 0 to 12 elements. Expects the size to be reverted
	 * back to 0 after each execution of clear().
	 */
	public void testClear() {
		try {
			testedMap.clear();
		} catch (UnsupportedOperationException e) {
			fail("Clear()" + ' ' + MESSAGE_UNSUPPORTED);
		}

		assertEquals("Map hasn't been emptied by clear() operation or size() returns inaccurate result.", 0,
				testedMap.size());
		for (int j = 0; j < KEY_SET.length; j++) {
			assertFalse("Values contained by the map weren't nulled out by clear().", testedMap
					.containsKey(KEY_SET[j]));
			assertNull("Values contained by the map weren't nulled out by clear().", testedMap
					.get(KEY_SET[j]));
		}
	}

	/**
	 * Tests {@link FastMap#clone()} and {@link FastMap#size()}.
	 * <p>
	 * <ul>
	 * Assertions :
	 * <li>Clone() returns non-null map.</li>
	 * <li>Clone() returns an instance of {@link FastMap}.</li>
	 * <li>Original and cloned map both have the same size.</li>
	 * <li>Original and cloned map contain the same mappings.</li>
	 * </ul>
	 * </p>
	 */
	public void testCloneSize() {
		FastMap clonedMap = null;
		try {
			clonedMap = (FastMap)(testedMap.clone());
		} catch (ClassCastException e) {
			fail("Result of clone() was not an instance of FastMap.");
		}

		assertNotNull("Result of clone() was null.", clonedMap);
		// Keeps compiler happy
		assert clonedMap != null;
		assertEquals("Result of clone() hasn't the same size as its original.", testedMap.size(), clonedMap
				.size());

		for (int i = 0; i < KEY_SET.length; i++) {
			assertTrue("Cloned map doesn't contain all the keys of its original.", clonedMap
					.containsKey(KEY_SET[i]));
			assertEquals("Result of clone() hasn't the same mappings as its original.", VALUE_SET[i],
					clonedMap.get(KEY_SET[i]));
		}
	}

	/**
	 * Tests {@link FastMap#isEmpty()}.
	 * <p>
	 * <ul>
	 * Assertions :
	 * <li>Empty map =&gt; isEmpty() returns <code>True</code>.</li>
	 * <li>After put() operation =&gt; isEmpty() returns <code>False</code>.</li>
	 * <li>Cleared map =&gt; isEmpty() returns <code>True</code>.</li>
	 * </ul>
	 * </p>
	 */
	public void testIsEmpty() {
		assertTrue("Method isEmpty() returns false on new map.", new FastMap().isEmpty());
		assertFalse("Method isEmpty() returns true on non-empty map.", testedMap.isEmpty());
		testedMap.clear();
		assertTrue("Unexpected result of isEmpty() on cleared map.", testedMap.isEmpty());
	}

	/**
	 * We cannot really test the output of the toString() method, the main purpose of this test is to ensure
	 * it throws no exception. Result of toString() is expected to be neither <code>null</code> nor empty.
	 */
	public void testToString() {
		for (int i = 0; i < KEY_SET.length; i++) {
			final FastMap map = new FastMap();
			for (int j = 0; j < i; j++) {
				map.put(KEY_SET[j], VALUE_SET[j]);
			}
			final String toString = map.toString();
			assertNotNull("Operation toString() returned null result.", toString);
			assertNotSame("Operation toString() on the map returned empty String.", toString, "");
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() {
		for (int i = 0; i < KEY_SET.length; i++) {
			testedMap.put(KEY_SET[i], VALUE_SET[i]);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() {
		testedMap.clear();
	}
}
