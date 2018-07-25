<?php

    include_once('connection.php');

    $nik        =$_POST['nik'];
    $nortu      =$_POST['nortu'];
    $pekerjaan  =$_POST['pekerjaan'];
    $alamat     =$_POST['alamat'];

    $insert = "INSERT INTO tb_ortu(nik,nortu,pekerjaan,alamat) VALUES ('$nik','$nortu','$pekerjaan','$alamat')";

    $exeinsert = mysqli_query($koneksi,$insert);

    $response = array();

    if($exeinsert)
    {
        $response['code'] =1;
        $response['message'] = "Data Berhasil Ditambahkan !";
    }
    else
    {
        $response['code'] =0;
        $response['message'] = "Data Gagal Ditambahkan !";
    }

        echo json_encode($response);
?>