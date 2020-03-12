package icu.cyclone.alex;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		Enumeration<String> params = config.getInitParameterNames();

		if (params.hasMoreElements()) {
			System.out.println("Init parameters included:");
		}

		while (params.hasMoreElements()) {
			System.out.println(params.nextElement());
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ServletOutputStream out = resp.getOutputStream();
		printHello(out);
		printInfo(req, resp);
		closeHtml(out);
	}

	private void printHello(ServletOutputStream out) throws IOException {
		out.println("<html>");
		out.println("<head><title>My Servlet</title></head>");
		out.println("<body>");
		out.println("<h3>Hello From Servlet</h3>");
		out.println("This is my first Servlet");
		out.println("<br>");
	}

	private void closeHtml(ServletOutputStream out) throws IOException {
		out.println("</body>");
		out.println("</html>");
	}

	private void printInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ServletOutputStream out = resp.getOutputStream();

		out.println("<style> span {color:blue;} </style>");

		String requestURL = req.getRequestURL().toString();
		out.println("<br><span>requestURL:</span>");
		out.println(requestURL);

		String requestURI = req.getRequestURI();
		out.println("<br><span>requestURI:</span>");
		out.println(requestURI);

		String contextPath = req.getContextPath();
		out.println("<br><span>contextPath:</span>");
		out.println(contextPath);

		out.println("<br><span>servletPath:</span>");
		String servletPath = req.getServletPath();
		out.println(servletPath);

		String queryString = req.getQueryString();
		out.println("<br><span>queryString:</span>");
		out.println(queryString);

		out.println("<br><br><b>Server info:</b>");

		out.println("<br><span>serverName:</span>");
		String serverName = req.getServerName();
		out.println(serverName);

		out.println("<br><span>serverPort:</span>");
		int serverPort = req.getServerPort();
		out.println(serverPort + "");


		out.println("<br><br><b>Init Parameters:</b>");
		Enumeration<String> parameterNames = getInitParameterNames();
		if (!parameterNames.hasMoreElements()) {
			out.println("<br><span>Init parameters not found</span>");
		} else {
			while (parameterNames.hasMoreElements()) {
				String parameter = parameterNames.nextElement();
				out.println("<br><span>" + parameter + ":</span>");
				out.println(getInitParameter(parameter));
			}
		}

		out.println("<br><br><b>Client info:</b>");

		out.println("<br><span>remoteAddr:</span>");
		String remoteAddr = req.getRemoteAddr();
		out.println(remoteAddr);

		out.println("<br><span>remoteHost:</span>");
		String remoteHost = req.getRemoteHost();
		out.println(remoteHost);

		out.println("<br><span>remoteHost:</span>");
		int remotePort = req.getRemotePort();
		out.println(remotePort + "");

		out.println("<br><span>remoteUser:</span>");
		String remoteUser = req.getRemoteUser();
		out.println(remoteUser);

		out.println("<br><br><b>headers:</b>");
		Enumeration<String> headers = req.getHeaderNames();
		while (headers.hasMoreElements()) {
			String header = headers.nextElement();
			out.println("<br><span>" + header + "</span>: " + req.getHeader(header));
		}

		out.println("<br><br><b>Servlet Context info:</b>");
		ServletContext servletContext = req.getServletContext();

		out.println("<br><span>realPath:</span>");
		String realPath = servletContext.getRealPath("");
		out.println(realPath);
	}
}
