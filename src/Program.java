
import java.util.InputMismatchException;
import java.util.Scanner;
public class Program {
    public static void main(String[] args) throws Exception {
        
        try{
            Scanner input = new Scanner(System.in);
            Barang connection = new Barang();
            connection.getKoneksi();
            boolean lanjut = true;

        System.out.println();
        while(lanjut){
               
                switch(Barang.menu()){
    
                    case 1:
                        connection.tampil();
                        break;
    
                    case 2:
                        connection.pesan();
                        break;
                    case 3: 
                        connection.update();
                        break;
                    case 4:
                        connection.batal();
                        break;
                    case 5:
                        connection.cari();
                        break;
                    default:
                        System.out.println("pilihan tidak ada");

                    break;
    
                }
            System.out.print("Apakah anda ingin melanjutkan y/n ?  ");
            String pilihan =  input.next();
            lanjut = pilihan.equalsIgnoreCase("y");
         }
         input.close();
              
    }catch (InputMismatchException e){
        e.printStackTrace();
    }

    
}
   

    }

