package org.hiper.todolistapi.services;

import org.hiper.todolistapi.models.Todo;
import org.hiper.todolistapi.models.TodoRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

@Service
@Transactional
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Page<Todo> list(int page, int size, String sortBy, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return repository.findAll(pageable);
    }

    public Todo create(Todo todo) {
        return repository.save(todo);
    }

    public Todo get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Todo no encontrado: " + id));
    }

    public Todo update(Long id, Todo data) {
        Todo existing = get(id);
        existing.setTitle(data.getTitle());
        existing.setCompleted(data.isCompleted());
        existing.setPriority(data.getPriority());
        existing.setDueDate(data.getDueDate());
        return repository.save(existing);
    }

    public Todo patch(Long id, Consumer<Todo> patcher) {
        Todo existing = get(id);
        patcher.accept(existing);
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Todo no encontrado: " + id);
        }
        repository.deleteById(id);
    }
}
