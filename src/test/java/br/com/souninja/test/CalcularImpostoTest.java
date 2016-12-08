package br.com.souninja.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.souninja.dao.ImpostoDAO;
import br.com.souninja.dto.ImpostoLucroPresumidoDto;
import br.com.souninja.entity.Cliente;
import br.com.souninja.entity.NotaFiscal;
import br.com.souninja.rules.ImpostoTipoRules;
import br.com.souninja.rules.SimplesNacionalRules;
import br.com.souninja.service.NotaFiscalService;
import br.com.souninja.service.impl.ImpostoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CalcularImpostoTest {

	@Mock
	private ImpostoDAO impostoDAO;

	@Mock
	private NotaFiscalService notaFiscalService;

	private ImpostoServiceImpl impostoService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.impostoService = new ImpostoServiceImpl(impostoDAO, notaFiscalService);
	}

	@Test
	public void calcularImpostoSimplesNacionalTest() {
		double totalImposto = impostoService.calcularImpostoSimplesNacional(buildNotaFiscalList());

		double expected = 610;

		Assert.assertEquals(expected, totalImposto, 0.001);
	}

	@Test
	public void calcularImpostoLucroPresumidoTest() {
		List<ImpostoLucroPresumidoDto> list = impostoService.calcularImpostoLucroPresumido(buildNotaFiscalList(), 2016,
				12, new Cliente());

		verifyImpostos(list);
	}

	private void verifyImpostos(List<ImpostoLucroPresumidoDto> list) {
		for (ImpostoLucroPresumidoDto lp : list) {
			if (lp.getImpostoTipoRules().equals(ImpostoTipoRules.IRPJ)) {
				double expected = 288;
				Assert.assertEquals(expected, lp.getValorTotal(), 0.001);
			}

			if (lp.getImpostoTipoRules().equals(ImpostoTipoRules.ISS)) {
				double expected = 120;
				Assert.assertEquals(expected, lp.getValorTotal(), 0.001);
			}

			if (lp.getImpostoTipoRules().equals(ImpostoTipoRules.COFINS)) {
				double expected = 180;
				Assert.assertEquals(expected, lp.getValorTotal(), 0.001);
			}
		}
	}

	private List<NotaFiscal> buildNotaFiscalList() {
		List<NotaFiscal> notaFilcalList = new ArrayList<>();

		NotaFiscal notaFiscal1 = new NotaFiscal();
		notaFiscal1.setAnexoTipo(SimplesNacionalRules.COMERCIO.getAnexoTipo());
		notaFiscal1.setDataEmissao(new Date());
		notaFiscal1.setValor(1000d);

		NotaFiscal notaFiscal2 = new NotaFiscal();
		notaFiscal2.setAnexoTipo(SimplesNacionalRules.PRESTACAO_DE_SERVICO.getAnexoTipo());
		notaFiscal2.setDataEmissao(new Date());
		notaFiscal2.setValor(5000d);

		notaFilcalList.add(notaFiscal1);
		notaFilcalList.add(notaFiscal2);

		return notaFilcalList;
	}

}
