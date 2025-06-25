package io.cesa.arquiteturaspring;

import io.cesa.arquiteturaspring.todos.TodoEntity;
import io.cesa.arquiteturaspring.todos.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  aqui eu chamo pela propriedade Bean
 * @Scope(BeanDefinition.SCOPE_SINGLETON)
 * @Scope(BeanDefinition.SCOPE_PROTOTYPE) // varias instancias
 * @Scope(WebApplicationContext.SCOPE_REQUEST)
 * @Scope(WebApplicationContext.SCOPE_SESSION)
 * @Scope(WebApplicationContext.SCOPE_APPLICATION)
 */

// escopo padrão de um bean singleton (instancia unica)
//@Scope("singleton")
//@Scope("request") é uma instancia para cada requisção,faz sentido app web
//@Scope("session") dura até quando a sessão do usuario durar,faz sentido app web
//@Scope("application") ele parecido com session, mas se extende p todos usuarios

/**
 * instanciando só se eu for usar obj
 * @Lazy(true) quando for usar
 *
 */

 
@Component
public class BeanGerenciado {
  
   @Autowired  // 3 forma
   private TodoValidator validator;   // injeção via propriedade
  
  @Autowired
  private  AppProperties properties;
  
  /**
   *  construtor não prec @Autowired, o const da idea de obrigatoriedade, recomen p spring 1 forma
   *  mais explicito
    */
  
  @Autowired
  public BeanGerenciado(TodoValidator validator) {
    this.validator = validator;
   
  }
  
  public void utilizar(){
     var todo = new TodoEntity();
     validator.validar(todo);
   }
  
   //via setter nessa opção precisa @autowired ,2 forma repr ideia opcional
  @Autowired
  public void setValidator(TodoValidator validator) {
    this.validator = validator;
  }
}
