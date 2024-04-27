package ada.tech.eleventwentysix.checkout.service;

import ada.tech.eleventwentysix.checkout.model.Compra;
import ada.tech.eleventwentysix.checkout.model.StatusCompra;
import ada.tech.eleventwentysix.checkout.repository.CompraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateEmptyCompraService {

    private final CompraRepository compraRepository;

    public Compra execute(){
        log.info("Criando uma compra vazia para a primeira adição de usuário");
        Compra compra = new Compra();
        compra.setStatus(StatusCompra.INICIAL);
        compra.setIdentificador(UUID.randomUUID().toString());
        compra.setValorTotal(BigDecimal.ZERO);
        compra.setDataCompra(LocalDateTime.now());
        return compraRepository.save(compra);
    }
}
