package br.senai.sc.ti20131n.pw.embelezzejsf.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Cliente;

@Entity
public class Evento {
	@Id
	@GeneratedValue
	private Long ID_Evento; // ID_Evento
	private String Titulo; // Titulo
	private Date InicioEvento; // InicioEvento
	private Date FimEvento; // FimEvento
	private String DescEvento; // DescEvento
	private boolean StatusEvento; // StatusEvento
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Cliente cliente;
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return ID_Evento;
	}

	public void setId(Long id) {
		this.ID_Evento = id;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		this.Titulo = titulo;
	}

	public Date getInicio() {
		return InicioEvento;
	}

	public void setInicio(Date inicio) {
		this.InicioEvento = inicio;
	}

	public Date getFim() {
		return FimEvento;
	}

	public void setFim(Date fim) {
		this.FimEvento = fim;
	}

	public String getDescricao() {
		return DescEvento;
	}

	public void setDescricao(String descricao) {
		this.DescEvento = descricao;
	}

	public boolean isStatus() {
		return StatusEvento;
	}

	public void setStatus(boolean status) {
		this.StatusEvento = status;
	}

}
