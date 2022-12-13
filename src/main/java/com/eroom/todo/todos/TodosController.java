package com.eroom.todo.todos;

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
public class TodosController {

    private final TodosService todosService;
    private final TodosMapper mapper;

    public TodosController(TodosService todosService, TodosMapper mapper) {
        this.todosService = todosService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTodos(@Valid @RequestBody TodosDto.Post todosPostDto) {
        Todos todos = todosService.createTodos(mapper.todosPostDtoToTodos(todosPostDto));

        return new ResponseEntity(todos, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTodos(@Positive @PathVariable("id") long id,
                                     @Valid @RequestBody TodosDto.Patch todosPatchDto) {
        todosPatchDto.setId(id);

        Todos todos = todosService.updateTodos(mapper.todosPatchDtoToTodos(todosPatchDto));

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTodos(@Positive @PathVariable("id") long id) {
        Todos todos = todosService.findTodos(id);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodosList() {
        return new ResponseEntity(todosService.findTodosList(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodos(@Positive @PathVariable("id") long id) {
        todosService.deleteTodos(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteAll() {
        todosService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
