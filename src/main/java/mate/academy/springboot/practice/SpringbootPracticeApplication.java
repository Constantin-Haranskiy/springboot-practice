package mate.academy.springboot.practice;

import java.math.BigDecimal;
import mate.academy.springboot.practice.model.Book;
import mate.academy.springboot.practice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootPracticeApplication {
    private final BookService bookService;

    @Autowired
    SpringbootPracticeApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPracticeApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            Book book = new Book();
            book.setTitle("To Kill a Mockingbird");
            book.setAuthor("Harper Lee");
            book.setIsbn("1234567890123");
            book.setPrice(new BigDecimal(100));
            book.setDescription("Book description");
            book.setCoverImage("https://google.com");

            bookService.save(book);
            bookService.findAll().forEach(System.out::println);
        };
    }
}
