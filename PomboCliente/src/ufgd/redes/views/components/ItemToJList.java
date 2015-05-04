/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufgd.redes.views.components;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import ufgd.redes.models.Usuario;

/**
 *
 * @author franco
 */
public class ItemToJList extends javax.swing.JPanel implements ListCellRenderer {

    /**
     * Creates new form ItemToList
     *
     */
    public ItemToJList() {
        initComponents();
        //this.contato = contato;
        //labelNome.setText(contato.getUsername());
        setOpaque(true);
    }

    public void setOffline() {
        labalStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufgd/redes/utils/imagens/offline.png"))); // NOI18N
    }

    public void setOnline() {
        labalStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufgd/redes/utils/imagens/online.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labalStatus = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        labelProfile = new javax.swing.JLabel();

        setOpaque(false);

        labalStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labalStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufgd/redes/utils/imagens/online.png"))); // NOI18N

        labelNome.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        labelNome.setForeground(new java.awt.Color(84, 84, 84));
        labelNome.setText("nome");

        labelProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufgd/redes/utils/imagens/profiles/1.jpeg"))); // NOI18N
        labelProfile.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(labelProfile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNome, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labalStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelProfile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labalStatus;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelProfile;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Usuario contato = (Usuario)value;
        labelNome.setText(contato.getUsername());
        labelProfile.setIcon(getImage(contato.getImage()));
        if(contato.isAtivo()){
            setOnline();
        }else{
            setOffline();
        }
        
        if (isSelected) {
            setForeground(list.getSelectionForeground());
            labelNome.setForeground(new java.awt.Color(254, 254, 254));
            setBackground(list.getSelectionBackground());
        } else {
            setForeground(list.getForeground());
            labelNome.setForeground(new java.awt.Color(84, 84, 84));
            setBackground(list.getBackground());
        }
        return this;
    }
    private Icon getImage(String image){
        return new javax.swing.ImageIcon(getClass().getResource("/ufgd/redes/utils/imagens/profiles/"+image)); // NOI18N
    }
}
