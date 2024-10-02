package controller;

import javax.swing.JOptionPane;
import model.Pessoa;
import model.PessoaDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.GUI;

public class ControllerUsuario {

    public void controllerCrudUsuarios(GUI gui, Usuario usuarioLogado, UsuarioDAO usuariodao, PessoaDAO pessoadao, Pessoa pessoa) {
        StringBuilder m;
        int menuUsuarioOpc = 0;

        while (menuUsuarioOpc != -1) {
            menuUsuarioOpc = gui.crudUsuario(usuarioLogado);
            switch (menuUsuarioOpc) {
                case 1:
                    //Gerar usuario
                    String novoNome = JOptionPane.showInputDialog("\n Quem é o dono do usuario? Digite o nome: ");
                    String novoTelefone = JOptionPane.showInputDialog("\nInforme o telefone de " + novoNome);
                    String novoNascimento = JOptionPane.showInputDialog("\nInforme a data de nascimento de " + novoNome);
                    String novoTipo = JOptionPane.showInputDialog("\nInforme o tipo de usuario de " + novoNome);
                    String novoLogin = JOptionPane.showInputDialog("\nInforme o login de usuario de " + novoNome);
                    String novaSenha = JOptionPane.showInputDialog("\nInforme a senha de usuario de " + novoNome);

                    if (!"".equals(novoNome) || !"".equals(novoTelefone) || !"".equals(novoNascimento) || !"".equals(novoTipo) || !"".equals(novoLogin) || !"".equals(novaSenha)) {
                        pessoa = pessoadao.criarPessoa(novoNome, novoTelefone, novoNascimento);
                        usuariodao.recebePessoa(pessoa, novoTipo, novoLogin, novaSenha);
                        JOptionPane.showMessageDialog(null, usuariodao.verUsuarios());
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: usuario não criado...");
                    }
                    break;

                case 2:
                    //Editar convites individuais
                    String s = usuariodao.verUsuarios();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do usuario que deseja editar: \n\n0- Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o usuario abaixo?\n\n" + usuariodao.verUsuario(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione enter.");
                                String nomeAtt = JOptionPane.showInputDialog("Informe o novo nome a ser atualizado: ");
                                String telefoneAtt = JOptionPane.showInputDialog("\nInforme o novo telefone a ser atualizado: ");
                                String nascimentoAtt = JOptionPane.showInputDialog("\nInforme a nova data de nascimento a ser atualizada: ");
                                String tipoAtt = JOptionPane.showInputDialog("\nInforme o novo tipo de usuario a ser atualizado: ");
                                String loginAtt = JOptionPane.showInputDialog("\nInforme o novo login de usuario a ser atualizado: ");
                                String senhaAtt = JOptionPane.showInputDialog("\nInforme a nova senha de usuario a ser atualizada: ");

                                if (!"".equals(nomeAtt) || !"".equals(telefoneAtt) || !"".equals(nascimentoAtt) || !"".equals(tipoAtt) || !"".equals(loginAtt) || !"".equals(senhaAtt)) {
                                    m = new StringBuilder("Convite atualizado com sucesso!\n\nAntes: \n" + usuariodao.verUsuario(id));
                                    usuariodao.atualizaPessoaUsuario(nomeAtt, telefoneAtt, nascimentoAtt, id);
                                    usuariodao.atualizaUsuario(tipoAtt, loginAtt, senhaAtt, id);
                                    m.append("\nAgora: \n").append(usuariodao.verUsuario(id));
                                    JOptionPane.showMessageDialog(null, m);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Nenhum dado enviado: atualizações não foram realizadas...");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Edição não sucedida...");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há usuarios cadastrados.");
                    }
                    break;

                case 3:
                    //Exibir convites individuais
                    s = usuariodao.verUsuarios();
                    if ("".equals(s)) {
                        s = "Ainda não há usuarios cadastrados.";
                    }
                    JOptionPane.showMessageDialog(null, s);
                    break;

                case 4:
                    //Desfazer convites
                    s = usuariodao.verUsuarios();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do usuario a ser desfeito: \n\n0- Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir este usuario abaixo?\n\n" + usuariodao.verUsuario(id), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso!\n\n" + usuariodao.verUsuario(id));
                                usuariodao.excluirUsuario(id);
                            } else {
                                JOptionPane.showMessageDialog(null, "Exclusão não sucedida...");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há usuários cadastrados.");
                    }
                    break;

                case 0:
                    //Voltar
                    menuUsuarioOpc = -1;
                    break;

                default:
                    menuUsuarioOpc = -1;
                    break;
            }
        }
    }
}
