package ada.tech.eleventwentysix.checkout.mapping;

import ada.tech.eleventwentysix.checkout.model.Compra;
import ada.tech.eleventwentysix.checkout.model.Item;
import ada.tech.eleventwentysix.checkout.payloads.response.ItemResponse;
import ada.tech.eleventwentysix.checkout.payloads.response.VendaResponse;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConvertCompraToVendaResponse {

    public VendaResponse convertToVendaResponse(Compra compra) {
        VendaResponse vendaResponse = new VendaResponse();
        vendaResponse.setId(compra.getIdentificador());
        vendaResponse.setStatus(compra.getStatus().name());
        vendaResponse.setItens(compra.getItemList()
                .stream().map(ConvertCompraToVendaResponse::convertToItemVendaResponse).toList());

        return vendaResponse;
    }

    private ItemResponse convertToItemVendaResponse(Item item) {
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setQuantidade(item.getQuantidadeUnidade());
        itemResponse.setSkuId(item.getSku());
        itemResponse.setValorUnitario(item.getValorUnitario().doubleValue());
        return itemResponse;
    }
}
