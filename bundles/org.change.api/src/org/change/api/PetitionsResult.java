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

import java.util.Collection;

public class PetitionsResult {
	
	private int page;
	private String prevPageEndpoint;
	private String nextPageEndpoint;
	private int totalPages;
	private Collection<Petition> petitions;
	
	public PetitionsResult(Integer page, String prevPageEndpoint, String nextPageEndpoint, Integer totalPages, Collection<Petition> petitions) {
		this.page = page;
		this.prevPageEndpoint = prevPageEndpoint;
		this.nextPageEndpoint = nextPageEndpoint;
		this.totalPages = totalPages;
		this.petitions = petitions;
	}

	public int getPage() {
		return page;
	}

	public String getPrevPageEndpoint() {
		return prevPageEndpoint;
	}

	public String getNextPageEndpoint() {
		return nextPageEndpoint;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public Collection<Petition> getPetitions() {
		return petitions;
	}

	@Override
	public String toString() {
		return "PetitionsResult [page=" + page + ", prevPageEndpoint="
				+ prevPageEndpoint + ", nextPageEndpoint=" + nextPageEndpoint
				+ ", totalPages=" + totalPages + ", petitions=" + petitions
				+ "]";
	}
	
}
