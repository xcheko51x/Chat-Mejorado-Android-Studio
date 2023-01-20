<?php

require "conexion.php";

$usuario = $_POST['usuario'];
$contrasena = $_POST['contrasena'];

//$usuario = "cheko";
//$contrasena = "cheko";

if(empty($usuario) || empty($contrasena)) {
	echo "Se debe llenar los campos.";
} else if($usuario == "todos" || $contrasena == "todos" || $usuario == "TODOS" || $contrasena == "TODOS") {
    echo "Ese usuario no existe";
} else {
	$sql = "SELECT * FROM usuarios WHERE usuario='$usuario' AND contrasena='$contrasena'";
	$query = $mysqli->query($sql);

	$data = array();

	$num = $query->num_rows;

	if($num > 0) {
		while($resultado = $query->fetch_assoc()) {
			$data[] = $resultado;

			echo json_encode(array("usuario" => $data));
		}
	} else {
		echo "No existe el registro con esos datos.";
	}

	$mysqli->close();
}

?>
