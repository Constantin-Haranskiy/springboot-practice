package mate.academy.springboot.practice.repository;

import mate.academy.springboot.practice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
