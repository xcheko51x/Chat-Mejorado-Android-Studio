<?php
    
     require "conexion.php"; // IMPORTA LOS DATOS DE CONEXION DE LA DB
    
     $accion = $_POST['accion'];
     $mensaje = $_POST['mensaje'];
    
    // PARA PRUEBAS
    //$accion = "nuevo";
    //$mensaje = "MENSAJE";
    
    // PARA INSERTAR MENSAJE EN LA DB
    if($accion == "nuevo") {
        $sql_insert = "INSERT INTO mensajes VALUES('', '$mensaje')";
        $query = $mysqli->query($sql_insert);

        $sql_consult = "SELECT * FROM mensajes";
        $query = $mysqli->query($sql_consult);
        
        $data = array();
        
        //$num = $query->num_rows;
        
        //if($num > 0) {
            while($resultado = $query->fetch_assoc()) {
                $data[] = $resultado;
            }
            echo json_encode(array("mensajes" => $data));
        //}
        
        $mysqli->close();

        
    } else {
        // PARA OBTENER LOS MENSAJES
        $sql_consult = "SELECT * FROM mensajes";
        $query = $mysqli->query($sql_consult);
        
        $data = array();
        
        //$num = $query->num_rows;
        
        //if($num > 0) {
            while($resultado = $query->fetch_assoc()) {
                $data[] = $resultado;
            }
            echo json_encode(array("mensajes" => $data));
        //}
        
        $mysqli->close();
    }
    
     ?>
