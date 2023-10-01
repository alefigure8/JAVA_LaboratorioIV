package presentacion.vista;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import entidad.Persona;


public interface IPanelList{
	public void setPersonaLista(List<Persona> personas);
}
