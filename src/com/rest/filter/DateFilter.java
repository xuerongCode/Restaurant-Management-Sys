package com.rest.filter;


import com.rest.domain.DinnerTable;

import javax.servlet.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xuero on 2017/7/15.
 */
public class DateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest,servletResponse);

        List<DinnerTable> tables = (List<DinnerTable>) servletRequest.getAttribute("tableList");

        Iterator<DinnerTable> iterator = tables.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    @Override
    public void destroy() {

    }
}
