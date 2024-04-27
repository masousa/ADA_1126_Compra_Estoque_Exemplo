package ada.tech.eleventwentysix.checkout.queue;

import ada.tech.eleventwentysix.checkout.payloads.response.EstoqueResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RemocaoItensPublisher {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void publish(EstoqueResponse estoqueResponse){
        log.info("Mensagem enviada para o broker {}",estoqueResponse);
        String mensagem = null;
        try {
            mensagem = objectMapper.writeValueAsString(estoqueResponse);
            rabbitTemplate.convertAndSend(queue.getName(), mensagem);

        } catch (JsonProcessingException e) {
            log.error("Não foi possível enviar a mensagem ",e);
            throw new RuntimeException(e);
        }
    }
}
