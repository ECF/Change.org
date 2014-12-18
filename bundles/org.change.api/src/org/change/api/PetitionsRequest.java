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
package org.change.api;

import java.util.Arrays;
import java.util.List;

public class PetitionsRequest extends AbstractRequest {

	private List<String> fields;
	
	public PetitionsRequest(int id) {
		addId(id);
	}
	
	public PetitionsRequest addId(int id) {
		super.addId0(id);
		return this;
	}
	
	public PetitionsRequest addIds(int[] ids) {
		for(int i=0;i < ids.length; i++) addId(ids[i]);
		return this;
	}
	
	public PetitionsRequest setPageSize(int page_size) {
		super.setPageSize0(page_size);
		return this;
	}
	
	public PetitionsRequest setPage(int page) {
		super.setPage0(page);
		return this;
	}
	
	public PetitionsRequest addField(String field) {
		this.fields.add(field);
		return this;
	}
	
	public PetitionsRequest addFields(String[] fields) {
		this.fields.addAll(Arrays.asList(fields));
		return this;
	}
	
}
