package com.example.todo.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

@Configuration
@EnableWebFlux
public class WebFluxGlobalConfig implements WebFluxConfigurer {

    // security의 corsFilter를 기본으로 사용. security를 사용하지 않을 경우 세팅.
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                //.allowCredentials(true)
//        ;
//        // Add more mappings...
//    }

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
//        registrar.setUseIsoFormat(true);
//        registrar.registerFormatters(registry);
//    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().enableLoggingRequestDetails(true);

        ObjectMapper jackson2ObjectMapperConfigure = Jackson2ObjectMapperBuilder
                .json()
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer())
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer())
                .build()
                .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                ;


        configurer.defaultCodecs().jackson2SmileDecoder(new Jackson2JsonDecoder(jackson2ObjectMapperConfigure));
        configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(jackson2ObjectMapperConfigure));
    }

    class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

        protected LocalDateTimeSerializer(Class t) {
            super(t);
        }

        public LocalDateTimeSerializer() {
            this(null);
        }

        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            if(Objects.nonNull(value)) {
                gen.writeString(value.format(DateTimeFormatter.ISO_DATE));
            }
        }
    }

    class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

        protected LocalDateTimeDeserializer(Class vc) {
            super(vc);
        }

        public LocalDateTimeDeserializer() {
            this(null);
        }

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return LocalDateTime.parse(p.getText());
        }
    }
}
