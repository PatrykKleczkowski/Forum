package forum.controller;

import forum.model.Category;
import forum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.ws.Response;
import java.util.List;

@RepositoryRestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategoriesByCategoryType(@RequestParam("categoryType") String categoryType){
        return ResponseEntity.ok(categoryService.getCategoriesByCategoryType(categoryType));
    }
}
