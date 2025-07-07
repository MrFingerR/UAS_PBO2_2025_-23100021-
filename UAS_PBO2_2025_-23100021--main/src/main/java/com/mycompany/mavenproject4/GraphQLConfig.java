package com.mycompany.mavenproject4;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

public class GraphQLConfig {
   public static GraphQL init() throws IOException {
       InputStream schemaStream = GraphQLConfig.class.getClassLoader().getResourceAsStream("schema.graphqls");

       if (schemaStream == null) {
           throw new RuntimeException("schema.graphqls not found in classpath.");
       }

       String schema = new String(Objects.requireNonNull(schemaStream).readAllBytes());

       TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schema);
       RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
           .type("Query", builder -> builder
               .dataFetcher("allProducts", env -> MahasiswaRepository.findAll())
               .dataFetcher("mahasiswaById", env -> {
                   Long id = Long.parseLong(env.getArgument("id")); 
                   return MahasiswaRepository.findById(id);
               })
           )
           .type("Mutation", builder -> builder
               .dataFetcher("addProduct", env -> MahasiswaRepository.add(
                   env.getArgument("name"),
                   ((Number) env.getArgument("price")).doubleValue(),
                   env.getArgument("study"), schema
               ))
               .dataFetcher("updateProduct", env -> MahasiswaRepository.update(
                   Long.parseLong(env.getArgument("id")),
                   env.getArgument("name"),
                   ((Number) env.getArgument("nim")).doubleValue(),
                   env.getArgument("study"), schema
               ))
               .dataFetcher("deleteProduct", env -> {
                   Long id = Long.parseLong(env.getArgument("id")); 
                   return MahasiswaRepository.delete(id);
               })
           )
           .build();

       GraphQLSchema schemaFinal = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
       return GraphQL.newGraphQL(schemaFinal).build();
   }
}