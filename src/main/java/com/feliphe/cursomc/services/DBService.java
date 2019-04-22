package com.feliphe.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.feliphe.cursomc.domain.Categoria;
import com.feliphe.cursomc.domain.Cidade;
import com.feliphe.cursomc.domain.Cliente;
import com.feliphe.cursomc.domain.Endereco;
import com.feliphe.cursomc.domain.Estado;
import com.feliphe.cursomc.domain.ItemPedido;
import com.feliphe.cursomc.domain.Pagamento;
import com.feliphe.cursomc.domain.PagamentoComBoleto;
import com.feliphe.cursomc.domain.PagamentoComCartao;
import com.feliphe.cursomc.domain.Pedido;
import com.feliphe.cursomc.domain.Produto;
import com.feliphe.cursomc.domain.enums.EstadoPagamento;
import com.feliphe.cursomc.domain.enums.Perfil;
import com.feliphe.cursomc.domain.enums.TipoCliente;
import com.feliphe.cursomc.repositories.CategoriaRepository;
import com.feliphe.cursomc.repositories.CidadeRepository;
import com.feliphe.cursomc.repositories.ClienteRepository;
import com.feliphe.cursomc.repositories.EnderecoRepository;
import com.feliphe.cursomc.repositories.EstadoRepository;
import com.feliphe.cursomc.repositories.ItemPedidoRepository;
import com.feliphe.cursomc.repositories.PagamentoRepository;
import com.feliphe.cursomc.repositories.PedidoRepository;
import com.feliphe.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {


	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		Categoria c3 = new Categoria(null, "Cama mesa e banho");
		Categoria c4 = new Categoria(null, "Eletrônicos");
		Categoria c5 = new Categoria(null, "Jardinagem");
		Categoria c6 = new Categoria(null, "Decoração");
		Categoria c7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 80.00);
		Produto p5 = new Produto(null, "Toalha", 80.00);
		Produto p6 = new Produto(null, "Colcha", 80.00);
		Produto p7 = new Produto(null, "TV true color", 80.00);
		Produto p8 = new Produto(null, "Roçadeira", 80.00);
		Produto p9 = new Produto(null, "Abajour", 80.00);
		Produto p10 = new Produto(null, "Pendente", 80.00);
		Produto p11 = new Produto(null, "Shampoo", 80.00);
		
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2, p4));
		c3.getProdutos().addAll(Arrays.asList(p5, p6));
		c4.getProdutos().addAll(Arrays.asList(p2, p2, p3, p7));
		c5.getProdutos().addAll(Arrays.asList(p8));
		c6.getProdutos().addAll(Arrays.asList(p9, p10));
		c7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(c1, c4));
		p2.getCategorias().addAll(Arrays.asList(c1, c2, c4));
		p3.getCategorias().addAll(Arrays.asList(c1, c4));
		p4.getCategorias().addAll(Arrays.asList(c2));
		p5.getCategorias().addAll(Arrays.asList(c3));
		p6.getCategorias().addAll(Arrays.asList(c3));
		p7.getCategorias().addAll(Arrays.asList(c4));
		p8.getCategorias().addAll(Arrays.asList(c5));
		p9.getCategorias().addAll(Arrays.asList(c6));
		p10.getCategorias().addAll(Arrays.asList(c6));
		p11.getCategorias().addAll(Arrays.asList(c7));
		
		
		
		categoriaRepo.saveAll(Arrays.asList(c1,c2, c3, c4, c5, c6, c7));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São paulo");
		
		Cidade cit1 = new Cidade(null, "Uberlândia", est1);
		Cidade cit2 = new Cidade(null, "São Paulo", est2);
		Cidade cit3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cit1));
		est2.getCidades().addAll(Arrays.asList(cit2, cit3));
		
		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(cit1, cit2, cit3));
		
		Cliente cli1 = new Cliente(null, "Feliphe Marinho", "feliphemarinho18@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, pe.encode("feliphe"));
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Cliente cli2 = new Cliente(null, "Adminstrador", "dev.apps.cursos@gmail.com", "05194768087", TipoCliente.PESSOAFISICA, pe.encode("feliphe"));
		cli2.addPerfil(Perfil.ADMIN);
		
		cli2.getTelefones().addAll(Arrays.asList("93838445","93838444"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, cit1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cit2);
		Endereco e3 = new Endereco(null, "Avenida Floriano", "106", null, "Centro", "38777012", cli2, cit2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepo.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepo.saveAll(Arrays.asList(e1, e2, e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1, pagto2));	
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepo.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}
}
