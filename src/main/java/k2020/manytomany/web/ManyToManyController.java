package k2020.manytomany.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import k2020.manytomany.domain.Book;
import k2020.manytomany.domain.BookRepository;
import k2020.manytomany.domain.CategoryRepository;
import k2020.manytomany.domain.Reader;
import k2020.manytomany.domain.ReaderRepository;

@Controller
public class ManyToManyController {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ReaderRepository readerRepository;

	// Read books from H2 and show on the booklist.html page
	@GetMapping(value = "/booklist")
	public String booklist(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}

	// Open addNewBook.html where you can insert new book information
	@GetMapping("addNewBook")
	public String addNewBook(Model model) {
		model.addAttribute("newBook", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addNewBook";
	}

	// Save new or edited book to H2
	@PostMapping("saveBook")
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "redirect:booklist";
	}

	// Open page where you can edit the book information
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBookId(@PathVariable("id") Long bookId, Model model) {
		System.out.println("edit book information ");
		model.addAttribute("editBook", bookRepository.findById(bookId).get());
		model.addAttribute("categories", categoryRepository.findAll());
		return "editBook";
	}

	/*
	 * Show html page where is listed all readers with the information about liked
	 * books
	 */
	@GetMapping(value = "readerLikes")
	public String readerLikes(Model model) {
		model.addAttribute("readers", readerRepository.findAll());
		return "readerLikes";
	}

	
	@RequestMapping(value = "/editReaderLikes/{id}", method = RequestMethod.GET)
		public String editReaderLikes(@PathVariable("id") Long readerId, Model model) {
	    	System.out.println("controller: editoidaan lukijan kirjatykkäämisiä ");
	    	model.addAttribute("editReader", readerRepository.findById(readerId).get());
			//model.addAttribute("editBook", bookRepository.findById(bookId).get());
			model.addAttribute("selectableBooks", bookRepository.findAll());
	        return "editReaderLikes";
	}
			
	//save to H2 information which books reader likes
	@PostMapping("saveReaderLikes")
	public String saveReaderLikes(Reader reader) {
		readerRepository.save(reader);
		return "redirect:readerLikes";
	}

}
