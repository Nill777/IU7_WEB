package com.dims.api.invoker

import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
//@ComponentScan(basePackages = ["com.dims.api.invoker", "com.dims.api.controller", "com.dims.api.model"])
@ComponentScan(basePackages = ["com.dims.api"])
@EnableJpaRepositories(basePackages = ["com.dims.api.data.repositories"])
@EntityScan(basePackages = ["com.dims.api.data.entities"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
