package ada.tech.eleventwentysix.checkout.mapping;

import ada.tech.eleventwentysix.checkout.client.payload.ItemEstoqueRequest;
import ada.tech.eleventwentysix.checkout.model.Item;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemItemEstoqueRequestConverter {

    public ItemEstoqueRequest convert(Item item) {
        ItemEstoqueRequest itemEstoqueRequest = new ItemEstoqueRequest();
        itemEstoqueRequest.setQuantidade(item.getQuantidadeUnidade());
        itemEstoqueRequest.setSkuId(item.getSku());
        itemEstoqueRequest.setCompraId(item.getCompra().getIdentificador());
        return itemEstoqueRequest;
    }
}
