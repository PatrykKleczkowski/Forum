package forum.service;

import forum.model.Category;
import forum.model.Topic;
import forum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Topic> getTopicsFromCategory(String categoryName) {
        Category cat = categoryRepository.findByTitle(categoryName);
        List<Topic> topics = cat.getTopics();
        return topics;
    }
}
