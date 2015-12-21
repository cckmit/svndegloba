package com.degloba.integration.spring.mongodb.store;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;

import org.junit.Test;

import org.springframework.context.support.GenericApplicationContext;

// Spring Data
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.degloba.integration.spring.mongodb.rules.MongoDbAvailable;
import com.degloba.integration.spring.mongodb.rules.MongoDbAvailableTests;

//Spring Integration
import org.springframework.integration.mongodb.store.ConfigurableMongoDbMessageStore;
import org.springframework.integration.mongodb.store.MongoDbMessageStore;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.test.util.TestUtils;
import org.springframework.integration.transformer.ClaimCheckInTransformer;
import org.springframework.integration.transformer.ClaimCheckOutTransformer;

import org.springframework.messaging.Message;

import com.mongodb.MongoClient;

/**
 * @author Mark Fisher
 * @author Artem Bilan
 */
public class MongoDbMessageStoreClaimCheckIntegrationTests extends MongoDbAvailableTests {

	@Test
	@MongoDbAvailable
	public void stringPayload() throws Exception {
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new MongoClient(), "test");
		MongoDbMessageStore messageStore = new MongoDbMessageStore(mongoDbFactory);
		messageStore.afterPropertiesSet();
		ClaimCheckInTransformer checkin = new ClaimCheckInTransformer(messageStore);
		ClaimCheckOutTransformer checkout = new ClaimCheckOutTransformer(messageStore);
		Message<?> originalMessage = MessageBuilder.withPayload("test1").build();
		Message<?> claimCheckMessage = checkin.transform(originalMessage);
		assertEquals(originalMessage.getHeaders().getId(), claimCheckMessage.getPayload());
		Message<?> checkedOutMessage = checkout.transform(claimCheckMessage);
		assertEquals(claimCheckMessage.getPayload(), checkedOutMessage.getHeaders().getId());
		assertEquals(originalMessage.getPayload(), checkedOutMessage.getPayload());
		assertEquals(originalMessage, checkedOutMessage);
	}

	@Test
	@MongoDbAvailable
	public void objectPayload() throws Exception {
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new MongoClient(), "test");
		MongoDbMessageStore messageStore = new MongoDbMessageStore(mongoDbFactory);
		messageStore.afterPropertiesSet();
		ClaimCheckInTransformer checkin = new ClaimCheckInTransformer(messageStore);
		ClaimCheckOutTransformer checkout = new ClaimCheckOutTransformer(messageStore);
		Beverage payload = new Beverage();
		payload.setName("latte");
		payload.setShots(3);
		payload.setIced(false);
		Message<?> originalMessage = MessageBuilder.withPayload(payload).build();
		Message<?> claimCheckMessage = checkin.transform(originalMessage);
		assertEquals(originalMessage.getHeaders().getId(), claimCheckMessage.getPayload());
		Message<?> checkedOutMessage = checkout.transform(claimCheckMessage);
		assertEquals(originalMessage.getPayload(), checkedOutMessage.getPayload());
		assertEquals(claimCheckMessage.getPayload(), checkedOutMessage.getHeaders().getId());
		assertEquals(originalMessage, checkedOutMessage);
	}

	@Test
	@MongoDbAvailable
	public void stringPayloadConfigurable() throws Exception {
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new MongoClient(), "test");
		ConfigurableMongoDbMessageStore messageStore = new ConfigurableMongoDbMessageStore(mongoDbFactory);
		GenericApplicationContext testApplicationContext = TestUtils.createTestApplicationContext();
		testApplicationContext.refresh();
		messageStore.setApplicationContext(testApplicationContext);
		messageStore.afterPropertiesSet();
		ClaimCheckInTransformer checkin = new ClaimCheckInTransformer(messageStore);
		ClaimCheckOutTransformer checkout = new ClaimCheckOutTransformer(messageStore);
		Message<?> originalMessage = MessageBuilder.withPayload("test1").build();
		Message<?> claimCheckMessage = checkin.transform(originalMessage);
		assertEquals(originalMessage.getHeaders().getId(), claimCheckMessage.getPayload());
		Message<?> checkedOutMessage = checkout.transform(claimCheckMessage);
		assertEquals(claimCheckMessage.getPayload(), checkedOutMessage.getHeaders().getId());
		assertEquals(originalMessage.getPayload(), checkedOutMessage.getPayload());
		assertEquals(originalMessage, checkedOutMessage);
	}

	@Test
	@MongoDbAvailable
	public void objectPayloadConfigurable() throws Exception {
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new MongoClient(), "test");
		ConfigurableMongoDbMessageStore messageStore = new ConfigurableMongoDbMessageStore(mongoDbFactory);
		GenericApplicationContext testApplicationContext = TestUtils.createTestApplicationContext();
		testApplicationContext.refresh();
		messageStore.setApplicationContext(testApplicationContext);
		messageStore.afterPropertiesSet();
		ClaimCheckInTransformer checkin = new ClaimCheckInTransformer(messageStore);
		ClaimCheckOutTransformer checkout = new ClaimCheckOutTransformer(messageStore);
		Beverage payload = new Beverage();
		payload.setName("latte");
		payload.setShots(3);
		payload.setIced(false);
		Message<?> originalMessage = MessageBuilder.withPayload(payload).build();
		Message<?> claimCheckMessage = checkin.transform(originalMessage);
		assertEquals(originalMessage.getHeaders().getId(), claimCheckMessage.getPayload());
		Message<?> checkedOutMessage = checkout.transform(claimCheckMessage);
		assertEquals(originalMessage.getPayload(), checkedOutMessage.getPayload());
		assertEquals(claimCheckMessage.getPayload(), checkedOutMessage.getHeaders().getId());
		assertEquals(originalMessage, checkedOutMessage);
	}

	@SuppressWarnings("serial")
	private static class Beverage implements Serializable {

		private String name;
		private int shots;
		private boolean iced;

		@SuppressWarnings("unused")
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@SuppressWarnings("unused")
		public int getShots() {
			return shots;
		}

		public void setShots(int shots) {
			this.shots = shots;
		}

		@SuppressWarnings("unused")
		public boolean isIced() {
			return iced;
		}

		public void setIced(boolean iced) {
			this.iced = iced;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (iced ? 1231 : 1237);
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + shots;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Beverage other = (Beverage) obj;
			if (iced != other.iced) {
				return false;
			}
			if (name == null) {
				if (other.name != null) {
					return false;
				}
			}
			else if (!name.equals(other.name)) {
				return false;
			}
			if (shots != other.shots) {
				return false;
			}
			return true;
		}

	}

}