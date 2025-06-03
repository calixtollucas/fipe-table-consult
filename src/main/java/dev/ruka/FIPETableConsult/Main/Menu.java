package dev.ruka.FIPETableConsult.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import dev.ruka.FIPETableConsult.model.FIPEApiData;
import dev.ruka.FIPETableConsult.model.ModeloFIPE;
import dev.ruka.FIPETableConsult.model.VeiculoData;
import dev.ruka.FIPETableConsult.services.ConsumoAPI;
import dev.ruka.FIPETableConsult.services.ConversorJson;

public class Menu {

    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";

    public void start(){

        ConversorJson conversor = new ConversorJson();
        List<VeiculoData> veiculos = new ArrayList<>();

        try( Scanner sc = new Scanner(System.in);){
            System.out.println("   CONSULTA FIPE   ");
            System.out.println("===================");
            System.out.println("1 - Carros");
            System.out.println("2 - motos");
            System.out.println("3 - Caminhões");
            System.out.println("Escolha um tipo de veículo: ");

            String veiculo = sc.nextLine();
            String tipoVeiculo = switch (veiculo){
                case "1" -> "carros";
                case "2" -> "motos";
                case "3" -> "caminhoes";
                default -> null;
            };

            if(tipoVeiculo == null){
                throw new IllegalArgumentException("Input inválido");
            } else {
                String json = ConsumoAPI.fetch(String.format("%s%s/marcas", BASE_URL, tipoVeiculo));
                List<FIPEApiData> listaMarcas = conversor.obterLista(json, FIPEApiData.class);
                
                listaMarcas.stream()
                    .sorted(Comparator.comparing(FIPEApiData::code))
                    .forEach(m -> System.out.println("Codigo: "+m.code()+" Marca: "+m.name()));
            }
            System.out.println("Escolha o código de uma marca: ");
            String codigoMarca = sc.nextLine();

            if(codigoMarca == null){
                throw new IllegalArgumentException("Input inválido");
            } else {

                String json = ConsumoAPI.fetch(String.format("%s%s/marcas/%s/modelos", BASE_URL, tipoVeiculo, codigoMarca));
                ModeloFIPE modelos = conversor.obterDados(json, ModeloFIPE.class);
                
                modelos.modelos().stream()
                    .sorted(Comparator.comparing(FIPEApiData::code))
                    .forEach(m -> System.out.println("Codigo: "+m.code()+" Modelo: "+m.name()));

                System.out.println("Digite um trecho do veículo para filtrar: ");
                String trechoVeiculo = sc.nextLine();

                modelos.modelos().stream()
                    .filter(m -> m.name().toLowerCase().contains(trechoVeiculo.toLowerCase()))
                    .forEach(m -> System.out.println("Codigo: "+m.code()+" Modelo: "+m.name()));
            }

            System.out.println("Digite o código do modelo: ");
            String codigoModelo = sc.nextLine();

            if(codigoModelo == null){
                throw new IllegalArgumentException("Input inválido");
            } else {
                String json = ConsumoAPI.fetch(String.format("%s%s/marcas/%s/modelos/%s/anos", BASE_URL, tipoVeiculo, codigoMarca, codigoModelo));
                List<FIPEApiData> anos = conversor.obterLista(json, FIPEApiData.class);

                for(FIPEApiData ano : anos){
                    String jsonVeiculo = ConsumoAPI.fetch(
                        String.format("%s%s/marcas/%s/modelos/%s/anos/%s", BASE_URL, tipoVeiculo, codigoMarca, codigoModelo, ano.code())
                    );
                    VeiculoData veiculoData = conversor.obterDados(jsonVeiculo, VeiculoData.class);
                    veiculos.add(veiculoData);
                }
                    System.out.println("  RESULTADO  ");
                    System.out.println("=============");
                    veiculos.stream()
                        .forEach(
                            v ->{
                                System.out.println("----------------");
                                System.out.println("Marca: "+v.marca());
                                System.out.println("Modelo: "+v.modelo());
                                System.out.println("Combustivel: "+v.combustivel());
                                System.out.println("Ano: "+v.ano());
                                System.out.println("Valor: "+v.valor());
                                System.out.println("Codigo FIPE: "+v.codigoFipe());
                                System.out.println("----------------");
                            }
                        );
            }

        } catch(IOException e){
            System.out.println("Ocorreu um erro!");
            e.printStackTrace();
        } catch(InterruptedException e){
            System.out.println("a consulta foi interrompida");
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
