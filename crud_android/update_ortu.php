<?php

    include_once('connection.php');

    $nik        =$_POST['nik'];

    $nortu      =$_POST['nortu'];
    $pekerjaan  =$_POST['pekerjaan'];
    $alamat     =$_POST['alamat'];

    $getdata = mysqli_query($koneksi,"SELECT * FROM tb_ortu WHERE nik = '$nik'");
    $rows = mysqli_num_rows($getdata);
   
    $response = array();

    if($rows > 0 )
    {
         $query = "UPDATE tb_ortu SET nortu='$nortu',pekerjaan='$pekerjaan',alamat='$alamat' WHERE nik='$nik'";
    
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