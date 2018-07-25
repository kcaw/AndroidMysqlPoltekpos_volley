<?php

    include_once('connection.php');

    $nisn           =$_POST['nisn'];
    $nsiswa         =$_POST['nsiswa'];
    $pilprodi       =$_POST['pilprodi'];
    $piljurusan     =$_POST['piljurusan'];
    $jumlah         =$_POST['jumlah'];

    $insert = "INSERT INTO tb_pembayaran(nisn,nsiswa,pilprodi,piljurusan,jumlah) VALUES ('$nisn','$nsiswa','$pilprodi','$piljurusan','$jumlah')";

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