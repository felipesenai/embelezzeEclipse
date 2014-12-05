package br.senai.sc.ti20131n.pw.embelezzejsf.mb;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.senai.sc.ti20131n.pw.embelezzejsf.dao.EventoDao;
import br.senai.sc.ti20131n.pw.embelezzejsf.entity.Cliente;
import br.senai.sc.ti20131n.pw.embelezzejsf.modelo.Evento;

@ManagedBean
@ViewScoped
public class EventoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Evento evento;
	private ScheduleModel eventoModel;
	private List<Evento> listaEventos;
	private EventoDao eventoDao;
	private List<Cliente> listaClientes;
	private Cliente clienteSelecionado;
	private boolean acaoRealizada = false;
	private boolean dataCorreta = false;

	@PostConstruct
	public void inicializar(){

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
			
			if(e.isStatus()) {
				evt.setStyleClass("classe1");
			} else {
				evt.setStyleClass("classe2");				
			}
			
			Date now = new Date();
			if(now.after(evt.getEndDate())){
				evt.setStyleClass("classe3");				
			}
			
			eventoModel.addEvent(evt);
		}

	}

	public void selecionaEvento(SelectEvent selectEvent) {

		ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();

		for (Evento e : listaEventos) {
			if (e.getId() == (Long) event.getData()) {
				evento = e;
				setClienteSelecionado(evento.getCliente());
				break;
			}
		}
	}

	public void novoEvento(SelectEvent selectEvent) {
		ScheduleEvent event = new DefaultScheduleEvent("",
				(Date) selectEvent.getObject(), (Date) selectEvent.getObject());
		evento = new Evento();
		Cliente c = new Cliente();
		setClienteSelecionado(null);
		evento.setInicio(new java.sql.Date(event.getStartDate().getTime()));
		evento.setFim(new java.sql.Date(event.getEndDate().getTime()));
//		evento.setInicio(new Date());
//		evento.setFim(new Date());

	}

	public void salvar() throws ParseException {
		if (evento.getId() == null) {
			if (evento.getInicio().getTime() <= evento.getFim().getTime()) {
				eventoDao = new EventoDao();
				try {
					if(clienteSelecionado == null) {
						FacesContext.getCurrentInstance().addMessage(null, 
								new FacesMessage( FacesMessage.SEVERITY_WARN,
										"Aviso!",
										"Para criar um evento, selecione um cliente!"));
						acaoRealizada = false;
						return;
					} else {
						acaoRealizada = true;
						dataCorreta = true;
						evento.setCliente(getClienteSelecionado());
						eventoDao.salvar(evento);
						setClienteSelecionado(null);
						inicializar();
					}
				} catch (SQLException e) {
					FacesContext.getCurrentInstance().addMessage(null, 
							new FacesMessage( FacesMessage.SEVERITY_ERROR,
									"Erro ao salvar evento!",
									"Erro: " + e.getMessage()));
				}
				evento = new Evento();
				
			} else {
				FacesContext .getCurrentInstance() .addMessage(null, 
						new FacesMessage( FacesMessage.SEVERITY_WARN,
								"Aviso!",
								"A data inicial não pode ser maior que a data final!"));
				dataCorreta = false;
				return;
			}
		} else {
			try {
				evento.setCliente(getClienteSelecionado());
				eventoDao.atualizar(evento);
				setClienteSelecionado(null);
				inicializar();
			} catch (Exception e) {
				FacesContext .getCurrentInstance() .addMessage(null, 
						new FacesMessage( FacesMessage.SEVERITY_ERROR,
								"Erro ao atualizar evento!",
								"Erro: " + e.getMessage()));
			}
		}
	}
	
	public void mover(ScheduleEntryMoveEvent event) {  
        for(Evento e : listaEventos){
            if(e.getId() == (Long)event.getScheduleEvent().getData()){	
                evento = e;
                eventoDao = new EventoDao();
                try {
                	eventoDao.atualizar(evento);
                	inicializar();
				} catch (SQLException ex) {
					FacesContext.getCurrentInstance().addMessage(null, 
							new FacesMessage( FacesMessage.SEVERITY_ERROR,
									"Erro ao mover evento!",
									"Erro: " + ex.getMessage()));
				}
                break;
            }
        }
    }  
	
	public void redimensionar(ScheduleEntryResizeEvent event) {  
		for(Evento e : listaEventos){
			if(e.getId() == (Long)event.getScheduleEvent().getData()){	
				evento = e;
				eventoDao = new EventoDao();
				try {
					eventoDao.atualizar(evento);
					inicializar();
				} catch (SQLException ex) {
					FacesContext.getCurrentInstance().addMessage(null, 
							new FacesMessage( FacesMessage.SEVERITY_ERROR,
									"Erro ao redimensionar evento!",
									"Erro: " + ex.getMessage()));
				}
				break;
			}
		}
	}
	
	public boolean isDataCorreta() {
		return dataCorreta;
	}

	public void setDataCorreta(boolean dataCorreta) {
		this.dataCorreta = dataCorreta;
	}

	public boolean isAcaoRealizada() {
		return acaoRealizada;
	}

	public void setAcaoRealizada(boolean acaoRealizada) {
		this.acaoRealizada = acaoRealizada;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

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
	
}
