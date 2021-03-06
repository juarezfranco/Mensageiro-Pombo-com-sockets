/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufgd.redes.views;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import ufgd.redes.controllers.Controller;
import ufgd.redes.models.Usuario;
import static ufgd.redes.utils.Constantes.DIGITANDO;
import static ufgd.redes.utils.Constantes.NOT_DIGITANDO;

/**
 *
 * @author franco
 */
public class PanelConversa extends javax.swing.JPanel {

    
    /**
     * Controller da aplicação
     * para utilizar seus métodos e recuperar determinados objetos.
     */
    private Controller controller;
    
    /**
     * Destinatário das mensagens que serão enviadas deste painel de conversa(aba)
     */
    private Usuario destinatario;
    
    /**
     * Texto padrões exibidos na area de texto EditorPane.
     */
    private final String textoUsuario;
    private final String textoContato;
    
    
    /**
     * Ferramentas de ediçao do EditorPane, painel de conversas.
     */
    private final HTMLEditorKit kit;
    private final HTMLDocument doc;
    
    /**
     * Construtor do painel de conversas(aba).
     * @param controller
     * @param contato, contato destinatario das mensagens que serão enviadas desta aba
     */
    public PanelConversa(Controller controller, Usuario contato) {
        initComponents();
        //recupera contexto da JanelaMain
        this.controller=controller;
        //recupera contato destinatario das mensagens da conversa desta aba
        destinatario = contato;
        labelNome.setText(contato.getUsername());
        labelNome.setToolTipText(contato.getUsername());
        labelProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufgd/redes/utils/imagens/profiles/"+contato.getImage())));
        labelStatus.setText(getStatus(contato));
        //formatação dos textos de saída que serão exibido no EditorPane
        textoUsuario = "<b color=blue>Você disse:</b>    ";
        textoContato = "<b color="+gerarCor()+">"+contato.getUsername()+" disse:</b>    ";
        
        //inicializa ferramentas do editorPane(Area de texto que exibe toda a conversa)
        kit = new HTMLEditorKit();
        doc = new HTMLDocument();
        //atribui as ferramentas ao editorPane
        textPaneConversa.setEditorKit(kit);
        textPaneConversa.setDocument(doc);
        
    }
    
    public void alterarStatus(Usuario contato){
        this.destinatario=contato;
        labelStatus.setText(getStatus(contato));
    }
    
    public void alterarAcao(String msg) {
        if(msg.equals(DIGITANDO))
            labelStatus.setText(msg);
        if(msg.equals(NOT_DIGITANDO))
            labelStatus.setText("online");
    }
    
    private String getStatus(Usuario contato){
        if(contato.isAtivo())
            return "online";
        int ano_atual    = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        int ano_lastlogin= Integer.parseInt(new SimpleDateFormat("yyyy").format(contato.getLastLogin()));
        int mes_atual    = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        int mes_lastlogin= Integer.parseInt(new SimpleDateFormat("MM").format(contato.getLastLogin()));
        int dia_atual    = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
        int dia_lastlogin= Integer.parseInt(new SimpleDateFormat("dd").format(contato.getLastLogin()));
        String hora_lastlogin=new SimpleDateFormat("HH:mm").format(contato.getLastLogin());
        
        if(ano_atual==ano_lastlogin)
        {
            if(mes_atual==mes_lastlogin)
            {
                if(dia_atual==dia_lastlogin)
                    return "visto por último hoje às "+hora_lastlogin;
                if((dia_atual-1)==dia_lastlogin)
                    return "visto por último ontem às "+hora_lastlogin;
            }
            return "último acesso em "+dia_lastlogin+"/"+mes_lastlogin+" às "+hora_lastlogin;
        }
        return "último acesso em "+dia_lastlogin+"/"+mes_lastlogin+"/"+ano_lastlogin+" às "+hora_lastlogin;
        /*
        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(contato.getDataNascimento());
        */
    }
    
    public void novaMensagem(String msg){
        try {
            //insere msg na tela
            kit.insertHTML(doc, doc.getLength(), "\n"+textoContato+msg, 0, 0, null);
            //posiciona no fim
            textPaneConversa.setCaretPosition(doc.getLength()-1);
        } catch (BadLocationException | IOException ex) {
            Logger.getLogger(PanelConversa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Envia mensagem ao servidor.
     */
    private void enviarMensagem(){
        controller.digitando(destinatario, false);
        //recupera msg que será enviada, remove espaços em branco do inicio.
        String msg = textFieldEntrada.getText().replaceAll("^\\s+", "");
        if(msg.isEmpty())
            return;
        
        try {
            //adiciona texto na area de texto (EditorPane).
            kit.insertHTML(doc, doc.getLength(), "\n"+textoUsuario+msg, 0, 0, null);
            //posiciona no fim
            textPaneConversa.setCaretPosition(doc.getLength()-1);
            //envia mensagem ao servidor.
            controller.enviarMensagem(destinatario, msg);
        //captura erro do kit.inserHTML
        } catch (BadLocationException | IOException ex ) {
            //exibe mensagem de erro na tela
            JOptionPane.showMessageDialog(this, "Mensagem inválida", "Pombo diz", JOptionPane.ERROR_MESSAGE);
            //exibe log de erro no terminal
            Logger.getLogger(PanelConversa.class.getName()).log(Level.SEVERE, null, ex);
        }
        //limpa campo de texto da msg
        textFieldEntrada.setText("");
        
    }
    /**
     * Método responsável por gerar uma cor aleatoriamente,
     * @return nome da cor (String)
     */
    private String gerarCor(){
        return "red";
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldEntrada = new javax.swing.JTextField();
        btEnviarMensagem = new javax.swing.JButton();
        btEnviarArquivo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textPaneConversa = new javax.swing.JTextPane();
        labelProfile = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        labelStatus = new javax.swing.JLabel();

        setBackground(new java.awt.Color(246, 246, 246));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        textFieldEntrada.setFont(new java.awt.Font("Droid Sans", 0, 24)); // NOI18N
        textFieldEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldEntradaKeyPressed(evt);
            }
        });

        btEnviarMensagem.setFont(new java.awt.Font("Droid Sans", 0, 20)); // NOI18N
        btEnviarMensagem.setText("Enviar");
        btEnviarMensagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarMensagemActionPerformed(evt);
            }
        });

        btEnviarArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufgd/redes/utils/imagens/file64.png"))); // NOI18N

        textPaneConversa.setEditable(false);
        textPaneConversa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textPaneConversaFocusGained(evt);
            }
        });
        jScrollPane2.setViewportView(textPaneConversa);

        labelProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufgd/redes/utils/imagens/profiles/1.jpeg"))); // NOI18N
        labelProfile.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelNome.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        labelNome.setForeground(new java.awt.Color(84, 84, 84));
        labelNome.setText("nome");

        labelStatus.setText("status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btEnviarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEnviarMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(labelProfile)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelNome, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelStatus))
                    .addComponent(labelProfile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btEnviarMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(textFieldEntrada, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btEnviarArquivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Listener do botão de enviar mensagem,
     * adiciona mensagem no TextArea na janela e envia pelo socket para o servidor.
     * @param evt 
     */
    private void btEnviarMensagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarMensagemActionPerformed
        enviarMensagem();
        
    }//GEN-LAST:event_btEnviarMensagemActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        textFieldEntrada.requestFocus();
    }//GEN-LAST:event_formFocusGained

    private void textPaneConversaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textPaneConversaFocusGained
        textFieldEntrada.requestFocus();
    }//GEN-LAST:event_textPaneConversaFocusGained

    private void textFieldEntradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldEntradaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        enviarMensagem();
    }//GEN-LAST:event_textFieldEntradaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEnviarArquivo;
    private javax.swing.JButton btEnviarMensagem;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelProfile;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JTextField textFieldEntrada;
    private javax.swing.JTextPane textPaneConversa;
    // End of variables declaration//GEN-END:variables

    
}
