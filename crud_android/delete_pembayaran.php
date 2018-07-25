<?php

        include_once('connection.php');

        $nisn = $_POST['nisn'];

        $getdata = mysqli_query($koneksi,"SELECT * FROM tb_pembayaran WHERE nisn = '$nisn'");
        $delete = "DELETE FROM tb_pembayaran WHERE nisn = '$nisn'";
        $rows = mysqli_num_rows($getdata);
        $exedelete = mysqli_query($koneksi,$delete);

        $response = array();

        if($rows > 0)
        {
            if($exedelete)
            {
                $response['code'] =1;
                $response['message'] = "Delete Data Succes";
            }
            else
            {
                $response['code'] =0;
                $response['message'] = "Delete Data Gagal";
            }
        }
        else
        {
             $response['code'] =0;
             $response['message'] = "Delete Data Gagal, Data Tidak Ada";
        }
        

        echo json_encode($response);
?>