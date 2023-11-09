package pruebas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoImp.ClienteDaoImp;
import entidad.Localidad;
import entidad.Provincia;

public class pruebaProvincias {

	public static void main(String[] args) {
		
		ClienteDaoImp cdao = new ClienteDaoImp();
		
		List<Provincia> listaProvincias = new ArrayList<Provincia>();
		
		List<Localidad> listaLocalidades = new ArrayList<Localidad>();
		
		/*try {
			listaProvincias = cdao.listarProvincias();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Provincia p : listaProvincias) {
			System.out.println(p.getNombre() );
			try {
				listaLocalidades = cdao.listarLocalidades(p.getIdProvincia());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			for (Localidad l : listaLocalidades) {
				System.out.println(l.getNombre());
			}
		}*/
		
	}

}
