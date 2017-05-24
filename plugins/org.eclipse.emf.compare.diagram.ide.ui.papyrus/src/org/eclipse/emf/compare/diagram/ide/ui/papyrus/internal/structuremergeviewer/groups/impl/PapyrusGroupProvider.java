/*******************************************************************************
 * Copyright (c) 2017 EclipseSource Services GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Tobias Ortmayr - initial API and implementation
 *******************************************************************************/

package org.eclipse.emf.compare.diagram.ide.ui.papyrus.internal.structuremergeviewer.groups.impl;

import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Collections2.filter;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.MatchResource;
import org.eclipse.emf.compare.diagram.ide.ui.papyrus.contentmergeviewer.facet.PapyrusFacetContentProviderWrapper;
import org.eclipse.emf.compare.diagram.ide.ui.papyrus.internal.context.PapyrusContextUtil;
import org.eclipse.emf.compare.rcp.ui.internal.structuremergeviewer.groups.impl.BasicDifferenceGroupImpl;
import org.eclipse.emf.compare.rcp.ui.internal.structuremergeviewer.nodes.MatchNode;
import org.eclipse.emf.compare.rcp.ui.mergeviewer.IMergeViewer.MergeViewerSide;
import org.eclipse.emf.compare.rcp.ui.structuremergeviewer.groups.AbstractDifferenceGroupProvider;
import org.eclipse.emf.compare.rcp.ui.structuremergeviewer.groups.IDifferenceGroup;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.tree.TreeFactory;
import org.eclipse.emf.edit.tree.TreeNode;
import org.eclipse.gmf.runtime.notation.Diagram;

/**
 * This implementation of a
 * {@link org.eclipse.emf.compare.rcp.ui.structuremergeviewer.groups.IDifferenceGroupProvider} will be used to
 * group the differences of Papyrus models.
 * 
 * @author Tobias Ortmayr <tortmayr.ext@eclipsesource.com>
 */
@SuppressWarnings("restriction")
public class PapyrusGroupProvider extends AbstractDifferenceGroupProvider {

	/**
	 * Specialized {@link BasicDifferenceGroupImpl} for Papyrus models.
	 * 
	 * @author Tobias Ortmayr <tortmayr.ext@eclipsesource.com>
	 */
	public static class PapyrusDifferenceGroup extends BasicDifferenceGroupImpl {

		/** Map for retrieving the corresponding TreeNode of a model element. **/
		private Map<EObject, TreeNode> valueToMatchNode;

		/** Map for retrieving the context UML element of a Diagram. **/
		private HashMap<Diagram, EObject> diagramToContextualParent;

		/**
		 * {@inheritDoc}.
		 * 
		 * @see org.eclipse.emf.compare.rcp.ui.internal.structuremergeviewer.groups.impl.BasicDifferenceGroupImpl#BasicDifferenceGroupImpl(org.eclipse.emf.compare.Comparison)
		 */
		public PapyrusDifferenceGroup(Comparison comparison, ECrossReferenceAdapter crossReferenceAdapter) {
			super(comparison, Predicates.<Diff> alwaysTrue(), crossReferenceAdapter);
			valueToMatchNode = new HashMap<EObject, TreeNode>();
			diagramToContextualParent = new HashMap<Diagram, EObject>();
			retrieveContextualDiagramParents(MergeViewerSide.LEFT);
			retrieveContextualDiagramParents(MergeViewerSide.RIGHT);
		}

		/**
		 * Makes use of the PapyrusFacetContentProviderWrapper to retrieve the contextual UML element for each
		 * Diagram of the Comparison.
		 * 
		 * @param side
		 *            Defines the comparison side for retrieving the elements
		 */
		private void retrieveContextualDiagramParents(MergeViewerSide side) {
			ResourceSet resourceSet = getResourceSet(getComparison(), side);
			if (resourceSet != null) {
				PapyrusFacetContentProviderWrapper facetContentProviderWrapper = new PapyrusFacetContentProviderWrapper(
						null, resourceSet);
				for (Object root : facetContentProviderWrapper.getElements(null)) {
					retrieveDiagramParents(root, facetContentProviderWrapper);
				}
			}
		}

		/**
		 * Recursively traverses the UML mode tree and maps all diagrams and their contextual parent.
		 * 
		 * @param object
		 *            the child Object
		 * @param facetContentProviderWrapper
		 *            the {@link PapyrusFacetContentProviderWrapper}
		 */
		private void retrieveDiagramParents(Object object,
				PapyrusFacetContentProviderWrapper facetContentProviderWrapper) {
			for (Object child : facetContentProviderWrapper.getChildren(object)) {
				if (child instanceof Diagram) {
					if (!diagramToContextualParent.containsKey(child)) {
						diagramToContextualParent.put((Diagram)child, (EObject)object);
					}
				}
				retrieveDiagramParents(child, facetContentProviderWrapper);
			}
		}

		@Override
		protected List<TreeNode> buildMatchTrees() {
			final List<TreeNode> matchTrees = new ArrayList<TreeNode>();

			final Collection<Match> matches = getComparison().getMatches();
			/*
			 * Diagram matches rely on information of other match trees for proper grouping (valueToMatchNode
			 * map needs to be populated ). So first all match trees for non-diagram matches are built and the
			 * diagram match sub trees are built separately in a second iteration.
			 */
			for (Match match : filter(matches, not(isMatchValueInstanceOf(Diagram.class)))) {
				MatchNode matchNode = buildTree(match);
				if (matchNode != null) {
					matchTrees.add(matchNode);
				}
			}

			for (Match match : filter(matches, isMatchValueInstanceOf(Diagram.class))) {
				TreeNode diagramNode = buildTree(match);
				if (diagramNode != null) {
					TreeNode parentNode = retrieveParentNode(diagramNode, getParent(getMatchValue(match)));
					parentNode.getChildren().add(diagramNode);
					diagramNode.setParent(parentNode);
				}
			}

			return matchTrees;
		}

		/**
		 * Creates a predicate for determining if the associated EObject of a Match is an instance of the
		 * given class.
		 * 
		 * @param clazz
		 *            the Class
		 * @return the created Predicate
		 */
		private Predicate<Match> isMatchValueInstanceOf(final Class<?> clazz) {
			return new Predicate<Match>() {
				public boolean apply(Match match) {
					return clazz.isInstance(getMatchValue(match));
				}
			};
		}

		/**
		 * Creates a new TreeNode.
		 * 
		 * @param data
		 *            data object of the node
		 * @return created TreeNode
		 */
		private TreeNode createTreeNode(EObject data) {
			TreeNode node = TreeFactory.eINSTANCE.createTreeNode();
			node.setData(data);
			return node;
		}

		/**
		 * Determines the parent node of the given TreeNode. If the parent node doesn't exist yet the
		 * corresponding node and (sub) tree is created and merged into the match tree.
		 * 
		 * @param childNode
		 *            the child TreeNode
		 * @param parentObject
		 *            the data object of the parent node
		 * @return (new) parent TreeNode
		 */
		private TreeNode retrieveParentNode(TreeNode childNode, EObject parentObject) {
			TreeNode parentNode = getOrCreateParentNode(childNode, parentObject);
			if (!(parentNode instanceof MatchNode)) {
				// the parent node is a newly created node -> the corresponding subtree has to be created
				createSubTree(parentNode, getParent(parentObject));
			}
			return parentNode;

		}

		/**
		 * Retrieves the parent node of given TreeNode. If no matching node exists in the match tree a new
		 * node is created
		 * 
		 * @param childNode
		 *            the child TreeNode
		 * @param parentObject
		 *            (new) parent TreeNode
		 * @return (new) parent TreeNode
		 */
		private TreeNode getOrCreateParentNode(TreeNode childNode, EObject parentObject) {
			TreeNode parentNode = valueToMatchNode.get(parentObject);
			if (parentNode == null) {
				parentNode = createTreeNode(parentObject);
			}
			childNode.setParent(parentNode);
			parentNode.getChildren().add(childNode);
			return parentNode;
		}

		/**
		 * Retrieves the contextual parent of the given EObject.
		 * 
		 * @param value
		 *            the child EObject
		 * @return parent EObject
		 */
		private EObject getParent(EObject value) {
			if (value instanceof Diagram) {
				return diagramToContextualParent.get(value);
			}
			return value.eContainer();
		}

		/**
		 * Creates the subtree for the given tree node with a recursive bottom-up approach.
		 *
		 * @param treeNode
		 *            the TreeNode
		 * @param parentObject
		 *            the parent EObject
		 */
		private void createSubTree(TreeNode treeNode, EObject parentObject) {
			if (parentObject == null) {
				children.add(treeNode);
				return;
			}
			TreeNode parentNode = getOrCreateParentNode(treeNode, parentObject);
			treeNode.setParent(parentNode);
			parentNode.getChildren().add(treeNode);
			if (parentNode instanceof MatchNode) {
				return;
			}
			createSubTree(parentNode, getParent(parentObject));
		}

		@Override
		protected void populateMatchNode(MatchNode matchNode) {
			super.populateMatchNode(matchNode);
			if (!matchNode.getChildren().isEmpty()) {
				valueToMatchNode.put(getMatchValue(matchNode.getMatch()), matchNode);
			}
		}

		/**
		 * Returns the corresponding EObject for the given Match.
		 * 
		 * @param match
		 *            the Match
		 * @return the corresponding EObject
		 */
		private EObject getMatchValue(Match match) {
			if (match.getLeft() != null) {
				return match.getLeft();
			}
			if (match.getRight() != null) {
				return match.getRight();
			}
			return match.getOrigin();
		}

		/**
		 * Determines the object of the given match and side.
		 * 
		 * @param comparison
		 *            the {@link Comparison}.
		 * @param side
		 *            the {@link MergeViewerSide}.
		 * @return the determined {@link Object}, may be {@code null}.
		 */
		private ResourceSet getResourceSet(Comparison comparison, MergeViewerSide side) {
			for (MatchResource matchResource : comparison.getMatchedResources()) {
				Resource resource = getResource(matchResource, side);
				if (resource != null) {
					return resource.getResourceSet();
				}
			}
			return null;
		}

		/**
		 * Determines the resource of the given match and side.
		 * 
		 * @param matchResource
		 *            the {@link MatchResource}.
		 * @param side
		 *            the {@link MergeViewerSide}.
		 * @return the determined {@link Resource}, may be {@code null}.
		 */
		private Resource getResource(MatchResource matchResource, MergeViewerSide side) {
			if (side == MergeViewerSide.LEFT) {
				return matchResource.getLeft();
			}
			if (side == MergeViewerSide.RIGHT) {
				return matchResource.getRight();
			}
			if (side == MergeViewerSide.ANCESTOR) {
				return matchResource.getOrigin();
			}
			return null;
		}
	}

	@Override
	protected Collection<? extends IDifferenceGroup> buildGroups(Comparison comparison2) {

		PapyrusDifferenceGroup group = new PapyrusDifferenceGroup(getComparison(),
				getCrossReferenceAdapter());
		group.buildSubTree();
		return ImmutableList.of(group);
	}

	@Override
	public boolean isEnabled(IComparisonScope scope, Comparison comparison) {
		return super.isEnabled(scope, comparison) && PapyrusContextUtil.isPapyrusContext(comparison);
	}

}
