package top.tosim.eqmanager.config.filter;

import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName="methodFilter", urlPatterns="/*")
public class HttpMethodFilter extends HiddenHttpMethodFilter{
}
