package ada.tech.eleventwentysix.checkout.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    @Value("${negocio.estoque.fila}")
    private String nomeFila;

    @Bean
    public Queue queue(){
        return new Queue(nomeFila,true);
    }

}
