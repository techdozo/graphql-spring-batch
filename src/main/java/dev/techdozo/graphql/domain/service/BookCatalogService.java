package dev.techdozo.graphql.domain.service;

import dev.techdozo.graphql.domain.model.Book;
import dev.techdozo.graphql.domain.model.Rating;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class BookCatalogService {

    private final HashMap<Integer, Book> bookRepository = new HashMap<>();
    private final HashMap<Integer, Rating> ratingRepository = new HashMap<>();

    public BookCatalogService() {
        bookRepository.put(1, new Book(1,"Zero to One","Peter Thiel",  "Crown Business", 16.14));
        bookRepository.put(2, new Book(2,"The Lean Startup","Eric Ries",  "VIKIN", 15.80));
        bookRepository.put(3, new Book(3,"Sapiens: A Brief History of Humankind","Yuval Noah Harari",  "Random House Uk", 18.75));
        bookRepository.put(4, new Book(4,"Atomic Habits","James Clear",  "Avery", 11.98));
        bookRepository.put(5, new Book(5,"The Subtle Art of Not Giving a F*ck","Mark Manson",  "Harper", 12.99));

        ratingRepository.put(1, new Rating(1,1,5,  "The 4 minutes that will help you decide if this book is for you", "Konstantinos Papakonstantinou"));
        ratingRepository.put(2, new Rating(2,1,3,  "Is Peter Thiel the next robber baron?", "Konstantinos Papakonstantinou"));
        ratingRepository.put(3, new Rating(3,1,3,  "Simple-minded. Is it satire? Poorly-reasoned? Historically-ignorant? Afraid of competition? IDK", "Todd Holscher"));

        ratingRepository.put(4, new Rating(4,3,5,  "The Intersubjective Realm", "P. Schuyler"));
        ratingRepository.put(5, new Rating(5,3,1,  "Don't be fooled. This book is opinion masquerading as science.", "Andrew Terhune"));
        ratingRepository.put(6, new Rating(6,3,1,  "Pretentious and pompous opinions presented as facts", "P. Daskaloff"));
    }

    public List<Rating> ratings(Book book) {
        return ratingRepository.values().stream().filter( r -> r.bookId().equals(book.id())).toList();
    }

    public Collection<Book> getBooks() {
        return bookRepository.values();
    }

    public Book bookById(Integer id) {
        return bookRepository.get(id);
    }
}

