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
		this.dado.setFrec(this.dado.getFrec() + a.dado.getFrec());
		
		return true;
	}

	public boolean find(char c){
		if(this.folha == false){
			if(this.esq.folha && this.esq.dado.getChar() == c){
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
			
			if(this.dir.folha && this.dir.dado.getChar() == c){
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

	public void print(){
		System.out.println(this.aux);
		aux = " ";
	}

	public void show_frec(){
		if(this.esq != null) this.esq.show_frec();
		System.out.println(this.dado.getFrec());
		if(this.dir != null) this.dir.show_frec();
	}
}

public class HuffmanEncoding{
	int numChar;

	public static void main(String[] args){
		System.out.println("teste");
		
		teste();
	}

	public static void teste(){
		Arvore tree = new Arvore(), a = new Arvore('a',10), b = new Arvore('b',20), d = new Arvore('d',3213), e = new Arvore('e',322);
		Arvore aux = new  Arvore();

		tree.insert(a,true);
		tree.insert(b,false);
		
		aux = tree;
		tree = new Arvore();

		tree.insert(aux,false);
		tree.insert(e,true);
		
		aux = tree;
		tree = new Arvore();
		
		tree.insert(aux,true);
		tree.insert(d,false);

		tree.show_frec();

		tree.find('a');
		tree.print();
		tree.find('b');
		tree.print();
		tree.find('e');
		tree.print();
		tree.find('d');
		tree.print();
	}
}
