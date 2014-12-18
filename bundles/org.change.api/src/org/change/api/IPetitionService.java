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


public interface IPetitionService {

	public PetitionsResult getPetitions(PetitionsRequest request);
	
	public Petition getPetition(int petitionId);
	public Petition getPetition(int petitionId, String[] fields);
	
	public int getPetitionId(String url);
	
}
