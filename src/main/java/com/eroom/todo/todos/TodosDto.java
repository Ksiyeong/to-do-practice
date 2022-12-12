package com.eroom.todo.todos;

import lombok.Getter;
import lombok.Setter;

public class TodosDto {
    @Getter
    public static class Post {
        private String title;
        private Long todo_order;
        private Boolean completed;
    }

    @Getter
    public static class Patch {
        @Setter
        private Long id;
        private String title;
        private Long todo_order;
        private Boolean completed;
    }

}
