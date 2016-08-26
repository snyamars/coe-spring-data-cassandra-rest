/*
 * Copyright 2016 the original author or authors.
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
package com.wipro.coe.microservices.data.promotions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.convert.CustomConversions;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.util.StringUtils;

import com.datastax.driver.core.Row;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.coe.microservices.data.promotions.entity.CustomPromotion;
import com.wipro.coe.microservices.data.promotions.entity.Offer;
import com.wipro.coe.microservices.data.promotions.entity.Promotion;

/**
 * {@link Configuration} class to register custom converters.
 * 
 * changes done . 
 * @author ranajit jana
 */
@Configuration
@EnableCassandraRepositories
class ConverterConfiguration extends AbstractCassandraConfiguration {

	   @Autowired
	    private Environment environment;
	   
	@Override
	public String getKeyspaceName() {
		return environment.getProperty("spring.data.cassandra.keyspace-name");
	}

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { Promotion.class.getPackage().getName() };
	}

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.RECREATE;
	}

	@Override
	public CustomConversions customConversions() {

		List<Converter<?, ?>> converters = new ArrayList<>();
		converters.add(new OfferWriteConverter());
		converters.add(new OfferReadConverter());
		converters.add(new CustomPromotionReadConverter());

		return new CustomConversions(converters);
	}

	/**
	 * Write a {@link Contact} into its {@link String} representation.
	 */
	static class OfferWriteConverter implements Converter<Offer, String> {

		public String convert(Offer source) {

			try {
				return new ObjectMapper().writeValueAsString(source);
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
	}

	/**
	 * Read a {@link Contact} from its {@link String} representation.
	 */
	static class OfferReadConverter implements Converter<String, Offer> {

		public Offer convert(String source) {

			if (StringUtils.hasText(source)) {
				try {
					return new ObjectMapper().readValue(source, Offer.class);
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
			return null;
		}
	}

	/**
	 * Perform custom mapping by reading a {@link Row} into a custom class.
	 */
	static class CustomPromotionReadConverter implements Converter<Row, CustomPromotion> {

		public CustomPromotion convert(Row source) {

			CustomPromotion result = new CustomPromotion();

			result.setTheId(source.getString("id"));
			result.setConvertOffers(source.getString("convertOffers"));

			return result;
		}
	}
}
