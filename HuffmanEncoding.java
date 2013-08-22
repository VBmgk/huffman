import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;
import java.util.Scanner;

class Data{
	char c;
	int freq;

	Data(){
		c = ' ';
		freq = 0;
	}

	public char getChar(){	return c;}
	
	public int getFrec(){	return freq;}	
	
	public void setChar(char ch){	c = ch;}
	public void setFrec(int f){	freq = f;}

	public void print(){ System.out.print(c+" "+freq);}
}

class Arvore{
	Data dado;
	boolean folha;
	public static String aux;
	Arvore esq,dir;

	Arvore(){
		folha = false;
		aux = " ";

		dado = new Data();

		this.esq = this.dir = null;
	}

	Arvore(char c, int frec){
		folha = true;
		aux = " ";

		dado = new Data();

		dado.setChar(c);
		dado.setFrec(frec);

		this.esq = this.dir = null;
	}

	Arvore(Data new_d){
		folha = true;
		aux = " ";

		dado = new_d;

		this.esq = this.dir = null;
	}
	
	boolean insert(Arvore a, boolean lado_esq){
		if(lado_esq == true){
			if(this.esq != null){ return false;}
			this.esq = a;
		}

		else{
			if(this.dir != null){ return false;}
			this.dir = a;
		}

		//atualizando frequencia geral da arvore
		if(a != null) this.dado.setFrec(this.dado.getFrec() + a.dado.getFrec());
		else { System.out.print("NULO!!");}


		return true;
	}

	
	public boolean find2(char c){
		if(this.folha == false){
			if(this.esq.folha && (this.esq.dado.getChar() == c)){
				this.aux = "0";

				return true;
			}

			if(this.esq.folha == false){
				if(this.esq.find2(c) == true){
					this.aux = "0"+this.aux;

					return true;
				}
			}
			
			if(this.dir.folha && (this.dir.dado.getChar() == c)){
				this.aux = "1";

				return true;
			}
			
			if(this.dir.folha == false){
				if(this.dir.find2(c) == true){
					this.aux = "1"+this.aux;

					return true;
				}
			}
		}

		return false;
	}

	public boolean find(char c){
		if(this.folha == false){
			if(this.esq.folha && (this.esq.dado.getChar() == c)){
				if(this.aux == " "){	this.aux = "01";}
				else{	this.aux = "00"+this.aux;}

				return true;
			}

			if(this.esq.folha == false){
				if(this.esq.find(c) == true){
					this.aux = "00"+this.aux;

					return true;
				}
			}
			
			if(this.dir.folha && (this.dir.dado.getChar() == c)){
				if(this.aux == " "){	this.aux = "11";}
				else{	this.aux = "10"+this.aux;}

				return true;
			}
			
			if(this.dir.folha == false){
				if(this.dir.find(c) == true){
					this.aux = "10"+this.aux;

					return true;
				}
			}
		}

		return false;
	}

	public String getTreeCode(char c){
		aux = " ";
		this.find(c);
		String buff = this.aux;
		aux = " ";

		return buff;
	}

	public String getHuffmanCode(char c){
		aux = " ";
		this.find2(c);
		String buff = this.aux;
		aux = " ";

		return buff;
	}

	public void show_frec(){
		System.out.println(this.dado.getFrec());
		if(this.esq != null) this.esq.show_frec();
		if(this.dir != null) this.dir.show_frec();
	}
}

public class HuffmanEncoding{
	int numChar;
	static Data[] d;
	String[] hufftree;

	public static void main(String[] args){
		int i;
	        String ent = "testing.txt";
		
		Arvore tree = HuffmanEncoding.buildFileTree(ent);

		for(i = 0; i<d.length ;i++){
			System.out.println(d[i].getChar()+" "+tree.getHuffmanCode(d[i].getChar()));
			System.out.println(d[i].getChar()+" "+tree.getTreeCode(d[i].getChar()));
		}

	        BufferedReader br = null;
	        
		try {
		        int sCurrentChar;
			br = new BufferedReader(new FileReader(ent));
			while ((sCurrentChar = br.read()) != -1) {	System.out.println((char)sCurrentChar+": "+tree.getHuffmanCode((char)sCurrentChar));}
		} catch (IOException e) {
	                e.printStackTrace();
	        } finally {
		        try {
	                    if (br != null)br.close();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		}
	}

	public static Arvore buildFileTree(String arq){
		int i,j;
		d = Estatistica.distribuicao(arq);

		Arvore tree = new Arvore();

		Arvore[] vec = new Arvore[d.length], aux;

		for(i = 0; i<d.length ; i++){ 
			vec[i] = new Arvore(d[i]);

			System.out.print("\n "+i+":");

			vec[i].dado.print();
		}

		while(vec.length >=2){
			//ordenar
			for(i = 0; i < vec.length ; i++){ 
				for(j = i+1; j < vec.length; j++){
					if(vec[i].dado.getFrec() > vec[j].dado.getFrec()){
						tree = vec[i];
						vec[i] = vec[j];
						vec[j] = tree;
					}
				}
			}

			tree = new Arvore();

			tree.insert(vec[0],true);
			tree.insert(vec[1],false);

			//eliminando os anteriores
			aux = new Arvore[vec.length];
			aux = vec;

			vec = new Arvore[aux.length - 1];

			//adicionando a arvore criada
			vec[0] = tree;
			
			for(i = 1; i < vec.length ; i++){ vec[i] = aux[i+1];}
		}

		return tree;
	}
}
