package pl.annawyszomirskaszmyd.farmerspring.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pl.annawyszomirskaszmyd.farmerspring.models.services.FarmerSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig extends HandlerInterceptorAdapter implements WebMvcConfigurer {

    private final FarmerSession farmerSession;
    private static final List<String> allowedUrls = Arrays.asList("/registration", "/index", "/login", "/confirm-account?token=");

    @Autowired
    public WebMvcConfig(FarmerSession farmerSession) {
        this.farmerSession = farmerSession;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(this);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrlAsString = request.getRequestURL().toString();

        if(farmerSession.isLogin()){
            return true;
        }

        if(allowedUrls.stream().anyMatch(requestUrlAsString::contains)){
            return super.preHandle(request, response, handler);
        }

        response.sendRedirect("/index");
        return false;
    }
}
