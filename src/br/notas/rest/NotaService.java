package br.notas.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.notas.dao.NotaDAO;
import br.notas.model.Nota;

@Path("/notas")
public class NotaService {
	private NotaDAO notaDao;

	@PostConstruct
	private void init() {
		notaDao = new NotaDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Nota> listarNotas() {
		List<Nota> lista = null;
		try {
			lista = notaDao.listarNotas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
