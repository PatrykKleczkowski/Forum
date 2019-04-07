package forum.repository;

import forum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);
    List<Category> findAll();


}