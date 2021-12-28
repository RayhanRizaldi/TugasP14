public interface Penjualan {
    
    public void NoFaktur(String nofa);
    public void NamaBarang(String nabar);
    public int HargaBarang();
    public int Jumlah();
    public int SubTotal();
    public int Discount();
    public int TotalHarga();
}
