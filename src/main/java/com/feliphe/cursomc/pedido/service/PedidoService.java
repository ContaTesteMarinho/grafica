package com.feliphe.cursomc.pedido.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.feliphe.cursomc.cliente.service.ClienteService;
import com.feliphe.cursomc.desconto.domain.enums.Status;
import com.feliphe.cursomc.desconto.service.DescontoService;
import com.feliphe.cursomc.email.service.EmailService;
import com.feliphe.cursomc.exception.AuthorizationException;
import com.feliphe.cursomc.exception.ObjectNotFoundException;
import com.feliphe.cursomc.item_pedido.domain.ItemPedido;
import com.feliphe.cursomc.item_pedido.repository.ItemPedidoRepository;
import com.feliphe.cursomc.pagamento.domain.PagamentoComBoleto;
import com.feliphe.cursomc.pagamento.domain.enums.EstadoPagamento;
import com.feliphe.cursomc.pagamento.repository.PagamentoRepository;
import com.feliphe.cursomc.pagamento.service.BoletoService;
import com.feliphe.cursomc.pedido.domain.Pedido;
import com.feliphe.cursomc.pedido.dto.PedidoDTO;
import com.feliphe.cursomc.pedido.repository.PedidoRepository;
import com.feliphe.cursomc.produto.service.ProdutoService;
import com.feliphe.cursomc.security.UserSS;
import com.feliphe.cursomc.user.service.UserService;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagtoRepo;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private DescontoService descontoService;

	public Pedido find(Integer id) {

		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public List<Pedido> listByCliente(Integer clienteId, Pageable pageable) {

		if (clienteId == null) {
			throw new AuthorizationException("Acesso negado");
		}

		return repo.listByCliente(clienteId, pageable);
	}

	public Pedido insert(Pedido obj) {

		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.setStatus(Status.PENDENTE);
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		if (obj.getDesconto() != null) {
			obj.setDesconto(descontoService.find(obj.getDesconto().getId()));
		}

		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}

		obj = repo.save(obj);
		pagtoRepo.save(obj.getPagamento());

		for (ItemPedido ip : obj.getItens()) {
			ip.setProduto(produtoService.find(ip.getId().getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}

		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;

	}

	public void update(PedidoDTO objDTO) {

		Pedido newObj = find(objDTO.getId());
		
		//Atualiza status do pedido
		updateStatus(newObj, objDTO);
		repo.save(newObj);
		
		emailService.sendChangeOrderStatus(newObj);
	}

	private void updateStatus(Pedido newObj, PedidoDTO objDTO) {
		newObj.setStatus(objDTO.getStatus());
	}

	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		UserSS user = UserService.authenticated();

		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		//Cliente cliente = clienteService.find(user.getId());

		return repo.findAll(pageRequest);
	}

}
