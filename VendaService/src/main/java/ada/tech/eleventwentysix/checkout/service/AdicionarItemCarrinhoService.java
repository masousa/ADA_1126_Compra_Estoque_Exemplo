package ada.tech.eleventwentysix.checkout.service;

import ada.tech.eleventwentysix.checkout.mapping.ConvertCompraToVendaResponse;
import ada.tech.eleventwentysix.checkout.model.Compra;
import ada.tech.eleventwentysix.checkout.model.Item;
import ada.tech.eleventwentysix.checkout.payloads.request.ItemRequest;
import ada.tech.eleventwentysix.checkout.payloads.response.VendaResponse;
import ada.tech.eleventwentysix.checkout.repository.CompraRepository;
import ada.tech.eleventwentysix.checkout.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdicionarItemCarrinhoService {

    private final ItemRepository itemRepository;
    private final CreateEmptyCompraService createEmptyCompraService;
    private final CompraRepository compraRepository;

    public VendaResponse execute(ItemRequest itemRequest){

        Compra compra;
        if(StringUtils.isEmpty(itemRequest.getIdCompra())){
           compra =  createEmptyCompraService.execute();
        }else {
            compra = compraRepository.findByIdentificador(itemRequest.getIdCompra());
        }
        Item item = new Item();
        item.setCompra(compra);
        item.setSku(itemRequest.getSkuId());
        item.setIdentificador(UUID.randomUUID().toString());
        item.setValorUnitario(BigDecimal.valueOf(itemRequest.getValorUnitario()));
        item.setQuantidadeUnidade(itemRequest.getQuantidade());
        itemRepository.save(item);
        return ConvertCompraToVendaResponse.convertToVendaResponse(compraRepository.findByIdentificador(compra.getIdentificador()));
    }
}
