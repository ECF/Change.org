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

import java.io.NotSerializableException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.change.api.IPetitionService;
import org.eclipse.ecf.core.ContainerConnectException;
import org.eclipse.ecf.core.ContainerCreateException;
import org.eclipse.ecf.core.ContainerTypeDescription;
import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.Namespace;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.ecf.remoteservice.IRemoteCall;
import org.eclipse.ecf.remoteservice.IRemoteServiceRegistration;
import org.eclipse.ecf.remoteservice.client.AbstractClientService.UriRequest;
import org.eclipse.ecf.remoteservice.client.IRemoteCallable;
import org.eclipse.ecf.remoteservice.client.IRemoteResponseDeserializer;
import org.eclipse.ecf.remoteservice.client.RemoteCallable;
import org.eclipse.ecf.remoteservice.rest.client.HttpGetRequestType;
import org.eclipse.ecf.remoteservice.rest.client.RestClientContainer;
import org.eclipse.ecf.remoteservice.rest.client.RestClientContainerInstantiator;
import org.json.JSONException;
import org.json.JSONObject;

public class ChangeClientContainer extends RestClientContainer {

	public static final String CONTAINER_TYPE_NAME = "ecf.change.org.container.client";

	public static class Instantiator extends RestClientContainerInstantiator {

		@Override
		public String[] getImportedConfigs(
				ContainerTypeDescription description,
				String[] exporterSupportedConfigs) {
			if (Arrays.asList(exporterSupportedConfigs).contains(
					CONTAINER_TYPE_NAME))
				return new String[] { CONTAINER_TYPE_NAME };
			return null;
		}

		@Override
		public IContainer createInstance(ContainerTypeDescription description,
				Object[] parameters) throws ContainerCreateException {
			return new ChangeClientContainer();
		}
	}

	private IRemoteServiceRegistration registration;

	public void connect(ID targetID, IConnectContext connectContext1)
			throws ContainerConnectException {
		// Set the connectTargetID in the AbstractClientContainer super class
		super.connect(targetID, connectContext1);

		// Create callables prior to registering them via registerCallables
		// below
		List<IRemoteCallable> petitionCallables = new ArrayList<IRemoteCallable>();
		// add callable for getPetitionId
		petitionCallables.add(new RemoteCallable.Builder("getPetitionId",
				"/v1/petitions/get_id")
				.setRequestType(new HttpGetRequestType()).build());
		// add callables for other IPetitionService methods

		// Register callables
		registration = registerCallables(IPetitionService.class,
				petitionCallables.toArray(new IRemoteCallable[petitionCallables
						.size()]), null);

		setResponseDeserializer(new IRemoteResponseDeserializer() {

			@Override
			public Object deserializeResponse(String endpoint,
					IRemoteCall call, IRemoteCallable callable,
					@SuppressWarnings("rawtypes") Map responseHeaders,
					byte[] responseBody) throws NotSerializableException {
				try {
					return new JSONObject(new String(responseBody))
							.getInt("petition_id");
				} catch (JSONException e) {
					NotSerializableException t = new NotSerializableException(
							"response from endpoint=" + endpoint
									+ " could not be parsed");
					t.setStackTrace(e.getStackTrace());
					throw t;
				}
			}
		});
	}

	@Override
	public UriRequest createUriRequest(String endpoint, IRemoteCall call,
			IRemoteCallable callable) {
		if (api_key == null)
			throw new NullPointerException(
					"no api_key set.  Set org.eclipse.ecf.provider.org.change.api_key to a valid change.org api key");
		String method = callable.getMethod();
		if (method.equals("getPetitionId")) {
			StringBuffer uriBuf = new StringBuffer(endpoint);
			// API key
			uriBuf.append("?").append(getApiKeyParam());
			// petition url
			uriBuf.append("&petition_url=").append(
					urlEncode((String) call.getParameters()[0]));
			return new UriRequest(uriBuf.toString(), call, callable);
		}
		return null;
	}

	private final String api_key = System
			.getProperty("org.eclipse.ecf.provider.org.change.api_key");

	private StringBuffer getApiKeyParam() {
		return new StringBuffer("api_key=").append(api_key);
	}

	private String urlEncode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// Should not happen
			return null;
		}
	}

	@Override
	public void disconnect() {
		super.disconnect();
		if (registration != null) {
			registration.unregister();
			registration = null;
		}
	}

	ChangeClientContainer() {
		// Set this container's ID to a ranmdom UUID
		super(ChangeNamespace.createUUID());
	}

	@Override
	public Namespace getConnectNamespace() {
		return ChangeNamespace.INSTANCE;
	}

}
