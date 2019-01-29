package pedidosrest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pedidosrest.entity.Pedidos;
import pedidosrest.repository.PedidosRepository;

@Service
public class PedidosService {

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchanges.pedidosexchange}")
    private String pedidosExchange;

    Logger logger = LoggerFactory.getLogger(PedidosService.class);

    public PedidosService(PedidosRepository pedidosRepository, RabbitTemplate rabbitTemplate) {
        this.pedidosRepository = pedidosRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Boolean salvarPedido(Pedidos pedido) {
        if (pedidosRepository.save(pedido) != null) {
            rabbitTemplate.convertAndSend(pedidosExchange, "", pedido);
            logger.info("Pedido salvo com sucesso.");
            return true;
        }
        logger.error("Falha ao salvar o pedido.");
        return false;
    }

    public Iterable<Pedidos> findAllPedidos() {
        return pedidosRepository.findAll();
    }
}
