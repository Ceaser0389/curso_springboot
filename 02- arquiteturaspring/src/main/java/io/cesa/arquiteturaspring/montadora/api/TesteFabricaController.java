package io.cesa.arquiteturaspring.montadora.api;

import io.cesa.arquiteturaspring.montadora.CarroStatus;
import io.cesa.arquiteturaspring.montadora.Chave;
import io.cesa.arquiteturaspring.montadora.HondaHRV;
import io.cesa.arquiteturaspring.montadora.Motor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carros")
public class TesteFabricaController {
  
  @Autowired
 // @Qualifier("motorEletrico") // eu uso quando eu tenho varios beans
  private Motor motor;
  
  @PostMapping
  public CarroStatus Ligarcarro(@RequestBody Chave chave){
      var carro = new HondaHRV(motor);
       return carro.darIngnicao(chave);
  }
  
  
}
