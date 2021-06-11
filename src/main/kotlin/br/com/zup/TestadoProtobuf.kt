package br.com.zup

import java.io.FileInputStream
import java.io.FileOutputStream

fun main(){

    //FuncionarioRequest foi descrita lá no arquivo .proto,
    // logo pra criar um objeto desse tipo teremos que usar o .newBuilder().build()
    val request = FuncionarioRequest.newBuilder()
        .setNome("Yuri Matheus")
        .setCpf("000.000.000-00")
        .setSalario(2000.0)
        .setAtivo(true)
        .setCargo(Cargo.DEV)
        .addEnderecos(Endereco.newBuilder()
            .setLogradouro("Rua das Tabajaras, 100")
            .setCep("00000-000")
            .setLogradouro("Na rua do mercado")
            .build())
        .build()

    //Pritn abaixo é para mostrar que funcionou. Usamos a classe FuncionarioRequest e Endereco que foram descritas
    // lá no arquivo .proto
    println(request)

    //Sabemos que para transportar as mensagens na rede o protobuf serializa para binário, ele fará isso por baixo dos panos
    // mas vamos forçar aqui somente para vermos! Para simnular vamos escrever o request em arquivo!
    request.writeTo(FileOutputStream("funcionario-request.bin"))

    //Vamos ler agora o arquivo novamente e alterá-lo. Novamente a simulação do que ocorre na rede
    val request2 = FuncionarioRequest.newBuilder().mergeFrom(FileInputStream("funcionario-request.bin"))
    request2.setCargo(Cargo.GERENTE)
    println(request2)

    //Agora vamos praticar utilizando a rede. Vá para arquivo Servidor.kt, execute-o e utilize a ferramenta
    // BloomRPC para testar. Nessa ferramente vc carregará o nosso arquivo .proto

}