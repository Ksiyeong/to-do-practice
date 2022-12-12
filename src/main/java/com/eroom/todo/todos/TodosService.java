package com.eroom.todo.todos;

import com.eroom.todo.exception.BusinessLogicException;
import com.eroom.todo.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodosService {
    private final TodosRepository todosRepository;

    public TodosService(TodosRepository todosRepository) {
        this.todosRepository = todosRepository;
    }

    public Todos createTodos(Todos todos) {
        return todosRepository.save(todos);
    }

    public Todos findTodos(long id) {
        return findVerifiedTodos(id);
    }

    public List<Todos> findTodosList() {
        return todosRepository.findAll();
    }

    public Todos updateTodos(Todos todos) {
        Todos findTodos = findTodos(todos.getId());

        Optional.ofNullable(todos.getTitle()).ifPresent(data -> findTodos.setTitle(data));
        Optional.ofNullable(todos.getTodo_order()).ifPresent(data -> findTodos.setTodo_order(data));
        Optional.ofNullable(todos.getCompleted()).ifPresent(data -> findTodos.setCompleted(data));

        return todosRepository.save(findTodos);
    }

    public void deleteTodos(long id) {
        todosRepository.delete(findVerifiedTodos(id));
    }

    public void deleteAll() {
        todosRepository.deleteAll();
    }

    private Todos findVerifiedTodos(long id) {
        Optional<Todos> optionalTodos = todosRepository.findById(id);
//        Todos todos = optionalTodos.orElseThrow(() -> new RuntimeException("Todos not found"));
        Todos todos = optionalTodos.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODOS_NOT_FOUND));

        return todos;
    }
}
