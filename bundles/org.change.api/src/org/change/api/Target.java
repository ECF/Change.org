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

public class Target {

	public static class TYPE {
		private String type;
		TYPE(String t) {
			this.type = t;
		}
		
		public String toString() {
			return type;
		}
		
		public static final TYPE US_GOVERNMENT = new TYPE("us_government");
		public static final TYPE CUSTOM = new TYPE("custom");
	}
	
	private String name;
	private String title;
	private TYPE type;
	private String target_area;
	
	public Target(String name, String title, TYPE type, String target_area) {
		this.name = name;
		this.title = title;
		this.type = type;
		this.target_area = target_area;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public TYPE getType() {
		return type;
	}

	public String getTargetArea() {
		return target_area;
	}

	@Override
	public String toString() {
		return "Target [name=" + name + ", title=" + title + ", type=" + type
				+ ", target_area=" + target_area + "]";
	}

}
