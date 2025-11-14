package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.repositories.RepositorioColecciones;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

public class MainColecciones implements WithSimplePersistenceUnit {
  public static void main(String[] args) {
    RepositorioColecciones repositorioColecciones = RepositorioColecciones.getInstance();
    repositorioColecciones.consesuareEchos();
  }
}
