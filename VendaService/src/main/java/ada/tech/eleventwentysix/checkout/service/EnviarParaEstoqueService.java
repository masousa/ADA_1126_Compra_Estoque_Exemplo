package ada.tech.eleventwentysix.checkout.service;

import ada.tech.eleventwentysix.checkout.client.EstoqueClient;
import ada.tech.eleventwentysix.checkout.client.payload.EstoqueResponse;
import ada.tech.eleventwentysix.checkout.client.payload.ItemEstoqueRequest;
import ada.tech.eleventwentysix.checkout.mapping.ItemItemEstoqueRequestConverter;
import ada.tech.eleventwentysix.checkout.model.Compra;
import ada.tech.eleventwentysix.checkout.model.Item;
import ada.tech.eleventwentysix.checkout.model.StatusCompra;
import ada.tech.eleventwentysix.checkout.repository.CompraRepository;
import ada.tech.eleventwentysix.checkout.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnviarParaEstoqueService {
    private final EstoqueClient estoqueClient;
    private final TratarRespostaEstoqueService tratarRespostaEstoqueService;
    private final CompraRepository compraRepository;

    @Async
    public void executar (String identificadorCompra){
        log.info("comunicação com o estoque será via rest para a compra {}",identificadorCompra);

        Compra compra = compraRepository.findByIdentificador(identificadorCompra);
        compra.setStatus(StatusCompra.ERROR);
        List<ItemEstoqueRequest> itens = compra.getItemList().stream()
                .map(ItemItemEstoqueRequestConverter::convert).toList();
        if(!CollectionUtils.isEmpty(itens)){
            EstoqueResponse estoqueResponse =  estoqueClient.removerItens(itens);

            tratarRespostaEstoqueService.execute(estoqueResponse);
        }


    }


}
