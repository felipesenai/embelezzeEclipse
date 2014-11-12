package br.senai.sc.ti20131n.pw.embelezzejsf.mb;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.senai.sc.ti20131n.pw.embelezzejsf.dao.EventoDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.modelo.Evento;

@ManagedBean
@ViewScoped
public class EventoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Evento evento;
	private ScheduleModel eventoModel;
	private List<Evento> listaEventos;
	private EventoDao eventoDao;

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public ScheduleModel getEventoModel() {
		return eventoModel;
	}

	public void setEventoModel(ScheduleModel eventoModel) {
		this.eventoModel = eventoModel;
	}

	public List<Evento> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}

	public EventoDao getEventoDao() {
		return eventoDao;
	}

	public void setEventoDao(EventoDao eventoDao) {
		this.eventoDao = eventoDao;
	}

	@PostConstruct
	public void inicializar() {

		eventoDao = new EventoDao();
		evento = new Evento();
		eventoModel = new DefaultScheduleModel();

		try {
			listaEventos = eventoDao.listarEventos();
		} catch (SQLException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao listar os Eventos."));
		}
		for (Evento e : listaEventos) {
			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setTitle(e.getTitulo());
			evt.setStartDate(e.getInicio());
			evt.setEndDate(e.getFim());
			evt.setData(e.getId());
			evt.setDescription(e.getDescricao());
			evt.setAllDay(true);
			evt.setEditable(true);

			eventoModel.addEvent(evt);
		}

	}

	public void selecionaEvento(SelectEvent selectEvent) {

		ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();

		for (Evento e : listaEventos) {
			if (e.getId() == (Long) event.getData()) {
				evento = e;
				break;
			}
		}
	}

	public void novoEvento(SelectEvent selectEvent) {
		ScheduleEvent event = new DefaultScheduleEvent("",
				(Date) selectEvent.getObject(), (Date) selectEvent.getObject());
		evento = new Evento();
		evento.setInicio(new java.sql.Date(event.getStartDate().getTime()));
		evento.setFim(new java.sql.Date(event.getEndDate().getTime()));

	}

	public void salvar() {
		if (evento.getId() == null) {
			if (evento.getInicio().getTime() >= evento.getFim().getTime()) {
				eventoDao = new EventoDao();
				try {
					eventoDao.salvar(evento);
					inicializar();
				} catch (SQLException e) {
					FacesContext.getCurrentInstance().addMessage(null, 
							new FacesMessage( FacesMessage.SEVERITY_ERROR,
									"Erro ao salvar evento!",
									"Erro: " + e.getMessage()));
				}
				evento = new Evento();
				
			} else {
				FacesContext .getCurrentInstance() .addMessage(null, 
						new FacesMessage( FacesMessage.SEVERITY_ERROR,
								"A data inicial não pode ser maior que a data final!",
								"A data inicial não pode ser maior que a data final!"));
			}
		} else {
			try {
				eventoDao.atualizar(evento);
				inicializar();
			} catch (Exception e) {
				FacesContext .getCurrentInstance() .addMessage(null, 
						new FacesMessage( FacesMessage.SEVERITY_ERROR,
								"Erro ao atualizar evento!",
								"Erro: " + e.getMessage()));
			}
		}
	}
}
