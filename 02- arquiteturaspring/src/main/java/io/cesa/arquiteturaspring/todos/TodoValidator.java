package io.cesa.arquiteturaspring.todos;

import org.springframework.stereotype.Component;

@Component
public class TodoValidator {
  
  private  TodoRepository repository;
  
  public TodoValidator(TodoRepository repository) {
    this.repository = repository;
  }
  
  public void validar(TodoEntity todo){
      if (existeTdoComDescricao(todo.getDescricao())){
        throw  new IllegalArgumentException("Ja existe um todo com esta descrição!");
      }
   }
   
   
   private  boolean existeTdoComDescricao(String descricao){
      return  repository.existsByDescricao(descricao);
   }
   
   
   
   
}
