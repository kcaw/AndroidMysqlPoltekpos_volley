<?php

    include_once('connection.php');

    $nisn           =$_POST['nisn'];

    $nsiswa         =$_POST['nsiswa'];
    $pilprodi       =$_POST['pilprodi'];
    $piljurusan     =$_POST['piljurusan'];
    $jumlah         =$_POST['jumlah'];

    $getdata = mysqli_query($koneksi,"SELECT * FROM tb_pembayaran WHERE nisn = '$nisn'");
    $rows = mysqli_num_rows($getdata);
   
    $response = array();

    if($rows > 0 )
    {
         $query = "UPDATE tb_pembayaran SET nsiswa='$nsiswa',pilprodi='$pilprodi',piljurusan='$piljurusan',jumlah='$jumlah' WHERE nisn='$nisn'";
    
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