/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.degloba.persistence.nosql.googleDatastore.api.lowlevel;

import java.util.List;

import com.degloba.domain.Message;



public interface MessageRepository {

  /** Save message to persistent storage. */
  void save(Message message);

  /**
   * Retrieve most recent stored messages.
   * @param limit number of messages
   * @return list of messages
   */
  List<Message> retrieve(int limit);
}
