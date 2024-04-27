package ada.tech.eleventwentysix.checkout.service;

import ada.tech.eleventwentysix.checkout.client.payload.ItemEstoqueRequest;
import ada.tech.eleventwentysix.checkout.mapping.ConvertCompraToVendaResponse;
import ada.tech.eleventwentysix.checkout.mapping.ItemItemEstoqueRequestConverter;
import ada.tech.eleventwentysix.checkout.model.Compra;
import ada.tech.eleventwentysix.checkout.model.Item;
import ada.tech.eleventwentysix.checkout.model.StatusCompra;
import ada.tech.eleventwentysix.checkout.payloads.request.VendaRequest;
import ada.tech.eleventwentysix.checkout.payloads.response.VendaResponse;
import ada.tech.eleventwentysix.checkout.queue.EstoquePublisher;
import ada.tech.eleventwentysix.checkout.repository.CompraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class RealizarCompraService {
    private final CompraRepository compraRepository;
    private final EnviarParaEstoqueService enviarParaEstoqueService;
    private final EstoquePublisher estoquePublisher;
    public VendaResponse executar(VendaRequest vendaRequest, boolean restResult)  {
        Compra compra =  compraRepository.findByIdentificador(vendaRequest.getCompraId());
        compra.setStatus(StatusCompra.PENDENTE);
        log.info("Recebendo solicitacao de venda {}", vendaRequest);
        compra.setDataCompra(LocalDateTime.now());

        if(restResult){
            enviarParaEstoqueService.executar(vendaRequest.getCompraId());
        }else{
            estoquePublisher.publish(compra.getItemList()
                    .stream().map(ItemItemEstoqueRequestConverter::convert).toList());
        }
        compraRepository.save(compra);
        Compra compraRealizada = compraRepository.findByIdentificador(compra.getIdentificador());
        log.info("solicitacao de venda depois de ir no estoque {}", vendaRequest);
        return ConvertCompraToVendaResponse.convertToVendaResponse(compraRealizada);

    }



}
