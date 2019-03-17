package com.feliphe.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.feliphe.cursomc.domain.Categoria;
import com.feliphe.cursomc.domain.Cidade;
import com.feliphe.cursomc.domain.Cliente;
import com.feliphe.cursomc.domain.Endereco;
import com.feliphe.cursomc.domain.Estado;
import com.feliphe.cursomc.domain.Produto;
import com.feliphe.cursomc.domain.enums.TipoCliente;
import com.feliphe.cursomc.repositories.CategoriaRepository;
import com.feliphe.cursomc.repositories.CidadeRepository;
import com.feliphe.cursomc.repositories.ClienteRepository;
import com.feliphe.cursomc.repositories.EnderecoRepository;
import com.feliphe.cursomc.repositories.EstadoRepository;
import com.feliphe.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepo;
	@Autowired
	ProdutoRepository produtoRepo;
	@Autowired
	CidadeRepository cidadeRepo;
	@Autowired
	EstadoRepository estadoRepo;
	@Autowired
	ClienteRepository clienteRepo;
	@Autowired
	EnderecoRepository enderecoRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		categoriaRepo.saveAll(Arrays.asList(c1,c2));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São paulo");
		
		Cidade cit1 = new Cidade(null, "Uberlândia", est1);
		Cidade cit2 = new Cidade(null, "São Paulo", est2);
		Cidade cit3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cit1));
		est2.getCidades().addAll(Arrays.asList(cit2, cit3));
		
		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(cit1, cit2, cit3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, cit1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cit2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(e1, e2));
	}

}
