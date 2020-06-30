package com.cepheid.cloud.skel;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.cepheid.cloud.skel.controller.ItemController;
import com.cepheid.cloud.skel.enums.ItemState;
import com.cepheid.cloud.skel.model.Descriptions;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.repository.ItemRepository;

@RunWith(SpringRunner.class)
public class ItemControllerTest extends TestBase {

	@InjectMocks
	ItemController restController;

	@Mock
	ItemRepository itemRepository;

	@Test
	public void testGetItems() throws Exception {
		Builder itemController = getBuilder("/app/api/1.0/items");

		Collection<Item> items = itemController.get(new GenericType<Collection<Item>>() {
		});

		Mockito.when(itemRepository.findAll()).thenReturn((List<Item>) items);

		Collection<Item> repoItems = itemRepository.findAll();

		assertEquals(items.size(), repoItems.size());

	}

	@Test
	public void testSaveItems() throws Exception {
		Item item = new Item();

		Mockito.when(itemRepository.save(item)).thenReturn(item);

		Long id = restController.saveItem(item);

		assertEquals(item.getId(), id);

	}

	@Test
	public void testfindByName() throws Exception {
		List<Descriptions> descList = new ArrayList<Descriptions>();
		Item item = new Item();
		List<Item> items = new ArrayList<Item>();
		descList.add(new Descriptions("This is desc item 1"));
		descList.add(new Descriptions("This is desc item 2"));
		item.setmName("item1");
		item.setItemState(ItemState.INVALID);
		item.setDescriptions(descList);
		items.add(item);
		items.add(item);

		Mockito.when(itemRepository.findByName("item1")).thenReturn(items);

		Collection<Item> repoItems = restController.findByName("item1");

		assertEquals(items.size(), repoItems.size());
		assertEquals(repoItems.stream().findFirst().get().getmName(), "item1");
		assertEquals(repoItems.stream().findFirst().get().getDescriptions().size(), 2);

	}

	@Test
	public void testfindByState() throws Exception {
		Item item = new Item();
		List<Item> items = new ArrayList<Item>();
		item.setmName("item1");
		item.setItemState(ItemState.UNDEFINED);
		items.add(item);

		Mockito.when(itemRepository.findByState(ItemState.UNDEFINED))
				.thenReturn(items);

		Collection<Item> repoItems = restController.findByState(ItemState.UNDEFINED);
		assertEquals(repoItems.stream().findFirst().get().getItemState(), ItemState.UNDEFINED);

	}
}
