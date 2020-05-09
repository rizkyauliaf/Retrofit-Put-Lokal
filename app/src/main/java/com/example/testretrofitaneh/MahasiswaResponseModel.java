package com.example.testretrofitaneh;

import android.os.Parcel;
import android.os.Parcelable;

public class MahasiswaResponseModel implements Parcelable {
    String id_siswa, nama,alamat,jenis_kelamin,no_telp;

    public MahasiswaResponseModel(String id_siswa, String nama, String alamat, String jenis_kelamin, String no_telp) {
        this.id_siswa = id_siswa;
        this.nama = nama;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.no_telp = no_telp;
    }

    protected MahasiswaResponseModel(Parcel in) {
        id_siswa = in.readString();
        nama = in.readString();
        alamat = in.readString();
        jenis_kelamin = in.readString();
        no_telp = in.readString();
    }

    public static final Creator<MahasiswaResponseModel> CREATOR = new Creator<MahasiswaResponseModel>() {
        @Override
        public MahasiswaResponseModel createFromParcel(Parcel in) {
            return new MahasiswaResponseModel(in);
        }

        @Override
        public MahasiswaResponseModel[] newArray(int size) {
            return new MahasiswaResponseModel[size];
        }
    };

    public String getId_siswa() {
        return id_siswa;
    }

    public void setId_siswa(String id_siswa) {
        this.id_siswa = id_siswa;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_siswa);
        dest.writeString(nama);
        dest.writeString(alamat);
        dest.writeString(jenis_kelamin);
        dest.writeString(no_telp);
    }
}
