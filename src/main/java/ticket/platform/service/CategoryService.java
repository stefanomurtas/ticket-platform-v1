package ticket.platform.service;

import org.springframework.stereotype.Service;
import ticket.platform.entity.Category;
import ticket.platform.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Categoria non trovata"));
    }

    public Category createCategory(String name) {
        Category category = new Category(LocalDateTime.now(), LocalDateTime.now(), name);
        return categoryRepository.save(category);
    }
}