package com.wipro.coe.microservices.data.promotions;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.wipro.coe.microservices.data.promotions.entity.Promotion;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@Slf4j
public class CoeSpringDataCassandraRestApplication {

	public static void main(String[] args) {
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new ClassPathResource("resources/spring-config.xml").getPath());
		SpringApplication.run(CoeSpringDataCassandraRestApplication.class, args);
	}
	
	@Autowired PromotionRepository promotionrepository ;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Value("${spring.application.name:coe-cassandra-promotions}")
	private String appName;

	
	

	  @PostConstruct
	  void checkitOut() {

		  Promotion promotion = new Promotion();
		  Long longid = 121L;
		  promotion.setId(longid);
		  promotion.setDescription("Description 1");
		  promotion.setPromotionEndDate(new Date());
		  promotion.setPromotionStartDate(new Date());
		  promotion.setPromotionOwnerName("ranajit jana");
		  promotion.setLastAction("add");
		  promotion.setLastActionBy("Ranajit Jana");
		  
//		  List<Offer> offerList = new ArrayList<Offer>();
//		  Offer offer1 = new Offer();
//		  offer1.setOfferDescription("Test Offer1");
//		  offer1.setOfferId("OfferID1");
//		  Offer offer2 = new Offer();
//		  offer2.setOfferDescription("Test Offer2");
//		  offer2.setOfferId("OfferID2");
//		  offerList.add(offer1);
//		  offerList.add(offer2);
//		  promotion.setOffers(offerList);
//		  
				  
	    promotionrepository.save(promotion);


	    for (Promotion promotion1 : promotionrepository.findAll()) {
	      System.out.println("Hello " + promotion1.toString());
	    }
      }

}