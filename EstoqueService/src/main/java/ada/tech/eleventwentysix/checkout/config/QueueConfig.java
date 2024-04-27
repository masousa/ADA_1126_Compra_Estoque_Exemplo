package ada.tech.eleventwentysix.checkout.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${negocio.remover.resposta.fila}")
    private String filaEstoqueResponse;

    @Bean
    public Queue queue(){
        return new Queue(filaEstoqueResponse,true);
    }
}
