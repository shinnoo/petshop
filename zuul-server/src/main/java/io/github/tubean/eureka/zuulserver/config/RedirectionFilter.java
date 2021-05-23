package io.github.tubean.eureka.zuulserver.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RedirectionFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 2;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		ctx.addZuulRequestHeader("Content-Type", "application/json");
//		String header = request.getHeader("YOUR_HEADER_PARAM");
//
//		if ("YOUR_A_LOGIC".equals(header) ) {
//			ctx.put("serviceId", "serviceA");
//			//ctx.setRouteHost(new URL("http://Service_A_URL”));
//		} else { // "YOUR_B_LOGIC"
//			ctx.put("serviceId", "serviceB");
//			//ctx.setRouteHost(new URL("http://Service_B_URL”));
//		}
//		log.info(String.format("%s requ
//		est to %s", request.getMethod(),
//				request.getRequestURL().toString()));
		return null;
	}
}

