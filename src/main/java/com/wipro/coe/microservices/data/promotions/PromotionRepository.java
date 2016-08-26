package com.wipro.coe.microservices.data.promotions;


import org.springframework.data.repository.CrudRepository;

import com.wipro.coe.microservices.data.promotions.entity.Promotion;



public interface PromotionRepository extends CrudRepository <Promotion, Long>{
	



}
