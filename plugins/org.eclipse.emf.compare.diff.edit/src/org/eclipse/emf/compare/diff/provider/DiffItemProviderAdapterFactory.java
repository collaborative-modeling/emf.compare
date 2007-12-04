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
package org.eclipse.emf.compare.diff.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.compare.diff.metamodel.util.DiffAdapterFactory;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers. The adapters
 * generated by this factory convert EMF adapter notifications into calls to
 * {@link #fireNotifyChanged fireNotifyChanged}. The adapters also support Eclipse property sheets. Note that
 * most of the adapters are shared among multiple instances. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class DiffItemProviderAdapterFactory extends DiffAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.AddAttribute} instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected AddAttributeItemProvider addAttributeItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.AddModelElement} instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected AddModelElementItemProvider addModelElementItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.AddReferenceValue} instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AddReferenceValueItemProvider addReferenceValueItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.AttributeChange} instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected AttributeChangeItemProvider attributeChangeItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.AttributeChangeLeftTarget} instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AttributeChangeLeftTargetItemProvider attributeChangeLeftTargetItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.AttributeChangeRightTarget} instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AttributeChangeRightTargetItemProvider attributeChangeRightTargetItemProvider;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.ConflictingDiffElement} instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ConflictingDiffElementItemProvider conflictingDiffElementItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.DiffGroup} instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected DiffGroupItemProvider diffGroupItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.DiffModel} instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected DiffModelItemProvider diffModelItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.GenericDiffElement} instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected GenericDiffElementItemProvider genericDiffElementItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.ModelElementChange} instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ModelElementChangeItemProvider modelElementChangeItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget} instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ModelElementChangeLeftTargetItemProvider modelElementChangeLeftTargetItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.ModelElementChangeRightTarget} instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ModelElementChangeRightTargetItemProvider modelElementChangeRightTargetItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.ModelInputSnapshot} instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ModelInputSnapshotItemProvider modelInputSnapshotItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.MoveModelElement} instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected MoveModelElementItemProvider moveModelElementItemProvider;

	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.ReferenceChange} instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected ReferenceChangeItemProvider referenceChangeItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.ReferenceChangeLeftTarget} instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ReferenceChangeLeftTargetItemProvider referenceChangeLeftTargetItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.ReferenceChangeRightTarget} instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ReferenceChangeRightTargetItemProvider referenceChangeRightTargetItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.RemoteAddAttribute} instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RemoteAddAttributeItemProvider remoteAddAttributeItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.RemoteAddModelElement} instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RemoteAddModelElementItemProvider remoteAddModelElementItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.RemoteAddReferenceValue} instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RemoteAddReferenceValueItemProvider remoteAddReferenceValueItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.RemoteMoveModelElement} instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RemoteMoveModelElementItemProvider remoteMoveModelElementItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.RemoteRemoveAttribute} instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RemoteRemoveAttributeItemProvider remoteRemoveAttributeItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.RemoteRemoveModelElement} instances. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RemoteRemoveModelElementItemProvider remoteRemoveModelElementItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.RemoteRemoveReferenceValue} instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RemoteRemoveReferenceValueItemProvider remoteRemoveReferenceValueItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.RemoteUpdateAttribute} instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RemoteUpdateAttributeItemProvider remoteUpdateAttributeItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.RemoteUpdateUniqueReferenceValue} instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RemoteUpdateUniqueReferenceValueItemProvider remoteUpdateUniqueReferenceValueItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.RemoveAttribute} instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected RemoveAttributeItemProvider removeAttributeItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.RemoveModelElement} instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RemoveModelElementItemProvider removeModelElementItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.RemoveReferenceValue} instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RemoveReferenceValueItemProvider removeReferenceValueItemProvider;

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Collection supportedTypes = new ArrayList();

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.UpdateAttribute} instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected UpdateAttributeItemProvider updateAttributeItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.UpdateModelElement} instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected UpdateModelElementItemProvider updateModelElementItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.UpdateReference} instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected UpdateReferenceItemProvider updateReferenceItemProvider;

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.emf.compare.diff.metamodel.UpdateUniqueReferenceValue} instances. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected UpdateUniqueReferenceValueItemProvider updateUniqueReferenceValueItemProvider;

	/**
	 * This constructs an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DiffItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class) || (((Class)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.AddAttribute}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createAddAttributeAdapter() {
		if (addAttributeItemProvider == null) {
			addAttributeItemProvider = new AddAttributeItemProvider(this);
		}

		return addAttributeItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.AddModelElement}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createAddModelElementAdapter() {
		if (addModelElementItemProvider == null) {
			addModelElementItemProvider = new AddModelElementItemProvider(this);
		}

		return addModelElementItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.AddReferenceValue}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createAddReferenceValueAdapter() {
		if (addReferenceValueItemProvider == null) {
			addReferenceValueItemProvider = new AddReferenceValueItemProvider(this);
		}

		return addReferenceValueItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.AttributeChange}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createAttributeChangeAdapter() {
		if (attributeChangeItemProvider == null) {
			attributeChangeItemProvider = new AttributeChangeItemProvider(this);
		}

		return attributeChangeItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.AttributeChangeLeftTarget}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createAttributeChangeLeftTargetAdapter() {
		if (attributeChangeLeftTargetItemProvider == null) {
			attributeChangeLeftTargetItemProvider = new AttributeChangeLeftTargetItemProvider(this);
		}

		return attributeChangeLeftTargetItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.AttributeChangeRightTarget}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createAttributeChangeRightTargetAdapter() {
		if (attributeChangeRightTargetItemProvider == null) {
			attributeChangeRightTargetItemProvider = new AttributeChangeRightTargetItemProvider(this);
		}

		return attributeChangeRightTargetItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.ConflictingDiffElement}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createConflictingDiffElementAdapter() {
		if (conflictingDiffElementItemProvider == null) {
			conflictingDiffElementItemProvider = new ConflictingDiffElementItemProvider(this);
		}

		return conflictingDiffElementItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.DiffGroup}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createDiffGroupAdapter() {
		if (diffGroupItemProvider == null) {
			diffGroupItemProvider = new DiffGroupItemProvider(this);
		}

		return diffGroupItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.DiffModel}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createDiffModelAdapter() {
		if (diffModelItemProvider == null) {
			diffModelItemProvider = new DiffModelItemProvider(this);
		}

		return diffModelItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.GenericDiffElement}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createGenericDiffElementAdapter() {
		if (genericDiffElementItemProvider == null) {
			genericDiffElementItemProvider = new GenericDiffElementItemProvider(this);
		}

		return genericDiffElementItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.ModelElementChange}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createModelElementChangeAdapter() {
		if (modelElementChangeItemProvider == null) {
			modelElementChangeItemProvider = new ModelElementChangeItemProvider(this);
		}

		return modelElementChangeItemProvider;
	}

	/**
	 * This creates an adapter for a
	 * {@link org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createModelElementChangeLeftTargetAdapter() {
		if (modelElementChangeLeftTargetItemProvider == null) {
			modelElementChangeLeftTargetItemProvider = new ModelElementChangeLeftTargetItemProvider(this);
		}

		return modelElementChangeLeftTargetItemProvider;
	}

	/**
	 * This creates an adapter for a
	 * {@link org.eclipse.emf.compare.diff.metamodel.ModelElementChangeRightTarget}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createModelElementChangeRightTargetAdapter() {
		if (modelElementChangeRightTargetItemProvider == null) {
			modelElementChangeRightTargetItemProvider = new ModelElementChangeRightTargetItemProvider(this);
		}

		return modelElementChangeRightTargetItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.ModelInputSnapshot}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createModelInputSnapshotAdapter() {
		if (modelInputSnapshotItemProvider == null) {
			modelInputSnapshotItemProvider = new ModelInputSnapshotItemProvider(this);
		}

		return modelInputSnapshotItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.MoveModelElement}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createMoveModelElementAdapter() {
		if (moveModelElementItemProvider == null) {
			moveModelElementItemProvider = new MoveModelElementItemProvider(this);
		}

		return moveModelElementItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.ReferenceChange}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createReferenceChangeAdapter() {
		if (referenceChangeItemProvider == null) {
			referenceChangeItemProvider = new ReferenceChangeItemProvider(this);
		}

		return referenceChangeItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.ReferenceChangeLeftTarget}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createReferenceChangeLeftTargetAdapter() {
		if (referenceChangeLeftTargetItemProvider == null) {
			referenceChangeLeftTargetItemProvider = new ReferenceChangeLeftTargetItemProvider(this);
		}

		return referenceChangeLeftTargetItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.ReferenceChangeRightTarget}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createReferenceChangeRightTargetAdapter() {
		if (referenceChangeRightTargetItemProvider == null) {
			referenceChangeRightTargetItemProvider = new ReferenceChangeRightTargetItemProvider(this);
		}

		return referenceChangeRightTargetItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.RemoteAddAttribute}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createRemoteAddAttributeAdapter() {
		if (remoteAddAttributeItemProvider == null) {
			remoteAddAttributeItemProvider = new RemoteAddAttributeItemProvider(this);
		}

		return remoteAddAttributeItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.RemoteAddModelElement}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createRemoteAddModelElementAdapter() {
		if (remoteAddModelElementItemProvider == null) {
			remoteAddModelElementItemProvider = new RemoteAddModelElementItemProvider(this);
		}

		return remoteAddModelElementItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.RemoteAddReferenceValue}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createRemoteAddReferenceValueAdapter() {
		if (remoteAddReferenceValueItemProvider == null) {
			remoteAddReferenceValueItemProvider = new RemoteAddReferenceValueItemProvider(this);
		}

		return remoteAddReferenceValueItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.RemoteMoveModelElement}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createRemoteMoveModelElementAdapter() {
		if (remoteMoveModelElementItemProvider == null) {
			remoteMoveModelElementItemProvider = new RemoteMoveModelElementItemProvider(this);
		}

		return remoteMoveModelElementItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.RemoteRemoveAttribute}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createRemoteRemoveAttributeAdapter() {
		if (remoteRemoveAttributeItemProvider == null) {
			remoteRemoveAttributeItemProvider = new RemoteRemoveAttributeItemProvider(this);
		}

		return remoteRemoveAttributeItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.RemoteRemoveModelElement}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createRemoteRemoveModelElementAdapter() {
		if (remoteRemoveModelElementItemProvider == null) {
			remoteRemoveModelElementItemProvider = new RemoteRemoveModelElementItemProvider(this);
		}

		return remoteRemoveModelElementItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.RemoteRemoveReferenceValue}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createRemoteRemoveReferenceValueAdapter() {
		if (remoteRemoveReferenceValueItemProvider == null) {
			remoteRemoveReferenceValueItemProvider = new RemoteRemoveReferenceValueItemProvider(this);
		}

		return remoteRemoveReferenceValueItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.RemoteUpdateAttribute}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createRemoteUpdateAttributeAdapter() {
		if (remoteUpdateAttributeItemProvider == null) {
			remoteUpdateAttributeItemProvider = new RemoteUpdateAttributeItemProvider(this);
		}

		return remoteUpdateAttributeItemProvider;
	}

	/**
	 * This creates an adapter for a
	 * {@link org.eclipse.emf.compare.diff.metamodel.RemoteUpdateUniqueReferenceValue}. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createRemoteUpdateUniqueReferenceValueAdapter() {
		if (remoteUpdateUniqueReferenceValueItemProvider == null) {
			remoteUpdateUniqueReferenceValueItemProvider = new RemoteUpdateUniqueReferenceValueItemProvider(
					this);
		}

		return remoteUpdateUniqueReferenceValueItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.RemoveAttribute}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createRemoveAttributeAdapter() {
		if (removeAttributeItemProvider == null) {
			removeAttributeItemProvider = new RemoveAttributeItemProvider(this);
		}

		return removeAttributeItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.RemoveModelElement}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createRemoveModelElementAdapter() {
		if (removeModelElementItemProvider == null) {
			removeModelElementItemProvider = new RemoveModelElementItemProvider(this);
		}

		return removeModelElementItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.RemoveReferenceValue}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createRemoveReferenceValueAdapter() {
		if (removeReferenceValueItemProvider == null) {
			removeReferenceValueItemProvider = new RemoveReferenceValueItemProvider(this);
		}

		return removeReferenceValueItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.UpdateAttribute}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createUpdateAttributeAdapter() {
		if (updateAttributeItemProvider == null) {
			updateAttributeItemProvider = new UpdateAttributeItemProvider(this);
		}

		return updateAttributeItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.UpdateModelElement}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createUpdateModelElementAdapter() {
		if (updateModelElementItemProvider == null) {
			updateModelElementItemProvider = new UpdateModelElementItemProvider(this);
		}

		return updateModelElementItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.UpdateReference}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createUpdateReferenceAdapter() {
		if (updateReferenceItemProvider == null) {
			updateReferenceItemProvider = new UpdateReferenceItemProvider(this);
		}

		return updateReferenceItemProvider;
	}

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diff.metamodel.UpdateUniqueReferenceValue}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Adapter createUpdateUniqueReferenceValueAdapter() {
		if (updateUniqueReferenceValueItemProvider == null) {
			updateUniqueReferenceValueItemProvider = new UpdateUniqueReferenceValueItemProvider(this);
		}

		return updateUniqueReferenceValueItemProvider;
	}

	/**
	 * This disposes all of the item providers created by this factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public void dispose() {
		if (diffModelItemProvider != null)
			diffModelItemProvider.dispose();
		if (genericDiffElementItemProvider != null)
			genericDiffElementItemProvider.dispose();
		if (conflictingDiffElementItemProvider != null)
			conflictingDiffElementItemProvider.dispose();
		if (diffGroupItemProvider != null)
			diffGroupItemProvider.dispose();
		if (modelInputSnapshotItemProvider != null)
			modelInputSnapshotItemProvider.dispose();
		if (modelElementChangeItemProvider != null)
			modelElementChangeItemProvider.dispose();
		if (modelElementChangeLeftTargetItemProvider != null)
			modelElementChangeLeftTargetItemProvider.dispose();
		if (modelElementChangeRightTargetItemProvider != null)
			modelElementChangeRightTargetItemProvider.dispose();
		if (addModelElementItemProvider != null)
			addModelElementItemProvider.dispose();
		if (remoteAddModelElementItemProvider != null)
			remoteAddModelElementItemProvider.dispose();
		if (removeModelElementItemProvider != null)
			removeModelElementItemProvider.dispose();
		if (remoteRemoveModelElementItemProvider != null)
			remoteRemoveModelElementItemProvider.dispose();
		if (updateModelElementItemProvider != null)
			updateModelElementItemProvider.dispose();
		if (moveModelElementItemProvider != null)
			moveModelElementItemProvider.dispose();
		if (remoteMoveModelElementItemProvider != null)
			remoteMoveModelElementItemProvider.dispose();
		if (attributeChangeItemProvider != null)
			attributeChangeItemProvider.dispose();
		if (attributeChangeLeftTargetItemProvider != null)
			attributeChangeLeftTargetItemProvider.dispose();
		if (attributeChangeRightTargetItemProvider != null)
			attributeChangeRightTargetItemProvider.dispose();
		if (addAttributeItemProvider != null)
			addAttributeItemProvider.dispose();
		if (remoteAddAttributeItemProvider != null)
			remoteAddAttributeItemProvider.dispose();
		if (removeAttributeItemProvider != null)
			removeAttributeItemProvider.dispose();
		if (remoteRemoveAttributeItemProvider != null)
			remoteRemoveAttributeItemProvider.dispose();
		if (updateAttributeItemProvider != null)
			updateAttributeItemProvider.dispose();
		if (remoteUpdateAttributeItemProvider != null)
			remoteUpdateAttributeItemProvider.dispose();
		if (referenceChangeItemProvider != null)
			referenceChangeItemProvider.dispose();
		if (referenceChangeLeftTargetItemProvider != null)
			referenceChangeLeftTargetItemProvider.dispose();
		if (referenceChangeRightTargetItemProvider != null)
			referenceChangeRightTargetItemProvider.dispose();
		if (addReferenceValueItemProvider != null)
			addReferenceValueItemProvider.dispose();
		if (remoteAddReferenceValueItemProvider != null)
			remoteAddReferenceValueItemProvider.dispose();
		if (removeReferenceValueItemProvider != null)
			removeReferenceValueItemProvider.dispose();
		if (remoteRemoveReferenceValueItemProvider != null)
			remoteRemoveReferenceValueItemProvider.dispose();
		if (updateReferenceItemProvider != null)
			updateReferenceItemProvider.dispose();
		if (updateUniqueReferenceValueItemProvider != null)
			updateUniqueReferenceValueItemProvider.dispose();
		if (remoteUpdateUniqueReferenceValueItemProvider != null)
			remoteUpdateUniqueReferenceValueItemProvider.dispose();
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This returns the root adapter factory that contains this factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This removes a listener. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This sets the composed adapter factory that contains this factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

}
