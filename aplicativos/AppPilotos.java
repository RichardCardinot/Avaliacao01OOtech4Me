package aplicativos;

import java.io.IOException;
import java.util.Scanner;

import classes.Aeronave;
import classes.Piloto;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        final int MAX_ELEMENTOS = 20; 
        int opcao, qtdCadastrados = 0, qtdAeroCadastrados = 0;
        Piloto[] pilotos = new Piloto[MAX_ELEMENTOS];
        Aeronave[] aeronaves = new Aeronave[MAX_ELEMENTOS];
        Scanner in = new Scanner(System.in);

        do {
            System.out.printf("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Cadastrar Aeronave");
            System.out.println("5 - Listar aeronaves cadastradas");
            System.out.println("6 - Localizar aeronave pelo código de registro");
            System.out.println("7 - Aumentar espaço de armazenamento");                
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.printf("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                } 


                //Cadastre seu piloto aqui
                Piloto piloto = new Piloto();
                System.out.print("Informe o nome do piloto: ");
                piloto.setNome(in.nextLine());
                System.out.print("Informe o CPF: ");
                piloto.setCpf(in.nextLine());
                System.out.print("Informe a Matrícula: ");
                piloto.setMatricula(in.nextInt());
                pilotos[qtdCadastrados] = piloto;
                System.out.printf("\nPiloto cadastrado com sucesso.");
                qtdCadastrados++;

                voltarMenu(in);
            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.printf("\nNão há pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                // Exiba os pilotos aqui
                for (int i = 0; i < qtdCadastrados; i++) {
                    System.out.printf("\n--Piloto %s, CPF: %s, Matrícula: %d", pilotos[i].getNome(), pilotos[i].getCpf(), pilotos[i].getMatricula());
                }          

                voltarMenu(in);
            } else if (opcao == 3) {
                //Localizar piloto pelo CPF

                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.printf("\nNão há pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }
                
                System.out.print("Informe o CPF a ser localizado: ");
                int posicaoCpf = localizarEmPilotos(pilotos, qtdCadastrados, in.nextLine());
                
                    if (posicaoCpf > -1) {
                        System.out.print("Piloto localizado!");
                        System.out.printf("\n--Piloto %s, CPF: %s, Matrícula: %d", pilotos[posicaoCpf].getNome(), pilotos[posicaoCpf].getCpf(), pilotos[posicaoCpf].getMatricula());                  
                        
                    } else {
                        System.out.println("Piloto não localizado!");
                    }

                
                voltarMenu(in);

            } else if (opcao == 4) {
                //Cadastrar Aeronave

                // Se não tem piloto cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.printf("\nPara cadastrar a aeronave, é necessário que o piloto já esteja cadastrado.");
                    voltarMenu(in);
                    continue;
                }

                // Se não tem mais espaço no vetor de aeronaves, caio fora
                if (qtdAeroCadastrados == MAX_ELEMENTOS) {
                    System.out.printf("\nNão há espaço para cadastrar novas aeronaves.");
                    voltarMenu(in);
                    continue;
                } 

                System.out.print("Informe o código de registro: ");
                String cdRegistro = in.nextLine();
                System.out.print("Informe o modelo: ");
                String modelo = in.nextLine();
                boolean concluido = false;
                int op = 1;

                while (!concluido && op == 1) {
                    
                    System.out.println("Informe o cpf do piloto: ");                
                    String alvoCpf = in.nextLine();
                    int posicaoCpf = localizarEmPilotos(pilotos, qtdCadastrados, alvoCpf);
                    
                        if (posicaoCpf > -1) {
                            Aeronave aeronave = new Aeronave(cdRegistro, modelo, pilotos[posicaoCpf]);
                            aeronaves[qtdAeroCadastrados] = aeronave;
                            System.out.print("Aeronave cadastrada com sucesso.");
                            qtdAeroCadastrados++;
                            concluido = true;         
                        } else {
                            System.out.println("Piloto não localizado!");
                            System.out.println("Tentar novamente?");
                            System.out.println("[1]Sim, [2]Não");
                            op = in.nextInt();
                            in.nextLine();
                        }
                }              


                voltarMenu(in);

            } else if (opcao == 5) {
                //Listar aeronaves cadastradas

                // Se não tem aeronaves cadastradas no vetor, caio fora
                if (qtdAeroCadastrados == 0) {
                    System.out.printf("\nNão há aeronaves cadastradas para exibir.");
                    voltarMenu(in);
                    continue;
                }

                // Exiba as aeronaves aqui
                for(int i = 0; i < qtdAeroCadastrados; i++) {
                    System.out.printf("%n--Aeronave: Registro: %s, Modelo: %s, Piloto: %s.",aeronaves[i].getCdRegistro(),aeronaves[i].getModelo(),aeronaves[i].getPiloto().getNome());    
                }

                voltarMenu(in);

            } else if (opcao == 6) {
                //Localizar aeronave pelo código de registro

                // Se não tem aeronaves cadastradas no vetor, caio fora
                if (qtdAeroCadastrados == 0) {
                    System.out.printf("\nNão há aeronaves cadastradas para exibir.");
                    voltarMenu(in);
                    continue;
                }
                
                System.out.print("Informe o código de registro a ser localizado: ");
                int posicaoRegistro = localizarEmAeronaves(aeronaves, qtdAeroCadastrados, in.nextLine());
                
                    if (posicaoRegistro > -1) {
                        System.out.print("Aeronave localizada!");
                        System.out.printf("%n--Aeronave: Registro: %s, Modelo: %s, Piloto: %s.",aeronaves[posicaoRegistro].getCdRegistro(),aeronaves[posicaoRegistro].getModelo(),aeronaves[posicaoRegistro].getPiloto().getNome());                    
                        
                    } else {
                        System.out.println("Aeronave não localizada!");
                    }

                
                voltarMenu(in);

            }  else if (opcao == 7) {
                // Aumentar espaço de armazenamento
                System.out.print("Informe o novo tamanho de armazenamento: ");
                final int NEW_MAX_ELEMENTOS = in.nextInt();

                if (NEW_MAX_ELEMENTOS > MAX_ELEMENTOS) {
                    Piloto[] pilotosAmpliado = new Piloto[NEW_MAX_ELEMENTOS];
                    Aeronave[] aeronavesAmpliado = new Aeronave[NEW_MAX_ELEMENTOS];

                    for (int i = 0; i < pilotos.length; i++) {
                        pilotosAmpliado[i] = pilotos[i];                  
                    }

                    for (int i = 0; i < aeronaves.length; i++) {
                        aeronavesAmpliado[i] = aeronaves[i];                  
                    }

                    //Menu para o vetor ampliado
                    do {
                        System.out.printf("\n****\nMENU\n****\n");
                        System.out.println("1 - Cadastrar piloto");
                        System.out.println("2 - Listar pilotos cadastrados");
                        System.out.println("3 - Localizar piloto pelo CPF");
                        System.out.println("4 - Cadastrar Aeronave");
                        System.out.println("5 - Listar aeronaves cadastradas");
                        System.out.println("6 - Localizar aeronave pelo código de registro");              
                        System.out.println("0 - Sair");
                        System.out.print("Opção: ");
            
                        opcao = in.nextInt();
                        in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior
            
                        if (opcao == 1) {
                            // Se não tem mais espaço no vetor, caio fora
                            if (qtdCadastrados == NEW_MAX_ELEMENTOS) {
                                System.out.printf("\nNão há espaço para cadastrar novos pilotos.");
                                voltarMenu(in);
                                continue;
                            }            
            
                            //Cadastre seu piloto aqui
                            Piloto piloto = new Piloto();
                            System.out.print("Informe o nome do piloto: ");
                            piloto.setNome(in.nextLine());
                            System.out.print("Informe o CPF: ");
                            piloto.setCpf(in.nextLine());
                            System.out.print("Informe a Matrícula: ");
                            piloto.setMatricula(in.nextInt());
                            
                            pilotosAmpliado[qtdCadastrados] = piloto;
                            qtdCadastrados++;
            
                            System.out.printf("\nPiloto cadastrado com sucesso.");

                            voltarMenu(in);
                        } else if (opcao == 2) {
                            // Se não tem ninguém cadastrado no vetor, caio fora
                            if (qtdCadastrados == 0) {
                                System.out.printf("\nNão há motoristas cadastrados para exibir.");
                                voltarMenu(in);
                                continue;
                            }
            
                            // Exiba os pilotos aqui
                            for(int i = 0; i < qtdCadastrados; i++) {
                                System.out.printf("\n--Piloto %s, CPF: %s, Matrícula: %d", pilotosAmpliado[i].getNome(), pilotosAmpliado[i].getCpf(), pilotosAmpliado[i].getMatricula());
                            }
            
                            voltarMenu(in);
                        } else if (opcao == 3) {
                            //Localizar piloto pelo CPF

                            // Se não tem ninguém cadastrado no vetor, caio fora
                            if (qtdCadastrados == 0) {
                                System.out.printf("\nNão há pilotos cadastrados para exibir.");
                                voltarMenu(in);
                                continue;
                            }
                            
                            System.out.print("Informe o CPF a ser localizado: ");
                            int posicaoCpf = localizarEmPilotosAmpliado(pilotosAmpliado, qtdAeroCadastrados, in.nextLine());
                            
                                if (posicaoCpf > -1) {
                                    System.out.print("Piloto localizado!");
                                    System.out.printf("\n--Piloto: %s, CPF: %s, Matrícula: %d", pilotosAmpliado[posicaoCpf].getNome(), pilotosAmpliado[posicaoCpf].getCpf(), pilotosAmpliado[posicaoCpf].getMatricula());                  
                                    
                                } else {
                                    System.out.println("Piloto não localizado!");
                                }

                            
                            voltarMenu(in);
                        } else if (opcao == 4) {
                            //Cadastrar Aeronave

                            // Se não tem piloto cadastrado no vetor, caio fora
                            if (qtdCadastrados == 0) {
                             System.out.printf("\nPara cadastrar a aeronave, é necessário que o piloto já esteja cadastrado.");
                                voltarMenu(in);
                                continue;
                            }

                            // Se não tem mais espaço no vetor de aeronaves, caio fora
                            if (qtdAeroCadastrados == NEW_MAX_ELEMENTOS) {
                                System.out.printf("\nNão há espaço para cadastrar novas aeronaves.");
                                voltarMenu(in);
                                continue;
                            } 

                            System.out.print("Informe o código de registro: ");
                            String cdRegistro = in.nextLine();
                            System.out.print("Informe o modelo: ");
                            String modelo = in.nextLine();
                            boolean concluido = false;
                            int op = 1;

                            while (!concluido && op == 1) {
                    
                                System.out.print("Informe o cpf do piloto: ");
                                String alvoCpf = in.nextLine();
                                int posicaoCpf = localizarEmPilotosAmpliado(pilotosAmpliado, qtdCadastrados, alvoCpf);
                    
                                    if (posicaoCpf > -1) {
                                        Aeronave aeronave = new Aeronave(cdRegistro, modelo, pilotosAmpliado[posicaoCpf]);
                                        aeronavesAmpliado[qtdAeroCadastrados] = aeronave;
                                        System.out.print("Aeronave cadastrada com sucesso.");
                                        qtdAeroCadastrados++;
                                        concluido = true;         
                                    } else {
                                        System.out.println("Piloto não localizado!");
                                        System.out.println("Tentar novamente?");
                                        System.out.println("[1]Sim, [2]Não");
                                        op = in.nextInt();
                                        in.nextLine();
                                    }
                            }              

                            voltarMenu(in);

                        } else if (opcao == 5) {
                            //Listar aeronaves cadastradas

                            // Se não tem aeronaves cadastradas no vetor, caio fora
                            if (qtdAeroCadastrados == 0) {
                                System.out.printf("\nNão há aeronaves cadastradas para exibir.");
                                voltarMenu(in);
                                continue;
                            }

                            // Exiba as aeronaves aqui
                            for(int i = 0; i < qtdAeroCadastrados; i++) {
                                System.out.printf("%n--Aeronave: Registro: %s, Modelo: %s, Piloto: %s.",aeronavesAmpliado[i].getCdRegistro(),aeronavesAmpliado[i].getModelo(),aeronavesAmpliado[i].getPiloto().getNome());    
                            }


                            voltarMenu(in);

                        } else if (opcao == 6) {
                            //Localizar aeronave pelo código de registro

                            // Se não tem aeronaves cadastradas no vetor, caio fora
                            if (qtdAeroCadastrados == 0) {
                                System.out.printf("\nNão há aeronaves cadastradas para exibir.");
                                voltarMenu(in);
                                continue;
                            }
                
                            System.out.print("Informe o código de registro a ser localizado: ");
                            int posicaoRegistro = localizarEmAeronavesAmpliado(aeronavesAmpliado, qtdAeroCadastrados, in.nextLine());
                
                                if (posicaoRegistro > -1) {
                                    System.out.print("Aeronave localizada!");
                                    System.out.printf("%n--Aeronave: Registro: %s, Modelo: %s, Piloto: %s.",aeronavesAmpliado[posicaoRegistro].getCdRegistro(),aeronavesAmpliado[posicaoRegistro].getModelo(),aeronavesAmpliado[posicaoRegistro].getPiloto().getNome());                    
                        
                                } else {
                                    System.out.println("Aeronave não localizada!");
                                }

                
                            voltarMenu(in);


            
                            voltarMenu(in);

                        }
                                               
                    } while (opcao != 0);
                    

                    } else {
                        System.out.printf("\nO novo tamanho deve ser maior que o tamanho atual!\nTamanho atual: %d ", MAX_ELEMENTOS);
                    }
                
            }
            else if (opcao != 0) {
                System.out.printf("\nOpção inválida!");
            }

        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    
    }

    private static int localizarEmPilotos(Piloto[] pilotos, int qtdCadastrados, String alvoCpf) {
        int posicaoCpf = -1;

        for (int i = 0; i < qtdCadastrados && posicaoCpf < 0; i++) {
            if (pilotos[i].getCpf().equals(alvoCpf)) {
                posicaoCpf = i;
                continue;
            }
        }

        if (posicaoCpf > -1) {
            return posicaoCpf;                
            
        } else {
            return -1;    
        }
    }


    private static int localizarEmAeronavesAmpliado(Aeronave[] aeronavesAmpliado, int qtdAeroCadastrados, String alvoRegistro) {
        int posicaoRegistro = -1;

        for (int i = 0; i < qtdAeroCadastrados && posicaoRegistro < 0; i++) {
            if (aeronavesAmpliado[i].getCdRegistro().equals(alvoRegistro)) {
                posicaoRegistro = i;
                continue;
            }
        }

        if (posicaoRegistro > -1) {
            return posicaoRegistro;                
            
        } else {
            return -1;    
        }
    }

    private static int localizarEmPilotosAmpliado(Piloto[] pilotosAmpliado, int qtdCadastrados,  String alvoCpf) {
        int posicaoCpf = -1;

        for (int i = 0; i < qtdCadastrados && posicaoCpf < 0; i++) {
            if (pilotosAmpliado[i].getCpf().equals(alvoCpf)) {
                posicaoCpf = i;
                continue;
            }
        }

        if (posicaoCpf > -1) {
            return posicaoCpf;                
            
        } else {
            return -1;    
        }
    }


    private static int localizarEmAeronaves(Aeronave[] aeronaves, int qtdAeroCadastrados, String alvoRegistro) {
        int posicaoRegistro = -1;

        for (int i = 0; i < qtdAeroCadastrados && posicaoRegistro < 0; i++) {
            if (aeronaves[i].getCdRegistro().equals(alvoRegistro)) {
                posicaoRegistro = i;
                continue;
            }
        }

        if (posicaoRegistro > -1) {
            return posicaoRegistro;                
            
        } else {
            return -1;    
        }
    }


    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}