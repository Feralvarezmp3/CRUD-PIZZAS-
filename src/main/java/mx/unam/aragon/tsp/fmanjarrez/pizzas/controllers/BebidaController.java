package mx.unam.aragon.tsp.fmanjarrez.pizzas.controllers;

import mx.unam.aragon.tsp.fmanjarrez.pizzas.dtos.BebidaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BebidaController {

    private List<BebidaDto> bebidas = new ArrayList<>();
    private int contador = 1;

    @GetMapping("/api/v1/bebidas")
    public List<BebidaDto> Listar(){
        return bebidas;
    }

    @GetMapping("/api/v1/bebidas/{id}")
    public ResponseEntity<BebidaDto> Obtener(@PathVariable Integer id){
        for (BebidaDto bebida : bebidas) {
            if (bebida.id().equals(id)) {
                return ResponseEntity.ok(bebida);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/v1/bebidas")
    public ResponseEntity<BebidaDto> Crear(@RequestBody BebidaDto dto){
        BebidaDto bebida = new BebidaDto(contador, dto.nombre(), dto.mililitros(), dto.precio());
        contador++;
        bebidas.add(bebida);
        return ResponseEntity.status(HttpStatus.CREATED).body(bebida);
    }

    @PutMapping("/api/v1/bebidas/{id}")
    public ResponseEntity<BebidaDto> Actualizar(@PathVariable Integer id, @RequestBody BebidaDto dto){
        for (int i = 0; i < bebidas.size(); i++) {
            if (bebidas.get(i).id().equals(id)) {
                BebidaDto bebida = new BebidaDto(id, dto.nombre(), dto.mililitros(), dto.precio());
                bebidas.set(i, bebida);
                return ResponseEntity.ok(bebida);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/v1/bebidas/{id}")
    public ResponseEntity<Void> Eliminar(@PathVariable Integer id){
        boolean eliminado = bebidas.removeIf(bebida -> bebida.id().equals(id));
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
