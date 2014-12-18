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

import java.net.URI;

import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDCreateException;
import org.eclipse.ecf.core.identity.IDFactory;
import org.eclipse.ecf.core.identity.Namespace;
import org.eclipse.ecf.remoteservice.rest.identity.RestID;

public class ChangeNamespace extends Namespace {

	private static final long serialVersionUID = -3433568817065075090L;
	public static final String NAME = "ecf.change.org.namespace";
	public static ChangeNamespace INSTANCE;

	public ChangeNamespace() {
		super(NAME, "Change.org Namespace");
		INSTANCE = this;
	}

	public class ChangeID extends RestID {
		private static final long serialVersionUID = -1523093091999324905L;

		ChangeID(URI uri) {
			super(ChangeNamespace.this, uri);
		}
	}

	@Override
	public ID createInstance(Object[] parameters) throws IDCreateException {
		try {
			return new ChangeID(URI.create((String) parameters[0]));
		} catch (Exception e) {
			throw new IDCreateException("Could not create change ID", e); //$NON-NLS-1$
		}
	}

	public static ChangeID createUUID() throws IDCreateException {
		return (ChangeID) IDFactory.getDefault().createID(INSTANCE,
				"uuid:" + java.util.UUID.randomUUID().toString());
	}

	@Override
	public String getScheme() {
		return "ecf.change.org";
	}

}
