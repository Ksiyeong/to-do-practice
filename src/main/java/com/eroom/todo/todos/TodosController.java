package com.eroom.todo.todos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/")
@CrossOrigin("https://todobackend.com")
@Slf4j
public class TodosController {

    private final TodosService todosService;
    private final TodosMapper mapper;

    public TodosController(TodosService todosService, TodosMapper mapper) {
        this.todosService = todosService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTodos(@Valid @RequestBody TodosDto.Post todosPostDto) {
        log.info("postTodos 메서드 입니다");

        Todos todos = todosService.createTodos(mapper.todosPostDtoToTodos(todosPostDto));

        return new ResponseEntity(mapper.todosToTodosResponseDto(todos), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTodos(@Positive @PathVariable("id") long id,
                                     @Valid @RequestBody TodosDto.Patch todosPatchDto) {
        log.info("patchTodos 메서드 입니다");
        todosPatchDto.setId(id);

        Todos todos = todosService.updateTodos(mapper.todosPatchDtoToTodos(todosPatchDto));

        return new ResponseEntity<>(mapper.todosToTodosResponseDto(todos), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity patchAllCheck() {
        log.info("patchAllCheck 메서드 입니다");

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTodos(@Positive @PathVariable("id") long id) {
        log.info("getTodos 메서드 입니다");

        Todos todos = todosService.findTodos(id);
        return new ResponseEntity<>(mapper.todosToTodosResponseDto(todos), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodosList() {
        log.info("getTodosList 메서드 입니다");

        return new ResponseEntity(mapper.todosListToTodosResponseList(todosService.findTodosList()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodos(@Positive @PathVariable("id") long id) {
        log.info("deleteTodos 메서드 입니다");

        todosService.deleteTodos(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteAll() {
        log.info("deleteAll 메서드 입니다");

        todosService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
