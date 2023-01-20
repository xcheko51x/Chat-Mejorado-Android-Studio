<?php
    
    require "conexion.php";
    
    $usuario = $_POST['usuario'];
    $usuarioDestino = $_POST['usuarioDestino'];
    $mensaje = $_POST['mensaje'];
    
    // PARA PRUEBAS
    //$usuario = "cheko";
    //$usuarioDestino = "TODOS";
    //$mensaje = "Hola a todos";
    
    if(empty($usuario) || empty($usuarioDestino) || empty($mensaje)) {
        echo "No puedes entrar";
    } else {
        $sql_insertar = "INSERT INTO mensajes VALUES('', '$mensaje', '$usuario', '$usuarioDestino')";
        $query_insertar = $mysqli->query($sql_insertar);
        
        echo "Se inserto correctamente el mensaje.";
    }
    ?>

