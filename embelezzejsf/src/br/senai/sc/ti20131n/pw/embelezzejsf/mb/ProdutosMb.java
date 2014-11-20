package br.senai.sc.ti20131n.pw.embelezzejsf.mb;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import br.senai.sc.ti20131n.pw.embelezzejsf.dao.ProdutoDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Produtos;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.UploadImageUtil;

@ManagedBean
public class ProdutosMb {
	
	private Produtos produto;

	private List<Produtos> listaProdutos;
	private ProdutoDao produtoDao;
	private Part imagem;
	private String imagemAntiga;

	@PostConstruct
	private void init() {
		produto = new Produtos();
		produtoDao = new ProdutoDao();
	}
	
	public void setProduto(Produtos produto) {
		this.produto = produto;
	}

	public Produtos getProduto() {
		return produto;
	}
	
	public void setListaProdutos(List<Produtos> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Produtos> getListaProdutos() {
		if(listaProdutos == null){
			listaProdutos = produtoDao.listarProduto();
		}
		return listaProdutos;
	}

	public String getCaminhoRelativo(String nomeImagem) {
		return UploadImageUtil.getCaminho(nomeImagem);
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
		if (validaCamposVazios()) {
			imagemAntiga = produto.getImagem();
			produto.setImagem(UploadImageUtil.copiar(imagem, imagemAntiga));
			produtoDao.salvar(getProduto());
		}
		return "listarprodutos";
	}

	public String editar(Long ID) {
		produto = produtoDao.buscarPorId(ID);
		return "formcadprodutos";
	}

	public String excluir(Long ID) {
		produto = produtoDao.excluirProdutoPorId(ID);
		produto = null;
		return "listarprodutos";
	}
	
	public void buttonAction(ActionEvent actionEvent) {
		addMessage("Mensagem do button Action!");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public boolean validaCamposVazios() {
		if (produto.getNomeProduto().isEmpty()) {
			addMessage("O nome do produto está vazio");
			return false;
		} else if (produto.getMarcaProduto().isEmpty()) {
			addMessage("A marca do produto está vazia");
			return false;
		}
		return true;
	}

}
