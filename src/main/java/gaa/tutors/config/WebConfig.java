package gaa.tutors.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public ClassLoaderTemplateResolver templateResolver(){
        var templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        var templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }


    @Bean
    public ViewResolver viewResolver() {
        var viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/orderPage/{id}").setViewName("order");
        registry.addViewController("/reg").setViewName("registration");
        registry.addViewController("/loginin").setViewName("login");
        registry.addViewController("/register").setViewName("registrationPage");
        registry.addViewController("/profile").setViewName("profile");
        registry.addViewController("/addtutor").setViewName("addtutor");
        registry.addViewController("/addtutoradmin").setViewName("addtutoradmin");
        registry.addViewController("/updatetutorAdmin/{id}").setViewName("updatetutorAdmin");
    }

}