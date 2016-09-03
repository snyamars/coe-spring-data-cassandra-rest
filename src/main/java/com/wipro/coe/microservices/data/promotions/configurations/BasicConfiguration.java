	package com.wipro.coe.microservices.data.promotions.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.Session;



@Configuration
@EnableAutoConfiguration
class BasicConfiguration {

	@Configuration
	@EnableCassandraRepositories
	static class CassandraConfig extends AbstractCassandraConfiguration {

	   @Autowired
	    private Environment environment;
	   
	   
		@Override
		public String getKeyspaceName() {
			return environment.getProperty("spring.data.cassandra.keyspace-name");
		}
		
		 
	    @Bean
	    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
	        return new BasicCassandraMappingContext();
	    }

		@Bean
		public CassandraTemplate cassandraTemplate(Session session) {
			return new CassandraTemplate(session);
		}

		@Bean
	    public CassandraClusterFactoryBean cluster() {
	        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
	        cluster.setContactPoints(environment.getProperty("spring.data.cassandra.contact-points"));
	        cluster.setPort(Integer.parseInt(environment.getProperty("spring.data.cassandra.port")));
	        return cluster;
	    }
		@Override
		public String[] getEntityBasePackages() {
			return new String[] { "com.wipro.coe.microservices.data.promotions.entity" };
		}

		@Override
		public SchemaAction getSchemaAction() {
			return SchemaAction.RECREATE;
		}
	}
}
