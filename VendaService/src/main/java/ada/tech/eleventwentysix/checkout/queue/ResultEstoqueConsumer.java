package ada.tech.eleventwentysix.checkout.queue;

import ada.tech.eleventwentysix.checkout.client.payload.EstoqueResponse;
import ada.tech.eleventwentysix.checkout.service.TratarRespostaEstoqueService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResultEstoqueConsumer {
    private final ObjectMapper objectMapper;
    private final TratarRespostaEstoqueService tratarRespostaEstoqueService;

    @RabbitListener(queues = {"${negocio.estoque.resposta.fila}"})
    public void consumer(Message message , Channel channel)  {
        try {
            String mensagemString = new String(message.getBody());
            EstoqueResponse estoqueResponse = objectMapper.readValue(mensagemString, EstoqueResponse.class);
            log.info("mensagem consumida  {}", estoqueResponse);
            tratarRespostaEstoqueService.execute(estoqueResponse);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
