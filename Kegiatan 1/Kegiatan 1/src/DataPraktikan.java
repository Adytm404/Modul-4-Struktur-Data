import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataPraktikan {
    private Map<String, DataPraktikan> tabelData;

    public DataPraktikan() {
        tabelData = new HashMap<>();
    }

    public boolean tambahData(String nimPraktikan, String namaAsisten, String email) {
        if (tabelData.containsKey(nimPraktikan) || nimPraktikan.length() != 15) {
            return false;
        } else {
            DataPraktikan dataPraktikan = new DataPraktikan();
            dataPraktikan.setNamaAsisten(namaAsisten);
            dataPraktikan.setEmail(email);
            tabelData.put(nimPraktikan, dataPraktikan);
            return true;
        }
    }

    public void tampil() {
        System.out.println("Total data yang tersimpan: " + tabelData.size());
        int i = 1;
        for (Map.Entry<String, DataPraktikan> entry : tabelData.entrySet()) {
            System.out.println("Data ke-" + i + ":");
            System.out.println("NIM: " + entry.getKey());
            System.out.println("Nama Asisten: " + entry.getValue().getNamaAsisten());
            System.out.println("Email: " + entry.getValue().getEmail());
            i++;
        }
    }

    public void listNimPraktikan() {
        System.out.println("List NIM praktikan:");
        for (String nim : tabelData.keySet()) {
            System.out.println(nim);
        }
    }

    public void listNamaAsisten() {
        System.out.println("List Nama Asisten:");
        for (DataPraktikan dataPraktikan : tabelData.values()) {
            System.out.println(dataPraktikan.getNamaAsisten());
        }
    }

    public int totalData() {
        return tabelData.size();
    }

    public boolean hapusData(String nimPraktikan, String namaAsisten, String email) {
        DataPraktikan dataPraktikan = tabelData.get(nimPraktikan);
        if (dataPraktikan != null && dataPraktikan.getNamaAsisten().equals(namaAsisten) && dataPraktikan.getEmail().equals(email)) {
            tabelData.remove(nimPraktikan);
            return true;
        } else {
            return false;
        }
    }

    public void editData(String nimPraktikan, String namaAsisten, String email) {
        if (tabelData.containsKey(nimPraktikan)) {
            DataPraktikan dataPraktikan = tabelData.get(nimPraktikan);
            dataPraktikan.setNamaAsisten(namaAsisten);
            dataPraktikan.setEmail(email);
            System.out.println("Data berhasil diubah.");
        } else {
            System.out.println("NIM praktikan tidak ditemukan.");
        }
    }

    public void cariDataByNamaAsisten(String namaAsisten) {
        System.out.println("Hasil pencarian data dengan nama asisten " + namaAsisten + ":");
        boolean found = false;
        for (Map.Entry<String, DataPraktikan> entry : tabelData.entrySet()) {
            if (entry.getValue().getNamaAsisten().equals(namaAsisten)) {
                System.out.println("NIM: " + entry.getKey());
                System.out.println("Nama Asisten: " + entry.getValue().getNamaAsisten());
                System.out.println("Email: " + entry.getValue().getEmail());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Tidak ditemukan data dengan nama asisten " + namaAsisten + ".");
        }
    }

    public static void main(String[] args) {
        DataPraktikan data = new DataPraktikan();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Tambah data");
            System.out.println("2. Tampilkan data");
            System.out.println("3. List NIM Praktikan");
            System.out.println("4. List nama asisten");
            System.out.println("5. Total data");
            System.out.println("6. Hapus data");
            System.out.println("7. Edit data");
            System.out.println("8. Search by nama asisten");
            System.out.println("9. Exit");
            System.out.print("Pilih menu (1-9): ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    System.out.println("-- Tambah data --");
                    System.out.print("Masukkan NIM: ");
                    String nim = scanner.nextLine();
                    System.out.print("Masukkan Nama Asisten: ");
                    String asisten = scanner.nextLine();
                    System.out.print("Masukkan Email: ");
                    String email = scanner.nextLine();

                    if (data.tambahData(nim, asisten, email)) {
                        System.out.println("Data berhasil ditambahkan.");
                    } else {
                        System.out.println("NIM praktikan sudah terdaftar atau tidak memenuhi syarat.");
                    }
                    break;
                case 2:
                    data.tampil();
                    break;
                case 3:
                    data.listNimPraktikan();
                    break;
                case 4:
                    data.listNamaAsisten();
                    break;
                case 5:
                    System.out.println("Total data yang tersimpan: " + data.totalData());
                    break;
                case 6:
                    System.out.println("-- Hapus data --");
                    System.out.print("Masukkan NIM: ");
                    String nimPraktikan = scanner.nextLine();
                    System.out.print("Masukkan Nama Asisten: ");
                    String namaAsisten = scanner.nextLine();
                    System.out.print("Masukkan Email: ");
                    String emailHapus = scanner.nextLine();

                    if (data.hapusData(nimPraktikan, namaAsisten, emailHapus)) {
                        System.out.println("Data berhasil dihapus.");
                    } else {
                        System.out.println("Data tidak ditemukan atau tidak sesuai.");
                    }
                    break;
                case 7:
                    System.out.println("-- Edit data --");
                    System.out.print("Masukkan NIM yang akan diubah: ");
                    String nimEdit = scanner.nextLine();

                    if (data.tabelData.containsKey(nimEdit)) {
                        System.out.print("Masukkan Nama Asisten baru: ");
                        String asistenBaru = scanner.nextLine();
                        System.out.print("Masukkan Email baru: ");
                        String emailBaru = scanner.nextLine();
                        data.editData(nimEdit, asistenBaru, emailBaru);
                    } else {
                        System.out.println("NIM praktikan tidak ditemukan.");
                    }
                    break;
                case 8:
                    System.out.println("-- Cari data by nama asisten --");
                    System.out.print("Masukkan Nama Asisten: ");
                    String namaAsistenCari = scanner.nextLine();
                    data.cariDataByNamaAsisten(namaAsistenCari);
                    break;
                case 9:
                    System.out.println("Terima kasih.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Menu tidak tersedia.");
            }
            System.out.println();
        }
    }

    private String namaAsisten;
    private String email;

    public String getNamaAsisten() {
        return namaAsisten;
    }

    public void setNamaAsisten(String namaAsisten) {
        this.namaAsisten = namaAsisten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}