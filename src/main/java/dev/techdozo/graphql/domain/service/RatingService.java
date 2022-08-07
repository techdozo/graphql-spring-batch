package dev.techdozo.graphql.domain.service;

import dev.techdozo.graphql.domain.model.Book;
import dev.techdozo.graphql.domain.model.Rating;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private final HashMap<Integer, Rating> ratingRepository = new HashMap<>();

    public RatingService() {

        ratingRepository.put(1, new Rating(1,1,5,  "The 4 minutes that will help you decide if this book is for you", "Konstantinos Papakonstantinou"));
        ratingRepository.put(2, new Rating(2,1,3,  "Is Peter Thiel the next robber baron?", "Konstantinos Papakonstantinou"));
        ratingRepository.put(3, new Rating(3,1,3,  "Simple-minded. Is it satire? Poorly-reasoned? Historically-ignorant? Afraid of competition? IDK", "Todd Holscher"));

        ratingRepository.put(4, new Rating(4,3,5,  "The Intersubjective Realm", "P. Schuyler"));
        ratingRepository.put(5, new Rating(5,3,1,  "Don't be fooled. This book is opinion masquerading as science.", "Andrew Terhune"));
        ratingRepository.put(6, new Rating(6,3,1,  "Pretentious and pompous opinions presented as facts", "P. Daskaloff"));
    }

    public List<Rating> ratings(Book book) {
        return ratingRepository.values().stream().filter(r -> r.bookId().equals(book.id())).toList();
    }

    public Map<Book, List<Rating>> ratingsForBooks(List<Book> books) {
        return books.stream()
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                book ->
                                        ratingRepository.values().stream()
                                                .filter(r -> r.bookId().equals(book.id()))
                                                .toList()));
    }


}

