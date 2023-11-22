package Helper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class ErrorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);

        /* Error 404 */
        if (response.getContentType() == null && response.getOutputStream().toString().contains("HTTP Status 404")) {
            /* Redirige a Login*/
        	RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
        }
    }

    @Override
    public void destroy() {}
}
