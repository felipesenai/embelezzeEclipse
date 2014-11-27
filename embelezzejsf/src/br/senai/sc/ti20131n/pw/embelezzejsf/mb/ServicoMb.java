package br.senai.sc.ti20131n.pw.embelezzejsf.mb;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

import br.senai.sc.ti20131n.pw.embelezzejsf.dao.ServicoDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Servico;
import br.senai.sc.ti20131n.pw.embelezzejsf.util.UploadImageUtil;

	@ManagedBean(name = "servicoMb")
	public class ServicoMb {
		private List<Servico> listaServicos;
		private Servico servico;
		private Part imagem;
		private String imagemAntiga;
		private ServicoDao servicoDao;

		@PostConstruct
		private void init() {
			servico = new Servico();
			servicoDao = new ServicoDao();
		}

		public String getCaminhoRelativo(String nomeImagem) {
			return UploadImageUtil.getCaminho(nomeImagem);
		}

		public List<Servico> getServicos() {
			if (listaServicos == null) {
				listaServicos = servicoDao.listarServico();
			}
			return listaServicos;

		}

		public void setServicos(List<Servico> servicos) {
			this.listaServicos = servicos;
		}

		public Servico getServico() {
			return servico;
		}

		public void setServico(Servico servico) {
			this.servico = servico;
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
			imagemAntiga = servico.getImagemServico();
			servico.setImagemServico(UploadImageUtil.copiar(imagem, imagemAntiga));
			servicoDao.salvar(getServico());
			return "listarservicos";
		}

		public String editar(Long ID) {
			servico = servicoDao.buscarPorId(ID);
			return "formcadservicos";
		}

		public String excluir(Long ID) {
			servico = servicoDao.buscarPorId(ID);
			servicoDao.excluirServicoPorId(ID);
			listaServicos = null;
			return "listarservicos";
		}

	}