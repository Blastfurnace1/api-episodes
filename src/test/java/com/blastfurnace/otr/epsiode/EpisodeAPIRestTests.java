/*
 * Copyright 2012-2014 the original author or authors.
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
package com.blastfurnace.otr.epsiode;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.blastfurnace.otr.AppConfigTest;
import com.blastfurnace.otr.data.episode.service.model.EpisodeDataWrapper;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.assertTrue;

/**
 * Integration Tests for Series Services
 *
 * @author Jim Blackson
 */
public class EpisodeAPIRestTests extends AppConfigTest {
	
	private static final Logger log = LoggerFactory.getLogger(EpisodeAPIRestTests.class); 
	
	@Test
	public void WhenSendingGetRequestToControllerReponseObject() throws Exception {
		log.info("Episode API Rest Tests - Start");
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.getTestRestTemplate().getForEntity(
				"http://localhost:" + this.getPort() + "/rest/episode/get/1", Map.class);

		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		HttpHeaders httpHeaders = this.getTestRestTemplate()
				  .headForHeaders("http://localhost:" + this.getPort() + "/rest/episode/get/1");
		
		assertTrue(httpHeaders.getContentType()
				  .includes(MediaType.APPLICATION_JSON));
		
		EpisodeDataWrapper episode = this.getTestRestTemplate().getForObject(
				"http://localhost:" + this.getPort() + "/rest/episode/get/1", EpisodeDataWrapper.class);
		log.info("Episode API Rest Tests - End");
	}
	
}
