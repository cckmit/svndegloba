package com.degloba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @category Un missatge (POJO) on podem guardar qualsevol tipus d'informaciĆ³ 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
	
  private String messageId;
  private String publishTime;
  private String data;

  public Message(String messageId) {
    this.messageId = messageId;
  }

}
