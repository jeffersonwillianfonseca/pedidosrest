package pedidosrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pedidosrest.entity.Pedidos;
import pedidosrest.services.PedidosService;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("pedidos")
public class PedidosController {

    @Autowired
    PedidosService pedidosService;

    @PostMapping
    public ResponseEntity<String> salvarPedido(@RequestBody Pedidos pedido) {
        if (pedidosService.salvarPedido(pedido)) {
            return new ResponseEntity<>("Inserido com sucesso", OK);
        } else {
            return new ResponseEntity<>("Falha na inserção", BAD_REQUEST);
        }
    }

    @GetMapping
    public Iterable<Pedidos> listarTodosPedidos() {
        return pedidosService.findAllPedidos();
    }
}
