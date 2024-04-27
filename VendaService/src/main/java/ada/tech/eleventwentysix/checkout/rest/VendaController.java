package ada.tech.eleventwentysix.checkout.rest;

import ada.tech.eleventwentysix.checkout.payloads.request.ItemRequest;
import ada.tech.eleventwentysix.checkout.payloads.request.VendaRequest;
import ada.tech.eleventwentysix.checkout.payloads.response.VendaResponse;
import ada.tech.eleventwentysix.checkout.service.AdicionarItemCarrinhoService;
import ada.tech.eleventwentysix.checkout.service.ConsultarCompraService;
import ada.tech.eleventwentysix.checkout.service.RealizarCompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(name = "/venda")
@RequiredArgsConstructor
public class VendaController {

    private final RealizarCompraService realizarCompraService;

    private final AdicionarItemCarrinhoService adicionarItemCarrinhoService;

    private final ConsultarCompraService consultarCompraService;

    @Operation(summary = "Adicionar Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item Adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao adicionar um item"),
    })
    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public VendaResponse adicionarItem(@RequestBody ItemRequest itemAdicionadoRequest){

        return adicionarItemCarrinhoService.execute(itemAdicionadoRequest);
    }

    @Operation(summary = "Realizar a compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "compra realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a compra"),
    })
    @PostMapping(value = "/{rest}")
    @ResponseStatus(HttpStatus.CREATED)
    public VendaResponse realizarCompra(@PathVariable(name = "rest")Boolean rest, @RequestBody VendaRequest vendaRequest){

        return realizarCompraService.executar(vendaRequest, rest);
    }

    @Operation(summary = "Verificar status de uma compra")
    @GetMapping(value = "/{compraId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados da compra realizada"),
            @ApiResponse(responseCode = "404", description = "Erro ao consultar compra"),
    })
    public VendaResponse consultarStatusVenda(@PathVariable(name = "compraId") String compraId){
        return consultarCompraService.execute(compraId);
    }
}
