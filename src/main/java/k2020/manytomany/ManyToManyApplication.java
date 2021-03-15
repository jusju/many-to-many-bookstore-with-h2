package k2020.manytomany;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k2020.manytomany.domain.Book;
import k2020.manytomany.domain.BookRepository;
import k2020.manytomany.domain.Category;
import k2020.manytomany.domain.CategoryRepository;
import k2020.manytomany.domain.Reader;
import k2020.manytomany.domain.ReaderRepository;

@SpringBootApplication
public class ManyToManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository
			, ReaderRepository readerRepository
			, CategoryRepository categoryRepository) {
		return (args) -> {

			System.out.println("save a couple of categories, books and readers");
			categoryRepository.save(new Category("Dekkari"));
			categoryRepository.save(new Category("Novelli"));
			categoryRepository.save(new Category("Dokumentti"));

			bookRepository.save(new Book("Kasvoton kuolema", "Hennig Mankell", 1991,
					categoryRepository.findByName("Dekkari").get(0)));
			bookRepository.save(new Book("Riian verikoirat", "Hennig Mankell", 1992,
					categoryRepository.findByName("Dekkari").get(0)));
			bookRepository.save(new Book("Koira", "Nina Mäki-Kihniä", 2017, 
					categoryRepository.findByName("Dokumentti").get(0)));
			
			Set<Book> likedBooks = new HashSet<Book>(0);
			likedBooks.add(bookRepository.findByTitle("Kasvoton kuolema").get(0));
			readerRepository.save(new Reader("Minna", likedBooks));
			likedBooks.add(bookRepository.findByTitle("Riian verikoirat").get(0));
			likedBooks.add(bookRepository.findByTitle("Koira").get(0));
			readerRepository.save(new Reader("Minna3", likedBooks));
			
			System.out.println("fetch all books");
			for (Book book : bookRepository.findAll()) {
				System.out.println(book.toString());
			}

			System.out.println();
			System.out.println("fetch all readers with book information ");
			for (Reader reader : readerRepository.findAll()) {
				System.out.println("Lukijan nimi " + reader.getName());
				Set<Book> lempikirjat = reader.getLikedBooks();
				System.out.println("lempikirjojen lukumäärä " + lempikirjat.size());
				for (Book lempikirja : lempikirjat) {
					System.out.println("Kirjan nimi " + lempikirja.getTitle());
				}
			}

			System.out.println();
			System.out.println("fetch all books with reader information");
			for (Book book : bookRepository.findAll()) {
				System.out.println("Kirjan nimi " + book.getTitle());
				Collection<Reader> lukijat = book.getReaders();
				System.out.println("lukijoiden lukumäärä " + lukijat.size());
				for (Reader lukija : lukijat) {
					System.out.println("Lukijan nimi " + lukija.getName());
				}
			}
		};
	}

}
