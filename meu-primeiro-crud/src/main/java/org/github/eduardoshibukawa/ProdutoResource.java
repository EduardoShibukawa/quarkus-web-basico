package org.github.eduardoshibukawa;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @GET
    public List<Produto> buscarTodosProdutos() {
        return Produto.listAll();
    }

    @POST
    @Transactional
    public void cadastrarProduto(CadastrarProdutoDTO dto) {
        final Produto p = new Produto();
        p.nome = dto.nome;
        p.valor = dto.valor;
        p.persist();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void cadastrarProduto(@PathParam("id") Long id, AtualizarProdutoDTO dto) {
        final Optional<Produto> op = Produto.findByIdOptional(id);    

        if (op.isEmpty()) {
            throw new NotFoundException();
        }

        final Produto p = op.get();
        p.valor = dto.valor;
        p.persist();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletarProduto(@PathParam("id") Long id) {
        final Optional<Produto> op = Produto.findByIdOptional(id);

        op.ifPresentOrElse(
            Produto::delete
            , () -> {
            throw new NotFoundException();
        });

    }

}
