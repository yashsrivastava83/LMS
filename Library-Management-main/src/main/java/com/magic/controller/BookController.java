package com.magic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.magic.entities.Book;
import com.magic.entities.BookCategory;
import com.magic.entities.BookPublisher;
import com.magic.service.BookCategoryService;
import com.magic.service.BookPublisherService;
import com.magic.service.BookService;

@Controller
@RequestMapping("/bookstore/inventory/")
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private BookCategoryService categoryService;
	@Autowired
	private BookPublisherService publisherService;

	@RequestMapping("/")
	public String getLoginView() {
		return "home/login";
	}

	@RequestMapping("login/validate")
	public String getLoginView(String username, String password, Model model) {
		if (username.equals("admin") && password.equals("admin123")) {
			return "home/home-page";
		}
		model.addAttribute("msg", "Wrong Credentials.");
		return "home/login";
	}
	

	@RequestMapping("login/home")
	public String getHomeView() {
		return "home/home-page";
	}

	@RequestMapping("login/book/manage/list")
	public String bookList(Model model) {
		model.addAttribute("pn", 1);
		model.addAttribute("tp", 1);
		List<Book> list = bookService.getBookList();
		model.addAttribute("blist", list);
		return "book/book-list";
	}

	@RequestMapping("login/book/manage/add")
	public String getAddBookView(Model model) {
		List<BookCategory> list1 = categoryService.getCategoryList();
		List<BookPublisher> list2 = publisherService.getPublisherList();
		model.addAttribute("plist", list2);
		model.addAttribute("clist", list1);
		return "book/add-book";
	}

	@RequestMapping("login/book/manage/save")
	public String saveCategory(Book book, Model model) {
		bookService.saveBook(book);
		List<BookCategory> list1 = categoryService.getCategoryList();
		List<BookPublisher> list2 = publisherService.getPublisherList();
		model.addAttribute("plist", list2);
		model.addAttribute("clist", list1);
		model.addAttribute("msg", "Book Added");
		return "book/add-book";
	}

	@RequestMapping("login/book/search")
	public String getSearchview(Model model) {
		List<BookCategory> list1 = categoryService.getCategoryList();
		List<BookPublisher> list2 = publisherService.getPublisherList();
		model.addAttribute("plist", list2);
		model.addAttribute("clist", list1);
		return "search/search-books";
	}

	

	@RequestMapping("login/book/manage/edit")
	public String updateBookView(@RequestParam int bid, Model model) {
		Book book = bookService.getBookByID(bid);
		model.addAttribute("book", book);
		model.addAttribute("bid", bid);
		List<BookCategory> list1 = categoryService.getCategoryList();
		List<BookPublisher> list2 = publisherService.getPublisherList();
		model.addAttribute("plist", list2);
		model.addAttribute("clist", list1);
		return "book/edit-book";
	}

	@RequestMapping("login/book/manage/update")
	public String updateBook(Book book, Model model) {
		bookService.saveBook(book);
		model.addAttribute("pn", 1);
		model.addAttribute("tp", 1);
		List<Book> list = bookService.getBookList();
		model.addAttribute("blist", list);
		return "book/book-list";
	}

	@RequestMapping("login/book/manage/remove")
	public String deleteById(@RequestParam int bid, Model model) {
		bookService.deleteById(bid);
		model.addAttribute("pn", 1);
		model.addAttribute("tp", 1);
		List<Book> list = bookService.getBookList();
		model.addAttribute("blist", list);
		return "book/book-list";
	}

	@RequestMapping("login/book/search/edit")
	public String updateByIdBookView(@RequestParam int bid, Model model) {
		Book book = bookService.getBookByID(bid);
		model.addAttribute("book", book);
		model.addAttribute("bid", bid);
		List<BookCategory> list1 = categoryService.getCategoryList();
		List<BookPublisher> list2 = publisherService.getPublisherList();
		model.addAttribute("plist", list2);
		model.addAttribute("clist", list1);
		return "book/edit-book";
	}

	@RequestMapping("login/book/search/update")
	public String updateBookById(Book book, Model model) {
		bookService.saveBook(book);
		model.addAttribute("pn", 1);
		model.addAttribute("tp", 1);
		List<Book> list = bookService.getBookList();
		model.addAttribute("blist", list);
		return "book/book-list";
	}

	@RequestMapping("login/book/search/remove")
	public String deleteSearchById(@RequestParam int bid, Model model) {
		bookService.deleteById(bid);
		model.addAttribute("pn", 1);
		model.addAttribute("tp", 1);
		List<Book> list = bookService.getBookList();
		model.addAttribute("blist", list);
		return "book/book-list";
	}

	
	@RequestMapping("login/book/search/byid")
	public String getBookById(@RequestParam int bid, Model model) {
		Book book = bookService.getBookByID(bid);
		List<Book> blist = new ArrayList<Book>();
		blist.add(book);
		model.addAttribute("blist", blist);
		return "search/book-list";
	}
	@RequestMapping("login/book/search/byauthor")
	public String getBookByAuthor(@RequestParam String author, Model model) {
		List<Book> blist = bookService.getBookByAuthor(author);
		model.addAttribute("blist", blist);
		return "search/book-list";
	}

	@RequestMapping("login/book/search/bytitle")
	public String getBookByTitle(@RequestParam String title, Model model) {
		List<Book> blist = bookService.getBookByTitle(title);
		model.addAttribute("blist", blist);
		return "search/book-list";
	}

	@RequestMapping("book/search")
	public String getBackSearchview(Model model) {
		List<BookCategory> list1 = categoryService.getCategoryList();
		List<BookPublisher> list2 = publisherService.getPublisherList();
		model.addAttribute("plist", list2);
		model.addAttribute("clist", list1);
		return "search/search-books";
	}

	@RequestMapping("login/book/search/bycategory")
	public String getBookByCategory(@RequestParam int cid, Model model) {
		List<Book> blist = bookService.getBookByCategory(cid);
		model.addAttribute("blist", blist);
		return "search/book-list";
	}

	@RequestMapping("login/book/search/bypublisher")
	public String getBookByPublisher(@RequestParam int pubid, Model model) {
		List<Book> blist = bookService.getBookByPublisher(pubid);
		model.addAttribute("blist", blist);
		return "search/book-list";
	}

	@RequestMapping("login/logout")
	public String logout(HttpSession ses) {
		ses.invalidate();
		return "home/login";
	}

}
