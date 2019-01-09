package koredotai.botkit.sdk.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
@Order(1)
public class ServiceFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(ServiceFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
/*        Enumeration<String> headerNames = req.getHeaderNames();
        log.debug("request "+ req.getRequestURL()+"  Headers: ");
        for (Iterator iterator = headerNames.asIterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            log.debug("        " + name + "\t\t" + req.getHeader(name));
        }*/

/*        HttpServletResponse res = (HttpServletResponse) response;

        ContentCachingResponseWrapper wrapper = new ContentCachingResponseWrapper(res);*/

        chain.doFilter(request, response);
/*        log.debug("response Headers: ");
        for (Iterator iterator = wrapper.getHeaderNames().iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            log.debug("        " + name + "\t\t" + wrapper.getHeader(name));
        }
        byte[] bytes = wrapper.getContentAsByteArray();
        String s = new String(bytes);
        log.debug("completed ServiceFilter" + res.getBufferSize() + "       " + s);*/
        log.debug("completed filter");
    }

    @Override
    public void destroy() {
        log.debug("Destroying WebFilter");
    }
}
