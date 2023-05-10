import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataPraktikan {

    private Map<String, DataPraktikan> tabelData;
    private Map<String, String> tabelSesiLogin;
    private boolean loggedIn;

    public DataPraktikan() {
        tabelData = new HashMap<>();
        tabelSesiLogin = new HashMap<>();
        tabelSesiLogin.put("admin@umm.ac.id", "@321NewAccount");
        loggedIn = false;
    }

    public static void main(String[] args) {
        DataPraktikan program = new DataPraktikan();

        program.run();
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
            System.out.println("\nData ke-" + i + ":");
            System.out.println("NIM: " + entry.getKey());
            System.out.println("Nama Asisten: " + entry.getValue().getNamaAsisten());
            System.out.println("Email: " + entry.getValue().getEmail());
            i++;
        }
    }

    public void listNimPraktikan() {
        System.out.println("\nList NIM praktikan:");
        for (String nim : tabelData.keySet()) {
            System.out.println(nim);
        }
    }

    public void listNamaAsisten() {
        System.out.println("\nList Nama Asisten:");
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

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (email.endsWith("@umm.ac.id") && (tabelSesiLogin.containsKey(email) && tabelSesiLogin.get(email).equals(password) || password.equals("@321NewAccount"))) {
            System.out.println("Login berhasil.");
            loggedIn = true;
            return true;
        } else {
            System.out.println("Gagal Login.");
            return false;
        }
    }

    public void logout() {
        System.out.println("Anda telah keluar dari program.");
        loggedIn = false;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            if (!loggedIn) {
                System.out.println("Silakan login terlebih dahulu.");
                login();
            } else {
                System.out.println("\n=== Aplikasi Data Praktikan ===");
                System.out.println("1. Tambah data");
                System.out.println("2. Tampilkan data");
                System.out.println("3. List NIM Praktikan");
                System.out.println("4. List Nama Asisten");
                System.out.println("5. Total Data");
                System.out.println("6. Hapus data");
                System.out.println("7. Edit data");
                System.out.println("8. Cari Data by Nama Asisten");
                System.out.println("9. Logout");

                System.out.print("Pilih menu: ");
                int pilihan = scanner.nextInt();

                switch (pilihan) {
                    case 1:
                        scanner.nextLine(); // consume newline character
                        System.out.print("\nNIM Praktikan: ");
                        String nimPraktikan = scanner.nextLine();
                        System.out.print("Nama Asisten: ");
                        String namaAsisten = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        if (tambahData(nimPraktikan, namaAsisten, email)) {
                            System.out.println("Data berhasil ditambahkan.");
                        } else {
                            System.out.println("Gagal menambahkan data.");
                        }
                        break;
                    case 2:
                        tampil();
                        break;
                    case 3:
                        listNimPraktikan();
                        break;
                    case 4:
                        listNamaAsisten();
                        break;
                    case 5:
                        System.out.println("\nTotal data: " + totalData());
                        break;
                    case 6:
                        scanner.nextLine(); // consume newline character
                        System.out.print("\nNIM Praktikan: ");
                        nimPraktikan = scanner.nextLine();
                        System.out.print("Nama Asisten: ");
                        namaAsisten = scanner.nextLine();
                        System.out.print("Email: ");
                        email = scanner.nextLine();

                        if (hapusData(nimPraktikan, namaAsisten, email)) {
                            System.out.println("Data berhasil dihapus.");
                        } else {
                            System.out.println("Gagal menghapus data.");
                        }
                        break;
                    case 7:
                        scanner.nextLine(); // consume newline character
                        System.out.print("\nNIM Praktikan: ");
                        nimPraktikan = scanner.nextLine();
                        System.out.print("Nama Asisten: ");
                        namaAsisten = scanner.nextLine();
                        System.out.print("Email: ");
                        email = scanner.nextLine();

                        editData(nimPraktikan, namaAsisten, email);
                        break;
                    case 8:
                        scanner.nextLine(); // consume newline character
                        System.out.print("\nNama Asisten: ");
                        namaAsisten = scanner.nextLine();

                        cariDataByNamaAsisten(namaAsisten);
                        break;
                    case 9:
                        logout();
                        break;
                    default:
                        System.out.println("Pilihan tidak tersedia.");
                        break;
                }
            }
        }
    }

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

    private String namaAsisten;
    private String email;
}