import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Barang implements crud {

    static Connection con;
    static Statement stt;
    static ResultSet rs;
    

    static Scanner input = new Scanner(System.in);
    public String nofa, nabar;
    public int habar,jum,sub,dis,total;
    private int jumlah;
    private int diskon;
    Transaksi transaksi;

    public Connection getKoneksi() {
        
        String url = "jdbc:mysql://localhost:3306/tugasp14";
        String user = "root";
        String pass = "";
   
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con  = DriverManager.getConnection(url, user, pass);
            System.out.print("Terkoneksi");
        }catch (Exception e){
            System.out.println(e);
        }
        return con;
    }

    public int HargaBarang(){
        return 0;
    }
    public int Jumlah() {
        return 0;
    }
    public int SubTotal() {
        return 0;
    }
    public int Discount() {
        return 0;
    }
    public int TotalHarga() {
        return 0;
    }
    public static int menu(){
        
        System.out.println();
         System.out.println("1. Lihat pesanan");
         System.out.println("2. Masukkan pesanan");
         System.out.println("3. Ubah pesanan");
         System.out.println("4. Batalkan pesanan");
         System.out.println("5. Cari pesanan");
        System.out.println(); 
        System.out.print("Masukkan pilihan   : ");
        int pilihan =  input.nextInt();
        return pilihan;
   
}   

    @Override
    public void tampil() {

        String sql =  "SELECT * FROM transaksi";
        try {
            stt = con.createStatement();
            rs =  stt.executeQuery(sql);
            
            String judul = "Tampilan Transaksi";
            System.out.println("\t\t\t-----------------------");
            System.out.printf("\t\t\t    %s\n",judul.toUpperCase());
            System.out.println("\t\t\t-----------------------\n");
           
            
            System.out.println("/ No. Faktur / Label\t  / Harga  / Jumlah /  Diskon /  Total /");
            System.out.println("---------------------------------------------------------");
            while(rs.next()){
                int nofaktur     =  rs.getInt("nofaktur");
                String label     =  rs.getString("label");
                int harga        =  rs.getInt("harga");
                int jumlah       =  rs.getInt("jumlah");
                int diskon       =  rs.getInt("diskon");
                int total        =  rs.getInt("total");
                System.out.println(String.format("|   %d\t    | %-10s  | %-6d | %-6d |  %-5d  |  %-6d   |", nofaktur,label,harga,jumlah,diskon,total));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
    @Override
    public void pesan() {

        try {
            System.out.print("\nMasukkan nofaktur  : ");
            int  nofaktur = input.nextInt();
          
            input.nextLine();

            System.out.print("Masukkan nama label : ");
            String label = input.nextLine();

            System.out.print("Masukkan harga      :");
            int harga = input.nextInt();

            System.out.print("Masukkan jumlah     : ");
            jumlah = input.nextInt();
            
            if(harga < 0 || jumlah < 0 || nofaktur < 0){
                throw new IllegalArgumentException("Negatif (X)");
            }
            transaksi = new Transaksi();
            total = transaksi.SubTotal();
            diskon =  transaksi.Discount();
            
            stt = con.createStatement();
            String sql =  "SELECT * FROM transaksi";
            stt = con.createStatement();
            rs =  stt.executeQuery(sql);
           
            while(rs.next()){
                if(nofaktur == rs.getInt("nofaktur")){
                    System.out.println("\nAda");
                    throw new SQLException();
                }   
               
            }
            String sql2 =  "INSERT INTO transaksi (nofaktur,label,harga,jumlah,diskon,total) VALUE('"+nofaktur+"','"+label+"','"+harga+"','"+jumlah+"','"+diskon+"','"+total+"')";
            stt.executeUpdate(sql2);
            System.out.println("Barang sudah di pesan");
        
           
        } catch (IllegalArgumentException  | SQLException e) {
            e.printStackTrace();
        }
    
        
    }
    @Override
    public void update() {

        try {
            tampil();
            System.out.println();
            System.out.print("Masukkan no faktur: ");
            int nofaktur =  input.nextInt();
            input.nextLine();

            System.out.print("Masukkan nama     : ");
            String label = input.nextLine();

            System.out.print("Masukkan harga    : ");
            int harga = input.nextInt();

            System.out.print("Masukkan jumlah   : ");
            int jumlah = input.nextInt();

            if(harga < 0 || jumlah < 0 || nofaktur < 0){
                throw new IllegalArgumentException("angka tidak boleh negatif");
            }
            
            transaksi = new Transaksi();
            total = transaksi.SubTotal();
            diskon =  transaksi.Discount();
            boolean isFound = false;
          
            String sql =  "SELECT * FROM penjualan";
            stt = con.createStatement();
            rs =  stt.executeQuery(sql);
           
            while(rs.next()){
                if(nofaktur == rs.getInt("nofaktur")){
                    isFound = true;
                }   
            }

            if(!isFound){
                throw new SQLException("data tidak ditemukan");
            }else{
                String sql2 = "UPDATE penjualan SET nofaktur='"+nofaktur+"',label='"+label+"',harga='"+harga+"',jumlah='"+jumlah+"' WHERE nofaktur= '"+nofaktur+"'";
                stt.executeUpdate(sql2);
                System.out.println("Barang pesanan berhasil diupdate");
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        
    }
    @Override
    public void batal() {

        try {
            tampil();
            System.out.println();
            System.out.print("Masukkan no faktur : ");
            int nofaktur = input.nextInt();

            if(nofaktur < 0){
                throw new IllegalArgumentException("angka tidak boleh negatif");
            }

            boolean isFound = false;
          
            String sql =  "SELECT * FROM transaksi";
            stt = con.createStatement();
            rs =  stt.executeQuery(sql);
           
            while(rs.next()){
                if(nofaktur == rs.getInt("nofaktur")){
                    isFound = true;
                }   
            }

            if(!isFound){
                throw new SQLException("data tidak ada");
            }else{
                String sql2 = "DELETE FROM  transaksi WHERE nofaktur= " +nofaktur;
                stt.executeUpdate(sql2);
                System.out.println("Pesanan dibatalkan");
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        
    }
    @Override
    public void cari() {

        try {

            System.out.print("masukkan keyword (nama barang/no faktur) : ");
            String keyword = input.next();
            
            String sql = "SELECT * FROM transaksi WHERE nofaktur LIKE '%"+keyword+"%' OR  label LIKE '%"+keyword+"%' ";

            stt = con.createStatement();
            rs =  stt.executeQuery(sql);

            System.out.println("/ No. Faktur / Label\t  / Harga  / Jumlah /  Diskon /  Total /");
                    System.out.println("---------------------------------------------------------");
            while(rs.next()){
                    int nofaktur    =  rs.getInt("nofaktur");
                    String label    =  rs.getString("label");
                    int jumlah      =  rs.getInt("jumlah");
                    int harga       =  rs.getInt("harga");
                    int diskon      =  rs.getInt("diskon");
                    int total       =  rs.getInt("total");
                    
                    System.out.println(String.format("|   %d\t    | %-10s  | %-6d | %-6d |  %-5d  |  %-6d   |", nofaktur,label,harga,jumlah,diskon,total));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
