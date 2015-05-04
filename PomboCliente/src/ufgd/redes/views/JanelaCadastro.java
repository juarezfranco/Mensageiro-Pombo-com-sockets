/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufgd.redes.views;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ufgd.redes.controllers.Controller;
import ufgd.redes.interfaces.Contexto;
import ufgd.redes.utils.Util;

/**
 *
 * @author franco
 */
public class JanelaCadastro extends javax.swing.JDialog implements Contexto{
    Controller controller;
    private String image="-1.jpeg";
    /**
     * Creates new form JanelaCadastro
     * @param controller
     */
    public JanelaCadastro(Controller controller) {
        super(new JFrame(), true);
        this.controller=controller;
        initComponents();
        setVisible(true);
    }
  
    @Override
    public void alterarFoto(String image) {
        this.image = image;
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufgd/redes/utils/imagens/profiles/"+image))); // NOI18N
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        textFieldUsername = new javax.swing.JTextField();
        labelErroUsername = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        labelErroPassword = new javax.swing.JLabel();
        btCriarConta = new javax.swing.JButton();
        labelErroLogin = new javax.swing.JLabel();
        passwordField1 = new javax.swing.JPasswordField();
        labelSenha1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btEscolherFoto = new javax.swing.JButton();
        jPanelEsquerdo = new javax.swing.JPanel();
        jPanelDireito = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Criar Conta - Pombo");
        setIconImage(Util.getIconeJanela());
        setType(java.awt.Window.Type.UTILITY);

        jPanelMain.setBackground(new java.awt.Color(254, 254, 254));

        jLabel1.setFont(new java.awt.Font("FreeMono", 1, 45)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 155, 25));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nova conta");

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));

        labelNome.setFont(new java.awt.Font("Droid Sans", 0, 16)); // NOI18N
        labelNome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNome.setText("Nome de usuario");

        textFieldUsername.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        textFieldUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textFieldUsernameFocusLost(evt);
            }
        });
        textFieldUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldUsernameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldUsernameKeyTyped(evt);
            }
        });

        labelErroUsername.setVisible(false);
        labelErroUsername.setBackground(new java.awt.Color(249, 79, 79));
        labelErroUsername.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        labelErroUsername.setForeground(new java.awt.Color(254, 254, 254));
        labelErroUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelErroUsername.setText("preencha seu nome");
        labelErroUsername.setName(""); // NOI18N
        labelErroUsername.setOpaque(true);

        labelSenha.setFont(new java.awt.Font("Droid Sans", 0, 16)); // NOI18N
        labelSenha.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSenha.setText("Senha");

        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFieldFocusLost(evt);
            }
        });
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passwordFieldKeyTyped(evt);
            }
        });

        labelErroPassword.setVisible(false);
        labelErroPassword.setBackground(new java.awt.Color(249, 79, 79));
        labelErroPassword.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        labelErroPassword.setForeground(new java.awt.Color(254, 254, 254));
        labelErroPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelErroPassword.setText("preencha sua senha");
        labelErroPassword.setName(""); // NOI18N
        labelErroPassword.setOpaque(true);

        btCriarConta.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        btCriarConta.setText("Criar conta");
        btCriarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCriarContaActionPerformed(evt);
            }
        });

        labelErroLogin.setVisible(false);
        labelErroLogin.setBackground(new java.awt.Color(249, 79, 79));
        labelErroLogin.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        labelErroLogin.setForeground(new java.awt.Color(254, 254, 254));
        labelErroLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelErroLogin.setText("cadastro inválido");
        labelErroLogin.setName(""); // NOI18N
        labelErroLogin.setOpaque(true);

        passwordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordField1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passwordField1KeyTyped(evt);
            }
        });

        labelSenha1.setFont(new java.awt.Font("Droid Sans", 0, 16)); // NOI18N
        labelSenha1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSenha1.setText("Repita a senha");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufgd/redes/utils/imagens/profiles/-1.jpeg"))); // NOI18N
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btEscolherFoto.setText("escolher foto");
        btEscolherFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEscolherFotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordField)
                    .addComponent(textFieldUsername, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelErroUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelErroPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordField1)
                    .addComponent(labelSenha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(72, 72, 72)))
                .addGap(50, 50, 50))
            .addComponent(labelErroLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btEscolherFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(143, 143, 143))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCriarConta, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(labelErroLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEscolherFoto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(textFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelErroUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(passwordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelErroPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btCriarConta, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jPanelEsquerdo.setBackground(new java.awt.Color(254, 254, 254));

        javax.swing.GroupLayout jPanelEsquerdoLayout = new javax.swing.GroupLayout(jPanelEsquerdo);
        jPanelEsquerdo.setLayout(jPanelEsquerdoLayout);
        jPanelEsquerdoLayout.setHorizontalGroup(
            jPanelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelEsquerdoLayout.setVerticalGroup(
            jPanelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelDireito.setBackground(new java.awt.Color(254, 254, 254));

        javax.swing.GroupLayout jPanelDireitoLayout = new javax.swing.GroupLayout(jPanelDireito);
        jPanelDireito.setLayout(jPanelDireitoLayout);
        jPanelDireitoLayout.setHorizontalGroup(
            jPanelDireitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelDireitoLayout.setVerticalGroup(
            jPanelDireitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jPanelEsquerdo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDireito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelEsquerdo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDireito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btCriarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCriarContaActionPerformed
        criarConta();
    }//GEN-LAST:event_btCriarContaActionPerformed

    /**
     * impede usuario de colocar espaços no username
     * @param evt 
     */
    private void textFieldUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldUsernameKeyTyped
        if(evt.getKeyChar()==' ')
            evt.consume();
    }//GEN-LAST:event_textFieldUsernameKeyTyped

    /**
     * remove espaços em branco ao tirar foco do textField,
     * limpa espaços em branco ao usuario copiar e colar um texto
     * @param evt 
     */
    private void textFieldUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFieldUsernameFocusLost
        String username = textFieldUsername.getText();
        textFieldUsername.setText(username.replace(" ", ""));
    }//GEN-LAST:event_textFieldUsernameFocusLost

    /**
     * Impede entrada de espaço em branco na senha
     * @param evt 
     */
    private void passwordFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyTyped
        if(evt.getKeyChar()==' ')
            evt.consume();
    }//GEN-LAST:event_passwordFieldKeyTyped

    /**
     * Impede entrada de espaço em branco na senha
     * @param evt 
     */
    private void passwordField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordField1KeyTyped
        if(evt.getKeyChar()==' ')
            evt.consume();
    }//GEN-LAST:event_passwordField1KeyTyped

    /**
     * remove espaços em branco da senha
     * @param evt 
     */
    private void passwordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusLost
        String username = passwordField.getText();
        passwordField.setText(username.replace(" ", ""));
    }//GEN-LAST:event_passwordFieldFocusLost

    private void passwordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            criarConta();
    }//GEN-LAST:event_passwordField1KeyPressed

    private void textFieldUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldUsernameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            passwordField.requestFocus();
    }//GEN-LAST:event_textFieldUsernameKeyPressed

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            passwordField1.requestFocus();
    }//GEN-LAST:event_passwordFieldKeyPressed

    private void btEscolherFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEscolherFotoActionPerformed
        new JanelaProfileImagens(this).setVisible(true);
    }//GEN-LAST:event_btEscolherFotoActionPerformed
    
    
    private void criarConta(){
        //labelAguardeCadastrando.setVisible(true);
        
        String username = textFieldUsername.getText().replaceAll(" ", "");
        String password = passwordField.getText().replaceAll(" ", "");
        String password2 = passwordField1.getText().replaceAll(" ", "");
        //valida dados de entrada do usuario
        if(!validaEntrada(username, password, password2,image)){
            //labelAguardeCadastrando.setVisible(false);
            return;
        }        
        
        
        if(controller.criarConta(username, password, image)){
            //labelAguardeCadastrando.setVisible(false);
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso", "Nova conta - Pombo", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }else{
            labelErroLogin.setText("Usuario já existe");
            labelErroLogin.setVisible(true);
        }
        //labelAguardeCadastrando.setVisible(false);
    }
    private boolean validaEntrada(String username, String password, String password2,String image){
        
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
            }else
                if(!password.equals(password2)){
                    labelErroPassword.setText("Senhas não são iguais");
                    labelErroPassword.setVisible(true);
                    flag = false;
                }
        if(image.equals("-1.jpeg")){
            JOptionPane.showMessageDialog(this, "Escolha uma imagem.", "O pombo diz:", JOptionPane.ERROR_MESSAGE);
            flag=false;
        }
            
        return flag;
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCriarConta;
    private javax.swing.JButton btEscolherFoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelDireito;
    private javax.swing.JPanel jPanelEsquerdo;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JLabel labelErroLogin;
    private javax.swing.JLabel labelErroPassword;
    private javax.swing.JLabel labelErroUsername;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelSenha1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField passwordField1;
    private javax.swing.JTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables

    
}
