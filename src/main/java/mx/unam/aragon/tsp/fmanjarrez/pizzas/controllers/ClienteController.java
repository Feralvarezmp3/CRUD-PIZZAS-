package mx.unam.aragon.tsp.fmanjarrez.pizzas.controllers;

import mx.unam.aragon.tsp.fmanjarrez.pizzas.dtos.ClienteDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ClienteController {

    private List<ClienteDto> clientes = new ArrayList<>();
    private int contador = 1;

    @GetMapping("/api/v1/clientes")
    public List<ClienteDto> Listar(){
        return clientes;
    }

    @GetMapping("/api/v1/clientes/{id}")
    public ResponseEntity<ClienteDto> Obtener(@PathVariable Integer id){
        for (ClienteDto cliente : clientes) {
            if (cliente.id().equals(id)) {
                return ResponseEntity.ok(cliente);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/v1/clientes")
    public ResponseEntity<ClienteDto> Crear(@RequestBody ClienteDto dto){
        ClienteDto cliente = new ClienteDto(contador, dto.nombre(), dto.telefono(), dto.correo(), dto.direccion());
        contador++;
        clientes.add(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PutMapping("/api/v1/clientes/{id}")
    public ResponseEntity<ClienteDto> Actualizar(@PathVariable Integer id, @RequestBody ClienteDto dto){
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).id().equals(id)) {
                ClienteDto cliente = new ClienteDto(id, dto.nombre(), dto.telefono(), dto.correo(), dto.direccion());
                clientes.set(i, cliente);
                return ResponseEntity.ok(cliente);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/v1/clientes/{id}")
    public ResponseEntity<Void> Eliminar(@PathVariable Integer id){
        boolean eliminado = clientes.removeIf(cliente -> cliente.id().equals(id));
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
