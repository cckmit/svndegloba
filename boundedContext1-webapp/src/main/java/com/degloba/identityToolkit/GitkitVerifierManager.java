/*
 * Copyright 2014 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.degloba.identityToolkit;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import net.oauth.jsontoken.crypto.Verifier;
import net.oauth.jsontoken.discovery.VerifierProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//////import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;


/**
 * Manages Gitkit token verifiers, taking care of download and refresh.
 */
public class GitkitVerifierManager implements VerifierProvider {

		
  // Lock on the verifiers.
  private final Lock lock = new ReentrantLock();

  //////////private static final Logger log = Logger.getLogger(GitkitVerifierManager.class.getName());
  private final Logger log = LoggerFactory.getLogger(getClass()); 
  private final RpcHelper rpcHelper;
  private final String serverApiKey;
  private Map<String, GitkitTokenVerifier> verifiers = Maps.newHashMap();

  public GitkitVerifierManager(RpcHelper rpcHelper) {
    this(rpcHelper, null);
  }

  public GitkitVerifierManager(RpcHelper rpcHelper, String serverApiKey) {
    this.rpcHelper = rpcHelper;
    this.serverApiKey = serverApiKey;
  }

  @Override
  public List<Verifier> findVerifier(String issuer, String keyId) {
    lock.lock();
    try {
      if (!verifiers.containsKey(keyId)) {
        initVerifiers();
      }
      return Lists.<Verifier>newArrayList(verifiers.get(keyId));
    } finally {
      lock.unlock();
    }
  }

  private void initVerifiers() {
    try {
      Map<String, String> certs = parseCertsResponse(rpcHelper.downloadCerts(serverApiKey));
      verifiers.clear();
      for (String kid : certs.keySet()) {
        verifiers.put(kid, new GitkitTokenVerifier(certs.get(kid)));
      }
    } catch (IOException e) {
      log.debug("unable to find token verifier: " + e.getMessage());
    } catch (GitkitServerException e) {
      log.debug("unable to find token verifier: " + e.getMessage());
    }
  }

  @VisibleForTesting
  Map<String, String> parseCertsResponse(String response)
      throws GitkitServerException {
    try {
      JsonObject jsonCerts = new JsonParser().parse(response).getAsJsonObject();
      Map<String, String> certs = Maps.newHashMap();
      for (Map.Entry<String, JsonElement> entry : jsonCerts.entrySet()) {
    	  
    	  
    	  log.debug("************** JSONELEMENT" + JsonElement.class.getProtectionDomain().getCodeSource().getLocation());
    	  log.debug("Key : " +entry.getKey() + " Value : " + entry.getValue()); 
        certs.put(entry.getKey(), entry.getValue().getAsString());
      }
      return certs;
    } catch (JsonSyntaxException e) {
      throw new GitkitServerException(e);
    }
  }

}
