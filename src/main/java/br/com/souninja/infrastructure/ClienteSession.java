package br.com.souninja.infrastructure;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.souninja.entity.Cliente;

public class ClienteSession implements Filter {

	public final static String SESSION = "PAYLOAD-SESSION";

	private static Cliente cliente;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = ((HttpServletRequest) req);

		HttpSession session = httpServletRequest.getSession();

		cliente = (Cliente) session.getAttribute(SESSION);

		chain.doFilter(req, res);
	}

	public static Cliente getCliente() {
		return cliente;
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}