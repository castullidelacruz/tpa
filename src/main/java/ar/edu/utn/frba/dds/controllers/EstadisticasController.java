package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.model.estadistica.ComponenteEstadistico;
import ar.edu.utn.frba.dds.model.estadistica.EstadisticaCantidadSpam;
import ar.edu.utn.frba.dds.model.estadistica.EstadisticaCategoriaMaxima;
import ar.edu.utn.frba.dds.model.estadistica.EstadisticaHoraHechosCategoria;
import ar.edu.utn.frba.dds.model.estadistica.EstadisticaProvMaxHechosCategoria;
import ar.edu.utn.frba.dds.model.estadistica.EstadisticaProvMaxHechosColeccion;
import ar.edu.utn.frba.dds.repositories.RepositorioColecciones;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.io.File;


public class EstadisticasController implements WithSimplePersistenceUnit {

  static RepositorioColecciones repoC = RepositorioColecciones.getInstance();

  public EstadisticasController() {
  }

  // --- Mostrar Estadisticas ---
  public static Map<String, Object> mostrarSpam(Context ctx) throws IOException {
    repoC.consesuareEchos();
    
    String path = "descargar";
    File carpeta = new File(path);
    if (!carpeta.exists()) {
      boolean creada = carpeta.mkdirs();
    }
    
    EstadisticaCantidadSpam estadisticaSpam = new EstadisticaCantidadSpam();
    estadisticaSpam.calcularEstadistica();
    estadisticaSpam.exportarEstadistica("descargar/estadisticas_cantidad_spam.csv");

    var cantSpam =  estadisticaSpam.getCantidadSpam();
    var fechaAhora = LocalDateTime.now();

    Map<String, Object> model = Map.of(
        "cantidadSpam", cantSpam,
        "fecha", fechaAhora
    );

    ctx.render("estadisticaSpam.hbs", model);
    return model;
  }

  public static Map<String, Object> mostrarHoraPico(Context ctx) throws IOException {
    repoC.consesuareEchos();

    String path = "descargar";
    File carpeta = new File(path);
    if (!carpeta.exists()) {
      boolean creada = carpeta.mkdirs();
    }

    EstadisticaHoraHechosCategoria estadisticaHhc =
        ComponenteEstadistico.getInstance()
            .getEstadistica(EstadisticaHoraHechosCategoria.class);
    estadisticaHhc.calcularEstadistica();

    estadisticaHhc.exportarEstadistica("descargar/estadisticas_categoria_horaspico.csv");

    var reporte =  estadisticaHhc.getReporte();
    var fechaAhora = LocalDateTime.now();

    Map<String, Object> model = Map.of(
        "fecha", fechaAhora,
        "reporte", reporte
    );

    ctx.render("estadisticaHoraPico.hbs", model);
    return model;
  }

  public static Map<String, Object> mostrarCategoriaMaxima(Context ctx) throws IOException {
    repoC.consesuareEchos();

    String path = "descargar";
    File carpeta = new File(path);
    if (!carpeta.exists()) {
      boolean creada = carpeta.mkdirs();
    }

    EstadisticaCategoriaMaxima estadisticaCm = new EstadisticaCategoriaMaxima();
    estadisticaCm.calcularEstadistica();

    estadisticaCm.exportarEstadistica("descargar/estadisticas_categoria_maxima.csv");

    var reporte =  estadisticaCm.getReporte();
    var fechaAhora = LocalDateTime.now();

    Map<String, Object> model = Map.of(
        "fecha", fechaAhora,
        "reporte", reporte
    );
    ctx.render("estadisticaCategoriaMaxima.hbs", model);
    return model;
  }

  public static Map<String, Object> mostrarCategoriaProvinciaMaxHechos(Context ctx)
      throws IOException {
    repoC.consesuareEchos();

    String path = "descargar";
    File carpeta = new File(path);
    if (!carpeta.exists()) {
      boolean creada = carpeta.mkdirs();
    }

    EstadisticaProvMaxHechosCategoria estadisticaPmhCat = new EstadisticaProvMaxHechosCategoria();
    estadisticaPmhCat.calcularEstadistica();

    estadisticaPmhCat.exportarEstadistica("descargar/estadisticas_categoria_hechosmaximos.csv");

    var reporte =  estadisticaPmhCat.getReporte();
    var fechaAhora = LocalDateTime.now();

    Map<String, Object> model = Map.of(
        "fecha", fechaAhora,
        "reporte", reporte
    );
    ctx.render("estadisticaCategoriaProvinciaMax.hbs", model);
    return model;
  }

  public static Map<String, Object> mostrarColeccionProvinciaMaxHechos(Context ctx)
      throws IOException {
    repoC.consesuareEchos();

    String path = "descargar";
    File carpeta = new File(path);
    if (!carpeta.exists()) {
      boolean creada = carpeta.mkdirs();
    }

    EstadisticaProvMaxHechosColeccion estadisticaPmhCol = new EstadisticaProvMaxHechosColeccion();
    estadisticaPmhCol.calcularEstadistica();

    estadisticaPmhCol.exportarEstadistica("descargar/estadisticas_coleccion_hechosmaximos.csv");

    var reporte =  estadisticaPmhCol.getReporte();
    var fechaAhora = LocalDateTime.now();

    Map<String, Object> model = Map.of(
        "fecha", fechaAhora,
        "reporte", reporte
    );
    ctx.render("estadisticaColeccionProvinciaMax.hbs", model);
    return model;
  }


}
