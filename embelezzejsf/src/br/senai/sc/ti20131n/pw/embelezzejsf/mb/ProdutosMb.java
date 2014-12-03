package br.senai.sc.ti20131n.pw.embelezzejsf.mb;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.servlet.http.Part;

import br.senai.sc.ti20131n.pw.embelezzejsf.dao.ProdutoDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Produtos;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.UploadImageUtil;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.Util;

@ManagedBean(name = "produtoMb")
public class ProdutosMb {
	private List<Produtos> listaProdutos;
	private Produtos produto;
	private Part imagem;
	private String imagemAntiga;
	private ProdutoDao produtoDao;
	private long ID;
	private EntityManager entityManager;
	private long idProduto;

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}


	@PostConstruct
	private void init() {
		produto = new Produtos();
		produtoDao = new ProdutoDao();
		produto = new Produtos();
		entityManager = Util.getEntityManager();
	}

	public String getCaminhoRelativo(String nomeImagem) {
		return UploadImageUtil.getCaminho(nomeImagem);
	}

	public List<Produtos> getProdutos() {
		if (listaProdutos == null) {
			listaProdutos = produtoDao.listarProduto();
		}
		return listaProdutos;

	}

	public void setProdutos(List<Produtos> produtos) {
		this.listaProdutos = produtos;
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
		// entityManager.merge(produto);
		produtoDao.salvar(getProduto());
		return "listarprodutos";
	}

	public String editar(Long ID) {
		produto = produtoDao.buscarPorId(ID);
		return "formcadprodutos";
	}

	public String excluir(Long ID) {
		produto = produtoDao.buscarPorId(ID);
		produtoDao.excluirProdutoPorId(ID);
		listaProdutos = null;

		return "listarprodutos";
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		this.ID = iD;
	}

	public void abreDetalhes() {
		// produto = produtoDao.buscarPorId(ID);
		// produto = produtoDao.ListarDetalhes(getID());
		 produto = entityManager.find(Produtos.class, idProduto);

	}

}