package br.com.souninja.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.souninja.dao.DAOFactory;
import br.com.souninja.dao.ImpostoDAO;
import br.com.souninja.dto.ImpostoLucroPresumidoDto;
import br.com.souninja.entity.Cliente;
import br.com.souninja.entity.Imposto;
import br.com.souninja.entity.NotaFiscal;
import br.com.souninja.infrastructure.ClienteSession;
import br.com.souninja.rules.ImpostoTipoRules;
import br.com.souninja.rules.RegimeTributario;
import br.com.souninja.rules.SimplesNacionalRules;
import br.com.souninja.service.ImpostoService;
import br.com.souninja.service.NotaFiscalService;

public class ImpostoServiceImpl implements ImpostoService {

	private ImpostoDAO impostoDAO;

	private NotaFiscalService notaFiscalService;

	public ImpostoServiceImpl() {
		this.notaFiscalService = new NotaFiscalServiceImpl();
		this.impostoDAO = DAOFactory.getFactory().getImpostoDAO();
	}

	// Construtor criado para executar os testes
	public ImpostoServiceImpl(ImpostoDAO impostoDAO, NotaFiscalService notaFiscalService) {
		super();
		this.impostoDAO = impostoDAO;
		this.notaFiscalService = notaFiscalService;
	}

	@Override
	public void calcularImposto(Integer mes, Integer ano) {
		Cliente cliente = ClienteSession.getCliente();

		if (!this.impostoDAO.findByClienteAndReferencia(cliente, toLocalDate(mes, ano)).isEmpty()) {
			this.impostoDAO.deleteByClienteAndReferencia(cliente, toLocalDate(mes, ano));
		}

		calcularPorRegimeTributario(mes, ano, cliente);

	}

	private void calcularPorRegimeTributario(Integer mes, Integer ano, Cliente cliente) {
		List<NotaFiscal> notaFiscalList = notaFiscalService.findByClienteAndDataEmissao(mes, ano);

		if (RegimeTributario.SIMPLES_NACIONAL.equal(cliente.getClienteRegimeTributario())) {
			Double valor = calcularImpostoSimplesNacional(notaFiscalList);

			impostoDAO.save(buildImposto(mes, ano, cliente, valor, ImpostoTipoRules.SIMPLES_NACIONAL));

		} else if (RegimeTributario.LUCRO_PRESUMIDO.equal(cliente.getClienteRegimeTributario())) {
			calcularImpostoLucroPresumido(notaFiscalList, ano, mes, cliente);
		}
	}

	private LocalDate toLocalDate(Integer mes, Integer ano) {
		return LocalDate.of(ano, mes, 1);
	}

	@Override
	public List<Imposto> findByClienteAndReferencia(Integer mes, Integer ano) {
		if (mes != null && ano != null) {
			return this.impostoDAO.findByClienteAndReferencia(ClienteSession.getCliente(), toLocalDate(mes, ano));
		}
		return this.impostoDAO.findByClienteAndReferencia(ClienteSession.getCliente(), LocalDate.now());
	}

	public List<ImpostoLucroPresumidoDto> calcularImpostoLucroPresumido(List<NotaFiscal> notaFiscalList, Integer ano,
			Integer mes, Cliente cliente) {

		// Lista crida para teste
		List<ImpostoLucroPresumidoDto> lucroPresumidoDtos = new ArrayList<>();

		for (ImpostoTipoRules imp : ImpostoTipoRules.values()) {
			if (!imp.equals(ImpostoTipoRules.SIMPLES_NACIONAL)) {
				Double valorTotal = imp.aplicarAliquota(notaFiscalList);
				impostoDAO.save(buildImposto(mes, ano, cliente, valorTotal, imp));
				lucroPresumidoDtos.add(new ImpostoLucroPresumidoDto(imp, valorTotal));
			}
		}

		return lucroPresumidoDtos;

	}

	public Double calcularImpostoSimplesNacional(List<NotaFiscal> notaFiscalList) {

		Double valorTotal = new Double(0d);

		for (NotaFiscal n : notaFiscalList) {
			valorTotal += SimplesNacionalRules.notaFiscalCalcule(n);
		}
		return valorTotal;
	}

	private Imposto buildImposto(Integer mes, Integer ano, Cliente cliente, Double valor,
			ImpostoTipoRules impostoTipo) {
		Imposto imposto = new Imposto();
		imposto.setCliente(cliente);
		imposto.setPago(Boolean.FALSE);
		imposto.setValor(Float.valueOf(valor.toString()));
		imposto.setImpostoTipo(impostoTipo.toImpostoTipo());
		imposto.setVencimento(buildDate(mes, ano, impostoTipo));
		imposto.setReferencia(buildDate(mes - 1, ano, impostoTipo));
		return imposto;
	}

	private Date buildDate(Integer mes, Integer ano, ImpostoTipoRules imTipoRules) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.DAY_OF_MONTH, imTipoRules.getDiaVencimento());
		calendar.set(Calendar.MONTH, mes);
		calendar.set(Calendar.YEAR, ano);
		return calendar.getTime();
	}

	@Override
	public void updateFromPagamentoCompleted(Integer idImposto) {
		this.impostoDAO.updateFromPagamentoCompleted(ClienteSession.getCliente(), idImposto);
	}

	@Override
	public void updateFromPagamentoCompleted(List<Integer> idImpostoList) {
		idImpostoList.removeAll(Collections.singleton(null));
		this.impostoDAO.updateFromPagamentoCompleted(ClienteSession.getCliente(), idImpostoList);
	}

}
