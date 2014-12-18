/****************************************************************************
 * Copyright (c) 2014  Composent, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Composent, Inc. - initial API and implementation
 *****************************************************************************/
package org.eclipse.ecf.provider.internal.org.change;

import org.eclipse.ecf.core.ContainerTypeDescription;
import org.eclipse.ecf.core.identity.Namespace;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		// Register an instance of TimezoneNamespace
		bundleContext.registerService(Namespace.class, new ChangeNamespace(),
				null);
		// Register an instance of TimezoneContainerTypeDescription (see class
		// below)
		bundleContext.registerService(ContainerTypeDescription.class,
				new TimezoneContainerTypeDescription(), null);
	}

	class TimezoneContainerTypeDescription extends ContainerTypeDescription {
		public TimezoneContainerTypeDescription() {
			super(ChangeClientContainer.CONTAINER_TYPE_NAME,
					new ChangeClientContainer.Instantiator(),
					"Change.org Remote Service Client Container");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
}
