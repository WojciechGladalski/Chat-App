package pl.wojciech.chatApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.wojciech.chatApp.chat.ChatRepository;
import pl.wojciech.chatApp.chat.ChatService;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.wojciech.chatApp")
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver; }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ChatRepository repository() {
        return new ChatRepository();
    }

    @Bean
    @Scope("session")
    public ChatService service() {
        return new ChatService(repository());
    }

    //kontroler nie jest beanem zarządzanym przez spring - nie jest nigdzie wstrzykiwany

}
