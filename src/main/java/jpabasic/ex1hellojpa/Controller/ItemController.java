package jpabasic.ex1hellojpa.Controller;


import jpabasic.ex1hellojpa.domain.item.Book;
import jpabasic.ex1hellojpa.domain.item.Item;
import jpabasic.ex1hellojpa.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form",new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm bookForm){
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());
        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findAll();
        model.addAttribute("items",items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId,Model model){
        Book item  = (Book) itemService.findOne(itemId);
        BookForm bookForm = new BookForm();
        bookForm.setId(item.getId());
        bookForm.setName(item.getName());
        bookForm.setPrice(item.getPrice());
        bookForm.setStockQuantity(item.getStockQuantity());
        bookForm.setAuthor(item.getAuthor());
        bookForm.setIsbn(item.getIsbn());

        model.addAttribute("form",bookForm);

        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") String itemId, @ModelAttribute("form") BookForm bookForm){
        Book book = new Book();
        book.setId(bookForm.getId());
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());

        itemService.saveItem(book);
        return "redirect:/items";
    }
}

