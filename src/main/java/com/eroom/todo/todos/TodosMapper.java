package com.eroom.todo.todos;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodosMapper {
    Todos todosPostDtoToTodos(TodosDto.Post todosPostDto);

    Todos todosPatchDtoToTodos(TodosDto.Patch todosPatchDto);

    default TodosDto.Response todosToTodosResponseDto(Todos todos) {
        if ( todos == null ) {
            return null;
        }

        TodosDto.Response response = new TodosDto.Response();

        response.setId( todos.getId() );
        response.setTitle( todos.getTitle() );
        response.setOrder( todos.getOrder() );
        response.setCompleted( todos.getCompleted() );
        response.setUrl("http://localhost:8080/" + todos.getId());

        return response;
    }

    List<TodosDto.Response> todosListToTodosResponseList(List<Todos> todosList);
}
