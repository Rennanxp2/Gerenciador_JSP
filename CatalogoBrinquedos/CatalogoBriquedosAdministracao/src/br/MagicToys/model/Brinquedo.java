package br.MagicToys.model;

public class Brinquedo {
	private int Codigo;
	private String Descricao;
	private String Marca;
	private String Categoria;
	private String Imagem;
	private double Valor;
	private String Detalhes;
	public Brinquedo () {}
	public Brinquedo (int codigo, String descricao, String marca, String categoria, String imagem, double valor, String detalhes)
	{
		this.Codigo = codigo;
		this.Descricao = descricao;
		this.Marca = marca;
		this.Categoria = categoria;
		this.Imagem = imagem;
		this.Valor = valor;
		this.Detalhes = detalhes;
	}
	public int getCodigo() {
		return Codigo;
	}
	public void setCodigo(int codigo) {
		Codigo = codigo;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	public String getCategoria() {
		return Categoria;
	}
	public void setCategoria(String categoria) {
		Categoria = categoria;
	}
	public String getImagem() {
		return Imagem;
	}
	public void setImagem(String imagem) {
		Imagem = imagem;
	}
	public double getValor() {
		return Valor;
	}
	public void setValor(double valor) {
		Valor = valor;
	}
	public String getDetalhes() {
		return Detalhes;
	}
	public void setDetalhes(String detalhes) {
		Detalhes = detalhes;
	}
	
}
