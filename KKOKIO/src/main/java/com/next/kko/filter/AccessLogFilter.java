package com.next.kko.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessLogFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, 
			FilterChain chain)
			throws IOException, ServletException {
			
			HttpServletRequest request =  (HttpServletRequest)req;
			
//			String ssoUser = request.getHeader("SSO_USER");
//			System.out.println(ssoUser+"!!!!");
			
			String remoteAddr = StringUtils.defaultString(request.getRemoteAddr(),"-");
//			System.out.println(remoteAddr);
			String uri = StringUtils.defaultIfEmpty(request.getRequestURI(), "");
			String url = request.getRequestURL() == null ? "":request.getRequestURL().toString();
			String queryString = StringUtils.defaultIfEmpty(request.getQueryString(), "");
			String refer = StringUtils.defaultIfEmpty(request.getHeader("Referer"),"");
			String agent = StringUtils.defaultIfEmpty(request.getHeader("User-Agent"),"-");
			
			String fullUrl = url;
			fullUrl += StringUtils.isNotEmpty(queryString) ? "?"+queryString:queryString;
			
//			System.out.println(fullUrl);
			
			StringBuffer result = new StringBuffer();
			result.append(":");
			result.append(remoteAddr);
			result.append(":");
			result.append(fullUrl);
			result.append(":");
			result.append(refer);
			result.append(":");
			result.append(agent);
			logger.info("[LOG FILTER]"+result.toString());
			chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	@Override
	public void destroy() {
	}


}
