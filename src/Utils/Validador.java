/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author DHANI
 */
import Model.Conexion;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Validador extends Conexion {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
    public static boolean validarEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    public boolean validarTelefono(String telefono) {
        if (telefono != null && telefono.matches("\\d{10}")) {
            return true; // el teléfono tiene 10 dígitos
        } else {
            return false; // el teléfono no tiene 10 dígitos
        }
    }
    public boolean verificarRegistroExistente(String tabla, String columna, String valor) {
        boolean resultado = false;
        try {
            Connection con = getConexion();
            // Crear una consulta preparada para verificar si el registro ya existe
            String query = "SELECT COUNT(*) AS count FROM "+tabla+" WHERE "+columna+" = ?";
            PreparedStatement statement = con.prepareStatement(query);

            // Establecer el valor del parámetro en la consulta
            statement.setString(1, valor);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Obtener el resultado y verificar si el registro existe
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                if(count > 0){
                    System.out.println("ya existe "+columna+" en "+tabla);
                    resultado = true;
                }else{
                    System.out.println("no existe "+columna+" en "+tabla);
                }
            }
            // Cerrar el resultSet y el statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado; // Si ocurre alguna excepción, se asume que el registro no existe
    }
    
}
