package com.cepheid.cloud.skel.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cepheid.cloud.skel.enums.ItemState;
import com.cepheid.cloud.skel.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	@Query("SELECT i FROM Item i WHERE i.mName = :mName")
    Collection<Item> findByName(@Param("mName") String mName);
	
	@Query("SELECT i FROM Item i WHERE i.itemState = :itemState")
	Collection<Item> findByState(@Param("itemState")ItemState itemState);

}
