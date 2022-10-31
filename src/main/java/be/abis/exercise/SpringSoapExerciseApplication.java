package be.abis.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

import javax.servlet.Servlet;

@SpringBootApplication
@EnableWs
public class SpringSoapExerciseApplication extends WsConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SpringSoapExerciseApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean<Servlet> messageDispatcherServlet(
            ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet =
                new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);

        return new ServletRegistrationBean<>(servlet,
                "/abis/ws/*");
    }

    @Bean(name = "courses")
    public Wsdl11Definition defaultWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition =
                new SimpleWsdl11Definition();
        wsdl11Definition
                .setWsdl(new ClassPathResource("wsdl/courses.wsdl"));

        return wsdl11Definition;
    }
}
