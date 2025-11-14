package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.repositories.RepositorioFuentes;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

public class MainFuentes implements WithSimplePersistenceUnit {
  public static void main(String[] args) {
    RepositorioFuentes repositorioFuentes = RepositorioFuentes.getInstance();
    repositorioFuentes.actualizarHechos();
  }
}


