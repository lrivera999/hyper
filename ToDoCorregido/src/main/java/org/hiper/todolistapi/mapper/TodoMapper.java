package org.hiper.todolistapi.mapper;

import org.hiper.todolistapi.data.TodoRequest;
import org.hiper.todolistapi.data.TodoResponse;
import org.hiper.todolistapi.models.Todo;

public class TodoMapper {

    public static Todo toEntity(TodoRequest req) {
        Todo t = new Todo();
        t.setTitle(req.getTitle());
        t.setCompleted(req.getCompleted() != null ? req.getCompleted() : false);
        t.setPriority(req.getPriority());
        t.setDueDate(req.getDueDate());
        return t;
    }

    public static void updateEntity(Todo entity, TodoRequest req) {
        if (req.getTitle() != null) entity.setTitle(req.getTitle());
        if (req.getCompleted() != null) entity.setCompleted(req.getCompleted());
        if (req.getPriority() != null) entity.setPriority(req.getPriority());
        if (req.getDueDate() != null) entity.setDueDate(req.getDueDate());
    }

    public static TodoResponse toResponse(Todo t) {
        return new TodoResponse(
                t.getId(), t.getTitle(), t.isCompleted(),
                t.getPriority(), t.getDueDate()
        );
    }
}
