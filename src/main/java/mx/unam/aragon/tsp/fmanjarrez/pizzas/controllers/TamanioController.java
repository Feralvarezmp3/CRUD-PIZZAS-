package mx.unam.aragon.tsp.fmanjarrez.pizzas.controllers;

import mx.unam.aragon.tsp.fmanjarrez.pizzas.dtos.TamanioDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TamanioController {

    private List<TamanioDto> tamanios = new ArrayList<>();
    private int contador = 1;

    @GetMapping("/api/v1/tamanios")
    public List<TamanioDto> Listar(){
        return tamanios;
    }

    @GetMapping("/api/v1/tamanios/{id}")
    public ResponseEntity<TamanioDto> Obtener(@PathVariable Integer id){
        for (TamanioDto tamanio : tamanios) {
            if (tamanio.id().equals(id)) {
                return ResponseEntity.ok(tamanio);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/v1/tamanios")
    public ResponseEntity<TamanioDto> Crear(@RequestBody TamanioDto dto){
        TamanioDto tamanio = new TamanioDto(contador, dto.nombre(), dto.diametroCm());
        contador++;
        tamanios.add(tamanio);
        return ResponseEntity.status(HttpStatus.CREATED).body(tamanio);
    }

    @PutMapping("/api/v1/tamanios/{id}")
    public ResponseEntity<TamanioDto> Actualizar(@PathVariable Integer id, @RequestBody TamanioDto dto){
        for (int i = 0; i < tamanios.size(); i++) {
            if (tamanios.get(i).id().equals(id)) {
                TamanioDto tamanio = new TamanioDto(id, dto.nombre(), dto.diametroCm());
                tamanios.set(i, tamanio);
                return ResponseEntity.ok(tamanio);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/v1/tamanios/{id}")
    public ResponseEntity<Void> Eliminar(@PathVariable Integer id){
        boolean eliminado = tamanios.removeIf(tamanio -> tamanio.id().equals(id));
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
