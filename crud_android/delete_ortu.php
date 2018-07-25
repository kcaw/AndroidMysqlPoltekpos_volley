<?php

        include_once('connection.php');

        $nik = $_POST['nik'];

        $getdata = mysqli_query($koneksi,"SELECT * FROM tb_ortu WHERE nik = '$nik'");
        $delete = "DELETE FROM tb_ortu WHERE nik = '$nik'";
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