package org.hiper.todolistapi.data;

import org.hiper.todolistapi.models.Priority;

import java.time.LocalDate;

public class TodoResponse {
    private Long id;
    private String title;
    private boolean completed;
    private Priority priority;
    private LocalDate dueDate;

    public TodoResponse(Long id, String title, boolean completed, Priority priority, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    // getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }
    public Priority getPriority() { return priority; }
    public LocalDate getDueDate() { return dueDate; }
}
