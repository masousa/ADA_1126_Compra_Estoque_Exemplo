package ada.tech.eleventwentysix.checkout.mapping;

import ada.tech.eleventwentysix.checkout.model.Item;
import ada.tech.eleventwentysix.checkout.payloads.response.ItemResponse;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConvertItemToItemResponse {

    public ItemResponse convert (Item item){
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setSkuId(item.getSku());
        itemResponse.setQuantidade(item.getQuantidadeEstoque());
        return itemResponse;
    }
}
