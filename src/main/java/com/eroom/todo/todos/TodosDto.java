package com.eroom.todo.todos;

import lombok.Getter;
import lombok.Setter;

public class TodosDto {
    @Getter
    public static class Post {
        private String title;
        private Long order;
        private Boolean completed = false;
    }

    @Getter
    public static class Patch {
        @Setter
        private Long id;
        private String title;
        private Long order;
        private Boolean completed;
    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String title;
        private Long order;
        private Boolean completed;

        private String url;
    }
}
