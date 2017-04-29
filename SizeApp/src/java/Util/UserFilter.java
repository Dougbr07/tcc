package Util;

import Model.UsuarioModel;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserFilter implements Filter {

    public void destroy() {
        // TODO Auto-generated method stub

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        UsuarioModel usuarioModel = (UsuarioModel) session.getAttribute("usuario");
        if (usuarioModel != null) {
            if (usuarioModel.getPerfil() == 2) {
                ((HttpServletResponse) response).sendRedirect(session.getServletContext().getContextPath() + "/");
            }
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse) response).sendRedirect(session.getServletContext().getContextPath() + "/");
        }
        
        
    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
