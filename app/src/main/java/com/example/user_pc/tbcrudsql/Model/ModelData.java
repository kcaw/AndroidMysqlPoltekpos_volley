package com.example.user_pc.tbcrudsql.Model;

public class ModelData {
    String npm,nama,prodi,fakultas,nik,nortu,pekerjaan,alamat,nisn,nsiswa,pilprodi,piljurusan,jumlah;

    public ModelData(){}

    public ModelData(String npm, String nama, String prodi, String fakultas,
                     String nik, String nortu, String pekerjaan, String alamat,
                     String nisn, String nsiswa, String pilprodi, String piljurusan, String jumlah) {
        this.npm = npm;
        this.nama = nama;
        this.prodi = prodi;
        this.fakultas = fakultas;

        this.nik = nik;
        this.nortu = nortu;
        this.pekerjaan = pekerjaan;
        this.alamat = alamat;

        this.nisn = nisn;
        this.nsiswa = nsiswa;
        this.pilprodi = pilprodi;
        this.piljurusan = piljurusan;
        this.jumlah = jumlah;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }


    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNortu() {
        return nortu;
    }

    public void setNortu(String nortu) {
        this.nortu = nortu;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }


    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getNsiswa() {
        return nsiswa;
    }

    public void setNsiswa(String nsiswa) {
        this.nsiswa = nsiswa;
    }

    public String getPilprodi() {
        return pilprodi;
    }

    public void setPilprodi(String pilprodi) {
        this.pilprodi = pilprodi;
    }

    public String getPiljurusan() {
        return piljurusan;
    }

    public void setPiljurusan(String piljurusan) {
        this.piljurusan = piljurusan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

}
