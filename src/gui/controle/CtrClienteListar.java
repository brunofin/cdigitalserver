package gui.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.WindowConstants;

import bean.Cliente;

import servidor.Configuracao;

import dao.cliente.ClienteDAO;
import dao.factory.DAOFactory;

import gui.modelo.FrmClienteListar;

public class CtrClienteListar implements Controle {
	
	private Controle parent;
	private FrmClienteListar form;
	private ClienteDAO clienteDao;
	private List<Cliente> listaCliente;
	
	public CtrClienteListar(Controle parent) {
		this.parent = parent;
		form = new FrmClienteListar();
		
		configurar();
		adicionarListeners();
	}
	
	private void configurar() {
		form.setTitle("Gerenciar clientes");
		form.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		DAOFactory factory = DAOFactory.getDaoFactory(Configuracao.DB_SELECIONADO);
		clienteDao = factory.getClienteDAO();
		
		try {
			listaCliente = clienteDao.listar();
		} catch(SQLException e) {
			System.out.println("<CtrClienteListar> Erro ao listar Cliente: " + e.getMessage());
			return;
		}
		
		DefaultListModel<Cliente> model = new DefaultListModel<Cliente>();
		for(Cliente c : listaCliente) {
			model.addElement(c);
		}
		form.getListClientes().setModel(model);
		
	}

	private void adicionarListeners() {
		form.getBtnFechar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				form.dispose();
			}
		});
		
		form.getBtnEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente c = form.getListClientes().getSelectedValue();
				
				try {
					clienteDao.excluir(c);
				} catch(SQLException ex) {
					System.out.println("<CtrClienteListar> Erro ao excluir Cliente: " + ex.getMessage());
				}
			}
		});
	}
	
	@Override
	public void setVisible(boolean b) {
		form.setVisible(b);
	}

}
