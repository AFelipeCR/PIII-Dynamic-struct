package com.afcr.dynamicstructs.dispersematrix;

import java.util.Comparator;

import com.afcr.dynamicstructs.simplelist.Node;
import com.afcr.dynamicstructs.simplelist.SimpleList;

/**
 * Class MyMatrix.
 *
 * @author Andrés Felipe Chaparro Rosas
 * @version 1.0 08/09/2018
 * @param <TC> Tipo de la columna
 * @param <TR> Tipo de la fila
 * @param <T> Tipo de dato
 */
public class MyMatrix<TC, TR, T> {

	protected SimpleList<Header<TC, T>> columnList;
	protected SimpleList<Header<TR, T>> rowList;

	public MyMatrix(MyMatrix<TC, TR, T> matrix) {
		this.columnList = matrix.columnList;
		this.rowList = matrix.rowList;
	}

	/**
	 * intancia un nuevo my matrix.
	 *
	 * @param comparatorCols el dato comparator cols
	 * @param comparatorRows el dato comparator rows
	 */
	public MyMatrix(Comparator<Header<TC, T>> comparatorCols, Comparator<Header<TR, T>> comparatorRows) {
		this.columnList = new SimpleList<>(comparatorCols);
		this.rowList = new SimpleList<>(comparatorRows);
	}

	public void set(TC col, TR row, T data) {
		if (this.columnList.isEmpty() && this.rowList.isEmpty()) {
			Cell<T> cell = new Cell<T>(data);
			this.columnList.add(new Header<TC, T>(col, cell));
			this.rowList.add(new Header<TR, T>(row, cell));
		} else {
			if (get(col, row) == null) {
				Cell<T> cell = new Cell<T>(data);
				verifyHeaderRow(row, cell);
				verifyHeaderColumn(col, cell);
			} else {
				searchCell(col, row).setData(data);
			}
		}
	}

	public T get(TC col, TR row) {
		Cell<T> cell = searchCell(col, row);
		return cell == null ? null : cell.data;
	}

	public void remove(TC col, TR row) {
		Header<TC, T> headerCol = this.headerSearch(col, this.columnList.getHead(), this.columnList.getComparator());
		Header<TR, T> headerRow = this.headerSearch(row, this.rowList.getHead(), this.rowList.getComparator());

		if (headerCol != null && headerRow != null) {
			Cell<T> cellAux = searchCell(col, row);
			Cell<T> cellAux1 = headerCol.cell;
			Cell<T> cellAux2 = headerCol.cell.down;

			if (cellAux1 == cellAux) {
				headerCol.setCell(cellAux2);
			} else {
				while (cellAux2 != null) {
					if (cellAux2 == cellAux)
						cellAux1.setDown(cellAux2.down);
					cellAux1 = cellAux2;
					cellAux2 = cellAux2.down;
				}
			}

			cellAux1 = headerRow.cell;
			if (headerRow.cell != null)
				cellAux2 = headerRow.cell.right;

			if (cellAux1 == cellAux) {
				headerRow.setCell(cellAux2);
			} else {
				while (cellAux2 != null) {
					if (cellAux2 == cellAux)
						cellAux1.setRight(cellAux2.right);
					cellAux1 = cellAux2;
					cellAux2 = cellAux2.right;
				}
			}

			if (headerCol.getCell() == null)
				this.columnList.remove(headerCol);

			if (headerRow.getCell() == null)
				this.rowList.remove(headerRow);
		}
	}

	private <TH> Header<TH, T> headerSearch(TH toVerify, Node<Header<TH, T>> auxNode,
			Comparator<Header<TH, T>> comparator) {
		Header<TH, T> header = null;
		Node<Header<TH, T>> aux = auxNode;

		while (aux != null) {

			if (comparator.compare(aux.getData(), new Header<TH, T>(toVerify)) == 0) {
				header = aux.getData();
				aux = null;
			}

			if (aux != null)
				aux = aux.getNext();
		}
		return header;
	}

	private void verifyHeaderRow(TR row, Cell<T> cell) {
		Header<TR, T> headerRow = this.headerSearch(row, this.rowList.getHead(), this.rowList.getComparator());
		if (headerRow == null) {
			headerRow = new Header<TR, T>(row, cell);
			this.rowList.add(headerRow);
		} else {
			Cell<T> aux = headerRow.cell;
			while (aux.right != null) {
				aux = aux.right;
			}
			aux.setRight(cell);
		}
	}

	private void verifyHeaderColumn(TC col, Cell<T> cell) {
		Header<TC, T> headerCol = this.headerSearch(col, this.columnList.getHead(), this.columnList.getComparator());
		if (headerCol == null) {
			headerCol = new Header<TC, T>(col, cell);
			this.columnList.add(headerCol);
		} else {
			Cell<T> aux = headerCol.cell;
			while (aux.down != null) {
				aux = aux.down;
			}
			aux.setDown(cell);
		}
	}

	private Cell<T> searchCell(TC col, TR row) {
		Header<TC, T> headerCol = this.headerSearch(col, this.columnList.getHead(), this.columnList.getComparator());
		Header<TR, T> headerRow = this.headerSearch(row, this.rowList.getHead(), this.rowList.getComparator());

		if (headerCol == null || headerRow == null) {
			return null;
		}

		Cell<T> cellAux = headerCol.cell;
		Cell<T> cellAux2 = null;

		while (cellAux != null) {
			cellAux2 = headerRow.cell;
			while (cellAux2 != null) {
				if (cellAux == cellAux2)
					return cellAux;
				cellAux2 = cellAux2.right;
			}
			cellAux = cellAux.down;
		}
		return null;
	}

	public SimpleList<Header<TR, T>> getRowList() {
		return rowList;
	}

	public SimpleList<Header<TC, T>> getColumnList() {
		return columnList;
	}
}