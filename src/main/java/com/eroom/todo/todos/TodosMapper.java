package com.eroom.todo.todos;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodosMapper {
    Todos todosPostDtoToTodos(TodosDto.Post todosPostDto);

    Todos todosPatchDtoToTodos(TodosDto.Patch todosPatchDto);
}
