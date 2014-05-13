/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.examples.extlibrary.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

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

import org.eclipse.emf.examples.extlibrary.util.EXTLibraryAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EXTLibraryItemProviderAdapterFactory extends EXTLibraryAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable 
{
  /**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected ComposedAdapterFactory parentAdapterFactory;

  /**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected IChangeNotifier changeNotifier = new ChangeNotifier();

  /**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected Collection<Object> supportedTypes = new ArrayList<Object>();

  /**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EXTLibraryItemProviderAdapterFactory()
  {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.examples.extlibrary.Book} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected BookItemProvider bookItemProvider;

  /**
	 * This creates an adapter for a {@link org.eclipse.emf.examples.extlibrary.Book}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createBookAdapter()
  {
		if (bookItemProvider == null) {
			bookItemProvider = new BookItemProvider(this);
		}

		return bookItemProvider;
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.examples.extlibrary.Library} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected LibraryItemProvider libraryItemProvider;

  /**
	 * This creates an adapter for a {@link org.eclipse.emf.examples.extlibrary.Library}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createLibraryAdapter()
  {
		if (libraryItemProvider == null) {
			libraryItemProvider = new LibraryItemProvider(this);
		}

		return libraryItemProvider;
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.examples.extlibrary.Writer} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected WriterItemProvider writerItemProvider;

  /**
	 * This creates an adapter for a {@link org.eclipse.emf.examples.extlibrary.Writer}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createWriterAdapter()
  {
		if (writerItemProvider == null) {
			writerItemProvider = new WriterItemProvider(this);
		}

		return writerItemProvider;
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.examples.extlibrary.BookOnTape} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected BookOnTapeItemProvider bookOnTapeItemProvider;

  /**
	 * This creates an adapter for a {@link org.eclipse.emf.examples.extlibrary.BookOnTape}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createBookOnTapeAdapter()
  {
		if (bookOnTapeItemProvider == null) {
			bookOnTapeItemProvider = new BookOnTapeItemProvider(this);
		}

		return bookOnTapeItemProvider;
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.examples.extlibrary.VideoCassette} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected VideoCassetteItemProvider videoCassetteItemProvider;

  /**
	 * This creates an adapter for a {@link org.eclipse.emf.examples.extlibrary.VideoCassette}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createVideoCassetteAdapter()
  {
		if (videoCassetteItemProvider == null) {
			videoCassetteItemProvider = new VideoCassetteItemProvider(this);
		}

		return videoCassetteItemProvider;
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.examples.extlibrary.Borrower} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected BorrowerItemProvider borrowerItemProvider;

  /**
	 * This creates an adapter for a {@link org.eclipse.emf.examples.extlibrary.Borrower}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createBorrowerAdapter()
  {
		if (borrowerItemProvider == null) {
			borrowerItemProvider = new BorrowerItemProvider(this);
		}

		return borrowerItemProvider;
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.examples.extlibrary.Person} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected PersonItemProvider personItemProvider;

  /**
	 * This creates an adapter for a {@link org.eclipse.emf.examples.extlibrary.Person}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createPersonAdapter()
  {
		if (personItemProvider == null) {
			personItemProvider = new PersonItemProvider(this);
		}

		return personItemProvider;
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.examples.extlibrary.Employee} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EmployeeItemProvider employeeItemProvider;

  /**
	 * This creates an adapter for a {@link org.eclipse.emf.examples.extlibrary.Employee}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createEmployeeAdapter()
  {
		if (employeeItemProvider == null) {
			employeeItemProvider = new EmployeeItemProvider(this);
		}

		return employeeItemProvider;
	}

  /**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ComposeableAdapterFactory getRootAdapterFactory()
  {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

  /**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory)
  {
		this.parentAdapterFactory = parentAdapterFactory;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public boolean isFactoryForType(Object type)
  {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

  /**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter adapt(Notifier notifier, Object type)
  {
		return super.adapt(notifier, this);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Object adapt(Object object, Object type)
  {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

  /**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void addListener(INotifyChangedListener notifyChangedListener)
  {
		changeNotifier.addListener(notifyChangedListener);
	}

  /**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void removeListener(INotifyChangedListener notifyChangedListener)
  {
		changeNotifier.removeListener(notifyChangedListener);
	}

  /**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void fireNotifyChanged(Notification notification)
  {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

  /**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void dispose()
  {
		if (bookItemProvider != null) bookItemProvider.dispose();
		if (libraryItemProvider != null) libraryItemProvider.dispose();
		if (writerItemProvider != null) writerItemProvider.dispose();
		if (bookOnTapeItemProvider != null) bookOnTapeItemProvider.dispose();
		if (videoCassetteItemProvider != null) videoCassetteItemProvider.dispose();
		if (borrowerItemProvider != null) borrowerItemProvider.dispose();
		if (personItemProvider != null) personItemProvider.dispose();
		if (employeeItemProvider != null) employeeItemProvider.dispose();
	}

}
