package forum.config;

import forum.model.Category;
import forum.model.Topic;
import forum.security.model.User;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;


@Component
public class RestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
        config.setBasePath("/api");
        config.exposeIdsFor(Category.class);
        config.exposeIdsFor(Topic.class);
        config.exposeIdsFor(User.class);

    }
}