import java.util.Scanner;

public class Transaksi extends Barang{
    Scanner in = new Scanner(System.in);
    @Override
    public int HargaBarang(){
        System.out.print("Input Harga  : ");
        habar = in.nextInt();
        return habar;
    }
    
    @Override
    public int Jumlah(){
        System.out.print("Input Jumlah : ");
        jum = in.nextInt();
        return jum;
    }
    @Override
    public int SubTotal() {
        sub=habar*jum;
        return sub;
    }
    @Override
    public int Discount() {
        if(sub> 15000000){
            this.dis = (sub*10)/100;
        }if(sub>10000000 && sub <= 15000000){
            this.dis = (sub*5)/100;
        }else if(sub> 1000000 && sub <= 10000000){
            this.dis = (sub*3)/100;
        }else if(sub> 500000 && sub <= 1000000){
            this.dis = (sub*1)/100;
        }

        return dis;
    }
    @Override
    public int TotalHarga() {
        total=sub-dis;
        return total;
    }

}
