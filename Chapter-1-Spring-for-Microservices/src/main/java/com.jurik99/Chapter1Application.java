package com.jurik99;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import com.jurik99.config.Config;

import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties(Config.class)
@EnableSwagger2
public class Chapter1Application {

	public static void main(final String[] args) {
		SpringApplication.run(Chapter1Application.class);
	}

	@Bean
    public Docket api() throws IOException, XmlPullParserException {
        final MavenXpp3Reader reader = new MavenXpp3Reader();
        final Model model = reader.read(new FileReader("pom.xml"));

        final ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder()
                .title("Person Service API Documentation")
                .description("Documentation automatically generated")
                .version(model.getVersion())
                .contact(new Contact("Patryk Grudzien", "patryk.grudzien@my-app.com", "test@email.com"));

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.jurik99.resource"))
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfoBuilder.build());
    }
}
