package io.cesa.arquiteturaspring.montadora;

import java.awt.*;

public class Carro {
  
  private String modelo;
  private Color cor;
  private Motor motor;
  private Montadora montadora;
  
  public Carro(Motor motor) {
    this.modelo = modelo;
    this.cor = cor;
    this.motor = motor;
    this.montadora = montadora;
  }
  
  public String getModelo() {
    return modelo;
  }
  
  public void setModelo(String modelo) {
    this.modelo = modelo;
  }
  
  public Color getCor() {
    return cor;
  }
  
  public void setCor(Color cor) {
    this.cor = cor;
  }
  
  public Motor getMotor() {
    return motor;
  }
  
  public void setMotor(Motor motor) {
    this.motor = motor;
  }
  
  public Montadora getMontadora() {
    return montadora;
  }
  
  public void setMontadora(Montadora montadora) {
    this.montadora = montadora;
  }
  
  public CarroStatus darIngnicao(Chave chave) {
     if ( chave.getMontadora() != this.montadora){
       return  new CarroStatus("Não é possível iniciar o carro com esta chave!");
     }
     return  new CarroStatus("Carro ligado. rodando com o motor " + motor);
  }
}
