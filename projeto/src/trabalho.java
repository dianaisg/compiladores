import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class trabalho {
	public static void main(String[] args) throws IOException {
		
		//criar hashmap para o ciclo while 
		HashMap < String, String > hashMap = new HashMap< String, String >();
		hashMap.put("mostrar", "mostrar ' aaa ' ");
		hashMap.put("enquanto", "enquanto num ate num");
		
		//lê a primeira linha do documento e insere o texto na string 
		String file = "C://Users/diana/OneDrive/Desktop/Uni/3ºAno/C/projeto/TENTATIVA.txt";
		BufferedReader buffRed = new BufferedReader(new FileReader(file));
		String linha = "";
		linha = buffRed.readLine();
		
		//separa as palavras por espaços e coloca num array
		String[] txt = linha.split(" ");
		
		
		//enquanto houver linhas no texto
		while(true) {	
			
			if(linha!=null) {
				//System.out.println(linha);
				
				if(txt[0].equals("mostrar"))
					mostrar(hashMap, txt);
				else if(txt[0].equals("enquanto"))
					enquanto(hashMap, txt);
				
			}
			else		//quando acaba de processar essa linha
				break;
			
			
			//lê a próxima linha e insere a no array
			linha = buffRed.readLine();
			if(linha!=null) 
				txt = linha.split(" ");
			/*else
				System.out.println("não há mais linhas");*/
				
				
		}
		buffRed.close();
	}
	
	
	
	/*
	 * método chamado para processar o mostrar/print
	 */
	public static void mostrar(HashMap < String, String > hashMap, String[] txt) {
		/*for(int i=0; i< txt.length; i++)
		System.out.println(txt[i]);
		
		System.out.println("\n\n");*/
		
		StringBuilder code = new StringBuilder ("System.out.println ");
		
		//ciclo para processar cada valor dessa linha, começa em 1 porque já verificou a posição 0
		for(int i=1; i<txt.length; i++) {
			
			if(txt[i].equals("'")) {	
				int j = i+1;
				while(!txt[j].equals("'")) {
					code.append("('");
					code.append(txt[j]);
					j++;
				}
				code.append("'); ");
				i=j;
			}
			else if (txt[i].matches("-?\\d+")) {	//verifica se o valor é um int
				int j;
				for(j=i; j<txt.length; j++) {
					code.append(txt[j]);
				}
				i=j;
			}
			
		}
	
		System.out.println(code);
	
	
	
	}//fim método mostrar
	
	
	
	
	/*
	 * método chamado para processar enquanto/while
	 */
	public static void enquanto(HashMap < String, String > hashMap, String[] txt) {
		
		//pega no value do hashmap com a key "enquanto" e coloca-a num array para termos de comparação
		String[] arrloops = hashMap.get("enquanto").split(" ");
		int[] num = new int[2];
		int pos = 0;
		
		String code="for (int i=";
		
		//verifica se txt tem o mesmo número de palavras que o value do hashmap
		if(txt.length  == arrloops.length) {
			
			//começa em 1 porque já verificou a posição 0
			for(int i = 1; i< txt.length; i++) {
				
				if(txt[i].equals(arrloops[i])) 		//ciclo para verificar o "ate"
					continue;		//System.out.println("ok");
				else {	
						try {
							num[pos++] = Integer.parseInt(txt[i]); 	//torna os valores do ciclo em ints
							
							if(pos ==1)
								code= code + txt[i] + "; i<";
							else code= code + txt[i] + "; i++)";
							
						
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
				}
			}
		}else 
			System.out.println("ERRO ENQUANTO");
		
		System.out.println(code);		//dar append num bloco de notas limpinho para depois executar
		
	}//fim método enquanto
	
	
}//fim classe
