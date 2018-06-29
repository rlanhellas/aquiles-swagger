# How to Use ?

1- Adicione a dependência do aquiles-swagger ao seu projeto:

        <dependency>
            <groupId>br.com.aquiles</groupId>
            <artifactId>aquiles-swagger</artifactId>
            <version>LATEST</version>
        </dependency>
        
2- Crie seus WebServices sempre com as anotações @API do Swagger, exemplo:

            @Api(value = "Testing Server")
            @Path(value = "main")
            public class ReportWs {
            
                @GET
                @Path("/test")
                @ApiOperation(value = "Test if server is OK")
                @ApiResponses({
                        @ApiResponse(
                                code = 200,
                                message = "Everything OK"),
                        @ApiResponse(code = 500,
                                message = "Fatal error")
                })
                @Produces("text/plain")
                public Response test() {
                    return Response.ok().build();
                }
            }
            
3- Inicie seu servidor e acesse o Aquiles Swagger UI através da URL: 

        http://seuip:porta/suaaplicacao/swagger/index.html
        
4- Através da interface do Swagger UI você conseguirá fazer chamadas e testes nos seus WebServices. O seu resultado final
será parecido com este exemplo: http://petstore.swagger.io/