package k2020.manytomany.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "reader")
public class Reader {
	
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	private String name;
	

	@ManyToMany(fetch = FetchType.EAGER)
	//@ManyToMany
    @JoinTable(name = "reader_likes",
            joinColumns = {
                    @JoinColumn(name = "reader_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "book_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Book> likedBooks = new HashSet<Book>(0);
	
	public Reader() {
		super();
	}

	public Reader(String name) {
		super();
		this.name = name;
	}



	public Reader(String name, Set<Book> likedBooks) {
		super();
		this.name = name;
		this.likedBooks = likedBooks;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getLikedBooks() {
		return Collections.unmodifiableSet(likedBooks);
	}

	public void setLikedBooks(Set<Book> likedBooks) {
		this.likedBooks = likedBooks;
	}


	
	



}
