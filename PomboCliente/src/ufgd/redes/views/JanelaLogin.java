/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufgd.redes.views;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ufgd.redes.utils.Util;
import ufgd.redes.controllers.Controller;

/**
 *
 * @author franco
 */
public class JanelaLogin extends javax.swing.JFrame {
    Controller controller;
    /**
     * Creates new form JanelaLogin
     */
    public JanelaLogin() {
        initComponents();
        labelErroUsername.setVisible(false);
        try {
            controller = new Controller();
        } catch (IOException ex) {
            //exibe janela de dialogo informando erro
            JOptionPane.showMessageDialog(this, "Falha ao inicializar conexão com servidor", "Falha na aplicação - Pombo",JOptionPane.ERROR_MESSAGE);
            //gera log de erro no terminal
            //Logger.getLogger(JanelaMain.class.getName()).log(Level.SEVERE, null, ex);
            //encerra aplicação
            exit();
        }
        automatico();
    }
    public void automatico(){
        textFieldUsername.setText("juarez");
        passwordField.setText("123");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textFieldUsername = new javax.swing.JTextField();
        labelNome = new javax.swing.JLabel();
        btEntrar = new javax.swing.JButton();
        labelLogo = new javax.swing.JLabel();
        labelErroUsername = new javax.swing.JLabel();
        labelErroPassword = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        labelNovaConta = new javax.swing.JLabel();
        labelErroLogin = new javax.swing.JLabel();
        labelAguardeLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(244, 240, 240));
        setIconImage(Util.getIconeJanela());
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        textFieldUsername.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        textFieldUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldUsernameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldUsernameKeyTyped(evt);
            }
        });

        labelNome.setFont(new java.awt.Font("Droid Sans", 0, 16)); // NOI18N
        labelNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNome.setText("Nome de usuario");

        btEntrar.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        btEntrar.setText("Entrar");
        btEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEntrarActionPerformed(evt);
            }
        });

        labelLogo.setFont(new java.awt.Font("FreeMono", 1, 45)); // NOI18N
        labelLogo.setForeground(new java.awt.Color(255, 155, 25));
        labelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufgd/redes/utils/imagens/pombo128x128.png"))); // NOI18N
        labelLogo.setText("Pombo");

        labelErroUsername.setVisible(false);
        labelErroUsername.setBackground(new java.awt.Color(249, 79, 79));
        labelErroUsername.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        labelErroUsername.setForeground(new java.awt.Color(254, 254, 254));
        labelErroUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelErroUsername.setText("preencha seu nome");
        labelErroUsername.setName(""); // NOI18N
        labelErroUsername.setOpaque(true);

        labelErroPassword.setVisible(false);
        labelErroPassword.setBackground(new java.awt.Color(249, 79, 79));
        labelErroPassword.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        labelErroPassword.setForeground(new java.awt.Color(254, 254, 254));
        labelErroPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelErroPassword.setText("preencha sua senha");
        labelErroPassword.setName(""); // NOI18N
        labelErroPassword.setOpaque(true);

        labelSenha.setFont(new java.awt.Font("Droid Sans", 0, 16)); // NOI18N
        labelSenha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSenha.setText("Senha");

        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
        });

        labelNovaConta.setFont(new java.awt.Font("Droid Sans", 0, 16)); // NOI18N
        labelNovaConta.setForeground(new java.awt.Color(70, 83, 254));
        labelNovaConta.setText("<html><u>Criar conta</u></html>");
        labelNovaConta.setToolTipText("");
        labelNovaConta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelNovaConta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelNovaContaMouseClicked(evt);
            }
        });

        labelErroLogin.setVisible(false);
        labelErroLogin.setBackground(new java.awt.Color(249, 79, 79));
        labelErroLogin.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        labelErroLogin.setForeground(new java.awt.Color(254, 254, 254));
        labelErroLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelErroLogin.setText("login inválido");
        labelErroLogin.setName(""); // NOI18N
        labelErroLogin.setOpaque(true);

        labelAguardeLogin.setVisible(false);
        labelAguardeLogin.setBackground(new java.awt.Color(27, 237, 42));
        labelAguardeLogin.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        labelAguardeLogin.setForeground(new java.awt.Color(254, 254, 254));
        labelAguardeLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAguardeLogin.setText("Aguarde fazendo login ...");
        labelAguardeLogin.setName(""); // NOI18N
        labelAguardeLogin.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelSenha)
                        .addGap(188, 267, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelNome)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldUsername)
                            .addComponent(labelErroUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                            .addComponent(labelErroPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(labelNovaConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(labelErroLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelAguardeLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelErroLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelAguardeLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(textFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelErroUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(labelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelErroPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNovaConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        exit();
    }//GEN-LAST:event_formWindowClosed

    private void btEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEntrarActionPerformed
        entrar();
    }//GEN-LAST:event_btEntrarActionPerformed

    private void labelNovaContaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNovaContaMouseClicked
        new JanelaCadastro(controller);
    }//GEN-LAST:event_labelNovaContaMouseClicked

    private void textFieldUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldUsernameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            passwordField.requestFocus();
    }//GEN-LAST:event_textFieldUsernameKeyPressed

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            entrar();
    }//GEN-LAST:event_passwordFieldKeyPressed

    private void textFieldUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldUsernameKeyTyped
        if(evt.getKeyChar()==' ')
            evt.consume();
    }//GEN-LAST:event_textFieldUsernameKeyTyped

    private void entrar(){
        String username = textFieldUsername.getText().replaceAll(" ", "");
        String password = passwordField.getText().replaceAll(" ", "");
        //valida dados de entrada do usuario
        if(!validaEntrada(username, password)){
            return;
        }        
        //autentica usuario com servidor
        labelAguardeLogin.setVisible(true);
        labelErroLogin.setVisible(false);
        if(controller.autenticarUsuario(username, password)){
            setVisible(false);//"fechar" tela de login
            new JanelaMain(controller);//abrir janela principal do pombo
        }else{
            labelErroLogin.setVisible(true);
        }
        labelAguardeLogin.setVisible(false);
    }
    private boolean validaEntrada(String username, String password){
        
        boolean flag=true;
        labelErroUsername.setVisible(false);
        labelErroPassword.setVisible(false);
        labelErroLogin.setVisible(false);
        if(username.isEmpty()){
            labelErroUsername.setText("preencha seu nome");
            labelErroUsername.setVisible(true);
            flag = false;
        }else
            if(username.length()<3){
                labelErroUsername.setText("minimo 3 caracteres");
                labelErroUsername.setVisible(true);
                flag = false;
            }
        if(password.isEmpty()){
            labelErroPassword.setText("preencha sua senha");
            labelErroPassword.setVisible(true);
            flag = false;
        }else
            if(password.length()<3){
                labelErroPassword.setText("minimo 3 caracteres");
                labelErroPassword.setVisible(true);
                flag = false;
            }
        return flag;
    }
    
     private void exit(){
         try {
            //finaliza controller
            if(controller!=null)
                controller.close();
        } catch (IOException ex) {
            Logger.getLogger(JanelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }        
        //encerra aplicação
        System.exit(1);
     }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEntrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelAguardeLogin;
    private javax.swing.JLabel labelErroLogin;
    private javax.swing.JLabel labelErroPassword;
    private javax.swing.JLabel labelErroUsername;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNovaConta;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables
}
