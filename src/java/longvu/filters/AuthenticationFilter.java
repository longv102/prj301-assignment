/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvu.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longvu.user.UserDTO;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {
    
    private static final boolean debug = true;
    
    private final List<String> USER_RESOURCES;
    private final List<String> ADMIN_PRODUCT_RESOURCES;
    private final List<String> ADMIN_USER_RESOURCES;
    private final List<String> NON_AUTHEN_RESOURCES;
    private final String US = "US";
    private final String ADU = "ADU";
    private final String ADP = "ADP";
    private final String LOGIN_PAGE = "login.html";
    
    private FilterConfig filterConfig = null;
    
    public AuthenticationFilter() {
        USER_RESOURCES = new ArrayList<>();
        USER_RESOURCES.add("user.jsp");
        
        ADMIN_PRODUCT_RESOURCES = new ArrayList<>();
        ADMIN_PRODUCT_RESOURCES.add("adminProduct.jsp");
        ADMIN_PRODUCT_RESOURCES.add("AddProductController");
        ADMIN_PRODUCT_RESOURCES.add("DeleteProductController");
        ADMIN_PRODUCT_RESOURCES.add("SearchProductController");
        ADMIN_PRODUCT_RESOURCES.add("UpdateProductController");
        
        
        ADMIN_USER_RESOURCES = new ArrayList<>();
        ADMIN_USER_RESOURCES.add("adminUser.jsp");
        
        NON_AUTHEN_RESOURCES = new ArrayList<>();
        NON_AUTHEN_RESOURCES.add("login.html");
        NON_AUTHEN_RESOURCES.add("createUser.jsp");
        NON_AUTHEN_RESOURCES.add("shopping.jsp");
        NON_AUTHEN_RESOURCES.add("MainController");
        NON_AUTHEN_RESOURCES.add("LoginController");
        NON_AUTHEN_RESOURCES.add("css/adminStyle.css");
        NON_AUTHEN_RESOURCES.add("css/createUserStyle.css");
        NON_AUTHEN_RESOURCES.add("css/loginStyle.css");
        NON_AUTHEN_RESOURCES.add("css/shoppingStyle.css");
        NON_AUTHEN_RESOURCES.add("js/createUserScript.js");
        NON_AUTHEN_RESOURCES.add("js/loginScript.js");
        NON_AUTHEN_RESOURCES.add(".png");
        NON_AUTHEN_RESOURCES.add(".jpg");
        NON_AUTHEN_RESOURCES.add(".gif");
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenticationFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenticationFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            String uri = req.getRequestURI();
            int index = uri.lastIndexOf("/");
            String resource = uri.substring(index + 1);
            boolean checkContain = false;
            for (String value : NON_AUTHEN_RESOURCES) {
                if (uri.contains(value)) {
                    checkContain = true;
                    break;
                }
            }
            if (checkContain) {
                chain.doFilter(request, response);
            } else {
                HttpSession session = req.getSession();
                if (session == null || session.getAttribute("LOGIN_USER") == null) {
                    res.sendRedirect(LOGIN_PAGE);
                } else {
                    UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                    String roleId = loginUser.getRoleId();
                    if (ADU.equals(roleId) && ADMIN_USER_RESOURCES.contains(resource)) {
                        chain.doFilter(request, response);
                    } else if (ADP.equals(roleId) && ADMIN_PRODUCT_RESOURCES.contains(resource)) {
                        chain.doFilter(request, response);
                    } else if (US.equals(roleId) && USER_RESOURCES.contains(resource)) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect(LOGIN_PAGE);
                    }
                }
            }
        } catch(IOException | ServletException e) {
            log("Error at Authentication Filter: " + e.toString());
        } 
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AuthenticationFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenticationFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthenticationFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
