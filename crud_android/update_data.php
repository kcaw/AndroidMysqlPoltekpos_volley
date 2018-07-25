<?php

    include_once('connection.php');

    $npm        =$_POST['npm'];

    $nama       =$_POST['nama'];
    $prodi      =$_POST['prodi'];
    $fakultas   =$_POST['fakultas'];

    $getdata = mysqli_query($koneksi,"SELECT * FROM tb_mahasiswa WHERE npm = '$npm'");
    $rows = mysqli_num_rows($getdata);
   
    $response = array();

    if($rows > 0 )
    {
         $query = "UPDATE tb_mahasiswa SET nama='$nama',prodi='$prodi',fakultas='$fakultas' WHERE npm='$npm'";
    
         $exequery = mysqli_query($koneksi,$query);
         if($exequery)
        {
            $response['code'] =1;
            $response['message'] = "Update Data Berhasil !";
        }
        else
        {
            $response['code'] =0;
            $response['message'] = "Update Data Gagal !";
        }
    }
    else
    {
        $response['code'] =0;
        $response['message'] = "Update Data Gagal, Data Tidak Ditemukan !";
    }
    

    echo json_encode($response);
?>