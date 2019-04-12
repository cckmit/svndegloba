package com.degloba.integration.spring.mongodb.rules;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.junit.Rule;

// Spring Data
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;

/**
 * Convenience base class that enables unit test methods to rely upon the {@link MongoDbAvailable} annotation.
 *
 * @author Oleg Zhurakousky
 * @since 2.1
 */
public abstract class MongoDbAvailableTests {

	@Rule
	public MongoDbAvailableRule redisAvailableRule = new MongoDbAvailableRule();


	protected MongoDbFactory prepareMongoFactory(String... additionalCollectionsToDrop) throws Exception {
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new MongoClient(), "test");
		cleanupCollections(mongoDbFactory, additionalCollectionsToDrop);
		return mongoDbFactory;
	}

	protected void cleanupCollections(MongoDbFactory mongoDbFactory, String... additionalCollectionsToDrop) {
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		template.dropCollection("messages");
		template.dropCollection("configurableStoreMessages");
		template.dropCollection("data");
		for (String additionalCollection : additionalCollectionsToDrop) {
			template.dropCollection(additionalCollection);
		}
	}

	public Person createPerson() {
		Address address = new Address();
		address.setCity("Philadelphia");
		address.setStreet("2121 Rawn street");
		address.setState("PA");

		Person person = new Person();
		person.setAddress(address);
		person.setName("Oleg");
		return person;
	}

	public Person createPerson(String name) {
		Address address = new Address();
		address.setCity("Philadelphia");
		address.setStreet("2121 Rawn street");
		address.setState("PA");

		Person person = new Person();
		person.setAddress(address);
		person.setName(name);
		return person;
	}

	public static class Person {

		private Address address;

		private String name;

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public static class Address {

		private String street;

		private String city;

		private String state;

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

	}

	public static class TestMongoConverter extends MappingMongoConverter {

		public TestMongoConverter(
				MongoDbFactory mongoDbFactory,
				MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext) {
			super(new DefaultDbRefResolver(mongoDbFactory), mappingContext);
		}


	}

}
