package org.hiper.todolistapi.data;

import lombok.Getter;
import lombok.Setter;
import org.hiper.todolistapi.models.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Getter
@Setter
public class TodoRequest {
    @NotBlank
    @Size(max = 160)
    private String title;

    private Boolean completed = false;
    private Priority priority = Priority.MEDIUM;
    private LocalDate dueDate;


}
