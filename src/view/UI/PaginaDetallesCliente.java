package view.UI;

import controller.Controller;

import javax.swing.*;

public class PaginaDetallesCliente extends JFrame{
    private JPanel container;
    private JPanel containerForm;
    private JLabel labelEmailValue;
    private JLabel labelCplValue;
    private JLabel labelNombreUserValue;
    private JLabel labelPwdValue;
    private JPanel JPanel;
    private JLabel labelApellidoValue;
    private JLabel labelDireccionValue;
    private JLabel labelTlfValue;
    private JLabel labelNombreValue;
    static PaginaDetallesCliente paginaDetallesCliente=new PaginaDetallesCliente();


    public static void crearVentanaDetallesCliente() {
        paginaDetallesCliente.setContentPane(paginaDetallesCliente.container);
        paginaDetallesCliente.setBounds(630, 250, 1000, 700);
        paginaDetallesCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paginaDetallesCliente.setVisible(true);

        paginaDetallesCliente.labelNombreUserValue.setText(Controller.clienteLogado.getNombreUsuario());
        paginaDetallesCliente.labelNombreValue.setText(Controller.clienteLogado.getNombre());
        paginaDetallesCliente.labelEmailValue.setText(Controller.clienteLogado.getEmail());
        paginaDetallesCliente.labelApellidoValue.setText(Controller.clienteLogado.getApellido());
        paginaDetallesCliente.labelDireccionValue.setText(Controller.clienteLogado.getDireccion());
        paginaDetallesCliente.labelCplValue.setText(Controller.clienteLogado.getCp());
        paginaDetallesCliente.labelPwdValue.setText(Controller.clienteLogado.getPwd());
        paginaDetallesCliente.labelTlfValue.setText(Controller.clienteLogado.getNumTelf());




    }

}
