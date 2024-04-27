package ada.tech.eleventwentysix.checkout.queue;

import ada.tech.eleventwentysix.checkout.payloads.request.ItemRequest;
import ada.tech.eleventwentysix.checkout.service.RemoverItensService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RemoverItemsConsumer {

    private final ObjectMapper objectMapper;
    private final RemoverItensService removerItensService;
    @RabbitListener(queues = {"${negocio.remover.fila}"})
    public void consumer(Message message , Channel channel) throws IOException {
        String stringMessage = new String(message.getBody());
        List<ItemRequest> items =
                Arrays.asList(objectMapper.readValue(stringMessage, ItemRequest[].class));

        removerItensService.execute(items, true);
        log.info("Mensagem recebida {}", stringMessage);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);

    }
}
