package mx.unam.aragon.tsp.fmanjarrez.pizzas.controllers;

import mx.unam.aragon.tsp.fmanjarrez.pizzas.dtos.PizzaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PizzaController {

    private List<PizzaDto> pizzas = new ArrayList<>();
    private int contador = 1;

    @GetMapping("/api/v1/pizzas")
    public List<PizzaDto> Listar(){
        return pizzas;
    }

    @GetMapping("/api/v1/pizzas/{id}")
    public ResponseEntity<PizzaDto> Obtener(@PathVariable Integer id){
        for (PizzaDto pizza : pizzas) {
            if (pizza.id().equals(id)) {
                return ResponseEntity.ok(pizza);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/v1/pizzas")
    public ResponseEntity<PizzaDto> Crear(@RequestBody PizzaDto dto){
        PizzaDto pizza = new PizzaDto(contador, dto.nombre(), dto.ingredientes(), dto.precio());
        contador++;
        pizzas.add(pizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(pizza);
    }

    @PutMapping("/api/v1/pizzas/{id}")
    public ResponseEntity<PizzaDto> Actualizar(@PathVariable Integer id, @RequestBody PizzaDto dto){
        for (int i = 0; i < pizzas.size(); i++) {
            if (pizzas.get(i).id().equals(id)) {
                PizzaDto pizza = new PizzaDto(id, dto.nombre(), dto.ingredientes(), dto.precio());
                pizzas.set(i, pizza);
                return ResponseEntity.ok(pizza);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/v1/pizzas/{id}")
    public ResponseEntity<Void> Eliminar(@PathVariable Integer id){
        boolean eliminado = pizzas.removeIf(pizza -> pizza.id().equals(id));
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
