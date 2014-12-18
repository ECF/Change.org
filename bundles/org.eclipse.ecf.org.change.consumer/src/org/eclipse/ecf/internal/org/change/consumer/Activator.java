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
package org.eclipse.ecf.internal.org.change.consumer;

import org.change.api.IPetitionServiceAsync;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class Activator implements BundleActivator, ServiceTrackerCustomizer<IPetitionServiceAsync,IPetitionServiceAsync>  {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	private ServiceTracker<IPetitionServiceAsync, IPetitionServiceAsync> tracker = null;
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		 tracker = new ServiceTracker<IPetitionServiceAsync,IPetitionServiceAsync>(context,IPetitionServiceAsync.class,this);
		 tracker.open();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		if (tracker != null) {
			tracker.close();
			tracker = null;
		}
		Activator.context = null;
	}

	@Override
	public IPetitionServiceAsync addingService(
			ServiceReference<IPetitionServiceAsync> reference) {
		final IPetitionServiceAsync petitionService = context.getService(reference);
		String petitionURL = "https://www.change.org/p/scottslewis-slewis-test-call-to-action";
		petitionService.getPetitionIdAsync(petitionURL).whenComplete(
				(result, exception) -> {
					// Check for exception and print out
					if (exception != null) {
						System.out.println(exception.getMessage());
						exception.printStackTrace();
					} else
						// Success!
						System.out.println("Received response:  petitionId="
								+ result + " for url=" + petitionURL);
				});
		System.out.println("Got petition service: "+petitionService);
		return petitionService;
	}

	@Override
	public void modifiedService(ServiceReference<IPetitionServiceAsync> reference,
			IPetitionServiceAsync service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference<IPetitionServiceAsync> reference,
			IPetitionServiceAsync service) {
		// TODO Auto-generated method stub
		
	}

}
