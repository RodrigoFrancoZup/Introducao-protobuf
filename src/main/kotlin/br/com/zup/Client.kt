package br.com.zup

import io.grpc.ManagedChannelBuilder

fun main(){

    //Para criar um Client (Consumir o serviço) precisamos primeiro de um canal - Channel
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    //Criando um client que consumirá o serviço (Pensa comigo, para criar um Cliente precismaos da classe FuncionarioServiceGrpc
    // e essa classe veio da compilação do arquivo .proto, logo para alguem de fora criar um cliente para consumir esse
    // serviço teremos que enviar o mesmo arquivo .proto, pois assim a pessoa poderá compilá-lo e terá a classe FuncionarioServiceGrpc
    val client = FuncionarioServiceGrpc.newBlockingStub(channel)

    //Criando nosso request
    val request = FuncionarioRequest.newBuilder()
        .setNome("Yuri Matheus")
        .setCpf("000.000.000-00")
        .setIdade(22)
        .setSalario(2000.0)
        .setAtivo(true)
        .setCargo(Cargo.DEV)
        .addEnderecos(Endereco.newBuilder()
            .setLogradouro("Rua das Tabajaras, 100")
            .setCep("00000-000")
            .setLogradouro("Na rua do mercado")
            .build())
        .build()

    //O client foi criado da classe de serviço FuncionarioServiceGrpc (essa foi descrita no nosso arquivo .proto)
    // por isso ele já tem o método cadastrar()
    val response = client.cadastrar(request)

    println(response)

}