package es.ucm.fdi.tp.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

class MyTableModel extends AbstractTableModel {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String[] colNames;
	List<String> Jugadores;

	MyTableModel() {
		this.colNames = new String[] {"#Player ","Color" };
		Jugadores = new ArrayList<>();
		Jugadores.add("");
		Jugadores.add("");
	}

	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return Jugadores != null ? Jugadores.size() : 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return rowIndex;
		} else {
			return Jugadores.get(rowIndex);
		}
	}
	public void refresh() {
		fireTableDataChanged();
	}
};