package org.hiper.todolistapi.controllers;

import lombok.extern.log4j.Log4j2;
import org.hiper.todolistapi.data.TodoRequest;
import org.hiper.todolistapi.data.TodoResponse;
import org.hiper.todolistapi.mapper.TodoMapper;
import org.hiper.todolistapi.models.Todo;
import org.hiper.todolistapi.services.TodoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/todos")
@Log4j2
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<TodoResponse> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        return service.list(page, size, sortBy, direction)
                .map(TodoMapper::toResponse);
    }

    @PostMapping
    public ResponseEntity<TodoResponse> create(@Valid @RequestBody TodoRequest request) {
        log.debug(request);
        Todo entity = TodoMapper.toEntity(request);
        log.debug(entity);
        Todo saved = service.create(entity);
        log.debug(saved);
        return ResponseEntity
                .created(URI.create("/api/todos/" + saved.getId()))
                .body(TodoMapper.toResponse(saved));
    }

    @GetMapping("/{id}")
    public TodoResponse get(@PathVariable Long id) {
        return TodoMapper.toResponse(service.get(id));
    }

    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Long id, @Valid @RequestBody TodoRequest request) {
        Todo entity = TodoMapper.toEntity(request);
        return TodoMapper.toResponse(service.update(id, entity));
    }

    @PatchMapping("/{id}/complete")
    public TodoResponse complete(@PathVariable Long id) {
        return TodoMapper.toResponse(
                service.patch(id, t -> t.setCompleted(true))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
