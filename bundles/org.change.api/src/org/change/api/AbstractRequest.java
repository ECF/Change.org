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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public abstract class AbstractRequest {

	public static SimpleDateFormat ISO8601;
	
	static {
		ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		ISO8601.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
	
	public static class SORT_TYPE {
		private String type;
		
		SORT_TYPE(String type) {
			this.type = type;
		}
		
		public String toString() {
			return type;
		}
		
		public static final SORT_TYPE SIGNATURES_ASC = new SORT_TYPE("signatures_asc");
		public static final SORT_TYPE SIGNATURES_DESC = new SORT_TYPE("signatures_desc");
		public static final SORT_TYPE TIME_ASC = new SORT_TYPE("time_asc");
		public static final SORT_TYPE TIME_DESC = new SORT_TYPE("time_desc");
	}
	
	private List<Integer> ids;
	private Integer page_size;
	private Integer page;
	private SORT_TYPE sortType;
	
	public AbstractRequest() {
		this.ids = new ArrayList<Integer>();
	}
	
	protected Collection<Integer> getIds0() {
		return ids;
	}
	
	protected void addId0(int id) {
		this.ids.add(id);
	}
	
	protected void setPageSize0(int page_size) {
		this.page_size = page_size;
	}
	
	public Integer getPageSize() {
		return page_size;
	}
	
	protected void setPage0(int page) {
		this.page = page;
	}
	
	public SORT_TYPE getSortType() {
		return this.sortType;
	}
	
	protected void setSortType0(SORT_TYPE sortType) {
		this.sortType = sortType;
	}
	
	@SuppressWarnings("rawtypes")
	public static String buildList(Collection l, String delimiter) {
		StringBuffer buf = new StringBuffer();
		for(Iterator i=l.iterator(); i.hasNext(); ) {
			buf.append(i.next());
			if (i.hasNext()) buf.append(delimiter);
		}
		return buf.toString();
	}
	
	public String buildPageParameters() {
		StringBuffer buf = new StringBuffer();
		if (this.page != null) 
			buf.append("page=").append(page);
		if (this.page_size != null) {
			if (buf.length() > 0) buf.append("&");
			buf.append("page_size=").append(page_size);
		}
		return buf.toString();
	}
	
}
