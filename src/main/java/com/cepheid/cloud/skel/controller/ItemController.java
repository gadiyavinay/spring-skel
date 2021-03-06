package com.cepheid.cloud.skel.controller;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.cepheid.cloud.skel.enums.ItemState;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.repository.ItemRepository;

import io.swagger.annotations.Api;


// curl http:/localhost:9443/app/api/1.0/items

@Component
@Path("/api/1.0/items")
@Api()
public class ItemController {

  private final ItemRepository mItemRepository;

  @Autowired
  public ItemController(ItemRepository itemRepository) {
    mItemRepository = itemRepository;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
  public Collection<Item> getItems() {
    return (Collection<Item>) mItemRepository.findAll();
  }
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
  public Long saveItem(@RequestBody Item item) {
    mItemRepository.save(item);
    return item.getId();
  }
  
  @GET
  @Path("byName")
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
  public Collection<Item> findByName(@QueryParam("mName") String mName) {
    return mItemRepository.findByName(mName);
  }
  
  @GET
  @Path("byState")
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
  public Collection<Item> findByState(@QueryParam("itemState") ItemState itemState) {
    return mItemRepository.findByState(itemState);
  }
  
    
}
