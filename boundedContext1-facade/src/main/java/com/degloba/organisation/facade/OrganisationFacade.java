/*
 * Copyright 2014 Dayatang Open Source..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.degloba.organisation.facade;

import java.util.Date;

import com.google.appengine.api.datastore.Key;

/**
 * @author yyang
 */
public interface OrganisationFacade {


    void createOrganization(OrganizationDto orgToCreate, Key parentOrgId, Date date);

    void terminateParty(Key partyId, Date date);

    void changeParentOfOrganization(Key organizationId, Key newParentId, Date date);

    void createPostUnderOrganization(PostDto postDto, Key organizationId, Date date);

    PostDto getPost(Key postId);
}
