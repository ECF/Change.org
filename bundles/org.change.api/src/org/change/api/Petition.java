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
import java.util.Date;

public class Petition {

	public static final String[] FIELDS = {"title", "status", "url", "overview", "targets", "letter_body", "signature_count", "image_url", "category", "goal", "created_at", "end_at", "creator_name", "creator_url","organization_name", "organization_url"};
	
	private String title;
	private String status;
	private String url;
	private String overview;
	private Collection<Target> targets;
	private String letterBody;
	private Integer signatureCount;
	private String imageUrl;
	private String category;
	private Integer goal;
	private Date createdAt;
	private Date endAt;
	private String creatorName;
	private String creatorUrl;
	private String organizationName;
	private String organizationUrl;
	
	public Petition(String title, String status, String url, Collection<Target> targets, String letterBody, Integer signatureCount, String imageUrl,
			String category, Integer goal, Date createdAt, Date endAt, String creatorName, String creatorUrl, String organizationName, String organizationUrl) {
		this.title = title;
		this.status = status;
		this.url = url;
		this.targets = targets;
		this.letterBody = letterBody;
		this.signatureCount = signatureCount;
		this.imageUrl= imageUrl;
		this.category = category;
		this.goal = goal;
		this.createdAt = createdAt;
		this.endAt = endAt;
		this.creatorName = creatorName;
		this.creatorUrl = creatorUrl;
		this.organizationName = organizationName;
		this.organizationUrl = organizationUrl;
	}
	
	public static String[] getFields() {
		return FIELDS;
	}
	public String getTitle() {
		return title;
	}
	public String getStatus() {
		return status;
	}
	public String getUrl() {
		return url;
	}
	public String getOverview() {
		return overview;
	}
	public Collection<Target> getTargets() {
		return targets;
	}
	public String getLetterBody() {
		return letterBody;
	}
	public int getSignatureCount() {
		return signatureCount;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public String getCategory() {
		return category;
	}
	public int getGoal() {
		return goal;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getEndAt() {
		return endAt;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public String getCreatorUrl() {
		return creatorUrl;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public String getOrganizationUrl() {
		return organizationUrl;
	}

	@Override
	public String toString() {
		return "Petition [title=" + title + ", status=" + status + ", url="
				+ url + ", overview=" + overview + ", targets=" + targets
				+ ", letterBody=" + letterBody + ", signatureCount="
				+ signatureCount + ", imageUrl=" + imageUrl + ", category="
				+ category + ", goal=" + goal + ", createdAt=" + createdAt
				+ ", endAt=" + endAt + ", creatorName=" + creatorName
				+ ", creatorUrl=" + creatorUrl + ", organizationName="
				+ organizationName + ", organizationUrl=" + organizationUrl
				+ "]";
	}
	
	
}
