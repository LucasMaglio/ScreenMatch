package br.com.lucas.screenmatch.service;

public interface IConverteDados {
    //Esse T indentifica que não sabemos oque ira retornar
    <T> T obterDados(String json, Class<T> classe);
}
