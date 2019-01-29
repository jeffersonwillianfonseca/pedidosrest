package pedidosrest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pedidosrest.entity.Endereco;
import pedidosrest.entity.Pedidos;
import pedidosrest.repository.EnderecoRepository;

import static org.springframework.amqp.core.ExchangeTypes.FANOUT;

@Service
public class EntregasService {

    @Autowired
    EnderecoRepository enderecoRepository;

    private Logger logger = LoggerFactory.getLogger(EntregasService.class);

    public EntregasService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.filas.sendpedidos}", durable = "true"),
            exchange = @Exchange(value = "${spring.rabbitmq.exchanges.pedidosexchange}",
                    type = FANOUT)
    )
    )
    public void enderecosEntregaReceiver(Pedidos pedido) {
        Endereco enderecoEntrega = new Endereco();
        if(pedido.getIdPedido() != null && pedido.getEndereco() != null) {
            enderecoEntrega.setEndereco(pedido.getEndereco());
            enderecoEntrega.setIdPedido(pedido.getIdPedido());
            enderecoRepository.save(enderecoEntrega);
            logger.info("O endereço para o pedido de id {} foi salvo", pedido.getIdPedido());
        } else {
            logger.error("Falha ao salvar o endereco id do pedido ou endereco nulo.");
        }
    }
}
