<?php
    
    require "conexion.php";

    $usuario = $_POST['usuario'];
    $usuarioDestino = $_POST['usuarioDestino'];
    
    // PARA PRUEBAS
    //$usuario = "cheko";
    //$usuarioDestino = "laura";
    
    $sql_consulta = "SELECT * FROM mensajes WHERE (usuarioOrigen='$usuario' AND usuarioDestino='$usuarioDestino') OR (usuarioOrigen='$usuarioDestino' AND usuarioDestino='$usuario')";
    $query_consulta = $mysqli->query($sql_consulta);
    
    $datos = array();
    
    while($resultado = $query_consulta->fetch_assoc()) {
        $datos[] = $resultado;
    }
    
    echo json_encode(array("mensajes" => $datos));
    
?>
