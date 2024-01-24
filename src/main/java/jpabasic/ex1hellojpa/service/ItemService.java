package jpabasic.ex1hellojpa.service;
import jpabasic.ex1hellojpa.domain.item.Book;
import jpabasic.ex1hellojpa.domain.item.Item;
import jpabasic.ex1hellojpa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

}