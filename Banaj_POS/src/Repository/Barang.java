/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */

public class Barang implements BarangInterface{
    Database dt = new Database();
    

    @Override
    public  void showBarang(JTable table,String opsi) {
        DefaultTableModel model = new DefaultTableModel();
        int no =1;
        
        try{
          //  sql = "SELECT barang.kode_barang, barang.nama_barang, kategori.nama_kategori, barang.harga, barang.stok, supplier.nama_supplier FROM barang JOIN kategori ON barang.kategori = kategori.id_kategori JOIN supplier ON barang.supplier = supplier.id_supplier ORDER BY barang.nama_barang ASC";

            String sql = "select product.kode_product,product.nama_product, product.harga_jual,product.harga_beli,product.stok,supplier.nama_supplier,kategori.nama_kategori from product join kategori on product.kategori = kategori.kode_kategori join supplier on product.supplier = supplier.kode_supplier order by product.kode_product asc ";
            String sqlKategori ="select kode_kategori, nama_kategori, deksripsi from kategori order by kode_kategori asc";
//            String sqlSupplier ="select kode_supplier, nama_supplier"
            
            Connection con = dt.conectDatabase();
            Statement st =con.createStatement();
            ResultSet res = st.executeQuery(sql);
            
            
          
                if(opsi.equals("barang")){ 
                     res=st.executeQuery(sql);
                     model.addColumn("No");
                     model.addColumn("Kode Barang");
                     model.addColumn("Nama Barang");
                     model.addColumn("Stok");
                     model.addColumn("Harga Beli");
                     model.addColumn("Harga Jual");
                     model.addColumn("Supplier");
                     model.addColumn("Kategori");
                     
                    while(res.next()){
                    
                    model.addRow(new Object[]{
                    no,res.getString("kode_product"),
                    res.getString("nama_product"),
                    res.getString("stok"),
                  
                    res.getString("harga_beli"),
                    res.getString("harga_jual"),
                    res.getString("supplier.nama_supplier"),
                    res.getString("kategori.nama_kategori")
                    });
                    
                   no++; 
                }
                     
                }else if(opsi.equals("kategori")){
                    res=st.executeQuery(sqlKategori);
                    
                    model.addColumn("No");
                    model.addColumn("Kode Kategori");
                    model.addColumn("Nama Kategori");
                    model.addColumn("Deskripsi Kategori");
                    
                    while(res.next()){
                    
                    model.addRow(new Object[]{
                    no,
                    res.getString("kode_kategori"),
                    res.getString("nama_kategori"),
                    res.getString("deksripsi"),
                  
                   
                    });
                    
                   no++; 
                }
                }
                
              
                
            table.setModel(model);
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        
       
    }
    
    
    
    
}