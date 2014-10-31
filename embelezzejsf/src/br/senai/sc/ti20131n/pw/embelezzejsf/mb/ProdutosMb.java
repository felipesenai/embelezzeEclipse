package br.senai.sc.ti20131n.pw.embelezzejsf.mb;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.Part;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Produtos;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.UploadImageUtil;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

@ManagedBean(name = "produtoMb")
public class ProdutosMb {
	private List<Produtos> listaProdutos;
	private EntityManager entityManager;
	private Produtos produto;
	private Part imagem;
	private String imagemAntiga;

	@PostConstruct
	private void init() {
		produto = new Produtos();
		entityManager = Util.getEntityManager();
	}

	public String getCaminhoRelativo(String nomeImagem) {
		return UploadImageUtil.getCaminho(nomeImagem);
	}

	public List<Produtos> getProdutos() {
		if (listaProdutos == null) {
			Query query = entityManager.createQuery("SELECT p FROM Produtos p",
					Produtos.class);
			listaProdutos = query.getResultList();
		}
		return listaProdutos;

	}

	public void setProdutos(List<Produtos> produtos) {
		this.listaProdutos = produtos;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
	}

	public Part getImagem() {
		return imagem;
	}

	public void setImagem(Part imagem) {
		this.imagem = imagem;
	}

	public String getImagemAntiga() {
		return imagemAntiga;
	}

	public void setImagemAntiga(String imagemAntiga) {
		this.imagemAntiga = imagemAntiga;
	}

	public String salvar() throws IOException {
		imagemAntiga = produto.getImagem();
		produto.setImagem(UploadImageUtil.copiar(imagem, imagemAntiga));
		entityManager.merge(produto);

		return "listarprodutos";
	}

	public String editar(Long ID) {
		produto = entityManager.find(Produtos.class, ID);

		return "formcadprodutos";
	}

	public String excluir(Long ID) {
		Produtos produtos = entityManager.find(Produtos.class, ID);
		entityManager.remove(produtos);
		produtos = null;
		return "listarprodutos";
	}

}
