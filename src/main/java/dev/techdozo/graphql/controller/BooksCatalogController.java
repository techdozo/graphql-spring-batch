package dev.techdozo.graphql.controller;

import dev.techdozo.graphql.domain.model.Book;
import dev.techdozo.graphql.domain.model.Rating;
import dev.techdozo.graphql.domain.service.BookCatalogService;
import dev.techdozo.graphql.domain.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Controller
public class BooksCatalogController {

  private final BookCatalogService bookCatalogService;
  private final RatingService ratingService;

  public BooksCatalogController(
      BookCatalogService bookCatalogService,
      RatingService ratingService,
      BatchLoaderRegistry batchLoaderRegistry) {
    this.bookCatalogService = bookCatalogService;
    this.ratingService = ratingService;
    //Data Loader approach
//    batchLoaderRegistry
//        .forTypePair(Book.class, List.class)
//        .registerMappedBatchLoader(
//            (books, env) -> {
//              log.info("Calling loader for books {}", books);
//              Map bookListMap = ratingService.ratingsForBooks(List.copyOf(books));
//              return Mono.just(bookListMap);
//            });
  }

  @QueryMapping()
  public Collection<Book> books() {
    log.info("Fetching all books..");
    return bookCatalogService.getBooks();
  }



  @BatchMapping
  public Map<Book, List<Rating>> ratings(List<Book> books) {
    log.info("Fetching ratings for books {} ", books);
    return ratingService.ratingsForBooks(books);
  }

//  @SchemaMapping
//  public CompletableFuture<List<Rating>> ratings(Book book, DataLoader<Book, List<Rating>> loader) {
//    log.info("Fetching rating for book, id {}", book.id());
//    return loader.load(book);
//  }
}
