package ada.tech.eleventwentysix.checkout.service;

import ada.tech.eleventwentysix.checkout.client.payload.EstoqueResponse;
import ada.tech.eleventwentysix.checkout.model.Compra;
import ada.tech.eleventwentysix.checkout.model.StatusCompra;
import ada.tech.eleventwentysix.checkout.repository.CompraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TratarRespostaEstoqueService {

    private final CompraRepository compraRepository;

    public void execute(EstoqueResponse estoqueResponse){
        log.info("Tratando a resposta do estoque {}", estoqueResponse);
        Compra compra = compraRepository.findByIdentificador(estoqueResponse.getIdRemocao());
        compra.setStatus(StatusCompra.ERROR);
        if(Objects.nonNull(estoqueResponse.getItemsRemovidos()) && !estoqueResponse
                .getItemsRemovidos().isEmpty() && estoqueResponse.getItemsRemovidos().size() == compra.getItemList().size()){
            compra.setStatus(StatusCompra.APROVADA);
            compra.setValorTotal(
                    BigDecimal.valueOf(
                            compra.getItemList().stream()
                                    .mapToDouble(item -> item.getValorUnitario()
                                            .multiply(BigDecimal.valueOf(item.getQuantidadeUnidade())).doubleValue()).sum()));

        }
        compraRepository.save(compra);
    }
}
