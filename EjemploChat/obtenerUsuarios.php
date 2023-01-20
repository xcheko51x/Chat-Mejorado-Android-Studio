<?php

    require "conexion.php";
    
    $usuario = $_POST['usuario'];
    // PRUEBAS
    //$usuario = "cheko";
    
    $sql = "SELECT * FROM usuarios WHERE usuario!='$usuario'";
    $query = $mysqli->query($sql);
    
    $datos = array();
    
    while($resultado = $query->fetch_assoc()) {
        $datos[] = $resultado;
    }
    
    echo json_encode(array("usuarios" => $datos));
?>
