package ada.tech.eleventwentysix.checkout.queue;

import ada.tech.eleventwentysix.checkout.client.payload.ItemEstoqueRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstoquePublisher {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void publish(List<ItemEstoqueRequest> itemEstoqueRequestList)  {
        try {

            String objeto = objectMapper.writeValueAsString(itemEstoqueRequestList);
            rabbitTemplate.convertSendAndReceive(queue.getName(), objeto);

        } catch (JsonProcessingException e) {
            log.error("Não foi possível realizar a conversao do objeto ", e);
            throw new RuntimeException(e);
        }

    }
}
